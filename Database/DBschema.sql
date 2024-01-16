CREATE DATABASE  IF NOT EXISTS `iwish` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `iwish`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: iwish
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `item_id` int NOT NULL,
  `owner_name` varchar(50) NOT NULL,
  PRIMARY KEY (`item_id`,`owner_name`),
  KEY `owner_idx` (`owner_name`),
  CONSTRAINT `itembasket` FOREIGN KEY (`item_id`) REFERENCES `item` (`ItemID`),
  CONSTRAINT `owner_name` FOREIGN KEY (`owner_name`) REFERENCES `user` (`user_unique_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contribution`
--

DROP TABLE IF EXISTS `contribution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contribution` (
  `idcontributor_name` varchar(50) NOT NULL,
  `Owner_name` varchar(50) NOT NULL,
  `Item_id` int NOT NULL,
  `contributed_amount` int DEFAULT NULL,
  PRIMARY KEY (`idcontributor_name`,`Owner_name`,`Item_id`),
  KEY `owner_idx` (`Owner_name`),
  KEY `item_idx` (`Item_id`),
  CONSTRAINT `contributor` FOREIGN KEY (`idcontributor_name`) REFERENCES `user` (`user_unique_name`),
  CONSTRAINT `item` FOREIGN KEY (`Item_id`) REFERENCES `item` (`ItemID`),
  CONSTRAINT `owner` FOREIGN KEY (`Owner_name`) REFERENCES `user` (`user_unique_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contribution`
--

LOCK TABLES `contribution` WRITE;
/*!40000 ALTER TABLE `contribution` DISABLE KEYS */;
/*!40000 ALTER TABLE `contribution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_request` (
  `sender_name` varchar(50) NOT NULL,
  `receiver_name` varchar(45) NOT NULL,
  PRIMARY KEY (`sender_name`,`receiver_name`),
  KEY `request_receiver_idx` (`receiver_name`),
  CONSTRAINT `request_receiver` FOREIGN KEY (`receiver_name`) REFERENCES `user` (`user_unique_name`),
  CONSTRAINT `request_sender` FOREIGN KEY (`sender_name`) REFERENCES `user` (`user_unique_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends_list`
--

DROP TABLE IF EXISTS `friends_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends_list` (
  `friend1_name` varchar(50) NOT NULL,
  `friend2_name` varchar(50) NOT NULL,
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`friend1_name`,`friend2_name`),
  KEY `friend 2_idx` (`friend2_name`),
  CONSTRAINT `friend1` FOREIGN KEY (`friend1_name`) REFERENCES `user` (`user_unique_name`),
  CONSTRAINT `friend2` FOREIGN KEY (`friend2_name`) REFERENCES `user` (`user_unique_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends_list`
--

LOCK TABLES `friends_list` WRITE;
/*!40000 ALTER TABLE `friends_list` DISABLE KEYS */;
INSERT INTO `friends_list` VALUES ('Ahmed','Mostafa','2024-01-08 20:28:34');
/*!40000 ALTER TABLE `friends_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ItemID` int NOT NULL AUTO_INCREMENT,
  `ItemName` varchar(45) NOT NULL,
  `ItemDescription` varchar(100) DEFAULT NULL,
  `ItemPrice` int NOT NULL,
  `ItemImage` blob,
  PRIMARY KEY (`ItemID`),
  UNIQUE KEY `ItemID_UNIQUE` (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_unique_name` varchar(50) NOT NULL,
  `email` varchar(60) NOT NULL,
  `name` varchar(70) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `balance` int DEFAULT NULL,
  `dob` date NOT NULL,
  `gender` varchar(1) NOT NULL,
  PRIMARY KEY (`user_unique_name`),
  UNIQUE KEY `user_uniqe_name_UNIQUE` (`user_unique_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Users tables ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Ahmed','Hatem@yahoo.com','Ahmed Hatem','123',100000,'2000-05-15','M'),('Emad','emad@gmail.com','Emad Adel','123',500000,'2001-01-12','M'),('Mostafa','Mostafa@gmail.com','Mostafa mohamed','123',461,'1996-04-30','M'),('Sherif4','Sherif@gmail.com','Sherif Ashraf','123',100000,'1997-04-04','M');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist_items`
--

DROP TABLE IF EXISTS `wishlist_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist_items` (
  `User_name` varchar(50) NOT NULL COMMENT 'FK',
  `Item_ID` int NOT NULL,
  PRIMARY KEY (`Item_ID`,`User_name`),
  KEY `user-wishlist_idx` (`User_name`),
  CONSTRAINT `item-wishlist` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`ItemID`),
  CONSTRAINT `user-wishlist` FOREIGN KEY (`User_name`) REFERENCES `user` (`user_unique_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist_items`
--

LOCK TABLES `wishlist_items` WRITE;
/*!40000 ALTER TABLE `wishlist_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlist_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'iwish'
--

--
-- Dumping routines for database 'iwish'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-08 23:20:05
