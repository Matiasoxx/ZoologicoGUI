CREATE DATABASE  IF NOT EXISTS `animales` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `animales`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: animales
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anfibios`
--

DROP TABLE IF EXISTS `anfibios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anfibios` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Piel` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anfibios`
--

LOCK TABLES `anfibios` WRITE;
/*!40000 ALTER TABLE `anfibios` DISABLE KEYS */;
INSERT INTO `anfibios` VALUES ('Sapo común',0.11,'marrón','no'),('Sapo gigante',1.01,'marrón','no'),('Salamandra',0.21,'negro','no'),('Tritón',0.06,'verde','no'),('Rana venenosa',0.022,'variado','si'),('Rana de Nueva Zelanda',0.11,'verde','si'),('Rana de Seychelles',0.06,'verde','si'),('Rana arborícola',0.033,'verde','si'),('Rana flecha azul',0.011,'azul','si'),('Axolotl o ajolote',0.21,'gris','no'),('Cecilia',0.55,'marrón','si'),('Pigmeos Salamandra pie plano',0.06,'marrón','si'),('Jalapa tritón falso',0.033,'verde','si'),('Rana arborícola',0.033,'verde','si'),('Pigmeos Salamandra pie plano',0.06,'marrón','no');
/*!40000 ALTER TABLE `anfibios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animales_generales`
--

DROP TABLE IF EXISTS `animales_generales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animales_generales` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Animal` varchar(45) DEFAULT NULL,
  `Tipo` varchar(45) DEFAULT NULL,
  `Grupo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animales_generales`
--

