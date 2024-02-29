package Transfers;

import Interfaces.CarrinhoProdutoInterfaces;
import Interfaces.CarrinhoInterface;
import Interfaces.ClienteInterface;
import Interfaces.MarcaInterface;
import Interfaces.PagamentoInterface;
import Interfaces.ProdutoPagoInterface;
import Interfaces.ProdutoInterface;
import Interfaces.VendedorInterface;
import XML.*;

public class XML extends Transfer{
	private CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoXML();
	private CarrinhoInterface carrinhodao = new CarrinhoXML();
	private ClienteInterface clientedao = new ClienteXML();
	private MarcaInterface marcadao = new MarcaXML();
	private PagamentoInterface pagdao = new PagamentoXML();
	private ProdutoPagoInterface produtopagoDao = new ProdutoPagoXML();
	private ProdutoInterface produtodao = new ProdutoXML();
	private VendedorInterface vendedordao = new VendedorXML();
	public CarrinhoProdutoInterfaces getCarrinhoR() {
		return carrinhoR;
	}
	public void setCarrinhoR(CarrinhoProdutoInterfaces carrinhoR) {
		this.carrinhoR = carrinhoR;
	}
	public CarrinhoInterface getCarrinhodao() {
		return carrinhodao;
	}
	public void setCarrinhodao(CarrinhoInterface carrinhodao) {
		this.carrinhodao = carrinhodao;
	}
	public ClienteInterface getClientedao() {
		return clientedao;
	}
	public void setClientedao(ClienteInterface clientedao) {
		this.clientedao = clientedao;
	}
	public MarcaInterface getMarcadao() {
		return marcadao;
	}
	public void setMarcadao(MarcaInterface marcadao) {
		this.marcadao = marcadao;
	}
	public PagamentoInterface getPagdao() {
		return pagdao;
	}
	public void setPagdao(PagamentoInterface pagdao) {
		this.pagdao = pagdao;
	}
	public ProdutoPagoInterface getProdutopagoDao() {
		return produtopagoDao;
	}
	public void setProdutopagoDao(ProdutoPagoInterface produtopagoDao) {
		this.produtopagoDao = produtopagoDao;
	}
	public ProdutoInterface getProdutodao() {
		return produtodao;
	}
	public void setProdutodao(ProdutoInterface produtodao) {
		this.produtodao = produtodao;
	}
	public VendedorInterface getVendedordao() {
		return vendedordao;
	}
	public void setVendedordao(VendedorInterface vendedordao) {
		this.vendedordao = vendedordao;
	}
	 
	 
}
