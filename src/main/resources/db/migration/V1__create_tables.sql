CREATE TABLE IF NOT EXISTS bucket (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(255) NOT NULL,
                        current_status ENUM('INIT', 'START', 'FINISH') NOT NULL DEFAULT 'INIT',
                        delivery ENUM('ON_PIZZA_PLACE', 'ON_YOUR_HOME') NOT NULL DEFAULT 'ON_PIZZA_PLACE',
                        price INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS pizza (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       dough ENUM('ITALIAN', 'POLISH', 'AMERICAN') NOT NULL,
                       sauce ENUM('TOMATO', 'CHEESE', 'TOMATO_CHEESE', 'PAPRIKA') NOT NULL,
                       size ENUM('SMALL', 'MEDIUM', 'LARGE', 'MEGA_LARGE') NOT NULL,
                       quantity INT DEFAULT 0,
                       bucket_id BIGINT,
                       price INT NOT NULL,
                       FOREIGN KEY (bucket_id) REFERENCES bucket(id)
);


CREATE TABLE  IF NOT EXISTS ingredient (
                            id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            ingredient ENUM('HAM', 'BACON', 'SALAMI', 'MUSHROOMS', 'TOMATO', 'PINEAPPLE', 'CHILLI') NOT NULL
);

CREATE TABLE IF NOT EXISTS pizza_ingredients (
                                   pizza_id BIGINT NOT NULL,
                                   ingredient_id BIGINT NOT NULL,
                                   PRIMARY KEY (pizza_id, ingredient_id),
                                   FOREIGN KEY (pizza_id) REFERENCES pizza(id) ON DELETE CASCADE,
                                   FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);
