CREATE TABLE `stackoverflow`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `stackoverflow`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(151) NOT NULL,
    PRIMARY KEY (`id`));

    CREATE TABLE `stackoverflow`.`tag` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `name` VARCHAR(45) NOT NULL,
      `questionId` INT NOT NULL,
      PRIMARY KEY (`id`),
      CONSTRAINT `tag_question`
        FOREIGN KEY (`questionId`)
        REFERENCES `stackoverflow`.`question` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT);