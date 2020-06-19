-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: arabaservisi
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `arac`
--

DROP TABLE IF EXISTS `arac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arac` (
  `arac_plaka` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mod_id` int NOT NULL,
  `arac_yıl` int NOT NULL,
  `arac_km` int NOT NULL,
  PRIMARY KEY (`arac_plaka`),
  KEY `mod_id` (`mod_id`),
  CONSTRAINT `arac_ibfk_1` FOREIGN KEY (`mod_id`) REFERENCES `model` (`mod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arac`
--

LOCK TABLES `arac` WRITE;
/*!40000 ALTER TABLE `arac` DISABLE KEYS */;
INSERT INTO `arac` VALUES ('05k55',10,2000,200000),('06adn06',16,2017,25000),('07arf07',3,2017,20000),('34adn96',9,2015,50000),('34k99',4,2015,45000),('34kr65',10,2014,25000),('35alp88',3,2010,120000),('35mst66',8,2018,12000),('41k78',15,2012,70000);
/*!40000 ALTER TABLE `arac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arac_olan_musteri`
--

DROP TABLE IF EXISTS `arac_olan_musteri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arac_olan_musteri` (
  `mus_id` int NOT NULL,
  `arac_plaka` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE KEY `arac_plaka` (`arac_plaka`,`mus_id`),
  KEY `mus_id` (`mus_id`),
  CONSTRAINT `arac_olan_musteri_ibfk_1` FOREIGN KEY (`mus_id`) REFERENCES `musteri` (`mus_id`),
  CONSTRAINT `arac_olan_musteri_ibfk_2` FOREIGN KEY (`arac_plaka`) REFERENCES `arac` (`arac_plaka`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arac_olan_musteri`
--

LOCK TABLES `arac_olan_musteri` WRITE;
/*!40000 ALTER TABLE `arac_olan_musteri` DISABLE KEYS */;
INSERT INTO `arac_olan_musteri` VALUES (1,'07arf07'),(2,'07arf07'),(6,'34adn96'),(7,'41k78'),(8,'35mst66'),(9,'34k99'),(10,'34kr65'),(11,'06adn06'),(12,'05k55');
/*!40000 ALTER TABLE `arac_olan_musteri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bakim`
--

DROP TABLE IF EXISTS `bakim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bakim` (
  `bak_id` int NOT NULL AUTO_INCREMENT,
  `ser_id` int NOT NULL,
  `bak_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bak_id`),
  KEY `ser_id` (`ser_id`),
  CONSTRAINT `bakim_ibfk_1` FOREIGN KEY (`ser_id`) REFERENCES `servis` (`ser_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bakim`
--

LOCK TABLES `bakim` WRITE;
/*!40000 ALTER TABLE `bakim` DISABLE KEYS */;
INSERT INTO `bakim` VALUES (1,1,'periyodik bakım'),(2,2,'periyodik bakım'),(3,3,'periyodik bakım'),(4,1,'boya bakım'),(5,2,'boya bakım'),(6,3,'boya bakım'),(7,1,'far bakım'),(8,2,'far bakım'),(9,3,'far bakım'),(10,1,'cam bakım'),(11,2,'cam bakım'),(12,3,'cam bakım'),(13,1,'elektronik bakım'),(14,2,'elektronik bakım'),(15,3,'elektronik bakım'),(16,1,'lastik bakım'),(17,2,'lastik bakım'),(18,3,'lastik bakım'),(19,1,'motor bakım'),(20,2,'motor bakım'),(21,3,'motor bakım'),(22,1,'yağ bakım'),(23,2,'yağ bakım'),(24,3,'yağ bakım'),(25,1,'debriyaj bakım'),(26,2,'debriyaj bakım'),(27,3,'debriyaj bakım'),(28,1,'balata bakım'),(29,2,'balata bakım'),(30,3,'balata bakım');
/*!40000 ALTER TABLE `bakim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bakim_giren_arac`
--

DROP TABLE IF EXISTS `bakim_giren_arac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bakim_giren_arac` (
  `arac_plaka` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bak_id` int NOT NULL,
  `giris_tarih` datetime NOT NULL,
  `bakim_neden` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bakim_durum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mus_id` int DEFAULT NULL,
  UNIQUE KEY `arac_plaka` (`arac_plaka`,`bak_id`),
  KEY `bak_id` (`bak_id`),
  KEY `mus_id` (`mus_id`),
  CONSTRAINT `bakim_giren_arac_ibfk_1` FOREIGN KEY (`arac_plaka`) REFERENCES `arac` (`arac_plaka`),
  CONSTRAINT `bakim_giren_arac_ibfk_2` FOREIGN KEY (`bak_id`) REFERENCES `bakim` (`bak_id`),
  CONSTRAINT `bakim_giren_arac_ibfk_3` FOREIGN KEY (`mus_id`) REFERENCES `musteri` (`mus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bakim_giren_arac`
--

LOCK TABLES `bakim_giren_arac` WRITE;
/*!40000 ALTER TABLE `bakim_giren_arac` DISABLE KEYS */;
INSERT INTO `bakim_giren_arac` VALUES ('06adn06',12,'2020-05-25 20:04:06','cam kırılması','mal kargoda',11),('07arf07',2,'2020-05-22 21:29:28','periyodik bakım','parça bekleniyor',2),('34k99',12,'2020-05-25 21:21:24','cam kırılması','tamirde',9),('34kr65',23,'2020-05-25 17:38:59','yağ azalması','tamirde',10),('41k78',5,'2020-05-24 22:29:18','boya aşınması','boyanıyor',7);
/*!40000 ALTER TABLE `bakim_giren_arac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `birim`
--

DROP TABLE IF EXISTS `birim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `birim` (
  `birim_id` int NOT NULL AUTO_INCREMENT,
  `birim_ad` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`birim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `birim`
--

LOCK TABLES `birim` WRITE;
/*!40000 ALTER TABLE `birim` DISABLE KEYS */;
INSERT INTO `birim` VALUES (1,'adet'),(2,'kg'),(3,'cm'),(4,'m'),(5,'lt'),(6,'mlt');
/*!40000 ALTER TABLE `birim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bulunan_malzeme`
--

DROP TABLE IF EXISTS `bulunan_malzeme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulunan_malzeme` (
  `bmal_id` int NOT NULL AUTO_INCREMENT,
  `ser_id` int NOT NULL,
  `mal_id` int NOT NULL,
  `miktar` int NOT NULL,
  PRIMARY KEY (`bmal_id`),
  KEY `ser_id` (`ser_id`),
  KEY `mal_id` (`mal_id`),
  CONSTRAINT `bulunan_malzeme_ibfk_1` FOREIGN KEY (`ser_id`) REFERENCES `servis` (`ser_id`),
  CONSTRAINT `bulunan_malzeme_ibfk_2` FOREIGN KEY (`mal_id`) REFERENCES `malzeme` (`mal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bulunan_malzeme`
--

LOCK TABLES `bulunan_malzeme` WRITE;
/*!40000 ALTER TABLE `bulunan_malzeme` DISABLE KEYS */;
INSERT INTO `bulunan_malzeme` VALUES (1,1,1,50),(2,1,2,50),(3,1,3,50),(4,1,4,46),(5,1,5,47),(6,2,1,100),(7,2,2,99),(8,2,3,114),(9,2,4,230),(10,2,5,73),(11,3,1,70),(12,3,2,66),(13,3,3,62),(14,3,4,47),(15,3,5,59),(18,1,6,200),(19,2,6,78),(20,3,6,100);
/*!40000 ALTER TABLE `bulunan_malzeme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calisan`
--

DROP TABLE IF EXISTS `calisan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calisan` (
  `cal_id` int NOT NULL AUTO_INCREMENT,
  `ser_id` int NOT NULL,
  `cal_ad` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cal_soyad` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cal_maas` int NOT NULL,
  `telefon` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kidem` int NOT NULL,
  PRIMARY KEY (`cal_id`),
  KEY `ser_id` (`ser_id`),
  CONSTRAINT `calisan_ibfk_1` FOREIGN KEY (`ser_id`) REFERENCES `servis` (`ser_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calisan`
--

LOCK TABLES `calisan` WRITE;
/*!40000 ALTER TABLE `calisan` DISABLE KEYS */;
INSERT INTO `calisan` VALUES (1,1,'mehmet','yıldırım',5000,'5345698775',4),(2,1,'zeki','çevik',4500,'5216989563',2),(3,1,'erdem','aslan',6200,'5327455449',7),(4,2,'emre','kılıç',6000,'5463212365',2),(5,2,'ahmet','alkan',8000,'5412147445',4),(6,2,'veli','sarı',4900,'5326326598',1),(7,3,'tolga','yıldız',5300,'5347546986',3),(8,3,'tunahan','eroğlu',4800,'5362426289',4),(9,3,'taha','tarık',6900,'5353697410',5);
/*!40000 ALTER TABLE `calisan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faturalar`
--

DROP TABLE IF EXISTS `faturalar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faturalar` (
  `fat_id` int NOT NULL AUTO_INCREMENT,
  `mus_id` int NOT NULL,
  `ser_id` int NOT NULL,
  `tutar` int NOT NULL,
  `odeme_durum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bak_id` int DEFAULT NULL,
  `kesilme_tarih` datetime DEFAULT NULL,
  PRIMARY KEY (`fat_id`),
  KEY `mus_id` (`mus_id`),
  KEY `ser_id` (`ser_id`),
  KEY `bak_id` (`bak_id`),
  CONSTRAINT `faturalar_ibfk_1` FOREIGN KEY (`mus_id`) REFERENCES `musteri` (`mus_id`),
  CONSTRAINT `faturalar_ibfk_2` FOREIGN KEY (`ser_id`) REFERENCES `servis` (`ser_id`),
  CONSTRAINT `faturalar_ibfk_3` FOREIGN KEY (`bak_id`) REFERENCES `bakim` (`bak_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faturalar`
--

LOCK TABLES `faturalar` WRITE;
/*!40000 ALTER TABLE `faturalar` DISABLE KEYS */;
INSERT INTO `faturalar` VALUES (10,2,2,1500,'odenmedi',2,'2020-05-23 19:07:04'),(15,9,3,4500,'odendi',12,NULL),(16,7,2,400,'odendi',5,'2020-05-24 22:29:18'),(19,10,2,70,'odendi',23,'2020-05-25 17:38:59'),(20,11,3,1500,'odendi',12,'2020-05-25 20:04:06'),(21,9,3,4500,'odenmedi',12,'2020-05-25 21:21:24'),(22,9,3,4600,'odendi',9,'2020-05-25 21:21:41');
/*!40000 ALTER TABLE `faturalar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici` (
  `cal_id` int NOT NULL,
  `kullanici_ad` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sifre` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`kullanici_ad`),
  KEY `cal_id` (`cal_id`),
  CONSTRAINT `kullanici_ibfk_1` FOREIGN KEY (`cal_id`) REFERENCES `calisan` (`cal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici`
--

LOCK TABLES `kullanici` WRITE;
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` VALUES (4,'emreklc','1234'),(1,'mhmtyldrm','12345'),(2,'veritabanı','1234');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `malzeme`
--

DROP TABLE IF EXISTS `malzeme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `malzeme` (
  `mal_id` int NOT NULL AUTO_INCREMENT,
  `birim_id` int NOT NULL,
  `barkod` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gelis_fiyat` decimal(9,2) NOT NULL,
  `satis_fiyat` decimal(9,2) NOT NULL,
  `mal_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`mal_id`),
  KEY `birim_id` (`birim_id`),
  CONSTRAINT `malzeme_ibfk_1` FOREIGN KEY (`birim_id`) REFERENCES `birim` (`birim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `malzeme`
--

LOCK TABLES `malzeme` WRITE;
/*!40000 ALTER TABLE `malzeme` DISABLE KEYS */;
INSERT INTO `malzeme` VALUES (1,1,'1453252659878',1500.00,2000.00,'kaporta'),(2,5,'1254698523654',50.00,70.00,'yağ'),(3,1,'1456323698785',1000.00,1500.00,'cam'),(4,1,'1234568965654',300.00,500.00,'lastik'),(5,1,'1457896587412',2000.00,2300.00,'far'),(6,5,'2113263254853',100.00,200.00,'boya');
/*!40000 ALTER TABLE `malzeme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marka`
--

DROP TABLE IF EXISTS `marka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marka` (
  `mar_id` int NOT NULL AUTO_INCREMENT,
  `mar_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`mar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marka`
--

LOCK TABLES `marka` WRITE;
/*!40000 ALTER TABLE `marka` DISABLE KEYS */;
INSERT INTO `marka` VALUES (1,'alfa romeo'),(2,'audi'),(3,'bmw'),(4,'chevrolet'),(5,'daica'),(6,'fiat'),(7,'honda'),(8,'opel');
/*!40000 ALTER TABLE `marka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `mod_id` int NOT NULL AUTO_INCREMENT,
  `mod_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mar_id` int NOT NULL,
  PRIMARY KEY (`mod_id`),
  KEY `mar_id` (`mar_id`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`mar_id`) REFERENCES `marka` (`mar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,'giulietta',1),(2,'stelvio',1),(3,'a5',2),(4,'q7',2),(5,'320',2),(6,'520',2),(7,'320',3),(8,'520',3),(9,'camaro',4),(10,'cruze',4),(11,'duster',5),(12,'sandero',5),(13,'500x',6),(14,'egea',6),(15,'civic',7),(16,'crv',7),(17,'astra',8),(18,'insignia',8);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musteri`
--

DROP TABLE IF EXISTS `musteri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musteri` (
  `mus_id` int NOT NULL AUTO_INCREMENT,
  `tc_no` decimal(11,0) NOT NULL,
  `mus_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mus_soyad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telefon` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`mus_id`),
  UNIQUE KEY `tc_no` (`tc_no`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musteri`
--

LOCK TABLES `musteri` WRITE;
/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
INSERT INTO `musteri` VALUES (1,21367556987,'arif','özbek','5439148002','arfzbk7@gmail.com'),(2,25698775896,'burak','selim','5496852336','burakselim@gmail.com'),(3,24252326147,'adem','kerim','5326987556','admkrm@gmail.com'),(5,23698996325,'alper','yılmaz','5693245698','alper@gmail.com'),(6,24365998659,'adnan','koç','5693214785','adnkc@hotmail.com'),(7,21367889658,'merve','eroğlu','5369656874','mrve@gmail.com'),(8,23896325896,'mesut','yılmaz','5478963225','mest@outlook.com'),(9,23696568956,'ahmet','aslan','5436985002','ahmtasln@hotmail.com'),(10,23683696325,'kerem','osman','5469878963','kerem@gmail.com'),(11,24547896569,'adnan','yıldız','5784789632','adnyldz@gmail.com'),(12,23686998965,'ezel','kamil','5436987889','kamil@gmail.com');
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servis`
--

DROP TABLE IF EXISTS `servis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servis` (
  `ser_id` int NOT NULL AUTO_INCREMENT,
  `ser_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ser_il` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ser_adres` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telefon` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `yonetici_no` int DEFAULT NULL,
  PRIMARY KEY (`ser_id`),
  KEY `yonetici_no` (`yonetici_no`),
  CONSTRAINT `servis_ibfk_1` FOREIGN KEY (`yonetici_no`) REFERENCES `calisan` (`cal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servis`
--

LOCK TABLES `servis` WRITE;
/*!40000 ALTER TABLE `servis` DISABLE KEYS */;
INSERT INTO `servis` VALUES (1,'eroğlu','kocaeli','izmit','2123456967',3),(2,'yılmazlar','istanbul','kadıköy','2423569856',5),(3,'ozbekler','antalya','muratpasa','2413214257',9);
/*!40000 ALTER TABLE `servis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuketilen_malzeme`
--

DROP TABLE IF EXISTS `tuketilen_malzeme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuketilen_malzeme` (
  `tuk_id` int NOT NULL AUTO_INCREMENT,
  `mal_id` int NOT NULL,
  `bak_id` int NOT NULL,
  `mus_id` int NOT NULL,
  `miktar` int NOT NULL,
  PRIMARY KEY (`tuk_id`),
  KEY `mal_id` (`mal_id`),
  KEY `mus_id` (`mus_id`),
  KEY `bak_id` (`bak_id`),
  CONSTRAINT `tuketilen_malzeme_ibfk_1` FOREIGN KEY (`mal_id`) REFERENCES `malzeme` (`mal_id`),
  CONSTRAINT `tuketilen_malzeme_ibfk_3` FOREIGN KEY (`mus_id`) REFERENCES `musteri` (`mus_id`),
  CONSTRAINT `tuketilen_malzeme_ibfk_4` FOREIGN KEY (`bak_id`) REFERENCES `bakim` (`bak_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuketilen_malzeme`
--

LOCK TABLES `tuketilen_malzeme` WRITE;
/*!40000 ALTER TABLE `tuketilen_malzeme` DISABLE KEYS */;
INSERT INTO `tuketilen_malzeme` VALUES (1,5,9,1,2),(2,5,8,2,2),(3,2,30,1,2),(4,5,9,1,2),(5,3,11,6,1),(6,4,18,7,3),(7,5,9,1,2),(8,3,11,1,3),(9,4,16,8,4),(10,5,8,2,2),(11,3,11,2,1),(12,3,2,2,1),(13,5,9,2,3),(17,3,12,9,3),(18,6,5,7,2),(21,2,23,10,1),(22,3,12,11,1),(23,3,12,9,3),(24,5,9,9,2);
/*!40000 ALTER TABLE `tuketilen_malzeme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-30 10:52:30
