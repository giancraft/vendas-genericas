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
	 public CarrinhoProdutoInterfaces carrinhoR = new CarrinhoProdutoXML();
	 public CarrinhoInterface carrinhodao = new CarrinhoXML();
	 public ClienteInterface clientedao = new ClienteXML();
	 public MarcaInterface marcadao = new MarcaXML();
	 public PagamentoInterface pagdao = new PagamentoXML();
	 public ProdutoPagoInterface produtopagoDao = new ProdutoPagoXML();
	 public ProdutoInterface produtodao = new ProdutoXML();
	 public VendedorInterface vendedordao = new VendedorXML();
}
