/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;

CREATE TABLE IF NOT EXISTS `forums` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `num_topics` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DELETE FROM `forums`;

INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('07dac9eb-b82a-486b-b44a-27534bac7c10', 'General', 'Foro general', 'fal fa-comments', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('08a7c71f-0a43-4a3c-9301-15a8f070958f', 'Alimentación', 'Foro de alimentación', 'fas fa-utensils', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('0926e25d-4721-4784-9eed-86b717688919', 'Mascotas', 'Foro de mascotas', 'fas fa-paw', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('209d5b79-e133-4b04-9f1a-29bc3542ebc0', 'Viajes', 'Foro de viajes', 'fas fa-plane-departure', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('245b3fbd-53bd-4beb-997d-9faa2b4f1c95', 'Inversiones', 'Foro de inversiones', 'fas fa-chart-line', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('255d6925-dd33-4635-b074-f4e7c7e5b2bd', 'Videojuegos', 'Foro de videojuegos', 'fas fa-gamepad', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('3e8b1d2c-2d43-4feb-b1e9-3553e01e6020', 'Política', 'Foro de política', 'fas fa-landmark', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('416ddf51-d2dc-4cd5-82d9-8a2f771fa3c1', 'Actualidad', 'Foro de actualidad', 'fas fa-newspaper', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('4b111265-e8e7-4415-b8c4-1fb3c8a7b6ec', 'Deporte', 'Foro de deportes', 'far fa-futbol', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('90b06c40-63de-4b05-9606-d1f6cd9b93a8', 'Tecnología', 'Foro de tecnología', 'fas fa-microchip', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('a065aafa-7643-4f8f-9091-c3ff61facec8', 'Offtopic', 'Sin categorizar', 'fas fa-question', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('ac3451bc-24a3-4de5-a3bb-e24672964c67', 'Salud', 'Foro de salud', 'fas fa-heartbeat', 0);
INSERT INTO `forums` (`id`, `name`, `description`, `icon`, `num_topics`) VALUES
	('ef6e8e59-8dc7-4fad-97b5-7b6eac9c4515', 'Televisión', 'Foro de televisión', 'fas fa-tv', 0);
