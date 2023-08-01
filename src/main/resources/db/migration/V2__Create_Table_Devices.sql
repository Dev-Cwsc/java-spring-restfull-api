CREATE TABLE IF NOT EXISTS `devices` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `device_name` varchar(255) NOT NULL,
    `installation_name` varchar(255) NOT NULL,
    `last_ch1_status` bit(1) DEFAULT NULL,
    `last_ch2_status` bit(1) DEFAULT NULL,
    `last_measurement_ch1` double DEFAULT NULL,
    `last_measurement_ch2` double DEFAULT NULL,
    `last_update` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_device_name` (`device_name`)
) ENGINE=InnoDB;