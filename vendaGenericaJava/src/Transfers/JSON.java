package Transfers;

import Interfaces.CarrinhoProdutoInterfaces;
import Interfaces.CarrinhoInterface;
import Interfaces.ClienteInterface;
import Interfaces.MarcaInterface;
import Interfaces.PagamentoInterface;
import Interfaces.ProdutoPagoInterface;
import Interfaces.ProdutoInterface;
import Interfaces.VendedorInterface;
import JSON.*;

public class JSON extends Transfer{
	 public static CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoJSON();
	 public static CarrinhoInterface carrinhodao = new CarrinhoJSON();
	 public static ClienteInterface clientedao = new ClienteJSON();
	 public static MarcaInterface marcadao = new MarcaJSON();
	 public static PagamentoInterface pagdao = new PagamentoJSON();
	 public static ProdutoPagoInterface produtopagoDao = new ProdutoPagoJSON();
	 public static ProdutoInterface produtodao = new ProdutoJSON();
	 public static VendedorInterface vendedordao = new VendedorJSON();
}
