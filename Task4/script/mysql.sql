DROP TABLE IF EXISTS `users`;
CREATE  TABLE users (
  user_id  INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE user_roles (
  user_role_id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,
  user_id  INT NOT NULL,
  role varchar(45) NOT NULL,
 UNIQUE KEY uni_user_role (role,user_id),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `question_id` INT NOT NULL,
  `answer_text` VARCHAR(255),
  `is_correct` BOOLEAN,
  CONSTRAINT `FK_QUESTION_ID` FOREIGN KEY (`question_id`) 
  REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `test_id` INT NOT NULL,
  `question_text` VARCHAR(255),
  CONSTRAINT `FK_TEST_ID` FOREIGN KEY (`test_id`) 
  REFERENCES `test` (`test_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `test_id` INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `category_id` INT NOT NULL,
  `title` VARCHAR(255),
  CONSTRAINT `FK_CATEGORY_ID` FOREIGN KEY (`category_id`) 
  REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics` (
  `statistics_id` INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  `correct_answers` INT NOT NULL,
  `wrong_answers` INT NOT NULL,
  `date` TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO users(username,password,enabled)
VALUES ('andrew','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO user_roles (user_id, role)
VALUES (1, 'ROLE_USER');
INSERT INTO user_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role)
VALUES (2, 'ROLE_USER');