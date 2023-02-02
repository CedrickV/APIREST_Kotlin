CREATE TABLE `Beer` (
                        `id` int NOT NULL,
                        `alcoholByVolume` double NOT NULL,
                        `name` varchar(255) NOT NULL,
                        `brewery_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Brewery` (
                           `id` int NOT NULL,
                           `address` varchar(255) NOT NULL,
                           `city` varchar(255) NOT NULL,
                           `country` varchar(255) NOT NULL,
                           `description` varchar(255) NOT NULL,
                           `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
ALTER TABLE `Beer`
 ADD PRIMARY KEY (`id`),
 ADD KEY `FKngrtrq2tn85uo2knb5rutxygs` (`brewery_id`);
ALTER TABLE `Brewery`
 ADD PRIMARY KEY (`id`);
ALTER TABLE `Beer`
 ADD CONSTRAINT `FKngrtrq2tn85uo2knb5rutxygs` FOREIGN KEY (`brewery_id`) REFERENCES `Brewery` (`id`);
