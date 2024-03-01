package XML;

import java.sql.ResultSet;
import java.util.List;

import DTO.MarcaDTO;
import DTO.ProdutoDTO;
import Interfaces.ProdutoInterface;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProdutoXML implements ProdutoInterface{
	protected String xmlFilePath = "./xml-data/produto.xml";
	@Override
	public boolean create(ProdutoDTO produto) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            File file = new File(xmlFilePath);
            Element rootElement;

            if (file.exists()) {
                doc = dBuilder.parse(file);
                rootElement = doc.getDocumentElement();
            } else {
                doc = dBuilder.newDocument();
                rootElement = doc.createElement("produtos");
                doc.appendChild(rootElement);
            }

            Element produtoElement = doc.createElement("produto");
            rootElement.appendChild(produtoElement);

            Element nomeElement = doc.createElement("nome");
            nomeElement.appendChild(doc.createTextNode(produto.getNome()));
            produtoElement.appendChild(nomeElement);

            Element precoElement = doc.createElement("preco");
            precoElement.appendChild(doc.createTextNode(String.valueOf(produto.getPreco())));
            produtoElement.appendChild(precoElement);

            Element estoqueElement = doc.createElement("estoque");
            estoqueElement.appendChild(doc.createTextNode(String.valueOf(produto.getEstoque())));
            produtoElement.appendChild(estoqueElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(produto.getId())));
            produtoElement.appendChild(idElement);

            Element descricaoElement = doc.createElement("descricao");
            descricaoElement.appendChild(doc.createTextNode(produto.getDescricao()));
            produtoElement.appendChild(descricaoElement);

            Element marcaElement = doc.createElement("marca");
            marcaElement.appendChild(doc.createTextNode(String.valueOf(produto.getMarca())));
            produtoElement.appendChild(marcaElement);

            Element vendedorElement = doc.createElement("vendedor");
            vendedorElement.appendChild(doc.createTextNode(String.valueOf(produto.getVendedor())));
            produtoElement.appendChild(vendedorElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            return true;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean update(ProdutoDTO produto) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int produtoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (produtoId == produto.getId()) {
                        element.getElementsByTagName("nome").item(0).setTextContent(produto.getNome());
                        element.getElementsByTagName("preco").item(0).setTextContent(String.valueOf(produto.getPreco()));
                        element.getElementsByTagName("estoque").item(0).setTextContent(String.valueOf(produto.getEstoque()));
                        element.getElementsByTagName("descricao").item(0).setTextContent(produto.getDescricao());
                        element.getElementsByTagName("marca").item(0).setTextContent(String.valueOf(produto.getMarca()));
                        element.getElementsByTagName("vendedor").item(0).setTextContent(String.valueOf(produto.getVendedor()));

                        // Salva as alterações no arquivo XML
                        saveChangesToFile(doc, file);
                        return true;
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return false;
	}

    private void saveChangesToFile(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

	@Override
	public boolean delete(ProdutoDTO produto) {

        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int produtoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (produtoId == produto.getId()) {
                        node.getParentNode().removeChild(node);

                        // Salva as alterações no arquivo XML
                        saveChangesToFile(doc, file);
                        return true;
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public List<ProdutoDTO> get() {
        List<ProdutoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoDTO produto = new ProdutoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setEstoque(Integer.parseInt(element.getElementsByTagName("estoque").item(0).getTextContent()));
                produto.setDescricao(element.getElementsByTagName("descricao").item(0).getTextContent());
                produto.setMarca(Integer.parseInt(element.getElementsByTagName("marca").item(0).getTextContent()));
                produto.setVendedor(Integer.parseInt(element.getElementsByTagName("vendedor").item(0).getTextContent()));
                produtos.add(produto);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoDTO> getByVendedor(int idVendedor) {
        List<ProdutoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoDTO produto = new ProdutoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setEstoque(Integer.parseInt(element.getElementsByTagName("estoque").item(0).getTextContent()));
                produto.setDescricao(element.getElementsByTagName("descricao").item(0).getTextContent());
                produto.setMarca(Integer.parseInt(element.getElementsByTagName("marca").item(0).getTextContent()));
                produto.setVendedor(Integer.parseInt(element.getElementsByTagName("vendedor").item(0).getTextContent()));
                if(produto.getVendedor()==idVendedor) {
                	produtos.add(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoDTO> find(ProdutoDTO produtoS) {
        List<ProdutoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoDTO produto = new ProdutoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setEstoque(Integer.parseInt(element.getElementsByTagName("estoque").item(0).getTextContent()));
                produto.setDescricao(element.getElementsByTagName("descricao").item(0).getTextContent());
                produto.setMarca(Integer.parseInt(element.getElementsByTagName("marca").item(0).getTextContent()));
                produto.setVendedor(Integer.parseInt(element.getElementsByTagName("vendedor").item(0).getTextContent()));
                if(produto.getId()==produtoS.getId()) {
                	produtos.add(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoDTO> getByMarca(MarcaDTO marca, int idVendedor) {

        List<ProdutoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoDTO produto = new ProdutoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setEstoque(Integer.parseInt(element.getElementsByTagName("estoque").item(0).getTextContent()));
                produto.setDescricao(element.getElementsByTagName("descricao").item(0).getTextContent());
                produto.setMarca(Integer.parseInt(element.getElementsByTagName("marca").item(0).getTextContent()));
                produto.setVendedor(Integer.parseInt(element.getElementsByTagName("vendedor").item(0).getTextContent()));
                if(produto.getId()==idVendedor && produto.getMarca()==marca.getId()) {
                	produtos.add(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoDTO> getByMarcaCliente(MarcaDTO marca) {

        List<ProdutoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoDTO produto = new ProdutoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setEstoque(Integer.parseInt(element.getElementsByTagName("estoque").item(0).getTextContent()));
                produto.setDescricao(element.getElementsByTagName("descricao").item(0).getTextContent());
                produto.setMarca(Integer.parseInt(element.getElementsByTagName("marca").item(0).getTextContent()));
                produto.setVendedor(Integer.parseInt(element.getElementsByTagName("vendedor").item(0).getTextContent()));
                if(produto.getMarca()==marca.getId()) {
                	produtos.add(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
