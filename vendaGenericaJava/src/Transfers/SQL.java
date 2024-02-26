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
	 public CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoDAO();
	 public CarrinhoInterface carrinhodao = new CarrinhoDAO();
	 public ClienteInterface clientedao = new ClienteDAO();
	 public MarcaInterface marcadao = new MarcaDAO();
	 public PagamentoInterface pagdao = new PagamentoDAO();
	 public ProdutoPagoInterface produtopagoDao = new ProdutoPagoDAO();
	 public ProdutoInterface produtodao = new ProdutoDAO();
	 public VendedorInterface vendedordao = new VendedorDAO();
}
