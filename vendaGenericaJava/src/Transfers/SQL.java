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
	 public static CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoDAO();
	 public static CarrinhoInterface carrinhodao = new CarrinhoDAO();
	 public static ClienteInterface clientedao = new ClienteDAO();
	 public static MarcaInterface marcadao = new MarcaDAO();
	 public static PagamentoInterface pagdao = new PagamentoDAO();
	 public static ProdutoPagoInterface produtopagoDao = new ProdutoPagoDAO();
	 public static ProdutoInterface produtodao = new ProdutoDAO();
	 public static VendedorInterface vendedordao = new VendedorDAO();
}
