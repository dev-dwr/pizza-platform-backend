INSERT INTO pizza (name, dough, sauce, size, quantity, bucket_id, price)
VALUES ('Classic Italian', 'ITALIAN', 'TOMATO', 'LARGE', 2, (SELECT id FROM bucket WHERE email = 'example1@email.com'),
        20),
       ('Spicy Polish', 'POLISH', 'PAPRIKA', 'MEGA_LARGE', 1,
        (SELECT id FROM bucket WHERE email = 'example2@email.com'), 22);