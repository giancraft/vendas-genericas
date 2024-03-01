package XML;

import java.sql.ResultSet;
import java.util.List;

import DTO.CarrinhoDTO;
import Interfaces.CarrinhoInterface;
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


public class CarrinhoXML implements CarrinhoInterface {

	protected String xmlFilePath = "./xml-data/cliente.xml";
	@Override
	public boolean create(CarrinhoDTO carrinho) {

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
                rootElement = doc.createElement("carrinhos");
                doc.appendChild(rootElement);
            }

            Element carrinhoElement = doc.createElement("carrinho");
            rootElement.appendChild(carrinhoElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(carrinho.getId())));
            carrinhoElement.appendChild(idElement);

            Element clienteElement = doc.createElement("cliente");
            clienteElement.appendChild(doc.createTextNode(String.valueOf(carrinho.getCliente())));
            carrinhoElement.appendChild(clienteElement);

            Element nomeElement = doc.createElement("nome");
            nomeElement.appendChild(doc.createTextNode(carrinho.getNome()));
            carrinhoElement.appendChild(nomeElement);

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
	public boolean update(CarrinhoDTO carrinho) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CarrinhoDTO> get() {
		try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return null;
            }
            List<CarrinhoDTO> carrinhos = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinho");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;    
                    CarrinhoDTO carrinhodto = new CarrinhoDTO();
                    carrinhodto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    carrinhodto.setCliente(Integer.parseInt(element.getElementsByTagName("cliente").item(0).getTextContent()));
                    carrinhodto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    carrinhos.add(carrinhodto);
                }
            }
            return carrinhos;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<CarrinhoDTO> find(CarrinhoDTO carrinho) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return null;
            }
            List<CarrinhoDTO> carrinhos = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinho");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int carrinhoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (carrinhoId == carrinho.getId()) {
                        CarrinhoDTO carrinhodto = new CarrinhoDTO();
                        carrinhodto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                        carrinhodto.setCliente(Integer.parseInt(element.getElementsByTagName("cliente").item(0).getTextContent()));
                        carrinhodto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                        carrinhos.add(carrinhodto);
                    }
                }
            }
            return carrinhos;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public List<CarrinhoDTO> getByCliente(int idCliente) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return null;
            }
            List<CarrinhoDTO> carrinhos = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinho");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    CarrinhoDTO carrinhodto = new CarrinhoDTO();
                    carrinhodto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    carrinhodto.setCliente(Integer.parseInt(element.getElementsByTagName("cliente").item(0).getTextContent()));
                    carrinhodto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    if(idCliente == carrinhodto.getCliente()){
                        carrinhos.add(carrinhodto);
                    }
                }
            }
            return carrinhos;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<CarrinhoDTO> getByName(String nome) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return null;
            }
            List<CarrinhoDTO> carrinhos = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinho");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    CarrinhoDTO carrinhodto = new CarrinhoDTO();
                    carrinhodto.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    carrinhodto.setCliente(Integer.parseInt(element.getElementsByTagName("cliente").item(0).getTextContent()));
                    carrinhodto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    if(carrinhodto.getNome().toString().equals(nome)){
                        carrinhos.add(carrinhodto);
                    }
                }
            }
            return carrinhos;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<CarrinhoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(CarrinhoDTO carrinho) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("carrinho");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int carrinhoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (carrinhoId == carrinho.getId()) {
                        node.getParentNode().removeChild(node);
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
	public boolean insertProdutoInCarrinho(int produtoId, int quantidade, int carrinhoId) {
		// TODO Auto-generated method stub
		return false;
	}
    private void saveChangesToFile(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}
