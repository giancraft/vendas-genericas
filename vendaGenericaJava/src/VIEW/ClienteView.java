package VIEW;

import DTO.ContaDTO;
import DTO.ProdutoDTO;

import java.util.List;
import java.util.Scanner;

import BO.ClienteBO;
import BO.ProdutoBO;

public class ClienteView {
	
	public static boolean loginStatus = false;
	public static ContaDTO clienteLogado;
	public static String redirectTo="";
	
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
		System.out.println("Bem vindo ao sistema!\n");
		if(!loginStatus) {
			int response;
			do {
				System.out.println("Gostaria de realizar o login[0] ou cadastrar-se[1]?");
				Scanner input = new Scanner(System.in);
				response = input.nextInt();
				input.nextLine();
			}while(!(response==1 || response==0));
			View.limparTerminal();
			if(response == 0) {
				realizarLogin();
			}else {
				realizarCadastro();
			}
		}else {
			int response;
			do {
				System.out.println("Gostaria de delogar [0], ver os produtos[1], ver carrinhos[2], alterar seus dados[3]?");
				Scanner input = new Scanner(System.in);
				response = input.nextInt();
				input.nextLine();
			}while(!(response==1 || response==0 || response==2));
			View.limparTerminal();
			if(response == 0) {
				deslogar();
			}else if(response == 1) {
				mostrarProdutos();
			}else if(response == 2) {
				mostrarCarrinhos();
			}else {
				alterardados();
			}
		}
	}
	public static void mostrarCarrinhos() {
		
	}
	public static void mostrarProdutos() {
		System.out.println("Você escolheu motrar os produtos!");
		List<ProdutoDTO> produtos = ProdutoBO.listarProdutos();
		System.out.print("id  |Nome\n");
		for(int i = 0; i<produtos.size(); i++) {
			System.out.print(i+1);
			for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
				System.out.print(" ");
			}
			System.out.print("|"+produtos.get(i).getNome()+"\n");
		}
		Scanner input = new Scanner(System.in);
		int response;
		System.out.println("Digite [0] para sair e o id do produto para ver outras operações?");
		response = input.nextInt();
		input.nextLine();
		if(response==0) {
			main(null);
		}else {
			ProdutoBO.mostrarDescricao(response);
		}
	}
	public static void alterardados() {
		System.out.println("Você escolheu alterar dados da sua conta!");
		Scanner input = new Scanner(System.in);
		String nome = "";
		System.out.println("Insira seu nome (limite de 60 caracteres): ");
		nome = input.nextLine();
		String email = "";
		System.out.println("Insira seu email (limite de 60 caracteres): ");
		email = input.nextLine();
		String telefone = "";
		System.out.println("Insira seu telefone (9 digitos): ");
		telefone = input.nextLine();
		String senha = "";
		System.out.println("Insira sua senha: ");
		senha = input.nextLine();
		// lógica se não estiver vazio
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
		Scanner input = new Scanner(System.in);
		
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
		Scanner input = new Scanner(System.in);
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
