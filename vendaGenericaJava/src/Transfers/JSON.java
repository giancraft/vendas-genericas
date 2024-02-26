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
	 public CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoJSON();
	 public CarrinhoInterface carrinhodao = new CarrinhoJSON();
	 public ClienteInterface clientedao = new ClienteJSON();
	 public MarcaInterface marcadao = new MarcaJSON();
	 public PagamentoInterface pagdao = new PagamentoJSON();
	 public ProdutoPagoInterface produtopagoDao = new ProdutoPagoJSON();
	 public ProdutoInterface produtodao = new ProdutoJSON();
	 public VendedorInterface vendedordao = new VendedorJSON();
}
