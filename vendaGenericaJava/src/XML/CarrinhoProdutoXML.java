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

import DTO.CarrinhoProdutoDTO;
import DTO.ProdutoDTO;
import Interfaces.CarrinhoProdutoInterfaces;

public class CarrinhoProdutoXML implements CarrinhoProdutoInterfaces{
	protected String xmlFilePath = "./xml-data/carrinho-produto.xml";
	@Override
	public boolean create(CarrinhoProdutoDTO carrinhoProduto) {
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
                rootElement = doc.createElement("carrinhoProdutos");
                doc.appendChild(rootElement);
            }

            Element carrinhoProdutoElement = doc.createElement("carrinhoProduto");
            rootElement.appendChild(carrinhoProdutoElement);

            Element idCarrinhoElement = doc.createElement("idCarrinho");
            idCarrinhoElement.appendChild(doc.createTextNode(String.valueOf(carrinhoProduto.getIdCarrinho())));
            carrinhoProdutoElement.appendChild(idCarrinhoElement);

            Element idProdutoElement = doc.createElement("idProduto");
            idProdutoElement.appendChild(doc.createTextNode(String.valueOf(carrinhoProduto.getIdProduto())));
            carrinhoProdutoElement.appendChild(idProdutoElement);

            Element quantidadeElement = doc.createElement("quantidade");
            quantidadeElement.appendChild(doc.createTextNode(String.valueOf(carrinhoProduto.getQuantidade())));
            carrinhoProdutoElement.appendChild(quantidadeElement);

            Element nomeElement = doc.createElement("nome");
            nomeElement.appendChild(doc.createTextNode(carrinhoProduto.getNome()));
            carrinhoProdutoElement.appendChild(nomeElement);

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
	public boolean update(CarrinhoProdutoDTO carrinhoProduto) {

        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinhoProduto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int carrinhoId = Integer.parseInt(element.getElementsByTagName("idCarrinho").item(0).getTextContent());
                    int produtoId = Integer.parseInt(element.getElementsByTagName("idProduto").item(0).getTextContent());
                    if (carrinhoId == carrinhoProduto.getIdCarrinho() && produtoId == carrinhoProduto.getIdProduto()) {
                        element.getElementsByTagName("quantidade").item(0).setTextContent(String.valueOf(carrinhoProduto.getQuantidade()));
                        element.getElementsByTagName("nome").item(0).setTextContent(carrinhoProduto.getNome());

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
	public boolean delete(CarrinhoProdutoDTO carrinhoProduto) {
		return false;
	}

	@Override
	public List<CarrinhoProdutoDTO> get() {
        List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return carrinhoProdutos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinhoProduto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
                carrinhoProduto.setIdCarrinho(Integer.parseInt(element.getElementsByTagName("idCarrinho").item(0).getTextContent()));
                carrinhoProduto.setIdProduto(Integer.parseInt(element.getElementsByTagName("idProduto").item(0).getTextContent()));
                carrinhoProduto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                carrinhoProduto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                carrinhoProdutos.add(carrinhoProduto);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> getByCarrinho(int id) {

        List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return carrinhoProdutos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinhoProduto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
                carrinhoProduto.setIdCarrinho(Integer.parseInt(element.getElementsByTagName("idCarrinho").item(0).getTextContent()));
                carrinhoProduto.setIdProduto(Integer.parseInt(element.getElementsByTagName("idProduto").item(0).getTextContent()));
                carrinhoProduto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                carrinhoProduto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                if(carrinhoProduto.getIdCarrinho()==id){
                	carrinhoProdutos.add(carrinhoProduto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> getByProduto(ProdutoDTO produto) {

        List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return carrinhoProdutos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinhoProduto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
                carrinhoProduto.setIdCarrinho(Integer.parseInt(element.getElementsByTagName("idCarrinho").item(0).getTextContent()));
                carrinhoProduto.setIdProduto(Integer.parseInt(element.getElementsByTagName("idProduto").item(0).getTextContent()));
                carrinhoProduto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                carrinhoProduto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                if(carrinhoProduto.getIdProduto()==produto.getId()){
                	carrinhoProdutos.add(carrinhoProduto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> find(CarrinhoProdutoDTO carrinhoProdutoS) {

        List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return carrinhoProdutos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinhoProduto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
                carrinhoProduto.setIdCarrinho(Integer.parseInt(element.getElementsByTagName("idCarrinho").item(0).getTextContent()));
                carrinhoProduto.setIdProduto(Integer.parseInt(element.getElementsByTagName("idProduto").item(0).getTextContent()));
                carrinhoProduto.setQuantidade(Integer.parseInt(element.getElementsByTagName("quantidade").item(0).getTextContent()));
                carrinhoProduto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                if(carrinhoProduto.getIdProduto()==carrinhoProdutoS.getIdProduto()&&carrinhoProduto.getIdCarrinho()==carrinhoProdutoS.getIdCarrinho()){
                	carrinhoProdutos.add(carrinhoProduto);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
