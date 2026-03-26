/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: hotel_rural
-- ------------------------------------------------------
-- Server version	12.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `habitaciones`
--

DROP TABLE IF EXISTS `habitaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `precio_noche` decimal(8,2) NOT NULL CHECK (`precio_noche` > 0),
  `capacidad` int(11) NOT NULL CHECK (`capacidad` > 0),
  `fecha_alta` date NOT NULL DEFAULT curdate(),
  `disponible` tinyint(1) NOT NULL DEFAULT 1,
  `imagen` varchar(255) DEFAULT NULL,
  `valoracion` decimal(3,2) DEFAULT 0.00 CHECK (`valoracion` >= 0 and `valoracion` <= 5),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitaciones`
--

LOCK TABLES `habitaciones` WRITE;
/*!40000 ALTER TABLE `habitaciones` DISABLE KEYS */;
INSERT INTO `habitaciones` VALUES
(1,'Habitación Roble','Habitación doble con vistas al bosque',85.00,2,'2024-01-01',1,NULL,4.50),
(2,'Suite Lavanda','Suite de lujo con bañera y terraza privada',150.00,2,'2024-01-01',1,NULL,4.80),
(3,'Cabaña del Río','Cabaña independiente junto al río',200.00,4,'2024-01-01',1,NULL,5.00),
(4,'Habitación Olivo','Habitación individual con jardín',60.00,1,'2024-01-01',0,NULL,4.20);
/*!40000 ALTER TABLE `habitaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  `habitacion_id` int(11) NOT NULL,
  `numero_huespedes` int(11) NOT NULL CHECK (`numero_huespedes` > 0),
  `precio_total` decimal(10,2) NOT NULL CHECK (`precio_total` >= 0),
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `confirmada` tinyint(1) NOT NULL DEFAULT 0,
  `observaciones` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_usuario` (`usuario_id`),
  KEY `fk_reserva_habitacion` (`habitacion_id`),
  CONSTRAINT `fk_reserva_habitacion` FOREIGN KEY (`habitacion_id`) REFERENCES `habitaciones` (`id`),
  CONSTRAINT `fk_reserva_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `chk_fechas` CHECK (`fecha_fin` > `fecha_inicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES
(1,2,1,2,255.00,'2025-06-01','2025-06-04',1,'Llegada tarde por la noche'),
(2,3,3,3,600.00,'2025-07-10','2025-07-13',1,NULL),
(3,2,2,2,300.00,'2025-08-20','2025-08-22',0,'Solicitan cuna para bebé');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas_servicios`
--

DROP TABLE IF EXISTS `reservas_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas_servicios` (
  `reserva_id` int(11) NOT NULL,
  `servicio_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 1 CHECK (`cantidad` > 0),
  PRIMARY KEY (`reserva_id`,`servicio_id`),
  KEY `fk_rs_servicio` (`servicio_id`),
  CONSTRAINT `fk_rs_reserva` FOREIGN KEY (`reserva_id`) REFERENCES `reservas` (`id`),
  CONSTRAINT `fk_rs_servicio` FOREIGN KEY (`servicio_id`) REFERENCES `servicios_extra` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas_servicios`
--

LOCK TABLES `reservas_servicios` WRITE;
/*!40000 ALTER TABLE `reservas_servicios` DISABLE KEYS */;
INSERT INTO `reservas_servicios` VALUES
(1,1,2),
(1,2,1),
(2,1,3),
(2,3,1),
(3,4,1);
/*!40000 ALTER TABLE `reservas_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios_extra`
--

DROP TABLE IF EXISTS `servicios_extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios_extra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `precio` decimal(8,2) NOT NULL CHECK (`precio` >= 0),
  `duracion_minutos` int(11) NOT NULL CHECK (`duracion_minutos` > 0),
  `fecha_creacion` date NOT NULL DEFAULT curdate(),
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios_extra`
--

LOCK TABLES `servicios_extra` WRITE;
/*!40000 ALTER TABLE `servicios_extra` DISABLE KEYS */;
INSERT INTO `servicios_extra` VALUES
(1,'Desayuno incluido','Desayuno buffet en el comedor principal',15.00,60,'2024-01-01',1),
(2,'Masaje relajante','Masaje de 60 minutos en el spa',50.00,60,'2024-01-01',1),
(3,'Excursión rural','Ruta guiada por el entorno natural',35.00,180,'2024-01-01',1),
(4,'Cena romántica','Cena privada con menú especial',80.00,90,'2024-01-01',0);
/*!40000 ALTER TABLE `servicios_extra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(10) NOT NULL DEFAULT 'CLIENTE' CHECK (`rol` in ('ADMIN','CLIENTE')),
  `edad` int(11) NOT NULL CHECK (`edad` >= 18),
  `saldo` decimal(10,2) NOT NULL DEFAULT 0.00,
  `fecha_registro` date NOT NULL DEFAULT curdate(),
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES
(1,'Admin Principal','admin@hotelrural.com','1234','ADMIN',35,0.00,'2024-01-01',1),
(2,'Carlos García','carlos@email.com','1234','CLIENTE',28,50.00,'2024-03-15',1),
(3,'Laura Martínez','laura@email.com','1234','CLIENTE',34,120.00,'2024-05-20',1),
(4,'Pedro Sánchez','pedro@email.com','1234','CLIENTE',45,0.00,'2024-07-10',0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotel_rural'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-03-26 21:13:55
