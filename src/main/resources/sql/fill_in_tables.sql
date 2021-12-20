INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Impressionism', 30, 300);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Expressionism', 20,120);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Cubism', 25, 70);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Modernism', 60, 150);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Romanticism', 27, 150);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('EPAMISM', 20, 150);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Realism', 30, 100);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Surrealism', 20, 150);
INSERT INTO exhibition_halls (name, capacity_of_paintings_on_the_wall, allowable_number_of_visitors_per_day)
VALUES ('Symbolism', 30, 100);



INSERT INTO exhibitions (name, description, begin_date, end_date, full_ticket_price, exhibition_hall_id)
VALUES ('Claude Monet',
        'Impressionism (from French impressionism and English impression in the sense of "impression") ',
        '2021-12-14', '2021-12-30', 200, 1);
INSERT INTO exhibitions (name, description, begin_date, end_date, full_ticket_price, exhibition_hall_id)
VALUES ('"The sorrow will last forever" / Vincent Van Gogh ', 'Expressionism',
        '2022-02-14', '2022-05-22', 220, 2);
INSERT INTO exhibitions (name, description, begin_date, end_date, full_ticket_price, exhibition_hall_id)
VALUES ('In art, truth is needed, but not sincerity.',
        'Modernism (from the French modern in the meaning of "modern")',
        '2021-11-01', '2022-05-24', 334, 4);
INSERT INTO exhibitions (name, description, begin_date, end_date, full_ticket_price, exhibition_hall_id)
VALUES ('EPAM FOREVER',
        'EPAM Systems is an American IT company Each product is a masterpiece of creativity',
        '2022-01-01', '2045-09-30', 500, 6);

INSERT INTO exhibition_users (email, pass, first_name, last_name, phone, role)
VALUES ('detikmua@gmail.com', '061277', 'Alex', 'Falendysh', '+38(098)890-61-86', 'ADMIN');
INSERT INTO exhibition_users (email, pass, first_name, last_name, phone)
VALUES ( 'ronet@gmail.com', 'compromise', 'Iosif', 'Nu-dic', '+38(098)000-88-77');
INSERT INTO exhibition_users (email, pass, first_name, last_name, phone)
VALUES ('dolmantin@gmail.com', 'l34f43m654', 'Kolya', 'Bortolo', '+38(050)477-44-00');
INSERT INTO exhibition_users (email, pass, first_name, last_name, phone)
VALUES ('ro4asDerino@gmail.com', 'alabama44', 'Russel', 'Morningstar', '+38(097)400-77-33');
INSERT INTO exhibition_users (email, pass, first_name, last_name, phone)
VALUES ('natashwsyawasha@gmail.com', '7766782f', 'Lock', 'Montenegrin', '+38(066)777-99-88');

INSERT INTO tickets (visit_date, order_date, ticket_type, ticket_price, user_id, exhibition_id)
VALUES ('2021-12-20', '2021-11-15', 'ADMIN', 0, 1, 1);
INSERT INTO tickets (visit_date, order_date, ticket_type, ticket_price, user_id, exhibition_id)
VALUES ('2022-03-11', '2021-11-12', 'PENSIONER', 100, 2, 2);
INSERT INTO tickets (visit_date, order_date, ticket_type, ticket_price, user_id, exhibition_id)
VALUES ('2021-11-14', '2021-10-23', 'STUDENT', 120, 3, 3);
INSERT INTO tickets (visit_date, order_date, ticket_type, ticket_price, user_id, exhibition_id)
VALUES ('2022-03-05', '2021-12-14', 'KID', 50, 4, 4);
INSERT INTO tickets (visit_date, order_date, ticket_type, ticket_price, user_id, exhibition_id)
VALUES ('2021-12-16', '2021-08-21', 'FULL', 300, 5, 1);