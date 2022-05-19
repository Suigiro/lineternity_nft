CREATE TABLE IF NOT EXISTS `buffer_schemes` (
  `object_id` INT UNSIGNED NOT NULL DEFAULT '0',
  `scheme_name` VARCHAR(255) NOT NULL DEFAULT 'default',
  `skills` VARCHAR(255) NOT NULL
);