-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: liga
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `arbitro`
--

DROP TABLE IF EXISTS `arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arbitro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `persona` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_arbitro_persona` (`persona`),
  CONSTRAINT `fk_arbitro_persona` FOREIGN KEY (`persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbitro`
--

LOCK TABLES `arbitro` WRITE;
/*!40000 ALTER TABLE `arbitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atributo`
--

DROP TABLE IF EXISTS `atributo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atributo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `posicion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atributo`
--

LOCK TABLES `atributo` WRITE;
/*!40000 ALTER TABLE `atributo` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cambio`
--

DROP TABLE IF EXISTS `cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cambio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hora` time DEFAULT NULL,
  `razon` varchar(200) DEFAULT NULL,
  `saliente` bigint(20) DEFAULT NULL,
  `entrante` bigint(20) DEFAULT NULL,
  `partido` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cambio_saliente` (`saliente`),
  KEY `fk_cambio_entra` (`entrante`),
  KEY `fk_cambio_partido` (`partido`),
  CONSTRAINT `fk_cambio_entra` FOREIGN KEY (`entrante`) REFERENCES `carnet` (`id`),
  CONSTRAINT `fk_cambio_partido` FOREIGN KEY (`partido`) REFERENCES `partido` (`id`),
  CONSTRAINT `fk_cambio_saliente` FOREIGN KEY (`saliente`) REFERENCES `carnet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cambio`
--

LOCK TABLES `cambio` WRITE;
/*!40000 ALTER TABLE `cambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carnet`
--

DROP TABLE IF EXISTS `carnet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carnet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jugador` bigint(20) DEFAULT NULL,
  `equipo` bigint(20) DEFAULT NULL,
  `dorsal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_carnet` (`jugador`),
  KEY `fk_equipo_carnet` (`equipo`),
  CONSTRAINT `fk_equipo_carnet` FOREIGN KEY (`equipo`) REFERENCES `equipo_temporada` (`id`),
  CONSTRAINT `fk_jugador_carnet` FOREIGN KEY (`jugador`) REFERENCES `jugador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carnet`
--

LOCK TABLES `carnet` WRITE;
/*!40000 ALTER TABLE `carnet` DISABLE KEYS */;
INSERT INTO `carnet` VALUES (1,60,1,10),(2,61,1,7),(3,86,3,1),(4,88,3,2),(5,89,3,3),(6,99,3,4),(7,108,3,5),(8,92,4,1),(9,104,3,6),(10,107,4,2),(11,112,3,7),(12,130,2,7),(13,128,3,8),(14,4,4,10),(15,62,2,3),(16,87,3,9),(17,121,5,1),(18,106,3,10),(19,127,5,7),(20,109,3,11),(21,5,4,7),(22,126,5,8),(23,110,3,12),(24,131,5,2),(25,105,3,13),(26,6,4,8),(27,115,3,15),(28,133,5,5),(29,129,5,6),(30,116,3,21),(31,3,4,9),(32,117,3,98),(33,124,5,4),(34,120,5,13),(35,7,4,12),(36,113,3,27),(37,118,3,19),(38,29,6,15),(39,23,6,17),(40,8,4,15),(41,30,6,11),(42,132,11,21),(43,31,6,10),(44,66,7,21),(45,9,4,18),(46,72,7,7),(47,10,4,23),(48,68,7,8),(49,27,6,9),(50,71,7,9),(51,91,6,5),(52,11,4,5),(53,74,7,11),(54,28,6,18),(55,32,6,1),(56,69,7,12),(57,12,4,4),(58,122,6,12),(59,125,7,15),(60,77,4,21),(61,26,6,2),(62,70,7,23),(63,94,4,11),(64,76,7,4),(65,25,6,6),(66,24,6,4),(67,95,4,3),(68,75,7,6),(69,79,6,14),(70,67,7,24),(71,13,8,1),(72,15,8,2),(73,18,8,3),(74,73,7,29),(75,96,4,13),(76,16,8,4),(77,17,8,5),(78,98,4,27),(79,21,8,6),(80,78,8,7),(81,19,8,8),(82,100,4,6),(83,90,8,9),(84,62,1,1),(85,22,8,10),(86,119,8,11),(87,63,1,2),(88,20,8,18),(89,64,1,3),(90,140,5,18),(91,65,1,4),(92,123,1,5),(93,134,9,1),(94,93,10,1),(95,139,10,2),(96,97,10,3),(97,103,10,4),(98,141,5,17),(99,142,5,16),(100,143,5,15),(101,144,5,14),(102,145,2,18),(103,146,2,13),(104,147,5,11),(105,148,2,12),(106,149,5,9),(107,150,2,4),(108,153,2,5),(109,151,5,3),(110,152,5,21),(111,154,5,27),(112,155,2,23),(113,157,9,19),(114,158,2,19),(115,159,2,20),(116,160,9,18),(117,163,9,23),(118,161,11,22),(119,164,11,13),(120,162,2,15),(121,165,2,21),(122,167,9,12),(123,166,11,28),(124,168,11,26),(125,169,9,24),(126,170,11,15),(127,171,9,13),(128,172,11,25),(129,173,2,27),(130,174,9,20),(131,175,2,26),(132,178,9,2),(133,176,2,22),(134,177,10,9),(135,179,10,7),(136,180,10,19),(137,181,2,29),(138,182,9,29),(139,183,10,13),(140,185,10,24),(141,184,9,50),(142,186,2,41),(143,187,2,6);
/*!40000 ALTER TABLE `carnet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `logo` text,
  `lugar` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lugar_equipo` (`lugar`),
  CONSTRAINT `fk_lugar_equipo` FOREIGN KEY (`lugar`) REFERENCES `lugar` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (15,'CD El Pino','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F24Pino.jpg?alt=media&token=b2f7572c-f6ee-4fff-9621-16f4b21eccbf',3),(17,'CD Hernandez','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F12Hernandez.jpg?alt=media&token=0e5ffe52-64a0-4901-9f7b-7202614c73f1',4),(19,'Plancito','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F58Plancito.png?alt=media&token=bd0a63a0-d82a-49a3-a916-8265737a3b30',7),(20,'La Vega','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F54Vega.png?alt=media&token=82bad439-4436-4217-b15e-f1cec1b423c1',6),(22,'Centro FC','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F0Centro.png?alt=media&token=55a3035c-0314-4e64-a427-d4c68ff7a905',2),(23,'Cruzeiro','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F52Cruzeiro.png?alt=media&token=d30e9d34-5bdc-4c7b-bb5f-f3802e57fcad',9),(24,' CD Mesitas','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F57Mesitas.png?alt=media&token=945a2d9a-0c56-4eab-8662-7599cb660db2',5),(25,'San Antonio FC','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F52SanAntonio.png?alt=media&token=0258422f-ccae-42a0-8959-9af4b829e7d8',10),(26,'Santa Cruz','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F39SantaCruz.png?alt=media&token=5da5f5fe-e1f6-4eac-b5a8-5264aab0dbaf',8),(27,'Celis FC','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F36Celis.png?alt=media&token=be8e20f4-312a-40f2-a4ab-6555b88d4f1d',15),(28,'Salitre','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F37Salitre.png?alt=media&token=376f34aa-0d4d-4172-ae37-ae925da1a898',1),(29,'Real Madrid','https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/equipos%2F26descargar.png?alt=media&token=170046ed-218d-418a-ad45-ffad339c4f3e',14);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_temporada`
--

DROP TABLE IF EXISTS `equipo_temporada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo_temporada` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equipo` bigint(20) NOT NULL,
  `temporada` bigint(20) NOT NULL,
  `representante` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_et_equipo` (`equipo`),
  KEY `fk_et_tempo` (`temporada`),
  KEY `fk_et_persona` (`representante`),
  CONSTRAINT `fk_et_equipo` FOREIGN KEY (`equipo`) REFERENCES `equipo` (`id`),
  CONSTRAINT `fk_et_persona` FOREIGN KEY (`representante`) REFERENCES `jugador` (`id`),
  CONSTRAINT `fk_et_tempo` FOREIGN KEY (`temporada`) REFERENCES `temporada` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_temporada`
--

LOCK TABLES `equipo_temporada` WRITE;
/*!40000 ALTER TABLE `equipo_temporada` DISABLE KEYS */;
INSERT INTO `equipo_temporada` VALUES (1,17,1,60),(2,19,1,62),(3,20,1,117),(4,28,1,3),(5,23,1,120),(6,15,1,79),(7,24,1,73),(8,22,1,20),(9,25,1,134),(10,27,1,103),(11,26,1,132),(12,29,1,188);
/*!40000 ALTER TABLE `equipo_temporada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacion`
--

DROP TABLE IF EXISTS `formacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equipo` bigint(20) DEFAULT NULL,
  `jornada` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_forma_et` (`equipo`),
  KEY `fk_forma_jornada` (`jornada`),
  CONSTRAINT `fk_forma_et` FOREIGN KEY (`equipo`) REFERENCES `equipo_temporada` (`id`),
  CONSTRAINT `fk_forma_jornada` FOREIGN KEY (`jornada`) REFERENCES `jornada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacion`
--

LOCK TABLES `formacion` WRITE;
/*!40000 ALTER TABLE `formacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `formacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacion_carnet`
--

DROP TABLE IF EXISTS `formacion_carnet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formacion_carnet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jugador` bigint(20) DEFAULT NULL,
  `formacion` bigint(20) DEFAULT NULL,
  `titular` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fc_jugador` (`jugador`),
  KEY `fk_fc_formacion` (`formacion`),
  CONSTRAINT `fk_fc_formacion` FOREIGN KEY (`formacion`) REFERENCES `formacion` (`id`),
  CONSTRAINT `fk_fc_jugador` FOREIGN KEY (`jugador`) REFERENCES `carnet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacion_carnet`
--

LOCK TABLES `formacion_carnet` WRITE;
/*!40000 ALTER TABLE `formacion_carnet` DISABLE KEYS */;
/*!40000 ALTER TABLE `formacion_carnet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gol`
--

DROP TABLE IF EXISTS `gol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hora` time DEFAULT NULL,
  `tipo` tinyint(1) DEFAULT NULL,
  `forma` varchar(200) DEFAULT NULL,
  `carnet` bigint(20) DEFAULT NULL,
  `partido` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carnet_gol` (`carnet`),
  KEY `fk_partido_gol` (`partido`),
  CONSTRAINT `fk_carnet_gol` FOREIGN KEY (`carnet`) REFERENCES `carnet` (`id`),
  CONSTRAINT `fk_partido_gol` FOREIGN KEY (`partido`) REFERENCES `partido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gol`
--

LOCK TABLES `gol` WRITE;
/*!40000 ALTER TABLE `gol` DISABLE KEYS */;
/*!40000 ALTER TABLE `gol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hora` time NOT NULL,
  `dia` varchar(15) NOT NULL,
  `jerarquia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (1,'08:00:00','Sábado',1),(2,'10:00:00','Sábado',2),(3,'01:00:00','Sábado',3),(4,'03:00:00','Sábado',4),(5,'08:00:00','Domingo',5),(6,'10:00:00','Domingo',6),(7,'01:00:00','Domingo',7),(8,'03:00:00','Domingo',8);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jornada` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `temporada` bigint(20) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jornada_temporada` (`temporada`),
  CONSTRAINT `fk_jornada_temporada` FOREIGN KEY (`temporada`) REFERENCES `temporada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `posicion` varchar(100) DEFAULT NULL,
  `persona` bigint(20) DEFAULT NULL,
  `foto` text,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_persona` (`persona`),
  CONSTRAINT `fk_jugador_persona` FOREIGN KEY (`persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (3,'centrocampista',3,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F55j6.jpg?alt=media&token=f5294707-76cf-4aed-9980-999bea7ec586'),(4,'centrocampista',4,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5images.jpg?alt=media&token=0458c0fa-bdde-4d46-aa0c-2b6d4265c1f2'),(5,'centrocampista',5,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F02.jpg?alt=media&token=c07f1387-57c4-44a0-957b-92fe28158497'),(6,'centrocampista',6,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F333.jpg?alt=media&token=eca362b1-fed9-4a38-81c0-59b1bb8e05d5'),(7,'centrocampista',7,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F584.jpg?alt=media&token=9b6b4689-f370-402d-8980-27c3fe83606e'),(8,'centrocampista',8,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F405.jpg?alt=media&token=1970c571-1d67-41c5-b350-3e30fde5f8af'),(9,'defensa',9,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F146.jpg?alt=media&token=ceb629c6-4f11-4fd8-8dd3-6b774c68032a'),(10,'defensa',10,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F497.png?alt=media&token=5a084823-2fa3-4bbe-a608-0daafffdea82'),(11,'defensa',11,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F148.jpg?alt=media&token=bd1df75a-c72c-4792-be97-2c2777889fd4'),(12,'defensa',12,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F149.jpg?alt=media&token=65d15ae5-cf7c-4033-9318-f0b424e6610a'),(13,'centrocampista',13,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F41descarga%20(3).jpg?alt=media&token=b88d4417-cf83-4fbc-b7a2-76e48d321a76'),(15,'defensa',15,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F10descarga.jpg?alt=media&token=4a11e44d-f9e3-4627-bef3-47872d8760fe'),(16,'defensa',16,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F34images%20(1).jpg?alt=media&token=c71fe608-4c26-4de1-af26-08e71213981f'),(17,'defensa',17,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5images%20(2).jpg?alt=media&token=83eab18c-8fbf-421f-a39b-4cd421f9afaa'),(18,'defensa',18,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F47images%20(4).jpg?alt=media&token=d11144b6-f0fa-42de-90b6-13f318b5a5b8'),(19,'defensa',19,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F11images%20(5).jpg?alt=media&token=7b3a8a35-879e-4a7a-92cf-567f6a7c603d'),(20,'defensa',20,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F46images%20(6).jpg?alt=media&token=61115073-18f8-4b5c-a6d0-712782a2173b'),(21,'defensa',21,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F12images%20(7).jpg?alt=media&token=65d31e0e-1501-46df-a238-d490c49458bc'),(22,'defensa',22,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F38images%20(8).jpg?alt=media&token=9e738dfc-24ad-43af-86ee-19a24d1ac90c'),(23,'defensa',23,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F45images%20(10).jpg?alt=media&token=e19d6c0a-347b-43d3-9870-03dfdf8ac618'),(24,'delantero',24,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5images%20(9).jpg?alt=media&token=3f3ed099-bad7-4412-9071-e5009a11655e'),(25,'delantero',25,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F23images%20(11).jpg?alt=media&token=795f7404-233f-4835-8f52-041830e7b918'),(26,'delantero',26,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F55images%20(12).jpg?alt=media&token=2a68b988-e5ff-4a79-be2a-973521565a85'),(27,'delantero',27,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F26images%20(13).jpg?alt=media&token=ca905939-2a27-40e4-bb9c-b9725402ba98'),(28,'delantero',28,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F56images%20(14).jpg?alt=media&token=fae71587-0863-4cdb-8d25-8ae10c798669'),(29,'delantero',29,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F30images%20(15).jpg?alt=media&token=b5a40eaa-e398-4137-9aa3-237ca0ecc14c'),(30,'delantero',30,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F52images%20(16).jpg?alt=media&token=e03b9436-aa98-471d-9cc9-2ba9f944a751'),(31,'delantero',31,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25images%20(17).jpg?alt=media&token=14cb78fc-08fc-4f32-b0cc-7f5855ab94d3'),(32,'delantero',32,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F58images%20(18).jpg?alt=media&token=1cd8825e-b6e4-4649-b3e0-00316b8e70c4'),(60,'defensa',39,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25images%20(19).jpg?alt=media&token=61d8b2b8-3d01-41f8-9204-b130fcf5c9cc'),(61,'defensa',40,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F49images%20(20).jpg?alt=media&token=55659c33-cee5-4558-bedc-a6d04930e8a9'),(62,'defensa',41,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25images%20(21).jpg?alt=media&token=2c9076ac-6c39-4efb-b7a4-ab410d26edd3'),(63,'defensa',42,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F53images%20(3).jpg?alt=media&token=4073a741-18d8-4b3f-a3e8-2f8b111aced4'),(64,'defensa',43,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F23descarga%20(3).jpg?alt=media&token=a6435dd1-b3ee-48c2-be28-a89553a40317'),(65,'defensa',44,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F52descarga.jpg?alt=media&token=2a0c0353-c83b-4658-b1e5-dd7bbfb1fd6a'),(66,'defensa',45,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F34images.jpg?alt=media&token=219f4070-633f-40a1-9cef-3e4aeea15ee1'),(67,'defensa',46,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F6images%20(6).jpg?alt=media&token=cb5c678b-3624-4839-8c2c-0d3e59272cf8'),(68,'centrocampista',47,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F33images%20(16).jpg?alt=media&token=6793a266-fbcd-411c-a484-3f9cdddfe9e8'),(69,'centrocampista',48,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F2images%20(7).jpg?alt=media&token=3193a918-1e58-4675-8a26-c457baad90c9'),(70,'centrocampista',49,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F24images%20(2).jpg?alt=media&token=750bb84f-be52-49b2-8477-57b81ed8d3be'),(71,'centrocampista',50,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F53images%20(10).jpg?alt=media&token=993fa723-add2-4b74-910f-5d129974db8a'),(72,'centrocampista',51,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F13images%20(19).jpg?alt=media&token=e16a8736-5649-4169-97b8-cf536732ef16'),(73,'centrocampista',52,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F36images%20(13).jpg?alt=media&token=860aad47-5b94-4e75-a391-43850231992f'),(74,'centrocampista',53,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F59images%20(21).jpg?alt=media&token=cbe583da-fc80-4209-8cd4-9100121bcb59'),(75,'delantero',54,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F23images%20(12).jpg?alt=media&token=6575c985-ceb8-49a2-b2b5-f47082a67e34'),(76,'portero',55,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F49descarga.jpg?alt=media&token=4b1d31b4-7f5c-4e49-b412-0b597975e0e9'),(77,'defensa',56,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5111.jpg?alt=media&token=dcf1e274-2cde-430d-a7f8-7ac03b049dac'),(78,'delantero',57,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F11images%20(9).jpg?alt=media&token=47afcc0b-ca77-4bb8-8185-1953affa24d3'),(79,'delantero',58,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5images.jpg?alt=media&token=0458c0fa-bdde-4d46-aa0c-2b6d4265c1f2'),(86,'defensa',67,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F55j5.jpg?alt=media&token=90741a5d-935a-4891-867a-e77e4bb51de9'),(87,'delantero',68,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F31j2jpg.jpg?alt=media&token=67b01b76-56de-4ae6-9ba7-4c9374d54a8e'),(88,'delantero',69,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F1j4.jpg?alt=media&token=7c5eb2ea-1089-4625-85d5-b919a86b4ad6'),(89,'centrocampista',70,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F32j6.jpg?alt=media&token=97101041-42e0-4763-910a-b7e576a518fc'),(90,'portero',71,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F18images%20(1).jpg?alt=media&token=5532662c-e48f-42d9-a13d-e21ecf47775c'),(91,'defensa',72,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F44descarga.jpg?alt=media&token=0e3fbd5d-d5d2-4464-a58c-0f033eac3081'),(92,'delantero',73,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F271.jpg?alt=media&token=97515f89-7483-4ed7-933f-3dc2cbd4d1b0'),(93,'defensa',74,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F40images%20(2).jpg?alt=media&token=399b9823-f2f7-41b0-9d45-c6ef860cf83c'),(94,'delantero',75,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F2111.jpg?alt=media&token=d057c998-18bf-4697-932c-4734eeb210f7'),(95,'delantero',76,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F34images.jpg?alt=media&token=10d93013-af85-4579-9b73-da19381e6b21'),(96,'delantero',77,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F413.jpg?alt=media&token=486cc2f9-e36d-46f1-9ff0-69e1cd4e2f37'),(97,'delantero',78,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F58images%20(3).jpg?alt=media&token=34db39b6-7d16-4c7b-9be2-6ce59c1aea17'),(98,'delantero',79,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5114.jpg?alt=media&token=2ce2f448-39db-44de-9797-172215f35f2c'),(99,'portero',80,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25j18.jpg?alt=media&token=2199fbbd-f6d5-4e7c-86c0-1680d03b0446'),(100,'delantero',81,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F3415.jpg?alt=media&token=73cdf8ef-8d61-4f72-9587-7eecacb5472d'),(101,'delantero',82,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5816.jpg?alt=media&token=8ff8991c-665b-47fa-91c3-e6c4682cf407'),(102,'defensa',83,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4517.jpg?alt=media&token=1f800340-4bb0-4bd8-b5a1-b91a76ba3dee'),(103,'portero',84,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F22descarga%20(4).jpg?alt=media&token=c850cd4a-a916-47c4-b0bc-37fb37155218'),(104,'defensa',85,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F57j17.jpg?alt=media&token=ddae70ce-d984-410b-9411-3d7cc5d91f84'),(105,'defensa',86,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F49j16.jpg?alt=media&token=04c16d1b-e048-4a26-a46a-eb2293d8f4ab'),(106,'defensa',87,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F32j15.jpg?alt=media&token=f3fc87dd-7cd7-4a13-844b-e7b765caeb70'),(107,'portero',88,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4918.jpg?alt=media&token=e14ecfe0-174f-462a-abf4-564fef89f459'),(108,'centrocampista',89,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F22j14.jpg?alt=media&token=bc8b616b-ebe1-403a-bf48-f66f6da9891b'),(109,'centrocampista',90,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F41j13.jpg?alt=media&token=049029ad-51f4-4155-a6b7-a341f7b53d9c'),(110,'centrocampista',91,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4j12.jpg?alt=media&token=12921cc4-4c8b-4594-84f6-da540da7ad67'),(111,'portero',92,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F27images%20(27).jpg?alt=media&token=0cce948c-b1ef-45d6-a7ff-791200073b8c'),(112,'centrocampista',93,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F14j11.jpg?alt=media&token=4bac4c06-8870-46bb-83d3-7b8a6eb2dbdf'),(113,'centrocampista',94,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F28j10.jpg?alt=media&token=41b69bc9-3728-4f70-9af8-8ed1c024f423'),(114,'centrocampista',95,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F14j9.jpg?alt=media&token=7ba7fb4f-a2e7-41a8-bb0b-56e65404a328'),(115,'delantero',96,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25j8.jpg?alt=media&token=a7d3accc-29ad-486b-964a-271024563258'),(116,'delantero',97,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F13j7.jpg?alt=media&token=62f8ac46-c551-430f-beb3-78d9cba9cabc'),(117,'defensa',98,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F43j6.jpg?alt=media&token=d63bed9f-0ba4-47e0-a08c-a27114270ff8'),(118,'defensa',99,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F54j4.jpg?alt=media&token=efd1fca1-ba9d-4d18-83f9-3d414395980e'),(119,'portero',100,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F18images%20(26).jpg?alt=media&token=6351c43e-34d6-4cb8-bffb-f122ba762732'),(120,'portero',101,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F3912.jpg?alt=media&token=9f58d822-360a-4735-a734-d0dfda705604'),(121,'defensa',102,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F3j1jpg.jpg?alt=media&token=0d5de540-1787-46d6-9b62-d3ddb931e611'),(122,'portero',103,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F12images%20(25).jpg?alt=media&token=3c23d9bf-f72e-45f7-914a-768da31b84b1'),(123,'portero',104,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F0images%20(23).jpg?alt=media&token=4bbed686-c6b4-4596-a071-cfc19a8a74ef'),(124,'defensa',105,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F59j2jpg.jpg?alt=media&token=d4db92cc-ed27-4c7f-9be3-bd5ed84d3c08'),(125,'portero',106,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F16descarga%20(4).jpg?alt=media&token=540a1a79-6228-415b-a6a0-6e38d709070d'),(126,'defensa',107,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F53j3.png?alt=media&token=8f485d90-97bf-44b7-9d65-d6460861ee35'),(127,'defensa',108,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F36j4.jpg?alt=media&token=c70c060b-985b-4654-a81d-4a3636123ac4'),(128,'portero',109,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F40images%20(25).jpg?alt=media&token=ec8e0413-ce12-4549-97f5-bebf707aab62'),(129,'defensa',110,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F44j5.jpg?alt=media&token=f6922240-ba87-4cac-b573-32892768011d'),(130,'portero',111,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F49images%20(27).jpg?alt=media&token=babc6109-816d-41c3-99b5-f8009bd8b772'),(131,'defensa',112,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F0j6.jpg?alt=media&token=5517b8db-e556-4fc3-99f7-7b3f85d4d9ce'),(132,'portero',113,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F29images%20(24).jpg?alt=media&token=34c87019-41d4-4fd0-89fb-89a4f4d7d7ed'),(133,'portero',114,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F52images%20(26).jpg?alt=media&token=710ebc76-bda8-482d-80d3-85b018d3ec32'),(134,'portero',115,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F46descarga%20(4).jpg?alt=media&token=837b0410-f96f-40c6-99f3-6b73e3153be8'),(135,'portero',116,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F6images%20(26).jpg?alt=media&token=44308622-188b-4b88-ae46-4a3840b30628'),(136,'portero',117,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F37descarga%20(4).jpg?alt=media&token=5fe879dd-2622-4c15-b57a-119c8503a620'),(137,'portero',118,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F52images%20(27).jpg?alt=media&token=48946ba8-b867-4fd0-9d7c-3d02fa3b7bf0'),(138,'portero',119,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F14images%20(25).jpg?alt=media&token=67df07a3-212f-4dfd-b7f6-83e0630052e6'),(139,'portero',120,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F52images%20(23).jpg?alt=media&token=bb35f1d0-f904-4ab7-bd91-9df9131da59b'),(140,'centrocampista',121,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25j6.jpg?alt=media&token=4abac7ea-a8f4-474d-92d6-ef551d31914c'),(141,'centrocampista',122,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F23j7.jpg?alt=media&token=3baf8320-8d37-46c8-a7b4-6ca39c485a66'),(142,'centrocampista',123,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F37j8.jpg?alt=media&token=eff03c0e-0947-44c4-839f-58531e3013d2'),(143,'centrocampista',124,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F20j9.jpg?alt=media&token=43f4423e-87a7-49bb-b1a3-53c0a9644ab4'),(144,'centrocampista',125,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F19j10.jpg?alt=media&token=41bbdbb4-84ba-4af2-a105-cf00b0f75b4f'),(145,'delantero',126,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F419.jpeg?alt=media&token=c8cf6d2d-5245-4e6c-a9c2-ec7707004cfc'),(146,'delantero',127,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5520.jpg?alt=media&token=a31f0698-1e11-4a1a-8f02-b7e82c36f051'),(147,'centrocampista',128,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F16j11.jpg?alt=media&token=3da214a4-cd7d-42be-be22-c010b9e2e4f6'),(148,'delantero',129,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4621.jpg?alt=media&token=76e2bc2b-90fd-4e51-ad8f-cc4fe3882285'),(149,'delantero',130,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F23j12.jpg?alt=media&token=619d7efe-ae0d-4c4c-8a44-be0a0c74b516'),(150,'delantero',131,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F1122.jpg?alt=media&token=79071fad-7437-4406-91eb-452e51102494'),(151,'delantero',132,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F32j13.jpg?alt=media&token=ca22a637-0e40-4565-9363-3d9b62720fb4'),(152,'delantero',133,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F48j14.jpg?alt=media&token=0f6e906c-415c-402b-a9da-b7fb33f86324'),(153,'delantero',134,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4023.jpg?alt=media&token=8093ea6c-14d1-4c74-ab25-007c9223f579'),(154,'centrocampista',135,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F45j15.jpg?alt=media&token=b5b8aa1c-84f8-468f-b0da-6a1e15bd68b2'),(155,'centrocampista',136,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F3224.jpg?alt=media&token=eb5669fb-c6f8-4aa0-a281-b648e11dcce3'),(156,'centrocampista',137,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F20j15.jpg?alt=media&token=98043431-914b-47f3-aa0d-65d6f70247f5'),(157,'defensa',138,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F16descarga.jpg?alt=media&token=8a4fa1f6-e684-4d06-bb9a-a87fd277ab5e'),(158,'centrocampista',139,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F1124.jpg?alt=media&token=c9b5e5f4-795f-4663-913a-472650614c86'),(159,'centrocampista',140,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F925.jpg?alt=media&token=2de2e2eb-a6c7-49c5-88e3-c2fe3dff68c7'),(160,'defensa',141,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F32images%20(7).jpg?alt=media&token=ce91b3e4-350c-41f0-a1b4-b63bb3a1f079'),(161,'portero',142,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4012.jpg?alt=media&token=55a82067-f9e5-4a36-959f-871dbf131762'),(162,'centrocampista',143,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5625.jpg?alt=media&token=c500ed80-8295-49e2-af96-def75d80e1cc'),(163,'defensa',144,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25images%20(17).jpg?alt=media&token=e44746cd-8e92-4593-9107-a6f84ebb4184'),(164,'defensa',145,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F29j1jpg.jpg?alt=media&token=343cf523-eeba-4ca4-974c-65eb12be0cce'),(165,'centrocampista',146,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4026.jpg?alt=media&token=d16f9aec-970b-402b-a4d8-cbfa93a05cc5'),(166,'defensa',147,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F22j2jpg.jpg?alt=media&token=ac7e13d0-27ee-4012-ba59-d2b96da3379f'),(167,'defensa',148,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F25images%20(1).jpg?alt=media&token=97597de4-5344-4586-8607-dab5adc464a4'),(168,'defensa',149,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F1j3.png?alt=media&token=7d26a620-0357-4503-8bdd-cc83df49af83'),(169,'centrocampista',150,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F38images%20(2).jpg?alt=media&token=8673b0d7-36d4-4523-95ec-7ea276c718f3'),(170,'defensa',151,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F45j4.jpg?alt=media&token=8dabb715-36da-48cc-a665-b13fd6c859af'),(171,'centrocampista',152,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F35images%20(11).jpg?alt=media&token=4bde3ba1-1f6e-4dbd-9654-86527f5c9575'),(172,'centrocampista',153,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F44j5.jpg?alt=media&token=038b7cea-6ca9-4fef-9547-fe80f933d18d'),(173,'centrocampista',154,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5927.jpg?alt=media&token=10f8774e-4299-417a-9875-6e6101e41b86'),(174,'centrocampista',155,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F41images%20(5).jpg?alt=media&token=5acc310a-c4ef-4c5e-9e79-631218d85252'),(175,'centrocampista',156,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F2630.jpg?alt=media&token=bfe981b8-66cc-4d1c-b79b-6ee502f0c1a3'),(176,'centrocampista',157,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4228.jpg?alt=media&token=cd8933c7-15d9-44a6-8853-5798efff07d6'),(177,'centrocampista',158,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F7j7.jpg?alt=media&token=e9172d26-373d-4156-92c9-785c86afcc4f'),(178,'delantero',159,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F11descarga%20(3).jpg?alt=media&token=257ea41b-ec73-4a64-ad66-6bbccd40d8e1'),(179,'centrocampista',160,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F51j18.jpg?alt=media&token=896e612d-b553-4d78-a0c6-b2a1e1a8b140'),(180,'centrocampista',161,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F51j18.jpg?alt=media&token=896e612d-b553-4d78-a0c6-b2a1e1a8b140'),(181,'centrocampista',162,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F3729.jpg?alt=media&token=d0d7e4ce-23b1-4aae-aa62-e19bea1d01b4'),(182,'delantero',163,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F6images%20(15).jpg?alt=media&token=f958ec77-2f55-4594-a3d5-924827de3df0'),(183,'delantero',164,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F29j17.jpg?alt=media&token=06078521-8f0d-4f63-96b8-21b62a375694'),(184,'delantero',165,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F56images%20(6).jpg?alt=media&token=9b58b8f4-911a-4d28-833e-eb6060be78ec'),(185,'delantero',166,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F7j16.jpg?alt=media&token=f5e861fd-7bcf-43dc-8236-82377e3dbd7d'),(186,'defensa',167,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F5331.jpg?alt=media&token=0f5ff17c-5f0e-4686-a018-67b06595fbdb'),(187,'defensa',168,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F4132.jpg?alt=media&token=22fe8ae2-91b6-4816-a7a8-801d37deedce'),(188,'centrocampista',169,'https://firebasestorage.googleapis.com/v0/b/action-gol.appspot.com/o/jugadores%2F201a016920-7ac4-4ea3-83d6-805a8dc03c7b_200x200.png?alt=media&token=e0fe037e-dba1-4f4f-9deb-4a4ebe271a5f');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador_atributo`
--

DROP TABLE IF EXISTS `jugador_atributo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador_atributo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `atributo` bigint(20) DEFAULT NULL,
  `jugador` bigint(20) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ja_jugador` (`jugador`),
  KEY `fk_ja_atributo` (`atributo`),
  CONSTRAINT `fk_ja_atributo` FOREIGN KEY (`atributo`) REFERENCES `atributo` (`id`),
  CONSTRAINT `fk_ja_jugador` FOREIGN KEY (`jugador`) REFERENCES `jugador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador_atributo`
--

LOCK TABLES `jugador_atributo` WRITE;
/*!40000 ALTER TABLE `jugador_atributo` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugador_atributo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugar`
--

DROP TABLE IF EXISTS `lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `direccion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
INSERT INTO `lugar` VALUES (1,'Cantón El salitre','San Miguel de Mercedes'),(2,'Barrio El centro','San Miguel de Mercedes'),(3,'Caserío El pino','San Miguel de Mercedes'),(4,'Cantón Los Hernandez','San Miguel de Mercedes'),(5,'caserío Las mesitas','San Miguel de Mercedes'),(6,'Barrio La Vega','San Miguel de Mercedes'),(7,'Barrio El plancito','San Miguel de Mercedes'),(8,'Cantón Santa cruz','San Luis del Carmen'),(9,'Barrio El calvario','San Miguel de Mercedes'),(10,'Barrio San Antonio','San Miguel de Mercedes'),(11,'Cantón Los Guardados','San Miguel de Mercedes'),(12,'Barrio La paterna','San Miguel de Mercedes'),(13,'Cantón El matazano','San Miguel de Mercedes'),(14,'Barrio El chorrón','San Miguel de Mercedes'),(15,'Barrio La celis','San Miguel de Mercedes');
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `horario` bigint(20) NOT NULL,
  `equipo1` bigint(20) NOT NULL,
  `equipo2` bigint(20) NOT NULL,
  `jornada` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partido_equipo1` (`equipo1`),
  KEY `fk_partido_equipo2` (`equipo2`),
  KEY `fk_partido_jornada` (`jornada`),
  KEY `fk_partido_horario` (`horario`),
  CONSTRAINT `fk_partido_equipo1` FOREIGN KEY (`equipo1`) REFERENCES `equipo_temporada` (`id`),
  CONSTRAINT `fk_partido_equipo2` FOREIGN KEY (`equipo2`) REFERENCES `equipo_temporada` (`id`),
  CONSTRAINT `fk_partido_horario` FOREIGN KEY (`horario`) REFERENCES `horario` (`id`),
  CONSTRAINT `fk_partido_jornada` FOREIGN KEY (`jornada`) REFERENCES `jornada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido_arbitro`
--

DROP TABLE IF EXISTS `partido_arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido_arbitro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arbitro` bigint(20) DEFAULT NULL,
  `partido` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pa_arbitro` (`arbitro`),
  KEY `fk_pa_partido` (`partido`),
  CONSTRAINT `fk_pa_arbitro` FOREIGN KEY (`arbitro`) REFERENCES `arbitro` (`id`),
  CONSTRAINT `fk_pa_partido` FOREIGN KEY (`partido`) REFERENCES `partido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido_arbitro`
--

LOCK TABLES `partido_arbitro` WRITE;
/*!40000 ALTER TABLE `partido_arbitro` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `dui` char(10) NOT NULL,
  `nit` char(17) DEFAULT NULL,
  `altura` double(6,2) NOT NULL,
  `edad` int(11) NOT NULL,
  `lugar` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lugar_persona` (`lugar`),
  CONSTRAINT `fk_lugar_persona` FOREIGN KEY (`lugar`) REFERENCES `lugar` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (3,'Gerson','Oliva','34567890-1','1234-567890-123-4',1.70,22,1),(4,'Edwin','Chavez','34567890-1','1234-567890-123-1',1.65,21,1),(5,'Jeancarlos','Figueroa','45678901-2','1234-567890-123-',1.68,22,1),(6,'Edgar','Linarez','56789012-3','1234-567890-123-',1.67,23,1),(7,'Miguel','Recinos','67890123-4','1234-567890-123-',1.70,24,1),(8,'Leonardo','Figueroa','78901234-5','1234-567890-123-',1.75,24,1),(9,'Elmer','Valdez','89012345-6','1234-567890-123-',1.65,23,1),(10,'Javier','Beltran','90123456-7','1234-567890-123-',1.70,25,1),(11,'Alex','Gomez','01234567-8','1234-567890-123-',1.70,23,1),(12,'Jorge','Perez','12345678-9','1234-567890-123-',1.60,21,1),(13,'Luis ','Chavez','23456789-0','1234-567890-123-',1.78,23,2),(15,'Carlos','Rodriguez','45678901-2','1234-567890-123-',1.73,21,2),(16,'Otoniel','Oliva','56789012-3','1234-567890-123-',1.67,22,2),(17,'Alexandeer','Jimenez','67890123-4','1234-567890-123-',1.68,23,2),(18,'Alexander','Escobar','78901234-5','1234-567890-123-',1.70,24,2),(19,'Alfredo','Ramirez','89012345-6','1234-567890-123-',1.67,25,2),(20,'Amilcar','Alvarado','90123456-7','1234-567890-123-',1.67,21,2),(21,'Adam','Peña','01234567-8','1234-567890-123-',1.70,21,2),(22,'Alfonso','Gutierrez','12345678-9','1234-567890-123-',1.73,23,2),(23,'Bartolo','Escobar','23456789-0','1234-567890-123-',1.67,23,3),(24,'Bryan','Hernandez','34567890-1','1234567890123',1.72,24,3),(25,'Balmo','Hernandez','45678901-2','1234-567890-123-',1.75,25,3),(26,'Celso','Perez','56789012-3','1234-567890-123-',1.73,26,3),(27,'Denis','Chavez','67890123-4','1234-567890-123-',1.71,21,3),(28,'Eugenio','Chicas','78901234-5','1234-567890-123-',1.72,22,3),(29,'Brandon','Nuñez','89012345-6','1234-567890-123-',1.73,23,3),(30,'Esau','Gomez','90123456-','1234-569012-3  -',1.74,24,3),(31,'Elian','Menjivar','01234567-8','1234-567890-123-',1.75,25,3),(32,'Fabian','Guzman','12345678-9','1234-567890-123-',1.60,26,3),(38,'Giovany','Rodriguez','789012345','1234567890123',1.65,28,4),(39,'Geovany','Guzman','89012345-6','1234-567890-123-',1.66,27,4),(40,'Gonsalo','Leon','90123456-7','1234-567890-123-',1.67,26,4),(41,'Hercules','Castro','01234567-8','1234-567890-123-',1.68,25,4),(42,'Helio','Escobar','12345678-9','1234-567890-123-',1.69,24,4),(43,'Herminio','Sanchez','23456789-0','1234-567890-123-',1.70,23,4),(44,'Ivan','Valencia','34567890-1','1234-567890-123-',1.71,22,4),(45,'Igor','Figueroa','45678901-2','1234-567890-123-',1.72,21,5),(46,'Irael','Hernandez','56789012-3','1234-567890-123-',1.73,20,5),(47,'Ismael','Hernandez','67890123-4','1234-567890-123-',1.74,19,5),(48,'Jairo','Oliva','12345678-9','1234-567890-123-',1.75,20,5),(49,'Jorge','Recinos','89012345-6','1234-567890-123-',1.76,19,5),(50,'Julio','Guardado','90123456-7','1234-567890-123-',1.77,20,5),(51,'Junior','Jimenez','01234567-8','1234-567890-123-',1.78,21,5),(52,'Jaime','Ramos','12345678-9','1234-567890-123-',1.79,22,5),(53,'Jeremias','Valdez','23456789-0','1234-567890-123-',1.80,23,5),(54,'Kevin','Hernandez','34567890-1','1234-567890-123-',1.79,24,5),(55,'Manuel','Menjivar','45678901-2','1234-567890-123-',1.78,25,5),(56,'Marcos','Oliva','56789012-3','1234-567890-123-',1.77,26,1),(57,'Melvin','Recinos','67890123-4','1234-567890-123-',1.76,27,2),(58,'Mario','Hernandez','789012345','1234567890123',1.75,28,3),(67,'Stanley','Baires','34576879-8','4567-890096-543-5',1.76,27,6),(68,'Stanley','Bingler','67128658-7','3726-283765-872-3',1.70,27,6),(69,'Wilber','Rodriguez','86876876-7','4678-989009-900-9',1.90,30,6),(70,'Kenny','Ramirez','49309683-0','8327-598273-598-3',1.75,28,6),(71,'Agustin','Sierra','54548787-8','8787-786744-554-5',1.90,19,2),(72,'Dustin','Moreno','78787878-7','7878-787678-787-8',1.75,24,3),(73,'Oscar','Romero','84652846-5','6451-248512-613-4',1.78,25,1),(74,'Gilberto','Castillo','99777775-6',' 478-787777-898-9',1.80,0,15),(75,'Pedro','Rivas','12345678-9','2345-678909-008-9',1.78,21,1),(76,'Eduardo ','Cabrera','77787878-7','7977-775474-547-8',1.70,22,1),(77,'Juan','Bonilla','32456789-8','3546-788908-676-5',1.80,22,1),(78,'Leo','Santiago','77777766-4','7787-654545-458-8',1.87,22,15),(79,'Ramon','Bolaños','13245678-9','3424-788765-567-6',1.78,25,1),(80,'Rogelio','Jimenez','83279583-7','3798-752938-579-2',1.75,30,6),(81,'Carlos','Recinos','12308766-5','4567-870987-654-3',1.76,23,1),(82,'Jose','Recinos','12308766-5','4567-870987-654-3',1.76,23,1),(83,'Manuel','Flores','12308766-5','4567-870987-654-3',1.76,23,1),(84,'Brayan ','Tamarin','78787545-4','8787-845445-454-5',1.90,25,15),(85,'Roberto','Ramos','89327498-2','7863-874628-746-2',1.75,28,6),(86,'Charlie','Romero','98136597-8','8787-235628-735-6',1.75,26,6),(87,'Fredy','Guerra','09283095-7','9021-735982-365-9',1.67,23,6),(88,'Raul','Chavez','45467897-9','3422-567890-089-7',1.78,24,1),(89,'Mauricio','Ramos','89734985-7','8726-873462-856-2',1.68,24,6),(90,'Francisco','Ramirez','98723985-7','9812-654876-287-5',1.77,28,6),(91,'Venedicto','Flores','98279835-7','8923-658972-365-8',1.69,29,6),(92,'Oscar ','Duarte','78745488-7','1567-889765-543-8',1.90,25,1),(93,'Alexander','Ramos','73985623-8','7163-487236-785-6',1.77,29,6),(94,'Ignacio','Valle','86239852-3','6235-623875-687-3',1.88,31,6),(95,'Raul','Gonzales','09872398-5','2783-956287-562-8',1.78,29,6),(96,'Kevin','Vazques','98327598-3','3827-598237-598-3',1.81,24,6),(97,'Victor','Roma','89632846-2','9862-387562-873-6',1.76,29,6),(98,'Felix','Aguilar','98347298-3','8216-548726-358-7',1.69,24,6),(99,'Eduardo','Villa','98276578-2','5264-517645-127-6',1.76,21,6),(100,'Melvin ','Recinos','78417477-4','7457-474445-787-7',1.80,22,2),(101,'Lucio','Ramos','71298741-9','7129-856728-563-2',1.69,24,9),(102,'Gerson','Lainez','38975982-3','7823-967982-739-6',1.69,31,9),(103,'Humberto','Juarez','78455787-7','8785-878787-878-7',1.90,23,3),(104,'Rene','Sanchez','78777757-7','8778-777778-779-7',1.90,23,4),(105,'Byron','Escobar','89167298-6','9182-741982-759-8',1.77,25,9),(106,'Dustin','Montaner','77677787-7','7845-457787-878-8',1.90,28,5),(107,'Rogelio','Recinos','61287561-8','2185-798237-598-2',1.68,31,9),(108,'Alejandro','Ramos','89327598-2','9821-759871-395-8',1.83,32,9),(109,'Pablo','Rivas','77777777-3','8777-777777-979-7',1.90,25,6),(110,'Rodrigo','Gomez','10927850-9','9873-958275-983-2',1.77,29,9),(111,'Eduardo ','Melgar','87577687-5','5748-787779-744-5',1.80,30,7),(112,'Fernando','Gonzales','87129751-9','8291-759273-957-5',1.80,29,9),(113,'Ricardo','Ramos','77787878-6','8787-545484-584-4',1.80,25,8),(114,'Ernan','Rodriguez','78778748-7','7875-488857-878-8',1.90,24,9),(115,'Beltran','Suarez','78787874-8','4544-545445-454-8',1.75,35,10),(116,'Remberto','Martinez','87878787-8','8787-878787-878-7',1.80,25,11),(117,'Julio','Arevalo','78787848-7','7878-484848-488-8',1.91,28,12),(118,'Julio','Serrano','78787878-7','8787-874848-884-8',1.95,23,13),(119,'Alexander','Merin','54777778-6','7878-778777-545-4',1.86,39,14),(120,'Leonardo','Recio','47749777-7','9979-797756-868-6',1.87,23,15),(121,'Julio','Flores','98349827-9','8732-987529-875-3',1.88,27,9),(122,'Javier','Castro','29836389-2','1286-487654-872-6',1.89,31,9),(123,'Matias','Jimenez','09184091-7','9816-727561-872-5',1.80,31,9),(124,'Rufino','Valle','89217579-1','9812-698179-821-7',1.77,21,9),(125,'Adonay','Valle','12879127-5','1987-249817-981-7',1.76,23,9),(126,'Ramon','Chavez','12345678-7','1234-565786-798-7',1.78,23,7),(127,'Felipe','Recinos','32454657-6','2345-657687-989-8',1.89,21,7),(128,'Regilon','Cuadrado','91729879-5','1278-418726-481-7',1.80,25,9),(129,'Nelson','Guzman','12243546-5','2345-667776-756-4',1.78,34,7),(130,'Silvano','Guardado','71925719-2','9862-876284-762-3',1.85,26,9),(131,'Antonio','Oliva','12345676-5','1234-567654-323-4',1.67,34,7),(132,'Wilber','Ramirez','89279812-7','9839-825982-375-9',1.67,28,9),(133,'Carlos','Prudente','89127981-7','9283-759823-759-8',1.80,24,9),(134,'Balmo','Rodriguez','12345676-7','2324-567654-322-1',1.78,23,7),(135,'Oscar','Recinos','98217598-7','2398-579283-759-3',1.80,27,9),(136,'David','Castro','12345678-8','2345-678876-543-2',1.76,34,7),(137,'Bladimir','Ramos','89279816-5','1786-238764-287-3',1.78,24,9),(138,'Ezequiel','Martinez','78777652-6','7567-467498-794-8',1.69,25,10),(139,'Frank','Escobar','43567878-6','1323-445676-879-8',1.87,23,7),(140,'Jonatan','Castro','21345677-7','1234-567788-786-5',1.67,45,7),(141,'Sebastian','Rodriguez','45675746-7','7797-978867-487-8',1.77,31,10),(142,'Rufino','Vasquez','98364187-6','9839-852385-623-8',1.80,28,8),(143,'Henrry','Escobar','34354656-7','1234-567686-756-4',1.89,23,7),(144,'Eduardo','Menjivar','87787867-8','7777-687774-545-4',1.85,21,10),(145,'Matias','Guardado','97825982-3','6487-236587-346-5',1.80,28,8),(146,'Edenilson','Ramos','21324567-7','2345-678876-543-2',1.76,22,7),(147,'Felix','Ibañes','97918721-9','8927-159817-298-5',1.78,27,8),(148,'Federico','Montalvo','75757517-9','7948-794747-489-7',1.78,27,10),(149,'Carlos','Nuñes','98217598-1','2893-749823-759-8',1.77,29,8),(150,'Ricardo','Recinos','78778484-8','7874-444445-454-5',1.80,27,10),(151,'Rodolfo','Beltran','87129857-1','7835-672387-956-8',1.78,27,8),(152,'Oscar','Menjivar','87848844-8','8784-448484-444-5',1.80,26,10),(153,'Leonardo','Garcia','19826759-1','2786-487364-872-6',1.78,25,8),(154,'Odalis','Chavez','23435465-0','2123-345666-756-5',1.68,25,7),(155,'Melvin','Ramos','87878748-4','7845-446545-455-4',1.90,25,10),(156,'Alex','Figueroa','34556778-6','1234-567890-987-6',1.89,25,7),(157,'Alexander','Campos','32344568-6','1234-567876-543-3',1.78,25,7),(158,'Danilo','Garcia','18732987-2','9823-587237-857-3',1.78,28,15),(159,'Jose','Ricardo','77756568-4','7743-434477-979-7',1.87,28,10),(160,'Gerson','Rodriguez','82798471-9','7823-648723-658-7',1.78,29,15),(161,'Gerson','Rodriguez','82798471-9','7823-648723-658-7',1.78,29,15),(162,'Otonirl','Onel','12345676-7','2345-678656-453-4',1.67,26,7),(163,'Siru','Lezcano','74747417-5','7656-755467-646-7',1.78,33,10),(164,'Brayan','Jimenez','89735982-3','9873-478236-587-3',1.78,28,15),(165,'Santiago','Duarte','76746754-7','7274-675984-978-4',1.89,26,10),(166,'Daniel','Menjivar','89275498-2','9841-289764-782-3',1.78,29,15),(167,'Felipe','Campos','12345678-9','2345-678876-543-2',1.78,22,7),(168,'Carmen','Escobar','24356787-8','1234-567678-786-7',1.78,24,7),(169,'Rosalio','rivera','46985498-2','0923-573589-534-7',1.90,20,14);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla`
--

DROP TABLE IF EXISTS `tabla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equipo` bigint(20) NOT NULL,
  `temporada` bigint(20) NOT NULL,
  `pj` int(11) NOT NULL,
  `pg` int(11) NOT NULL,
  `pe` int(11) NOT NULL,
  `pp` int(11) NOT NULL,
  `gf` int(11) NOT NULL,
  `gc` int(11) NOT NULL,
  `puntos` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tabla_equipot` (`equipo`),
  KEY `fk_tabla_temporada` (`temporada`),
  CONSTRAINT `fk_tabla_equipot` FOREIGN KEY (`equipo`) REFERENCES `equipo_temporada` (`id`),
  CONSTRAINT `fk_tabla_temporada` FOREIGN KEY (`temporada`) REFERENCES `temporada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla`
--

LOCK TABLES `tabla` WRITE;
/*!40000 ALTER TABLE `tabla` DISABLE KEYS */;
/*!40000 ALTER TABLE `tabla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hora` time DEFAULT NULL,
  `tipo` tinyint(1) DEFAULT NULL,
  `carnet` bigint(20) DEFAULT NULL,
  `partido` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carnet_tarjeta` (`carnet`),
  KEY `fk_partido_tarjeta` (`partido`),
  CONSTRAINT `fk_carnet_tarjeta` FOREIGN KEY (`carnet`) REFERENCES `carnet` (`id`),
  CONSTRAINT `fk_partido_tarjeta` FOREIGN KEY (`partido`) REFERENCES `partido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporada`
--

DROP TABLE IF EXISTS `temporada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temporada` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero` bigint(20) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fecha_ini` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporada`
--

LOCK TABLES `temporada` WRITE;
/*!40000 ALTER TABLE `temporada` DISABLE KEYS */;
INSERT INTO `temporada` VALUES (1,12,'llenando','2019-12-12');
/*!40000 ALTER TABLE `temporada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'liga'
--

--
-- Dumping routines for database 'liga'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-25 16:35:32
