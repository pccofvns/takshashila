CREATE TABLE `permission` (
                        `id` bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        `name` varchar(255) NOT NULL
) ENGINE=InnoDB;
CREATE TABLE `role` (
                        `id` bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        `name` varchar(255) NOT NULL
) ENGINE=InnoDB;
CREATE TABLE `role_permission_x` (
                               `role_id` bigint(20) NOT NULL,
                               `permission_id` bigint(20) NOT NULL,
                               KEY `fk_role_id` (`role_id`),
                               KEY `fk_permission_id` (`permission_id`),
                               CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                               CONSTRAINT `fk_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB;
CREATE TABLE `user` (
                        `id` bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `credentials_non_expired` bit(1) NOT NULL,
                        `non_expired` bit(1) NOT NULL,
                        `non_locked` bit(1) NOT NULL,
                        `enabled` bit(1) NOT NULL
) ENGINE=InnoDB;
CREATE TABLE `user_role_x` (
                               `user_id` bigint(20) NOT NULL,
                               `role_id` bigint(20) NOT NULL,
                               KEY `fk_user_role_x_user_id` (`user_id`),
                               KEY `fk_user_role_x_role_id` (`role_id`),
                               CONSTRAINT `fk_user_role_x_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `fk_user_role_x_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB;
