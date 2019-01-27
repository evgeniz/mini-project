create database if not exists mini_project;
create table if not exists `mini_project`.`documents` (
  `id` VARCHAR(32) NOT NULL,
  `hash` VARCHAR(36) NOT NULL,
  `description` VARCHAR(2048) NULL,
  `language` VARCHAR(32) NULL,
  `format` VARCHAR(256) NOT NULL,
  `url` VARCHAR(1024) NOT NULL,
  `title` VARCHAR(256) NOT NULL,
  `document_of` VARCHAR(64) NOT NULL,
  `date_published` VARCHAR(32) NOT NULL,
  `type` VARCHAR(32) NULL,
  `date_modified` VARCHAR(32) NOT NULL,
  `related_item` VARCHAR(32) NULL,
  `contract_id` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC) VISIBLE);