LOCK TABLES `animales_generales` WRITE;
/*!40000 ALTER TABLE `animales_generales` DISABLE KEYS */;
INSERT INTO `animales_generales` VALUES (1,'Alfonsino','vertebrado','Peces'),(2,'Anchoveta','vertebrado','Peces'),(3,'Bacalao de profundidad','vertebrado','Peces'),(4,'Besugo','vertebrado','Peces'),(5,'Caballa','vertebrado','Peces'),(6,'Congrio dorado','vertebrado','Peces'),(7,'Congrio negro','vertebrado','Peces'),(8,'Jurel','vertebrado','Peces'),(9,'Lenguado','vertebrado','Peces'),(10,'Merluza común','vertebrado','Peces'),(11,'Merluza de cola','vertebrado','Peces'),(12,'Merluza de tres aletas','vertebrado','Peces'),(13,'Merluza del sur','vertebrado','Peces'),(14,'Orange roughy','vertebrado','Peces'),(15,'Pejegallo','vertebrado','Peces'),(16,'pez espada','vertebrado','Peces'),(17,'Puye','vertebrado','Peces'),(18,'Raya volantín','vertebrado','Peces'),(19,'Reineta','vertebrado','Peces'),(20,'Salmon coho','vertebrado','Peces'),(21,'Salmón del atlántico','vertebrado','Peces'),(22,'Sardina austral','vertebrado','Peces'),(23,'Sardina común','vertebrado','Peces'),(24,'Sardina española','vertebrado','Peces'),(25,'Tortugas','vertebrado','Reptiles'),(26,'Tuátara','vertebrado','Reptiles'),(27,'Anfisbenio','vertebrado','Reptiles'),(28,'Iguana','vertebrado','Reptiles'),(29,'Anolis','vertebrado','Reptiles'),(30,'Lagartija','vertebrado','Reptiles'),(31,'Chucuala','vertebrado','Reptiles'),(32,'Agama','vertebrado','Reptiles'),(33,'Dragón de Komodo','vertebrado','Reptiles'),(34,'Geco','vertebrado','Reptiles'),(35,'Camaleón','vertebrado','Reptiles'),(36,'Salamanquesa','vertebrado','Reptiles'),(37,'Cocodrilos','vertebrado','Reptiles'),(38,'Escinco','vertebrado','Reptiles'),(39,'Eslizón','vertebrado','Reptiles'),(40,'Teyú','vertebrado','Reptiles'),(41,'Lagarto','vertebrado','Reptiles'),(42,'Calango verde','vertebrado','Reptiles'),(43,'Monstruo de Gila','vertebrado','Reptiles'),(44,'Serpientes','vertebrado','Reptiles'),(45,'Ganso','vertebrado','Aves'),(46,'Urraca','vertebrado','Aves'),(47,'Cóndor','vertebrado','Aves'),(48,'Lechuza','vertebrado','Aves'),(49,'Golondrina','vertebrado','Aves'),(50,'Loro','vertebrado','Aves'),(51,'Koel','vertebrado','Aves'),(52,'Azulejo','vertebrado','Aves'),(53,'Secretario','vertebrado','Aves'),(54,'Garza','vertebrado','Aves'),(55,'Canario','vertebrado','Aves'),(56,'Cisne','vertebrado','Aves'),(57,'Quebrantahuesos','vertebrado','Aves'),(58,'Frailecillo','vertebrado','Aves'),(59,'Albatros','vertebrado','Aves'),(60,'Herrerillo','vertebrado','Aves'),(61,'Carpintero','vertebrado','Aves'),(62,'Pavo real','vertebrado','Aves'),(63,'Martín pescador','vertebrado','Aves'),(64,'Tucán','vertebrado','Aves'),(65,'Halcones','vertebrado','Aves'),(66,'Pinzón','vertebrado','Aves'),(67,'Cuervo','vertebrado','Aves'),(68,'Vencejo','vertebrado','Aves'),(69,'Flamenco','vertebrado','Aves'),(70,'ChotacabrasCárabo','vertebrado','Aves'),(71,'Guacamaya','vertebrado','Aves'),(72,'Jilguero','vertebrado','Aves'),(73,'Pingüino','vertebrado','Aves'),(74,'Gallina','vertebrado','Aves'),(75,'Quetzal','vertebrado','Aves'),(76,'Búho','vertebrado','Aves'),(77,'Avestruz','vertebrado','Aves'),(78,'Aguilucho','vertebrado','Aves'),(79,'Ñandú','vertebrado','Aves'),(80,'Perico','vertebrado','Aves'),(81,'Mosquitero','vertebrado','Aves'),(82,'Paloma','vertebrado','Aves'),(83,'León','vertebrado','Mamíferos'),(84,'Elefante','vertebrado','Mamíferos'),(85,'Ballena','vertebrado','Mamíferos'),(86,'Delfín','vertebrado','Mamíferos'),(87,'Ratón','vertebrado','Mamíferos'),(88,'Caballo','vertebrado','Mamíferos'),(89,'Perro','vertebrado','Mamíferos'),(90,'Lobo','vertebrado','Mamíferos'),(91,'Vaca','vertebrado','Mamíferos'),(92,'Oso','vertebrado','Mamíferos'),(93,'Conejo','vertebrado','Mamíferos'),(94,'Gorila','vertebrado','Mamíferos'),(95,'Chimpancé','vertebrado','Mamíferos'),(96,'Jirafa','vertebrado','Mamíferos'),(97,'Hipopótamo','vertebrado','Mamíferos'),(98,'Canguro','vertebrado','Mamíferos'),(99,'Rinoceronte','vertebrado','Mamíferos'),(100,'Oveja','vertebrado','Mamíferos'),(101,'Cerdo','vertebrado','Mamíferos'),(102,'Sapo común','vertebrado','Anfibios'),(103,'Sapo gigante','vertebrado','Anfibios'),(104,'Salamandra','vertebrado','Anfibios'),(105,'Tritón','vertebrado','Anfibios'),(106,'Rana venenosa','vertebrado','Anfibios'),(107,'Rana de Nueva Zelanda','vertebrado','Anfibios'),(108,'Rana de Seychelles','vertebrado','Anfibios'),(109,'Rana arborícola','vertebrado','Anfibios'),(110,'Rana flecha azul','vertebrado','Anfibios'),(111,'Axolotl o ajolote (salamandra mexicana)','vertebrado','Anfibios'),(112,'Cecilia','vertebrado','Anfibios'),(113,'Pigmeos Salamandra pie plano','vertebrado','Anfibios'),(114,'Jalapa tritón falso','vertebrado','Anfibios'),(115,'Insectos','invertebrado','Artrópodos'),(116,'Arácnidos','invertebrado','Artrópodos'),(117,'Miriápodos','invertebrado','Artrópodos'),(118,'Crustáceos','invertebrado','Artrópodos'),(119,'Cefalópodos','invertebrado','Moluscos'),(120,'Bivalvos','invertebrado','Moluscos'),(121,'Gasterópodos','invertebrado','Moluscos'),(122,'Estrella de Mar','invertebrado','Equinodermos'),(123,'Erizo','invertebrado','Equinodermos'),(124,'Anélidos','invertebrado','Gusanos'),(125,'Nematodos','invertebrado','Gusanos'),(126,'Platelmintos','invertebrado','Gusanos'),(127,'Esponjas','invertebrado','Poríferos'),(128,'Medusas','invertebrado','Celentéreos'),(129,'Pólipos','invertebrado','Celentéreos');
/*!40000 ALTER TABLE `animales_generales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artrópodos`
--

DROP TABLE IF EXISTS `artrópodos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artrópodos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `CantidadParesPatas` int NOT NULL,
  `Antenas` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artrópodos`
--

LOCK TABLES `artrópodos` WRITE;
/*!40000 ALTER TABLE `artrópodos` DISABLE KEYS */;
INSERT INTO `artrópodos` VALUES ('Insectos',2,'verde',5,1),('Arácnidos',3,'marrón',5,1),('Miriápodos',2,'gris',5,1),('Crustáceos',5,'rojo',5,1),('Insectos',2,'verde',5,1),('Arácnidos',3,'marrón',5,0),('Miriápodos',2,'gris',5,1),('Crustáceos',5,'rojo',5,1),('Arácnidos',3,'marrón',5,0),('Miriápodos',2,'gris',5,1),('Crustáceos',5,'rojo',5,1),('Insectos',2,'verde',5,1),('Insectos',2,'verde',5,1),('Arácnidos',3,'marrón',5,0),('Miriápodos',2,'gris',5,1),('Arácnidos',3,'marrón',5,0),('Crustáceos',5,'rojo',5,1),('Insectos',2,'verde',5,1),('Miriápodos',2,'gris',5,1);
/*!40000 ALTER TABLE `artrópodos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aves`
--

DROP TABLE IF EXISTS `aves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aves` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Cantidad_alas` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aves`
--

LOCK TABLES `aves` WRITE;
/*!40000 ALTER TABLE `aves` DISABLE KEYS */;
INSERT INTO `aves` VALUES ('Ñandú',3000,'Marrón','2'),('Canario',1.5,'Amarillo','2'),('Ganso',7,'blanco','2'),('Urraca',0.26,'negro','2'),('Cóndor',13,'negro','2'),('Lechuza',1.1,'marrón','2'),('Golondrina',0.022,'marrón','2'),('Loro',0.55,'multicolor','2'),('Koel',0.16,'negro','2'),('Azulejo',0.027,'azul','2'),('Secretario',5,'gris','2'),('Garza',2.2,'blanco','2'),('Canario',0.022,'amarillo','2'),('Cisne',16,'blanco','2'),('Quebrantahuesos',8,'negro','2'),('Frailecillo',0.42,'negro','2'),('Albatros',11,'blanco','2'),('Herrerillo',0.011,'azul','2'),('Carpintero',0.22,'negro','2'),('Pavo real',7,'multicolor','2'),('Martín pescador',0.11,'azul','2'),('Tucán',0.55,'negro','2'),('Halcones',20,'negro','2'),('Pinzón',0.017,'marrón','2'),('Cuervo',1.1,'negro','2'),('Vencejo',0.055,'negro','2'),('Flamenco',3.2,'rosa','2'),('Chotacabras',0.11,'marrón','2'),('Cárabo',0.55,'marrón','2'),('Guacamaya',1.1,'rojo','2'),('Jilguero',0.022,'amarillo','2'),('Pingüino',22,'negro','2'),('Gallina',2.2,'varios colores','2'),('Quetzal',0.26,'verde','2'),('Búho',1.1,'marrón','2'),('Avestruz',110,'negro','2'),('Aguilucho',1.1,'marrón','2'),('Ñandú',28,'gris','2'),('Vencejo',0,'Negro','2'),('Vencejo',0,'Negro','2'),('Vencejo',0,'Negro','2');
/*!40000 ALTER TABLE `aves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `celentéreos`
--

DROP TABLE IF EXISTS `celentéreos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `celentéreos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Tentaculos` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `celentéreos`
--

LOCK TABLES `celentéreos` WRITE;
/*!40000 ALTER TABLE `celentéreos` DISABLE KEYS */;
INSERT INTO `celentéreos` VALUES ('Medusas',50,'blanco','Tentaculos'),('Pólipos',1,'rojo','Sin Tentaculos'),('Medusas',20,'blanco','Tentaculos'),('Medusas',30,'blanco','Tentaculos'),('Pólipos',1,'rojo','Sin Tentaculos'),('Medusas',10,'blanco','Tentaculos'),('Medusas',50,'blanco','Tentaculos'),('Pólipos',1,'rojo','Sin Tentaculos'),('Pólipos',1,'rojo','Sin Tentaculos'),('Medusas',50,'blanco','Tentaculos'),('Pólipos',1,'rojo','Sin Tentaculos'),('Medusas',29,'blanco','Tentaculos'),('Medusas',12,'blanco','Tentaculos'),('Medusas',50,'blanco','Tentaculos'),('Pólipos',1,'rojo','Sin Tentaculos');
/*!40000 ALTER TABLE `celentéreos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equinodermos`
--

DROP TABLE IF EXISTS `equinodermos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equinodermos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Tipo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equinodermos`
--

LOCK TABLES `equinodermos` WRITE;
/*!40000 ALTER TABLE `equinodermos` DISABLE KEYS */;
INSERT INTO `equinodermos` VALUES ('Estrella de mar',5,'rojo','estrella'),('Erizo',2,'negro','erizo'),('Estrella de mar',5,'rojo','estrella'),('Erizo',2,'negro','erizo'),('Erizo',2,'amarillo','erizo'),('Estrella de mar',2,'marrón','estrella'),('Erizo',2,'naranjo','erizo'),('Estrella de mar',3,'rojo','estrella'),('Estrella de mar',1,'verde','estrella'),('Erizo',2,'amarillo','erizo'),('Erizo',2,'marrón','erizo'),('Estrella de mar',4,'rojo','estrella'),('Estrella de mar',3,'azul','estrella'),('Erizo',1,'negro','erizo'),('Estrella de mar',2,'amarillo','estrella'),('Erizo',2,'blanco','erizo'),('Estrella de mar',5,'verde','estrella');
/*!40000 ALTER TABLE `equinodermos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gusanos`
--

DROP TABLE IF EXISTS `gusanos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gusanos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Tipo_cuerpo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gusanos`
--

LOCK TABLES `gusanos` WRITE;
/*!40000 ALTER TABLE `gusanos` DISABLE KEYS */;
INSERT INTO `gusanos` VALUES ('Anélidos',1,'rosado','segmentado'),('Nematodos',0.0001,'gris ','cilíndricos'),('Platelmintos',0.0001,'negro','aplanados'),('Anélidos',1,'rosado','segmentado'),('Nematodos',2,'gris ','cilíndricos'),('Platelmintos',1,'negro','aplanados'),('Nematodos',2,'marrón ','cilíndricos'),('Anélidos',3,'rojo','segmentado'),('Nematodos',2,'negro ','cilíndricos'),('Platelmintos',1,'blanco','aplanados'),('Anélidos',2,'blanco','segmentado'),('Anélidos',1,'naranjo','segmentado'),('Platelmintos',2,'gris','aplanados'),('Nematodos',2,'marrón ','cilíndricos'),('Anélidos',1,'rosa','segmentado'),('Nematodos',1,'negro ','cilíndricos'),('Platelmintos',2,'rojo','aplanados'),('Nematodos',1,'marrón ','cilíndricos');
/*!40000 ALTER TABLE `gusanos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mamíferos`
--

DROP TABLE IF EXISTS `mamíferos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mamíferos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Cantidad_patas` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mamíferos`
--

LOCK TABLES `mamíferos` WRITE;
/*!40000 ALTER TABLE `mamíferos` DISABLE KEYS */;
INSERT INTO `mamíferos` VALUES ('León',210,'amarillo','4'),('Elefante',5010,'gris','4'),('Ballena',80010,'gris','0'),('Delfín',160,'gris','0'),('Ratón',0.022,'gris','4'),('Caballo',510,'blanco','4'),('Perro',22,'variable','4'),('Lobo',33,'gris','4'),('Vaca',610,'negro','4'),('Oso',310,'negro','4'),('Conejo',2.2,'blanco','4'),('Gorila',210,'negro','2'),('Chimpancé',55,'negro','2'),('Jirafa',1610,'amarillo','4'),('Hipopótamo',2020,'gris','4'),('Canguro',55,'marrón','2'),('Rinoceronte',2020,'gris','4'),('Oveja',55,'blanco','4'),('Cerdo',160,'rosa','4');
/*!40000 ALTER TABLE `mamíferos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moluscos`
--

DROP TABLE IF EXISTS `moluscos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moluscos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Tipo_concha` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moluscos`
--

LOCK TABLES `moluscos` WRITE;
/*!40000 ALTER TABLE `moluscos` DISABLE KEYS */;
INSERT INTO `moluscos` VALUES ('Gasterópodos',2,'gris','valva'),('Gasterópodos',2,'blanco','valva'),('Gasterópodos',2,'gris perla','valva'),('Gasterópodos',2,'gris','valva'),('Bivalvos',120,'marrón','espiral'),('Bivalvos',50,'blanco','valva'),('Bivalvos',30,'marrón','espiral'),('Bivalvos',35,'marrón','espiral'),('Cefalópodos',7,'blanco','valva'),('Cefalópodos',4,'gris','valva'),('Cefalópodos',2,'blanco','valva'),('Cefalópodos',3.5,'negro','valva'),('Gasterópodos',2,'gris','valva'),('Gasterópodos',2,'blanco','valva'),('Gasterópodos',2,'gris perla','valva'),('Gasterópodos',2,'gris','valva'),('Bivalvos',120,'marrón','espiral'),('Bivalvos',50,'blanco','valva'),('Bivalvos',30,'marrón','espiral'),('Bivalvos',35,'marrón','espiral'),('Cefalópodos',7,'blanco','valva'),('Cefalópodos',4,'gris','valva'),('Cefalópodos',2,'blanco','valva'),('Cefalópodos',3.5,'negro','valva'),('Bivalvos',40,'marrón','espiral'),('Cefalópodos',3.5,'negro','valva'),('Bivalvos',60,'gris','espiral');
/*!40000 ALTER TABLE `moluscos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peces`
--

DROP TABLE IF EXISTS `peces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peces` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Cantidad_aletas` varchar(45) NOT NULL,
  `Escamas` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peces`
--

LOCK TABLES `peces` WRITE;
/*!40000 ALTER TABLE `peces` DISABLE KEYS */;
INSERT INTO `peces` VALUES ('Merluza del sur',50,'Plateado','7',1),('Alfonsino',5,'rojo','3',1),('Anchoveta',0.04,'plateado','3',1),('Bacalao de profundidad',15,'marrón','3',1),('Besugo',1.1,'rojo','3',1),('Caballa',1.1,'azul','2',1),('Congrio dorado',20,'marrón','2',1),('Congrio negro',9,'negro','1',1),('Jurel',0.51,'plateado','2',1),('Lenguado',0.71,'marrón ','2',1),('Merluza común',3.5,'plateado','3',1),('Merluza de cola',3,'gris','3',1),('Merluza de tres aletas',2.2,'plateado','3',1),('Merluza del sur',4.5,'gris','3',1),('Orange roughy',0.51,'naranja','2',1),('Pejegallo',0.31,'plateado','2',1),('Pez espada',210,'negro','2',1),('Puye',0.16,'marrón','1',1),('Raya volantín',1.6,'gris','0',1),('Reineta',0.71,'plateado','2',1),('Salmón coho',15,'plateado','2',1),('Salmón del Atlántico',17,'plateado','2',1),('Sardina austral',0.06,'plateado','2',1),('Sardina común',0.04,'plateado','2',1),('Sardina española',0.05,'plateado','2',1);
/*!40000 ALTER TABLE `peces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poríferos`
--

DROP TABLE IF EXISTS `poríferos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poríferos` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poríferos`
--

LOCK TABLES `poríferos` WRITE;
/*!40000 ALTER TABLE `poríferos` DISABLE KEYS */;
INSERT INTO `poríferos` VALUES ('Esponjas',1,'amarillo'),('Esponjas',3,'verde'),('Esponjas',5,'marrón'),('Esponjas',2,'amarillo'),('Esponjas',1,'verde'),('Esponjas',4,'rojo'),('Esponjas',2,'verde'),('Esponjas',3,'amarillo'),('Esponjas',1,'naranjo'),('Esponjas',5,'rosado'),('Esponjas',5,'amarillo'),('Esponjas',2,'azul'),('Esponjas',1,'rojo'),('Esponjas',3,'azul'),('Esponjas',4,'naranjo');
/*!40000 ALTER TABLE `poríferos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reptiles`
--

DROP TABLE IF EXISTS `reptiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reptiles` (
  `Nombre` varchar(45) NOT NULL,
  `Peso` double NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Habitad` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reptiles`
--

LOCK TABLES `reptiles` WRITE;
/*!40000 ALTER TABLE `reptiles` DISABLE KEYS */;
INSERT INTO `reptiles` VALUES ('Geco',0.1,'verde','tierra'),('Chucuala',0.2,'negro','tierra'),('Tortugas',6,'verde','ambos'),('Tuátara',0.21,'gris','Tierra'),('Anfisbenio',0.06,'marrón','Tierra'),('Iguana',1.1,'verde','Tierra'),('Anolis',0.022,'verde','Tierra'),('Lagartija',0.011,'marrón','Tierra'),('Chucuala',0.11,'negro','Tierra'),('Agama',0.055,'marrón','Tierra'),('Dragón de Komodo',75,'gris','Tierra'),('Geco',0.033,'verde','Tierra'),('Camaleón',0.055,'verde','Tierra'),('Salamanquesa',0.017,'marrón','Tierra'),('Cocodrilos',550,'verde','ambos'),('Escinco',0.11,'marrón','Tierra'),('Eslizón',0.011,'gris','Tierra'),('Teyú',0.33,'marrón','Tierra'),('Lagarto',0.55,'verde','Tierra'),('Calango verde',0.033,'verde','Tierra'),('Monstruo de Gila',0.55,'marrón','Tierra'),('Serpientes',50,'verde','ambos');
/*!40000 ALTER TABLE `reptiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'animales'
--

--
-- Dumping routines for database 'animales'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05 21:00:46
