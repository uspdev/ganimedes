-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: ganimedes
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ganimedes`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ganimedes` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ganimedes`;

--
-- Table structure for table `ALUNO`
--

DROP TABLE IF EXISTS `ALUNO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALUNO` (
  `codpes` int(11) NOT NULL,
  `nompes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codpes`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ALUNO`
--

LOCK TABLES `ALUNO` WRITE;
/*!40000 ALTER TABLE `ALUNO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ALUNO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ANUNCIO`
--

DROP TABLE IF EXISTS `ANUNCIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ANUNCIO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aprovado` bit(1) NOT NULL,
  `aprovadoPor` bit(1) NOT NULL,
  `areaAtuacao` varchar(255) NOT NULL,
  `benofr` text,
  `cartaz` longblob,
  `descricaoEmpresa` text,
  `desvag` text,
  `dtaAprovacao` datetime DEFAULT NULL,
  `dtaCriacao` datetime DEFAULT NULL,
  `dtafimdiv` date DEFAULT NULL,
  `dtainidiv` date DEFAULT NULL,
  `dtarepchk` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `emailInscricao` varchar(255) DEFAULT NULL,
  `horsem` int(11) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `loctrb` varchar(255) NOT NULL,
  `logotipo` longblob,
  `nomeEmpresa` varchar(255) NOT NULL,
  `nompes` varchar(255) NOT NULL,
  `numtel` varchar(255) NOT NULL,
  `numtelInscricao` varchar(255) DEFAULT NULL,
  `perfilCandidato` text,
  `regimeTrabalho` int(11) DEFAULT NULL,
  `repchk` bit(1) NOT NULL,
  `repcod` bit(1) NOT NULL,
  `reppwd` bit(1) NOT NULL,
  `salmes` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `titvag` varchar(255) NOT NULL,
  `urlweb` varchar(255) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpb88jg5al410voxchneljn6re` (`usuario_id`),
  CONSTRAINT `FKpb88jg5al410voxchneljn6re` FOREIGN KEY (`usuario_id`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ANUNCIO`
--

LOCK TABLES `ANUNCIO` WRITE;
/*!40000 ALTER TABLE `ANUNCIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ANUNCIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ANUNCIO_CURSO`
--

DROP TABLE IF EXISTS `ANUNCIO_CURSO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ANUNCIO_CURSO` (
  `Anuncio_id` int(11) NOT NULL,
  `cursos_id` int(11) NOT NULL,
  KEY `FKdddhfu6xofc73adbew4l50hwq` (`cursos_id`),
  KEY `FKexoavuvpw2o2ql8bu1xgl5m4l` (`Anuncio_id`),
  CONSTRAINT `FKdddhfu6xofc73adbew4l50hwq` FOREIGN KEY (`cursos_id`) REFERENCES `CURSO` (`id`),
  CONSTRAINT `FKexoavuvpw2o2ql8bu1xgl5m4l` FOREIGN KEY (`Anuncio_id`) REFERENCES `ANUNCIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ANUNCIO_CURSO`
--

LOCK TABLES `ANUNCIO_CURSO` WRITE;
/*!40000 ALTER TABLE `ANUNCIO_CURSO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ANUNCIO_CURSO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONFIGURACAO`
--

DROP TABLE IF EXISTS `CONFIGURACAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONFIGURACAO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONFIGURACAO`
--

LOCK TABLES `CONFIGURACAO` WRITE;
/*!40000 ALTER TABLE `CONFIGURACAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `CONFIGURACAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTATO`
--

DROP TABLE IF EXISTS `CONTATO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTATO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `fone` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKolv8isgqfsus5h4e698ph1qf4` (`empresa_id`),
  CONSTRAINT `FKolv8isgqfsus5h4e698ph1qf4` FOREIGN KEY (`empresa_id`) REFERENCES `EMPRESA` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTATO`
--

LOCK TABLES `CONTATO` WRITE;
/*!40000 ALTER TABLE `CONTATO` DISABLE KEYS */;
/*!40000 ALTER TABLE `CONTATO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONVENIO`
--

DROP TABLE IF EXISTS `CONVENIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONVENIO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtafim` datetime NOT NULL,
  `dtaini` datetime NOT NULL,
  `numpro` varchar(255) NOT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8pr69ep10wox1p452lrhrbamk` (`empresa_id`),
  CONSTRAINT `FK8pr69ep10wox1p452lrhrbamk` FOREIGN KEY (`empresa_id`) REFERENCES `EMPRESA` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONVENIO`
--

LOCK TABLES `CONVENIO` WRITE;
/*!40000 ALTER TABLE `CONVENIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `CONVENIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CURSO`
--

DROP TABLE IF EXISTS `CURSO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CURSO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codcur` int(11) NOT NULL,
  `nivel` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CURSO`
--

LOCK TABLES `CURSO` WRITE;
/*!40000 ALTER TABLE `CURSO` DISABLE KEYS */;
/*!40000 ALTER TABLE `CURSO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CURSO_ANUNCIO`
--

DROP TABLE IF EXISTS `CURSO_ANUNCIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CURSO_ANUNCIO` (
  `Curso_id` int(11) NOT NULL,
  `vagas_id` int(11) NOT NULL,
  KEY `FK2oi4rgg3xso7xofakhvic7s9d` (`vagas_id`),
  KEY `FK9cpxqljueriphbnhcd19kyyld` (`Curso_id`),
  CONSTRAINT `FK2oi4rgg3xso7xofakhvic7s9d` FOREIGN KEY (`vagas_id`) REFERENCES `ANUNCIO` (`id`),
  CONSTRAINT `FK9cpxqljueriphbnhcd19kyyld` FOREIGN KEY (`Curso_id`) REFERENCES `CURSO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CURSO_ANUNCIO`
--

LOCK TABLES `CURSO_ANUNCIO` WRITE;
/*!40000 ALTER TABLE `CURSO_ANUNCIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `CURSO_ANUNCIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DOCUMENTO`
--

DROP TABLE IF EXISTS `DOCUMENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DOCUMENTO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cgahorsem` int(11) DEFAULT NULL,
  `dtaent` datetime NOT NULL,
  `dtafim` datetime NOT NULL,
  `dtaini` datetime NOT NULL,
  `dtaultand` datetime DEFAULT NULL,
  `statusAndamentoDoc` int(11) DEFAULT NULL,
  `statusDoc` int(11) NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  `estagio_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnwsgx2mglfo05y7h50axks7yj` (`estagio_id`),
  CONSTRAINT `FKnwsgx2mglfo05y7h50axks7yj` FOREIGN KEY (`estagio_id`) REFERENCES `ESTAGIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DOCUMENTO`
--

LOCK TABLES `DOCUMENTO` WRITE;
/*!40000 ALTER TABLE `DOCUMENTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `DOCUMENTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPRESA`
--

DROP TABLE IF EXISTS `EMPRESA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPRESA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d1fdliukk028cm02nb8dm4bnl` (`cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPRESA`
--

LOCK TABLES `EMPRESA` WRITE;
/*!40000 ALTER TABLE `EMPRESA` DISABLE KEYS */;
/*!40000 ALTER TABLE `EMPRESA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ESQUECIMINHASENHA`
--

DROP TABLE IF EXISTS `ESQUECIMINHASENHA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ESQUECIMINHASENHA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigoURL` varchar(255) DEFAULT NULL,
  `validade` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t9jodo6lem6vxpa3n932y9qsi` (`codigoURL`),
  KEY `FK354laohc2nx426xa7au34b0af` (`usuario_id`),
  CONSTRAINT `FK354laohc2nx426xa7au34b0af` FOREIGN KEY (`usuario_id`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ESQUECIMINHASENHA`
--

LOCK TABLES `ESQUECIMINHASENHA` WRITE;
/*!40000 ALTER TABLE `ESQUECIMINHASENHA` DISABLE KEYS */;
/*!40000 ALTER TABLE `ESQUECIMINHASENHA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ESTAGIO`
--

DROP TABLE IF EXISTS `ESTAGIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ESTAGIO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aluno_codpes` int(11) DEFAULT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6wv9fbn40ok13qy4qdhnystc6` (`aluno_codpes`),
  KEY `FKha9paf9e3xd3tjfo3bk4w30td` (`empresa_id`),
  CONSTRAINT `FK6wv9fbn40ok13qy4qdhnystc6` FOREIGN KEY (`aluno_codpes`) REFERENCES `ALUNO` (`codpes`),
  CONSTRAINT `FKha9paf9e3xd3tjfo3bk4w30td` FOREIGN KEY (`empresa_id`) REFERENCES `EMPRESA` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ESTAGIO`
--

LOCK TABLES `ESTAGIO` WRITE;
/*!40000 ALTER TABLE `ESTAGIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ESTAGIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GRUPO`
--

DROP TABLE IF EXISTS `GRUPO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GRUPO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GRUPO`
--

LOCK TABLES `GRUPO` WRITE;
/*!40000 ALTER TABLE `GRUPO` DISABLE KEYS */;
/*!40000 ALTER TABLE `GRUPO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JORNADA`
--

DROP TABLE IF EXISTS `JORNADA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JORNADA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtaini` datetime NOT NULL,
  `jornada` int(11) DEFAULT NULL,
  `estagio_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9rbf8eew3se50reesk3iw52ee` (`estagio_id`),
  CONSTRAINT `FK9rbf8eew3se50reesk3iw52ee` FOREIGN KEY (`estagio_id`) REFERENCES `ESTAGIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JORNADA`
--

LOCK TABLES `JORNADA` WRITE;
/*!40000 ALTER TABLE `JORNADA` DISABLE KEYS */;
/*!40000 ALTER TABLE `JORNADA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOG`
--

DROP TABLE IF EXISTS `LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOG` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `operacao` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfco04r37dk4tq224k0dble5tq` (`usuario`),
  CONSTRAINT `FKfco04r37dk4tq224k0dble5tq` FOREIGN KEY (`usuario`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOG`
--

LOCK TABLES `LOG` WRITE;
/*!40000 ALTER TABLE `LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MENSAGEM`
--

DROP TABLE IF EXISTS `MENSAGEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MENSAGEM` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assunto` varchar(255) DEFAULT NULL,
  `criacao` datetime DEFAULT NULL,
  `de` varchar(255) DEFAULT NULL,
  `envio` datetime DEFAULT NULL,
  `mensagem` longtext,
  `para` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MENSAGEM`
--

LOCK TABLES `MENSAGEM` WRITE;
/*!40000 ALTER TABLE `MENSAGEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `MENSAGEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTIFICACAO`
--

DROP TABLE IF EXISTS `NOTIFICACAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NOTIFICACAO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) NOT NULL,
  `data` datetime NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  `estagio_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsbxa8x0kogi06x4u4oeohc658` (`estagio_id`),
  CONSTRAINT `FKsbxa8x0kogi06x4u4oeohc658` FOREIGN KEY (`estagio_id`) REFERENCES `ESTAGIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTIFICACAO`
--

LOCK TABLES `NOTIFICACAO` WRITE;
/*!40000 ALTER TABLE `NOTIFICACAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `NOTIFICACAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAPEL`
--

DROP TABLE IF EXISTS `PAPEL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAPEL` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAPEL`
--

LOCK TABLES `PAPEL` WRITE;
/*!40000 ALTER TABLE `PAPEL` DISABLE KEYS */;
/*!40000 ALTER TABLE `PAPEL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REMUNERACAO`
--

DROP TABLE IF EXISTS `REMUNERACAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REMUNERACAO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtaini` datetime DEFAULT NULL,
  `remuneracao` double DEFAULT NULL,
  `estagio_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9q94xankdchn5cuvv2ruxest9` (`estagio_id`),
  CONSTRAINT `FK9q94xankdchn5cuvv2ruxest9` FOREIGN KEY (`estagio_id`) REFERENCES `ESTAGIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REMUNERACAO`
--

LOCK TABLES `REMUNERACAO` WRITE;
/*!40000 ALTER TABLE `REMUNERACAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `REMUNERACAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUPERVISOR`
--

DROP TABLE IF EXISTS `SUPERVISOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUPERVISOR` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtaini` datetime NOT NULL,
  `nome` varchar(255) NOT NULL,
  `estagio_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjao5v00crgtxkqyq54fs6awb5` (`estagio_id`),
  CONSTRAINT `FKjao5v00crgtxkqyq54fs6awb5` FOREIGN KEY (`estagio_id`) REFERENCES `ESTAGIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUPERVISOR`
--

LOCK TABLES `SUPERVISOR` WRITE;
/*!40000 ALTER TABLE `SUPERVISOR` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUPERVISOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOKEN`
--

DROP TABLE IF EXISTS `TOKEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TOKEN` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codlog` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `validade` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4xdwi34jp01x8envqt99yb01u` (`codlog`),
  UNIQUE KEY `UK_g40yae7iopvtjjs6cstb9hfat` (`token`),
  KEY `FK77d2xhvyiyjwnpirr1k33wjqq` (`usuario_id`),
  CONSTRAINT `FK77d2xhvyiyjwnpirr1k33wjqq` FOREIGN KEY (`usuario_id`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOKEN`
--

LOCK TABLES `TOKEN` WRITE;
/*!40000 ALTER TABLE `TOKEN` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOKEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ativado` bit(1) NOT NULL,
  `codlog` varchar(255) DEFAULT NULL,
  `codpes` int(11) NOT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_cadastro` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `nompes` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9hjgkqhprhekldfwk0ukv7eyb` (`codpes`),
  UNIQUE KEY `UK_5xjxs5v53l7iufhc7yicafs7b` (`email`),
  UNIQUE KEY `UK_mowjmim75ohxf82esair1hm8h` (`codlog`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_ANUNCIO`
--

DROP TABLE IF EXISTS `USUARIO_ANUNCIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_ANUNCIO` (
  `Usuario_id` int(11) NOT NULL,
  `anuncios_id` int(11) NOT NULL,
  UNIQUE KEY `UK_qfo2rfph2akh7qphgu6yfr5t9` (`anuncios_id`),
  KEY `FKre9c0l56d0cygrw58bcx16npk` (`Usuario_id`),
  CONSTRAINT `FK1sic4xxjg2xnyp8td7kdw8fy0` FOREIGN KEY (`anuncios_id`) REFERENCES `ANUNCIO` (`id`),
  CONSTRAINT `FKre9c0l56d0cygrw58bcx16npk` FOREIGN KEY (`Usuario_id`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_ANUNCIO`
--

LOCK TABLES `USUARIO_ANUNCIO` WRITE;
/*!40000 ALTER TABLE `USUARIO_ANUNCIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIO_ANUNCIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_GRUPO`
--

DROP TABLE IF EXISTS `USUARIO_GRUPO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_GRUPO` (
  `usuario_id` int(11) NOT NULL,
  `grupo_id` int(11) NOT NULL,
  KEY `FKa7i6gumw8t15nereb6bodeu3f` (`grupo_id`),
  KEY `FKh4jkenqjkj997xbywvqasayll` (`usuario_id`),
  CONSTRAINT `FKa7i6gumw8t15nereb6bodeu3f` FOREIGN KEY (`grupo_id`) REFERENCES `GRUPO` (`id`),
  CONSTRAINT `FKh4jkenqjkj997xbywvqasayll` FOREIGN KEY (`usuario_id`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_GRUPO`
--

LOCK TABLES `USUARIO_GRUPO` WRITE;
/*!40000 ALTER TABLE `USUARIO_GRUPO` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIO_GRUPO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_PAPEL`
--

DROP TABLE IF EXISTS `USUARIO_PAPEL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_PAPEL` (
  `usuario_id` int(11) NOT NULL,
  `papel_id` int(11) NOT NULL,
  KEY `FKfsmgqm18ynsovtk2g4rfcb3ap` (`papel_id`),
  KEY `FKhq4khauufag4fbn9tim9v9mc3` (`usuario_id`),
  CONSTRAINT `FKfsmgqm18ynsovtk2g4rfcb3ap` FOREIGN KEY (`papel_id`) REFERENCES `PAPEL` (`id`),
  CONSTRAINT `FKhq4khauufag4fbn9tim9v9mc3` FOREIGN KEY (`usuario_id`) REFERENCES `USUARIO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_PAPEL`
--

LOCK TABLES `USUARIO_PAPEL` WRITE;
/*!40000 ALTER TABLE `USUARIO_PAPEL` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIO_PAPEL` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-09 10:57:27