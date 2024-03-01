package XML;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
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

import DTO.ContaDTO;
import Interfaces.VendedorInterface;

public class VendedorXML implements VendedorInterface {
	protected String xmlFilePath = "./xml-data/vendedor.xml";
	@Override
	public boolean create(ContaDTO cliente) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;
            File file = new File(xmlFilePath);

            if (!file.exists()) {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("contas");
                doc.appendChild(rootElement);
            } else {
                doc = dBuilder.parse(file);
            }

            Element contaElement = doc.createElement("conta");
            doc.getDocumentElement().appendChild(contaElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(cliente.getId())));
            contaElement.appendChild(idElement);

            Element senhaElement = doc.createElement("senha");
            senhaElement.appendChild(doc.createTextNode(cliente.getSenha()));
            contaElement.appendChild(senhaElement);

            Element nomeElement = doc.createElement("nome");
            nomeElement.appendChild(doc.createTextNode(cliente.getNome()));
            contaElement.appendChild(nomeElement);

            Element emailElement = doc.createElement("email");
            emailElement.appendChild(doc.createTextNode(cliente.getEmail()));
            contaElement.appendChild(emailElement);

            Element telefoneElement = doc.createElement("telefone");
            telefoneElement.appendChild(doc.createTextNode(cliente.getTelefone()));
            contaElement.appendChild(telefoneElement);

            // Write the content into XML file
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
	public boolean update(ContaDTO cliente) {
	    try {
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(xmlFilePath);
	        doc.getDocumentElement().normalize();

	        NodeList nodeList = doc.getElementsByTagName("conta");

	        for (int temp = 0; temp < nodeList.getLength(); temp++) {
	            Node node = nodeList.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
	                if (id == cliente.getId()) {
	                    element.getElementsByTagName("senha").item(0).setTextContent(cliente.getSenha());
	                    element.getElementsByTagName("nome").item(0).setTextContent(cliente.getNome());
	                    element.getElementsByTagName("email").item(0).setTextContent(cliente.getEmail());
	                    element.getElementsByTagName("telefone").item(0).setTextContent(cliente.getTelefone());
	                }
	            }
	        }

	        // Write the updated content into XML file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(xmlFilePath);
	        transformer.transform(source, result);
	        return true;
	    } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
	        e.printStackTrace();
	    }
		return false;
	}

	@Override
	public List<ContaDTO> get() {
        List<ContaDTO> contas = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("conta");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ContaDTO conta = new ContaDTO();
                    conta.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    conta.setSenha(element.getElementsByTagName("senha").item(0).getTextContent());
                    conta.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    conta.setEmail(element.getElementsByTagName("email").item(0).getTextContent());
                    conta.setTelefone(element.getElementsByTagName("telefone").item(0).getTextContent());
                    contas.add(conta);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return contas;
	}

	@Override
	public List<ContaDTO> find(ContaDTO cliente) {
		List<ContaDTO> contas = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("conta");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ContaDTO conta = new ContaDTO();
                    conta.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    conta.setSenha(element.getElementsByTagName("senha").item(0).getTextContent());
                    conta.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    conta.setEmail(element.getElementsByTagName("email").item(0).getTextContent());
                    conta.setTelefone(element.getElementsByTagName("telefone").item(0).getTextContent());
                    if(cliente.getId()==conta.getId()) {
                    	contas.add(conta);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return contas;
	}

	@Override
	public List<ContaDTO> findByEmail(ContaDTO cliente) {
		List<ContaDTO> contas = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("conta");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ContaDTO conta = new ContaDTO();
                    conta.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    conta.setSenha(element.getElementsByTagName("senha").item(0).getTextContent());
                    conta.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    conta.setEmail(element.getElementsByTagName("email").item(0).getTextContent());
                    conta.setTelefone(element.getElementsByTagName("telefone").item(0).getTextContent());
                    if(cliente.getEmail().toString().equals(conta.getEmail())) {
                    	contas.add(conta);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return contas;
	}

	@Override
	public List<ContaDTO> findByEmailPassword(ContaDTO cliente) {

		List<ContaDTO> contas = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("conta");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ContaDTO conta = new ContaDTO();
                    conta.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    conta.setSenha(element.getElementsByTagName("senha").item(0).getTextContent());
                    conta.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    conta.setEmail(element.getElementsByTagName("email").item(0).getTextContent());
                    conta.setTelefone(element.getElementsByTagName("telefone").item(0).getTextContent());
                    if(cliente.getEmail().toString().equals(conta.getEmail()) && cliente.getSenha().toString().equals(conta.getSenha())) {
                    	contas.add(conta);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return contas;
	}

	@Override
	public List<ContaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ContaDTO cliente) {
	    try {
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(xmlFilePath);
	        doc.getDocumentElement().normalize();

	        NodeList nodeList = doc.getElementsByTagName("conta");

	        for (int temp = 0; temp < nodeList.getLength(); temp++) {
	            Node node = nodeList.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                int accountId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
	                if (accountId == cliente.getId()) {
	                    node.getParentNode().removeChild(node);
	                }
	            }
	        }
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(xmlFilePath);
	        transformer.transform(source, result);
	        return true;
	    } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
	        e.printStackTrace();
	    }
		return false;
	}
}
