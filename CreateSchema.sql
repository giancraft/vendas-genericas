CREATE DATABASE vendasgenericas;
USE vendasgenericas;
CREATE TABLE vendedor(
	idVendedor INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(idVendedor),
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    telefone VARCHAR(9),
    senha VARCHAR(60) NOT NULL
);
CREATE TABLE marca(
	idMarca INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(idMarca),
    nome VARCHAR(60)
);
CREATE TABLE produto(
	idProduto INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(idProduto),
    nome VARCHAR(60) NOT NULL,
    preco DOUBLE NOT NULL,
    estoque INT NOT NULL,
    descricao VARCHAR(255),
    idMarca INT NOT NULL,
    FOREIGN KEY(idMarca) REFERENCES MARCA(idMarca),
    idVendedor INT NOT NULL,
    FOREIGN KEY(idVendedor) REFERENCES VENDEDOR(idVendedor)
);
CREATE TABLE cliente(
	idCliente INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(idCliente),
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    telefone VARCHAR(9),
    senha VARCHAR(60) NOT NULL
);
 CREATE TABLE carrinho(
	idCarrinho INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(idCarrinho),
    idCliente INT NOT NULL,
    FOREIGN KEY(idCliente) REFERENCES CLIENTE(idCliente),
    nome VARCHAR(60)
 );
 CREATE TABLE carrinhoproduto(
	idCarrinho INT NOT NULL,
	idProduto INT NOT NULL,
    PRIMARY KEY(idCarrinho, idProduto),
    FOREIGN KEY(idCarrinho) REFERENCES CARRINHO(idCarrinho),
    FOREIGN KEY(idProduto) REFERENCES PRODUTO(idProduto),
    quantidade INT
 );
 CREATE TABLE pagamento(
	idPagamento INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (idPagamento),
    formaPagamento ENUM('DEBITO', 'CREDITO', 'BOLETO', 'PIX'),
    status ENUM('FINALIZADO', 'PENDENTE'),
    idCarrinho INT NOT NULL,
    dataAlteracao DATE,
    FOREIGN KEY(idCarrinho) REFERENCES CARRINHO(idCarrinho)
 );
 #teste
use vendasgenericas;
 CREATE TABLE produtopago(
	idProdutoPago INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (idProdutoPago),
    idPagamento INT NOT NULL,
    FOREIGN KEY(idPagamento) REFERENCES PAGAMENTO(idPagamento),
    nome VARCHAR(60),
    preco DOUBLE,
    quantidade INT
 );