package XML;

import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import Interfaces.ProdutoPagoInterface;

public class ProdutoPagoXML implements ProdutoPagoInterface{
	protected String xmlFilePath = "./xml-data/produtopago.xml";
	@Override
	public boolean create(ProdutoPagoDTO produto) {
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
                rootElement = doc.createElement("produtosPagos");
                doc.appendChild(rootElement);
            }

            Element produtoElement = doc.createElement("produtoPago");
            rootElement.appendChild(produtoElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(produto.getId())));
            produtoElement.appendChild(idElement);

            Element idPagamentoElement = doc.createElement("idPagamento");
            idPagamentoElement.appendChild(doc.createTextNode(String.valueOf(produto.getIdPagamento())));
            produtoElement.appendChild(idPagamentoElement);

            Element nomeElement = doc.createElement("nome");
            nomeElement.appendChild(doc.createTextNode(produto.getNome()));
            produtoElement.appendChild(nomeElement);

            Element precoElement = doc.createElement("preco");
            precoElement.appendChild(doc.createTextNode(String.valueOf(produto.getPreco())));
            produtoElement.appendChild(precoElement);

            Element quantidadeElement = doc.createElement("quantidade");
            quantidadeElement.appendChild(doc.createTextNode(String.valueOf(produto.getQuantidade())));
            produtoElement.appendChild(quantidadeElement);

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
	public List<ProdutoPagoDTO> getByCarrinho(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ProdutoPagoDTO produto) {

        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produtoPago");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int produtoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (produtoId == produto.getId()) {
                        element.getElementsByTagName("idPagamento").item(0).setTextContent(String.valueOf(produto.getIdPagamento()));
                        element.getElementsByTagName("nome").item(0).setTextContent(produto.getNome());
                        element.getElementsByTagName("preco").item(0).setTextContent(String.valueOf(produto.getPreco()));
                        element.getElementsByTagName("quantidade").item(0).setTextContent(String.valueOf(produto.getQuantidade()));

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
	public boolean delete(ProdutoPagoDTO produto) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produtoPago");

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
	public List<ProdutoPagoDTO> get() {
        List<ProdutoPagoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produtoPago");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoPagoDTO produto = new ProdutoPagoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setIdPagamento(Integer.parseInt(element.getElementsByTagName("idPagamento").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                produtos.add(produto);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoPagoDTO> find(ProdutoDTO produtoS) {
        List<ProdutoPagoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produtoPago");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoPagoDTO produto = new ProdutoPagoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setIdPagamento(Integer.parseInt(element.getElementsByTagName("idPagamento").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                if(produtoS.getId()==produto.getId()) {
                	produtos.add(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoPagoDTO> getByPagamento(int id) {
        List<ProdutoPagoDTO> produtos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return produtos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("produtoPago");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ProdutoPagoDTO produto = new ProdutoPagoDTO();
                produto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                produto.setIdPagamento(Integer.parseInt(element.getElementsByTagName("idPagamento").item(0).getTextContent()));
                produto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                produto.setPreco(Double.parseDouble(element.getElementsByTagName("preco").item(0).getTextContent()));
                produto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                if(id==produto.getIdPagamento()) {
                	produtos.add(produto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return produtos;
	}

	@Override
	public List<ProdutoPagoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}


    private void saveChangesToFile(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

}
