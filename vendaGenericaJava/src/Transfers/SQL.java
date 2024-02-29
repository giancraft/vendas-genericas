package Transfers;

import Interfaces.CarrinhoProdutoInterfaces;
import Interfaces.CarrinhoInterface;
import Interfaces.ClienteInterface;
import Interfaces.MarcaInterface;
import Interfaces.PagamentoInterface;
import Interfaces.ProdutoPagoInterface;
import Interfaces.ProdutoInterface;
import Interfaces.VendedorInterface;
import DAO.*;

public class SQL extends Transfer{
	private CarrinhoProdutoInterfaces carrinhoR;
	private CarrinhoInterface carrinhodao;
	private ClienteInterface clientedao;
	private MarcaInterface marcadao;
	private PagamentoInterface pagdao;
	private ProdutoPagoInterface produtopagoDao;
	private ProdutoInterface produtodao;
	private VendedorInterface vendedordao;
	
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

	public SQL(){
		 super();
		 carrinhoR = new CarrinhoProdutoDAO();
		 carrinhodao = new CarrinhoDAO();
		 clientedao = new ClienteDAO();
		 marcadao = new MarcaDAO();
		 pagdao = new PagamentoDAO();
		 produtopagoDao = new ProdutoPagoDAO();
		 produtodao = new ProdutoDAO();
		 vendedordao = new VendedorDAO();
	}
}
