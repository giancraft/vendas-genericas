package VIEW;

import DTO.ContaDTO;

import java.util.Scanner;

import BO.ClienteBO;

public class ClienteView {
	
	public static boolean loginStatus = false;
	public static ContaDTO clienteLogado;
	public static void main(String[] args) {
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
				System.out.println("Gostaria de delogar [0], ver os produtos[1] ou alterar seus dados[2]?");
				Scanner input = new Scanner(System.in);
				response = input.nextInt();
				input.nextLine();
			}while(!(response==1 || response==0) || response==2);
			View.limparTerminal();
			if(response == 0) {
				deslogar();
			}else if(response == 1) {
				mostrarProdutos();
			}else {
				alterardados();
			}
		}
	}
	public static void mostrarProdutos() {

		main(null);
	}
	public static void alterardados() {
		System.out.println("Você escolheu alterar dados da sua conta!");
		Scanner input = new Scanner(System.in);
		String nome = "";
		do {
			System.out.println("Insira seu nome (limite de 60 caracteres): ");
			nome = input.nextLine();
		}while(nome.length()!=0 || nome.length()>60);

		String email = "";
		do {
			System.out.println("Insira seu email (limite de 60 caracteres): ");
			email = input.nextLine();
		}while(email.length()!=0 || email.length()>60 || !ClienteBO.validarEmail(email));

		String telefone = "";
		do {
			System.out.println("Insira seu telefone (9 digitos): ");
			telefone = input.nextLine();
		}while(telefone.length()!=0 || telefone.length()!=9 || !ClienteBO.validarTelefone(telefone));

		String senha = "";
		System.out.println("Insira sua senha: ");
		senha = input.nextLine();
		
		clienteLogado.setNome(nome);
		clienteLogado.setEmail(email);
		clienteLogado.setTelefone(telefone);
		clienteLogado.setSenha(ClienteBO.calcularMD5(senha));
		ClienteBO.alterarDados(clienteLogado);
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
		ClienteBO.logarCliente(cliente);
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
		ClienteBO.cadastrarCliente(cliente);
		main(null);
	}
	

}
