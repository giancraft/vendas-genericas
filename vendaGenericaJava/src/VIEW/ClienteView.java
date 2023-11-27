package VIEW;

import DTO.ContaDTO;

import java.util.Scanner;

import BO.ClienteBO;

public class ClienteView {
	
	public static boolean loginStatus = false;
	
	public static void main(String[] args) {
		View.limparTerminal();
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
		}
	}
	public static void realizarLogin() {
		
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
	}
	

}
