package VIEW;

import java.util.Scanner;

import BO.CarrinhoBO;
import BO.MarcaBO;
import BO.PagamentoBO;
import BO.ProdutoBO;
import BO.VendedorBO;
import BO.ClienteBO;
import DTO.ContaDTO;

public abstract class Painel {
	public static Scanner input = new Scanner(System.in);
	public static boolean loginStatus = false;
	public static ContaDTO clienteLogado;
	public static String redirectTo="";
	public static CarrinhoBO CarrinhoBO= new CarrinhoBO();
	public static VendedorBO VendedorBO= new VendedorBO();
	public static ClienteBO ClienteBO= new ClienteBO();
	public static PagamentoBO PagamentoBO= new PagamentoBO();
	public static ProdutoBO ProdutoBO= new ProdutoBO();
	public static MarcaBO MarcaBO= new MarcaBO();
    public static void limparTerminal() {
        for(int i = 0; i<30; i++) {
        	System.out.print("\n");
        }
    }
}
