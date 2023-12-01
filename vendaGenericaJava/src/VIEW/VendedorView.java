package VIEW;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import BO.MarcaBO;
import BO.PagamentoBO;
import BO.ProdutoBO;
import BO.VendedorBO;
import DAO.CarrinhoDAO;
import DAO.CarrinhoProdutoDAO;
import DAO.ClienteDAO;
import DAO.MarcaDAO;
import DAO.ProdutoDAO;
import DTO.CarrinhoDTO;
import DTO.CarrinhoProdutoDTO;
import DTO.ContaDTO;
import DTO.MarcaDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;

public class VendedorView {

	public static Scanner input = new Scanner(System.in);
	public static boolean loginStatus = false;
	public static ContaDTO vendedorLogado;
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
		if(!loginStatus) {
			int response;
			do {
				System.out.println("Gostaria de realizar o login[0] ou cadastrar-se[1]?");
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
				System.out.println("Gostaria de deslogar [0], ver os produtos[1], adicionar um produto[2], ver marcas[3], adicionar marca[4] alterar seus dados[5], ver seus dados[6] ou gerar um relatorio[7]?");
				response = input.nextInt();
				input.nextLine();
			}while(!(response==1 || response==0 || response==2 || response==3 || response==4 || response==5 || response==6 || response==7));
			View.limparTerminal();
			if(response == 0) {
				deslogar();
			}else if(response == 1) {
				mostrarProdutos(new MarcaDTO());
			}else if(response == 2) {
				adicionarProduto();
			}else if(response == 3) {
				mostrarMarcas();
			}else if(response == 4) {
				adicionarMarca();
			}else if(response == 5) {
				alterardados();
			}else if(response == 6){
				System.out.println("Dados do usuário: ");
				System.out.println("Nome: "+VendedorView.vendedorLogado.getNome());
				System.out.println("E-mail: "+VendedorView.vendedorLogado.getEmail());
				System.out.println("Telefone: "+VendedorView.vendedorLogado.getTelefone());
				main(null);
			}else {
				System.out.println("Insira a data de inicio: ");
				String data = input.nextLine();
	            LocalDate inicio = LocalDate.now();
		        try {
		            inicio = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		        } catch (Exception e) {
		            System.out.println("Formato de data inválido. Certifique-se de usar o formato yyyy-MM-dd.");
		        }
				System.out.println("Insira a data de fim: ");
				data = input.nextLine();
				LocalDate fim = LocalDate.now();
		        try {
		            fim = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		        } catch (Exception e) {
		            System.out.println("Formato de data inválido. Certifique-se de usar o formato yyyy-MM-dd.");
		        }
				obterVendas(inicio, fim);
			}
		}
	}
	public static void obterVendas(LocalDate inicio, LocalDate fim) {
		List<PagamentoDTO> pagamentos = PagamentoBO.getByData(inicio, fim);
		for(int i=0; i<pagamentos.size();i++) {
			CarrinhoDAO carrinhodao = new CarrinhoDAO();
			CarrinhoDTO carrinhodto = new CarrinhoDTO();
			carrinhodto.setId(pagamentos.get(i).getCarrinho());
			carrinhodto = carrinhodao.find(carrinhodto).get(0);
			ClienteDAO clientedao = new ClienteDAO();
			ContaDTO cliente = new ContaDTO();
			cliente.setId(carrinhodto.getCliente());
			cliente = clientedao.find(cliente).get(0);
			System.out.println("Cliente: " + cliente.getNome());
			System.out.println("Produtos: ");
			CarrinhoProdutoDAO carrinhopdao = new CarrinhoProdutoDAO();
			List<CarrinhoProdutoDTO> carrinhosrs = carrinhopdao.getByCarrinho(carrinhodto.getId());
			System.out.println("id  |Nome  | Quantidade | Preço| Data | Total");
			for(int j=0; j<carrinhosrs.size(); j++) {
				List<ProdutoPagoDTO> produtos = ProdutoBO.pagoObterPorId(pagamentos.get(i).getId());
				for(int k=0; k<produtos.size();k++) {
					for(int l = 0; l<produtos.size(); l++) {
						System.out.print(i+1);
						System.out.print("|"+produtos.get(k).getNome()+"|");
						System.out.print("|"+produtos.get(k).getQuantidade()+"|");
						System.out.print("|"+produtos.get(k).getPreco()+"|");
						System.out.print("|"+pagamentos.get(i).getData()+"|");
						System.out.print("|"+produtos.get(k).getPreco()*produtos.get(k).getQuantidade()+"|\n");
					}
				}
			}
			
		}
		main(null);
	}
	public static void alterarProduto(int id) {
		System.out.println("Você escolheu alterar produto!");
		System.out.println("Preencha os campos para alterar");
		
		String nome = "";
		System.out.println("Insira o nome (vazio para não alterar): ");
		nome = input.nextLine();
			
		String descricao = "";
		System.out.println("Insira a descrição (vazio para não alterar): ");
		descricao = input.nextLine();

		List<MarcaDTO> marcas;
		String nomeMarca="";
		do {
			System.out.println("Insira a marca(vazio para não alterar): ");
			nomeMarca = input.nextLine();
			marcas = MarcaBO.getByNome(nomeMarca);
		}while(marcas.size()!=1 && !nomeMarca.equals(""));
		
		Double preco = 0.00;
		do {
			System.out.println("Insira o preço(0 para vazio): ");
			preco = input.nextDouble();
		}while(preco<0);

		int estoque = 0;
		do {
			System.out.println("Insira a quantidade em estoque([-1] para vazio): ");
			estoque = input.nextInt();
		}while(estoque<-1);
		ProdutoDAO prodDao = new ProdutoDAO();
		ProdutoDTO produtoUpdate = new ProdutoDTO();
		produtoUpdate.setId(id);
		produtoUpdate= prodDao.find(produtoUpdate).get(0);
		if(!nome.equals("")) {
			produtoUpdate.setNome(nome);
		}
		if(!descricao.equals("")) {
			produtoUpdate.setDescricao(descricao);;	
		}
		if(marcas.size()>1) {
			produtoUpdate.setMarca(marcas.get(0).getId());
		}
		if(preco!=0) {
			produtoUpdate.setPreco(preco);
		}
		if(estoque!=-1) {
			produtoUpdate.setEstoque(estoque);
		}
		System.out.println(ProdutoBO.alterarDados(produtoUpdate));
		main(null);
	}
	public static void adicionarProduto() {
		System.out.println("Você escolheu cadastrar produto!");
		System.out.println("Preencha os campos para cadastrar-se");
		String nome = "";
		do {
			System.out.println("Insira o nome (limite de 60 caracteres): ");
			nome = input.nextLine();
		}while(nome.equals("") || nome.length()>60);
		
		String descricao = "";
		do {
			System.out.println("Insira a descrição (60 digitos): ");
			descricao = input.nextLine();
		}while(descricao.length()>60);

		List<MarcaDTO> marcas;
		do {
			System.out.println("Insira a marca: ");
			String nomeMarca = input.nextLine();
			marcas = MarcaBO.getByNome(nomeMarca);
		}while(marcas.size()!=1);
		
		Double preco = 0.00;
		do {
			System.out.println("Insira o preço: ");
			preco = input.nextDouble();
		}while(preco<=0);

		int estoque = 0;
		do {
			System.out.println("Insira a quantidade em estoque: ");
			estoque = input.nextInt();
		}while(estoque<0);
		
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setEstoque(estoque);
		produto.setMarca(marcas.get(0).getId());
		produto.setVendedor(VendedorView.vendedorLogado.getId());
		System.out.println(ProdutoBO.cadastrarProduto(produto));
		main(null);		
	}
	public static void mostrarMarcas() {
		System.out.println("Você escolheu motrar as marcas!");
		List<MarcaDTO> marcas = MarcaBO.listarMarcas();
		System.out.print("id  |Nome\n");
		for(int i = 0; i<marcas.size(); i++) {
			System.out.print(i+1);
			for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
				System.out.print(" ");
			}
			System.out.print("|"+marcas.get(i).getNome()+"\n");
		}
		int response;
		System.out.println("Digite [0] para sair e o id da marca para vizualizar os produtos desta marca");
		response = input.nextInt();
		input.nextLine();
		if(response==0 || response>marcas.size()) {
			main(null);
		}else {
			mostrarProdutosByMarca(response);
		}
	}
	public static void mostrarProdutosByMarca(int id) {
		MarcaDTO marca = new MarcaDTO();
		marca.setId(id);
		MarcaDAO marcadao = new MarcaDAO();
		marca = marcadao.find(marca).get(0);
		mostrarProdutos(marca);
	}
	public static void adicionarMarca() {
		System.out.println("Você escolheu cadastrar Marca!");
		System.out.println("Preencha os campos para cadastrar a marca");
		String nome = "";
		do {
			System.out.println("Insira o nome da marca (limite de 60 caracteres): ");
			nome = input.nextLine();
		}while(nome.equals("") || nome.length()>60);
		
		MarcaDTO marca = new MarcaDTO();
		marca.setNome(nome);
		System.out.println(MarcaBO.cadastrarMarca(marca));
		main(null);		
	}
	public static void deslogar() {
		System.out.println("Você escolheu deslogar com sua conta!");
		VendedorView.vendedorLogado = null;
		VendedorView.loginStatus = false;
		main(null);
	}
	public static void mostrarProdutos(MarcaDTO marca) {
		System.out.println("Você escolheu motrar os produtos!");
		List<ProdutoDTO> produtos = ProdutoBO.listarProdutos(marca);
		System.out.print("id  |Nome\n");
		for(int i = 0; i<produtos.size(); i++) {
			System.out.print(i+1);
			for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
				System.out.print(" ");
			}
			System.out.print("|"+produtos.get(i).getNome()+"\n");
		}
		int response;
		System.out.println("Digite [0] para sair e o id do produto para ver outras operações");
		response = input.nextInt();
		input.nextLine();
		if(response==0 || response>produtos.size()) {
			main(null);
		}else {
			int id = response;
			ProdutoBO.mostrarDescricao(produtos.get(id-1).getId());
			System.out.println("[0] para voltar para listagem");
			System.out.println("[1] para alterar os dados");
			System.out.println("[2] para excluir o produto");
			response = input.nextInt();
			input.nextLine();
			if(response == 0) {
				mostrarProdutos(new MarcaDTO());
			}else if(response==1) {
				alterarProduto(produtos.get(id-1).getId());
			}else {
				System.out.println(ProdutoBO.excluirProduto(produtos.get(id-1)));
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
		System.out.println("Insira sua senha, deixe vazio para não alterar ");
		senha = input.nextLine();
		if(!nome.equals("")) {
			vendedorLogado.setNome(nome);	
		}
		if(!email.equals("")) {
			vendedorLogado.setEmail(email);	
		}
		if(!telefone.equals("")) {
			vendedorLogado.setTelefone(telefone);	
		}
		if(!senha.equals("")) {
			vendedorLogado.setSenha(VendedorBO.calcularMD5(senha));	
		}
		System.out.println(VendedorBO.alterarDados(vendedorLogado));
		main(null);
	}
	public static void realizarLogin() {
		System.out.println("Você escolheu logar com sua conta!");
		System.out.println("Preencha os campos para logar-se");
		
		String email = "";
		do {
			System.out.println("Insira seu email (limite de 60 caracteres): ");
			email = input.nextLine();
		}while(email.equals("") || email.length()>60 || !VendedorBO.validarEmail(email));

		String senha = "";
		do {
			System.out.println("Insira sua senha: ");
			senha = input.nextLine();
		}while(senha.equals(""));
		
		ContaDTO vendedor = new ContaDTO();
		vendedor.setEmail(email);
		vendedor.setSenha(VendedorBO.calcularMD5(senha));
		System.out.println(VendedorBO.logarVendedor(vendedor));
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
		}while(email.equals("") || email.length()>60 || !VendedorBO.validarEmail(email));

		String telefone = "";
		do {
			System.out.println("Insira seu telefone (9 digitos): ");
			telefone = input.nextLine();
		}while(telefone.length()!=9 || !VendedorBO.validarTelefone(telefone));

		String senha = "";
		do {
			System.out.println("Insira sua senha: ");
			senha = input.nextLine();
		}while(senha.equals(""));
		
		ContaDTO cliente = new ContaDTO();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setSenha(VendedorBO.calcularMD5(senha));
		System.out.println(VendedorBO.cadastrarVendedor(cliente));
		main(null);
	}
}
