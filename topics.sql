/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;

CREATE TABLE IF NOT EXISTS `topics` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `last_poster_name` varchar(255) DEFAULT NULL,
  `last_post_date` datetime(6) DEFAULT NULL,
  `num_messages` int(11) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `forum_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DELETE FROM `topics`;
