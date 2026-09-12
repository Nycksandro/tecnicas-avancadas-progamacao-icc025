CREATE DATABASE IF NOT EXISTS locadora_db;
USE locadora_db;

CREATE TABLE IF NOT EXISTS `clientes` (
  `idclientes` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `veiculoAlugado` tinyint DEFAULT NULL,
  `idade` int NOT NULL,
  `cnh` tinyint DEFAULT NULL,
  `endereco` varchar(80) NOT NULL,
  PRIMARY KEY (`idclientes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `veiculos` (
  `idVeiculos` int NOT NULL AUTO_INCREMENT,
  `disponibilidade` tinyint DEFAULT NULL,
  `categoria` varchar(10) NOT NULL,
  `custoDiario` double DEFAULT NULL,
  `mediaConsumo` double DEFAULT NULL,
  `numMaxPassag` int NOT NULL,
  `tamBagageiro` double NOT NULL,
  `tipoCambio` varchar(11) NOT NULL,
  `arCondicionado` tinyint NOT NULL,
  `airBag` tinyint NOT NULL,
  `freioABS` varchar(45) NOT NULL,
  `dvd` tinyint NOT NULL,
  PRIMARY KEY (`idVeiculos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `alugados` (
  `nomeCliente` varchar(50) NOT NULL,
  `idClientes` int NOT NULL,
  `idVeiculos` int NOT NULL,
  `diasAlugados` int DEFAULT NULL,
  `cpf` varchar(11) NOT NULL,
  `categoria` varchar(10) NOT NULL,
  `custoDiario` double NOT NULL,
  `gps` tinyint NOT NULL,
  `assentosCriancas` tinyint NOT NULL,
  `seguroCompleto` tinyint NOT NULL,
  `localRetirada` varchar(50) NOT NULL,
  `localDevolucao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;