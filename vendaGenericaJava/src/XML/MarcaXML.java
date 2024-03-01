package XML;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import DTO.MarcaDTO;
import Interfaces.MarcaInterface;

public class MarcaXML implements MarcaInterface{
	protected String xmlFilePath = "./xml-data/marca.xml";
	@Override
	public boolean create(MarcaDTO marca) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            File file = new File(xmlFilePath);
            if (file.exists()) {
                doc = dBuilder.parse(file);
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("marcas");
                doc.appendChild(rootElement);
            }

            Element rootElement = doc.getDocumentElement();
            Element marcaElement = doc.createElement("marca");

            marcaElement.setAttribute("id", String.valueOf(marca.getId()));
            marcaElement.appendChild(createElement(doc, "nome", marca.getNome()));

            rootElement.appendChild(marcaElement);

            saveXml(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean update(MarcaDTO marca) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return false; // Arquivo não existe, não há nada para atualizar
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("marca");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int marcaId = Integer.parseInt(eElement.getAttribute("id"));
                    if (marcaId == marca.getId()) {
                        eElement.getElementsByTagName("nome").item(0).setTextContent(marca.getNome());
                        // Atualize outros atributos conforme necessário
                        saveXml(doc); // Salva as alterações no arquivo XML
                        return true; // Encerra o método após atualizar a marca
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public List<MarcaDTO> get() {
        List<MarcaDTO> marcas = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return marcas;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("marca");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    MarcaDTO marca = new MarcaDTO();
                    marca.setId(Integer.parseInt(eElement.getAttribute("id")));
                    marca.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
                    marcas.add(marca);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marcas;
	}

	@Override
	public List<MarcaDTO> find(MarcaDTO marcaS) {
        List<MarcaDTO> marcas = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return marcas;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("marca");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    MarcaDTO marca = new MarcaDTO();
                    marca.setId(Integer.parseInt(eElement.getAttribute("id")));
                    marca.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
                    if(marca.getId()==marcaS.getId()) {
                    	marcas.add(marca);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marcas;
	}

	@Override
	public List<MarcaDTO> getByNome(String nomeM) {
        List<MarcaDTO> marcas = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return marcas;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("marca");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    MarcaDTO marca = new MarcaDTO();
                    marca.setId(Integer.parseInt(eElement.getAttribute("id")));
                    marca.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
                    if(marca.getNome().toString().equals(nomeM)) {
                    	marcas.add(marca);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marcas;
	}

	@Override
	public List<MarcaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(MarcaDTO marca) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return true; // Arquivo não existe, não há nada para excluir
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("marca");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int marcaId = Integer.parseInt(eElement.getAttribute("id"));
                    if (marcaId == marca.getId()) {
                        nNode.getParentNode().removeChild(nNode); // Remove o nó do elemento do XML
                        saveXml(doc); // Salva as alterações no arquivo XML
                        return true; // Encerra o método após excluir a marca
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

    // Método auxiliar para criar elementos no XML
    private static Node createElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    private void saveXml(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(xmlFilePath));
        transformer.transform(source, result);
    }
}
