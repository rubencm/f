/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;

CREATE TABLE IF NOT EXISTS `messages` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `message_body` longtext DEFAULT NULL,
  `poster_name` varchar(255) DEFAULT NULL,
  `num_modifications` int(11) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `last_modification_date` datetime(6) DEFAULT NULL,
  `topic_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DELETE FROM `messages`;
