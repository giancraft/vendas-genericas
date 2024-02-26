package VIEW;

import DTO.CarrinhoDTO;
import DTO.CarrinhoProdutoDTO;
import DTO.ContaDTO;
import DTO.MarcaDTO;
import DTO.ProdutoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteView extends Painel{
	public static void main(String[] args) {
		if(!redirectTo.equals("")) {
			switch(redirectTo) {
				case "login":
					realizarLogin();
				break;
				case "cadastro":
					realizarCadastro();
				break;
			}
		}
		if(!loginStatus) {
			int response;
			do {
				System.out.println("Gostaria de realizar o login[0] ou cadastrar-se[1]?");
				response = input.nextInt();
				input.nextLine();
			}while(!(response==1 || response==0));
			limparTerminal();
			if(response == 0) {
				realizarLogin();
			}else {
				realizarCadastro();
			}
		}else {
			int response;
			do {
				System.out.println("Gostaria de deslogar [0], ver os produtos[1], ver carrinhos[2], adicionar carrinho[3], alterar seus dados[4] ou ver seus dados[5]?");
				response = input.nextInt();
				input.nextLine();
			}while(!(response==1 || response==0 || response==2 || response==3 || response==4 || response==5));
			limparTerminal();
			if(response == 0) {
				deslogar();
			}else if(response == 1) {
				mostrarProdutos();
			}else if(response == 2) {
				mostrarCarrinhos();
			}else if(response == 3) {
				adicionarCarrinho();
			}else if(response == 4) {
				alterardados();
			}else {
				System.out.println("Dados do usuário: ");
				System.out.println("Nome: "+ClienteView.clienteLogado.getNome());
				System.out.println("E-mail: "+ClienteView.clienteLogado.getEmail());
				System.out.println("Telefone: "+ClienteView.clienteLogado.getTelefone());
				main(null);
			}
		}
	}
	public static void adicionarCarrinho(){
		System.out.println("Você escolheu cadastrar carrinho!");
		System.out.println("Preencha os campos para cadastrar");
		String nome = "";
		do {
			System.out.println("Insira seu nome (limite de 60 caracteres): ");
			nome = input.nextLine();
		}while(nome.equals("") || nome.length()>60);		
		CarrinhoDTO carrinho = new CarrinhoDTO();
		carrinho.setNome(nome);
		carrinho.setCliente(ClienteView.clienteLogado.getId());
		System.out.println(CarrinhoBO.criarCarrinho(carrinho));
		main(null);
	}
	public static void mostrarCarrinhos() {
		System.out.println("Você escolheu mostrar os seus carrinhos!");
		List<CarrinhoDTO> carrinhos = CarrinhoBO.listarCarrinhoCliente(ClienteView.clienteLogado.getId());
		System.out.print("id  |Nome\n");
		for(int i = 0; i<carrinhos.size(); i++) {
			System.out.print(i+1);
			for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
				System.out.print(" ");
			}
			System.out.print("|"+carrinhos.get(i).getNome()+"\n");
		}
		Scanner input = new Scanner(System.in);
		int response;
		System.out.println("Digite [0 id inválido] para sair ou o id do carrinho para ver outras operações");
		response = input.nextInt();
		input.nextLine();
		int idCarrinho = response-1;
		if(response==0 || response>carrinhos.size()) {
			main(null);
		}else {
			List<CarrinhoProdutoDTO> produtos = CarrinhoBO.mostrarProdutos(carrinhos.get(idCarrinho).getId());
			System.out.println("id  |Nome    |Quantidade");
			for(int i = 0; i<produtos.size(); i++) {
				System.out.print(i+1);
				for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
					System.out.print(" ");
				}
				System.out.print("|"+produtos.get(i).getNome());
				for(int x = ProdutoBO.qtdDigitos(i); x<8; x++) {
					System.out.print(" ");
				}
				System.out.print("|"+produtos.get(i).getQuantidade()+"\n");
			}
			do {
				System.out.println("[0] para voltar para listagem");
				System.out.println("[1] para alterar o nome");
				System.out.println("[2] para deletar o carrinho");
				System.out.println("[3] para realizar o pagamento");
				System.out.println("[4] para remover o produto");
				System.out.println("[5] para alterar a quantidade do produto");
				response = input.nextInt();
			}while(response<0 && response>5);
			input.nextLine();
			if(response == 0) {
				mostrarCarrinhos();
			}else if(response == 1){
				alterarNomeCarrinho(carrinhos.get(idCarrinho));
			}else if(response == 2){
				deletarCarrinho(carrinhos.get(idCarrinho));
			}else if(response == 3){
				pagarCarrinho(carrinhos.get(idCarrinho));
			}else if(response == 4){
				int idprod =0;
				do {
					System.out.println("Insira a id do produto");
					idprod = input.nextInt();
				}while(idprod<=0 || idprod>produtos.size());	
				removerProdutoDoCarrinho(produtos.get(idprod-1));
			}else if(response == 5){
				int idprod =0;
				do {
					System.out.println("Insira a id do produto");
					idprod = input.nextInt();
				}while(idprod<=0 || idprod>produtos.size());

				int quantidade =0;
				do {
					System.out.println("Insira quantidade desejada do produto");
					quantidade = input.nextInt();
				}while(quantidade<=0 || quantidade>ProdutoBO.find(produtos.get(idprod-1).getIdProduto()).get(0).getEstoque());		
				produtos.get(idprod-1).setQuantidade(quantidade);
				alterarQuantidadeNoCarrinho(produtos.get(idprod-1));
			}
		}
		input.close();
	}
	public static void alterarQuantidadeNoCarrinho(CarrinhoProdutoDTO carrinhoproduto) {
		System.out.println(CarrinhoBO.alterarCarrinho(carrinhoproduto));
		main(null);
	}
	public static void removerProdutoDoCarrinho(CarrinhoProdutoDTO carrinhoproduto) {
		System.out.println(CarrinhoBO.deletarCarrinhoProd(carrinhoproduto));
		main(null);
	}
	public static void pagarCarrinho(CarrinhoDTO carrinho) {
		System.out.println("Você escolheu pagar o carrinho!");
		String formaPagamento = "";
		do {
			System.out.println("Insira sua forma de pagamento: ");
			formaPagamento = input.nextLine().toUpperCase();
		}while(formaPagamento.equals(""));	
		List<CarrinhoProdutoDTO> produtos = CarrinhoBO.mostrarProdutos(carrinho.getId());
		List<ProdutoDTO> produtosNovos = new ArrayList<>();
		boolean valido=true;
		for(int i = 0; i<produtos.size(); i++) {
			if(ProdutoBO.obterPorId(produtos.get(i)).size()>0) {
				ProdutoDTO produtoNovo = ProdutoBO.obterPorId(produtos.get(i)).get(0);
				if((produtoNovo.getEstoque()-produtos.get(i).getQuantidade())>=0) {
					produtoNovo.setEstoque(produtoNovo.getEstoque()-produtos.get(i).getQuantidade());
					produtosNovos.add(produtoNovo);
				}else {
					System.out.println("Invalido o estoque não supre a quantidade");
					valido = false;
				}
			}else {
				valido = false;
			}
		}
		if(valido) {
			for(int i = 0; i<produtosNovos.size(); i++) {
				ProdutoBO.alterarDados(produtosNovos.get(i));
			}
		}else {
			main(null);
		}
		System.out.println(PagamentoBO.cadastrarPagamento(carrinho.getId(), formaPagamento));
		main(null);
	}
	public static void deletarCarrinho(CarrinhoDTO carrinho) {
		System.out.println("Você escolheu deletar o carrinho!");
		System.out.println(CarrinhoBO.deletarCarrinho(carrinho));
		main(null);
	}
	public static void alterarNomeCarrinho(CarrinhoDTO carrinho) {
		System.out.println("Você escolheu alterar o nome do carrinho!");
		String nome = "";
		do {
			System.out.println("Insira o nome: ");
			nome = input.nextLine();
		}while(nome.equals("") || nome.length()>60);
		carrinho.setNome(nome);
		System.out.println(CarrinhoBO.alterarCarrinho(carrinho));
		main(null);
	}
	public static void mostrarProdutos() {
		System.out.println("Você escolheu motrar os produtos!");
		List<ProdutoDTO> produtos = ProdutoBO.listarProdutosCliente(new MarcaDTO());
		System.out.print("id  |Nome\n");
		for(int i = 0; i<produtos.size(); i++) {
			System.out.print(i+1);
			for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
				System.out.print(" ");
			}
			System.out.print("|"+produtos.get(i).getNome()+"\n");
		}
		int response;
		System.out.println("Digite [0 ou um id inválido] para sair ou o id do produto para ver outras operações?");
		response = input.nextInt();
		input.nextLine();
		int id = response;
		if(response==0 || response>produtos.size()) {
			main(null);
		}else {
			ProdutoBO.mostrarDescricao(produtos.get(id-1).getId());
			do {
				System.out.println("[0] para voltar para listagem");
				System.out.println("[1] para adicionar à um carrinho");
				response = input.nextInt();
			}while(response!=0 && response!=1);
			input.nextLine();
			if(response == 0) {
				mostrarProdutos();
			}else {
				List<CarrinhoDTO> carrinhos;
				do {
					System.out.println("Insira o carrinho: ");
					String nomeCarrinho = input.nextLine();
					carrinhos = CarrinhoBO.getByNome(nomeCarrinho);
				}while(carrinhos.size()!=1);
				if(produtos.get(id-1).getEstoque()<=0) {
					System.out.println("Produto não possui itens no estoque");
					main(null);
				}
				int quantidade;
				do {
					System.out.println("Insira a quantidade");
					quantidade = input.nextInt();
				}while(quantidade<=0 || quantidade > produtos.get(id-1).getEstoque());
				if(CarrinhoBO.isProdutoCadastrado(produtos.get(id-1))) {
					CarrinhoProdutoDTO carrinhoProduto = CarrinhoBO.getProdutoCadastrado(produtos.get(id-1)).get(0);
					carrinhoProduto.setQuantidade(quantidade);
					System.out.println(CarrinhoBO.alterarCarrinho(carrinhoProduto));
				}else {
					CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
					carrinhoProduto.setIdCarrinho(carrinhos.get(0).getId());
					carrinhoProduto.setIdProduto(produtos.get(id-1).getId());
					carrinhoProduto.setNome(produtos.get(id-1).getNome());
					carrinhoProduto.setQuantidade(quantidade);
					System.out.println(CarrinhoBO.adicionarCarrinhoProduto(carrinhoProduto));
				}
				main(null);
			}
		}
	}
	public static void alterardados() {
		System.out.println("Você escolheu alterar dados da sua conta!");
		String nome = "";
		System.out.println("Insira seu nome, deixe vazio para não alterar: ");
		nome = input.nextLine();
		String email = "";
		System.out.println("Insira seu email, deixe vazio para não alterar: ");
		email = input.nextLine();
		String telefone = "";
		System.out.println("Insira seu telefone, deixe vazio para não alterar: ");
		telefone = input.nextLine();
		String senha = "";
		System.out.println("Insira sua senha, deixe vazio para não alterar: ");
		senha = input.nextLine();
		if(!nome.equals("")) {
			clienteLogado.setNome(nome);	
		}
		if(!email.equals("")) {
			clienteLogado.setEmail(email);	
		}
		if(!telefone.equals("")) {
			clienteLogado.setTelefone(telefone);	
		}
		if(!senha.equals("")) {
			clienteLogado.setSenha(ClienteBO.calcularMD5(senha));	
		}
		System.out.println(ClienteBO.alterarDados(clienteLogado));
		main(null);
	}
	public static void deslogar() {
		System.out.println("Você escolheu deslogar com sua conta!");
		ClienteView.clienteLogado = null;
		ClienteView.loginStatus = false;
		main(null);
	}
	public static void realizarLogin() {
		System.out.println("Você escolheu logar com sua conta!");
		System.out.println("Preencha os campos para logar-se");
		String email = "";
		do {
			System.out.println("Insira seu email (limite de 60 caracteres): ");
			email = input.nextLine();
		}while(email.equals("") || email.length()>60 || !ClienteBO.validarEmail(email));

		String senha = "";
		do {
			System.out.println("Insira sua senha: ");
			senha = input.nextLine();
		}while(senha.equals(""));
		
		ContaDTO cliente = new ContaDTO();
		cliente.setEmail(email);
		cliente.setSenha(ClienteBO.calcularMD5(senha));
		System.out.println(ClienteBO.logarCliente(cliente));
		main(null);	
	}
	public static void realizarCadastro() {
		System.out.println("Você escolheu cadastrar usuário!");
		System.out.println("Preencha os campos para cadastrar-se");
		String nome = "";
		do {
			System.out.println("Insira seu nome (limite de 60 caracteres): ");
			nome = input.nextLine();
		}while(nome.equals("") || nome.length()>60);

		String email = "";
		do {
			System.out.println("Insira seu email (limite de 60 caracteres): ");
			email = input.nextLine();
		}while(email.equals("") || email.length()>60 || !ClienteBO.validarEmail(email));

		String telefone = "";
		do {
			System.out.println("Insira seu telefone (9 digitos): ");
			telefone = input.nextLine();
		}while(telefone.length()!=9 || !ClienteBO.validarTelefone(telefone));

		String senha = "";
		do {
			System.out.println("Insira sua senha: ");
			senha = input.nextLine();
		}while(senha.equals(""));
		
		ContaDTO cliente = new ContaDTO();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setSenha(ClienteBO.calcularMD5(senha));
		System.out.println(ClienteBO.cadastrarCliente(cliente));
		main(null);
	}
}
