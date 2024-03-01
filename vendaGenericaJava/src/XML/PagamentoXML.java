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
import java.time.LocalDate;

import DTO.PagamentoDTO;
import ENUMS.FormaPagamento;
import ENUMS.StatusPagamento;
import Interfaces.PagamentoInterface;

public class PagamentoXML implements PagamentoInterface {
	protected String xmlFilePath = "./xml-data/pagamento.xml";
	@Override
	public boolean create(PagamentoDTO pagamento) {
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
                rootElement = doc.createElement("pagamentos");
                doc.appendChild(rootElement);
            }

            Element pagamentoElement = doc.createElement("pagamento");
            rootElement.appendChild(pagamentoElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(pagamento.getId())));
            pagamentoElement.appendChild(idElement);

            Element valorElement = doc.createElement("valor");
            valorElement.appendChild(doc.createTextNode(String.valueOf(pagamento.getValor())));
            pagamentoElement.appendChild(valorElement);

            Element formaPagamentoElement = doc.createElement("formaPagamento");
            formaPagamentoElement.appendChild(doc.createTextNode(pagamento.getFormaPagamento().toString()));
            pagamentoElement.appendChild(formaPagamentoElement);

            Element statusElement = doc.createElement("status");
            statusElement.appendChild(doc.createTextNode(pagamento.getStatus().toString()));
            pagamentoElement.appendChild(statusElement);

            Element produtosElement = doc.createElement("produtos");
            for (int i = 0; i < pagamento.getProdutosNome().size(); i++) {
                Element produtoElement = doc.createElement("produto");
                produtoElement.setAttribute("nome", pagamento.getProdutosNome().get(i));
                produtoElement.setAttribute("preco", String.valueOf(pagamento.getProdutosPreco().get(i)));
                produtoElement.setAttribute("quantidade", String.valueOf(pagamento.getProdutosQuantidade().get(i)));
                produtosElement.appendChild(produtoElement);
            }
            pagamentoElement.appendChild(produtosElement);

            Element dataElement = doc.createElement("data");
            dataElement.appendChild(doc.createTextNode(pagamento.getData().toString()));
            pagamentoElement.appendChild(dataElement);

            Element carrinhoElement = doc.createElement("carrinho");
            carrinhoElement.appendChild(doc.createTextNode(String.valueOf(pagamento.getCarrinho())));
            pagamentoElement.appendChild(carrinhoElement);

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
	public boolean update(PagamentoDTO pagamento) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return false; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int pagamentoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (pagamentoId == pagamento.getId()) {
                        // Atualiza os dados do pagamento com os novos valores
                        element.getElementsByTagName("valor").item(0).setTextContent(String.valueOf(pagamento.getValor()));
                        element.getElementsByTagName("formaPagamento").item(0).setTextContent(pagamento.getFormaPagamento().toString());
                        element.getElementsByTagName("status").item(0).setTextContent(pagamento.getStatus().toString());
                        element.getElementsByTagName("data").item(0).setTextContent(pagamento.getData().toString());
                        element.getElementsByTagName("carrinho").item(0).setTextContent(String.valueOf(pagamento.getCarrinho()));

                        // Atualiza os produtos do pagamento
                        Element produtosElement = (Element) element.getElementsByTagName("produtos").item(0);
                        produtosElement.setTextContent(""); // Limpa os produtos existentes
                        for (int j = 0; j < pagamento.getProdutosNome().size(); j++) {
                            Element produtoElement = doc.createElement("produto");
                            produtoElement.setAttribute("nome", pagamento.getProdutosNome().get(j));
                            produtoElement.setAttribute("preco", String.valueOf(pagamento.getProdutosPreco().get(j)));
                            produtoElement.setAttribute("quantidade", String.valueOf(pagamento.getProdutosQuantidade().get(j)));
                            produtosElement.appendChild(produtoElement);
                        }

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
	public boolean delete(PagamentoDTO pagamento) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return false; // Nada a fazer se o arquivo não existe
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int pagamentoId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (pagamentoId == pagamento.getId()) {
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
    private void saveChangesToFile(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

	@Override
	public List<PagamentoDTO> get() {
        List<PagamentoDTO> pagamentos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return pagamentos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                
                PagamentoDTO pagamento = new PagamentoDTO();
                pagamento.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                pagamento.setValor(Double.parseDouble(element.getElementsByTagName("valor").item(0).getTextContent()));
                pagamento.setFormaPagamento(FormaPagamento.valueOf(element.getElementsByTagName("formaPagamento").item(0).getTextContent()));
                pagamento.setStatus(StatusPagamento.valueOf(element.getElementsByTagName("status").item(0).getTextContent()));

                // Tratamento para os produtos
                NodeList produtosList = element.getElementsByTagName("produto");
                ArrayList<String> produtosNome = new ArrayList<>();
                ArrayList<Double> produtosPreco = new ArrayList<>();
                ArrayList<Integer> produtosQuantidade = new ArrayList<>();
                for (int j = 0; j < produtosList.getLength(); j++) {
                    Element produtoElement = (Element) produtosList.item(j);
                    produtosNome.add(produtoElement.getAttribute("nome"));
                    produtosPreco.add(Double.parseDouble(produtoElement.getAttribute("preco")));
                    produtosQuantidade.add(Integer.parseInt(produtoElement.getAttribute("quantidade")));
                }
                pagamento.setProdutosNome(produtosNome);
                pagamento.setProdutosPreco(produtosPreco);
                pagamento.setProdutosQuantidade(produtosQuantidade);

                pagamento.setData(LocalDate.parse(element.getElementsByTagName("data").item(0).getTextContent()));
                pagamento.setCarrinho(Integer.parseInt(element.getElementsByTagName("carrinho").item(0).getTextContent()));

                pagamentos.add(pagamento);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return pagamentos;
	}
	
	@Override
	public List<PagamentoDTO> getLastByCarrinho(int id) {
        List<PagamentoDTO> pagamentos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return pagamentos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = nodeList.getLength()-1; i >0 ; i--) {
                Element element = (Element) nodeList.item(i);
                
                PagamentoDTO pagamento = new PagamentoDTO();
                pagamento.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                pagamento.setValor(Double.parseDouble(element.getElementsByTagName("valor").item(0).getTextContent()));
                pagamento.setFormaPagamento(FormaPagamento.valueOf(element.getElementsByTagName("formaPagamento").item(0).getTextContent()));
                pagamento.setStatus(StatusPagamento.valueOf(element.getElementsByTagName("status").item(0).getTextContent()));

                // Tratamento para os produtos
                NodeList produtosList = element.getElementsByTagName("produto");
                ArrayList<String> produtosNome = new ArrayList<>();
                ArrayList<Double> produtosPreco = new ArrayList<>();
                ArrayList<Integer> produtosQuantidade = new ArrayList<>();
                for (int j = 0; j < produtosList.getLength(); j++) {
                    Element produtoElement = (Element) produtosList.item(j);
                    produtosNome.add(produtoElement.getAttribute("nome"));
                    produtosPreco.add(Double.parseDouble(produtoElement.getAttribute("preco")));
                    produtosQuantidade.add(Integer.parseInt(produtoElement.getAttribute("quantidade")));
                }
                pagamento.setProdutosNome(produtosNome);
                pagamento.setProdutosPreco(produtosPreco);
                pagamento.setProdutosQuantidade(produtosQuantidade);

                pagamento.setData(LocalDate.parse(element.getElementsByTagName("data").item(0).getTextContent()));
                pagamento.setCarrinho(Integer.parseInt(element.getElementsByTagName("carrinho").item(0).getTextContent()));

                if(pagamento.getId()==id) {
                    pagamentos.add(pagamento);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return pagamentos;
	}

	@Override
	public List<PagamentoDTO> getByData(LocalDate inicio, LocalDate fim) {

        List<PagamentoDTO> pagamentos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return pagamentos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                
                PagamentoDTO pagamento = new PagamentoDTO();
                pagamento.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                pagamento.setValor(Double.parseDouble(element.getElementsByTagName("valor").item(0).getTextContent()));
                pagamento.setFormaPagamento(FormaPagamento.valueOf(element.getElementsByTagName("formaPagamento").item(0).getTextContent()));
                pagamento.setStatus(StatusPagamento.valueOf(element.getElementsByTagName("status").item(0).getTextContent()));

                // Tratamento para os produtos
                NodeList produtosList = element.getElementsByTagName("produto");
                ArrayList<String> produtosNome = new ArrayList<>();
                ArrayList<Double> produtosPreco = new ArrayList<>();
                ArrayList<Integer> produtosQuantidade = new ArrayList<>();
                for (int j = 0; j < produtosList.getLength(); j++) {
                    Element produtoElement = (Element) produtosList.item(j);
                    produtosNome.add(produtoElement.getAttribute("nome"));
                    produtosPreco.add(Double.parseDouble(produtoElement.getAttribute("preco")));
                    produtosQuantidade.add(Integer.parseInt(produtoElement.getAttribute("quantidade")));
                }
                pagamento.setProdutosNome(produtosNome);
                pagamento.setProdutosPreco(produtosPreco);
                pagamento.setProdutosQuantidade(produtosQuantidade);

                pagamento.setData(LocalDate.parse(element.getElementsByTagName("data").item(0).getTextContent()));
                pagamento.setCarrinho(Integer.parseInt(element.getElementsByTagName("carrinho").item(0).getTextContent()));
                if(pagamento.getData().isAfter(inicio) && pagamento.getData().isBefore(fim)) {
                    pagamentos.add(pagamento);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return pagamentos;
	}

	@Override
	public List<PagamentoDTO> find(PagamentoDTO pagamentoS) {

        List<PagamentoDTO> pagamentos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return pagamentos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                
                PagamentoDTO pagamento = new PagamentoDTO();
                pagamento.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                pagamento.setValor(Double.parseDouble(element.getElementsByTagName("valor").item(0).getTextContent()));
                pagamento.setFormaPagamento(FormaPagamento.valueOf(element.getElementsByTagName("formaPagamento").item(0).getTextContent()));
                pagamento.setStatus(StatusPagamento.valueOf(element.getElementsByTagName("status").item(0).getTextContent()));

                // Tratamento para os produtos
                NodeList produtosList = element.getElementsByTagName("produto");
                ArrayList<String> produtosNome = new ArrayList<>();
                ArrayList<Double> produtosPreco = new ArrayList<>();
                ArrayList<Integer> produtosQuantidade = new ArrayList<>();
                for (int j = 0; j < produtosList.getLength(); j++) {
                    Element produtoElement = (Element) produtosList.item(j);
                    produtosNome.add(produtoElement.getAttribute("nome"));
                    produtosPreco.add(Double.parseDouble(produtoElement.getAttribute("preco")));
                    produtosQuantidade.add(Integer.parseInt(produtoElement.getAttribute("quantidade")));
                }
                pagamento.setProdutosNome(produtosNome);
                pagamento.setProdutosPreco(produtosPreco);
                pagamento.setProdutosQuantidade(produtosQuantidade);

                pagamento.setData(LocalDate.parse(element.getElementsByTagName("data").item(0).getTextContent()));
                pagamento.setCarrinho(Integer.parseInt(element.getElementsByTagName("carrinho").item(0).getTextContent()));

                if(pagamentoS.getId()==pagamento.getId()) {
                    pagamentos.add(pagamento);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return pagamentos;
	}

	@Override
	public List<PagamentoDTO> getByCarrinho(int id) {
        List<PagamentoDTO> pagamentos = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                return pagamentos;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pagamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                
                PagamentoDTO pagamento = new PagamentoDTO();
                pagamento.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                pagamento.setValor(Double.parseDouble(element.getElementsByTagName("valor").item(0).getTextContent()));
                pagamento.setFormaPagamento(FormaPagamento.valueOf(element.getElementsByTagName("formaPagamento").item(0).getTextContent()));
                pagamento.setStatus(StatusPagamento.valueOf(element.getElementsByTagName("status").item(0).getTextContent()));

                // Tratamento para os produtos
                NodeList produtosList = element.getElementsByTagName("produto");
                ArrayList<String> produtosNome = new ArrayList<>();
                ArrayList<Double> produtosPreco = new ArrayList<>();
                ArrayList<Integer> produtosQuantidade = new ArrayList<>();
                for (int j = 0; j < produtosList.getLength(); j++) {
                    Element produtoElement = (Element) produtosList.item(j);
                    produtosNome.add(produtoElement.getAttribute("nome"));
                    produtosPreco.add(Double.parseDouble(produtoElement.getAttribute("preco")));
                    produtosQuantidade.add(Integer.parseInt(produtoElement.getAttribute("quantidade")));
                }
                pagamento.setProdutosNome(produtosNome);
                pagamento.setProdutosPreco(produtosPreco);
                pagamento.setProdutosQuantidade(produtosQuantidade);

                pagamento.setData(LocalDate.parse(element.getElementsByTagName("data").item(0).getTextContent()));
                pagamento.setCarrinho(Integer.parseInt(element.getElementsByTagName("carrinho").item(0).getTextContent()));

                if(pagamento.getId()==id) {
                    pagamentos.add(pagamento);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return pagamentos;
	}

	@Override
	public List<PagamentoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
