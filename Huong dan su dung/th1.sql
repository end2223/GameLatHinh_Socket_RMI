-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: th1
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `isfriend`
--

DROP TABLE IF EXISTS `isfriend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `isfriend` (
  `id1` int(11) NOT NULL,
  `id2` int(11) NOT NULL,
  KEY `id1` (`id1`),
  KEY `id2` (`id2`),
  CONSTRAINT `isfriend_ibfk_1` FOREIGN KEY (`id1`) REFERENCES `users` (`id`),
  CONSTRAINT `isfriend_ibfk_2` FOREIGN KEY (`id2`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `isfriend`
--

LOCK TABLES `isfriend` WRITE;
/*!40000 ALTER TABLE `isfriend` DISABLE KEYS */;
INSERT INTO `isfriend` VALUES (1,2),(1,3),(4,1);
/*!40000 ALTER TABLE `isfriend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lala`
--

DROP TABLE IF EXISTS `lala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lala` (
  `a` int(11) NOT NULL,
  PRIMARY KEY (`a`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lala`
--

LOCK TABLES `lala` WRITE;
/*!40000 ALTER TABLE `lala` DISABLE KEYS */;
/*!40000 ALTER TABLE `lala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchhistory`
--

DROP TABLE IF EXISTS `matchhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matchhistory` (
  `id1` int(11) NOT NULL,
  `id2` int(11) NOT NULL,
  `result` varchar(20) DEFAULT NULL,
  KEY `id1` (`id1`),
  KEY `id2` (`id2`),
  CONSTRAINT `matchhistory_ibfk_1` FOREIGN KEY (`id1`) REFERENCES `users` (`id`),
  CONSTRAINT `matchhistory_ibfk_2` FOREIGN KEY (`id2`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchhistory`
--

LOCK TABLES `matchhistory` WRITE;
/*!40000 ALTER TABLE `matchhistory` DISABLE KEYS */;
INSERT INTO `matchhistory` VALUES (4,1,'win'),(1,4,'win'),(2,1,'lose');
/*!40000 ALTER TABLE `matchhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hoten` varchar(500) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `isOnl` int(11) DEFAULT NULL,
  `points` float DEFAULT NULL,
  `totaltime` mediumtext,
  `games` int(11) DEFAULT NULL,
  `gameswin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tuan','tuan','tuan',0,2,'63875',3,2),(2,'tuan1','tuan1','tuan1',0,0,'0',1,0),(3,'tuan2','tuan2','tuan2',0,0,'0',0,0),(4,'t','t','t',0,1,'26998',2,1),(5,'q','q','q',0,0,'0',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-20  0:14:28
