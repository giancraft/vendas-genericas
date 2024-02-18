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
	 public static CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoXML();
	 public static CarrinhoInterface carrinhodao = new CarrinhoXML();
	 public static ClienteInterface clientedao = new ClienteXML();
	 public static MarcaInterface marcadao = new MarcaXML();
	 public static PagamentoInterface pagdao = new PagamentoXML();
	 public static ProdutoPagoInterface produtopagoDao = new ProdutoPagoXML();
	 public static ProdutoInterface produtodao = new ProdutoXML();
	 public static VendedorInterface vendedordao = new VendedorXML();
}
