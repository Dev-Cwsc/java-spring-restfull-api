CREATE TABLE IF NOT EXISTS `device` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `device_name` varchar(255) NOT NULL,
    `installation_name` varchar(255) NOT NULL,
    `last_ch1_status` bit(1) DEFAULT NULL,
    `last_ch2_status` bit(1) DEFAULT NULL,
    `last_measurement_ch1` double DEFAULT NULL,
    `last_measurement_ch2` double DEFAULT NULL,
    `last_update` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_p5gu5ocugwvuxay0m9u34560m` (`device_name`)
);

CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `login` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ew1hvam8uwaknuaellwhqchhb` (`login`)
);