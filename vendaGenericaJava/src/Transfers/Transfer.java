package Transfers;

import Interfaces.CarrinhoInterface;
import Interfaces.CarrinhoProdutoInterfaces;
import Interfaces.ClienteInterface;
import Interfaces.MarcaInterface;
import Interfaces.PagamentoInterface;
import Interfaces.ProdutoInterface;
import Interfaces.ProdutoPagoInterface;
import Interfaces.VendedorInterface;

public abstract class Transfer {
	 public CarrinhoProdutoInterfaces carrinhoR;
	 public CarrinhoInterface carrinhodao;
	 public ClienteInterface clientedao;
	 public MarcaInterface marcadao;
	 public PagamentoInterface pagdao;
	 public ProdutoPagoInterface produtopagoDao;
	 public ProdutoInterface produtodao;
	 public VendedorInterface vendedordao;
}
