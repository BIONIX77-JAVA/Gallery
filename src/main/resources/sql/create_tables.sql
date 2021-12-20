DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS exhibition_users;
DROP TABLE IF EXISTS exhibitions;
DROP TABLE IF EXISTS exhibition_halls;

CREATE TABLE exhibition_halls (
    id SERIAL PRIMARY KEY,
    name varchar (255) NOT NULL UNIQUE,
    capacity_of_paintings_on_the_wall int not NULL,
    allowable_number_of_visitors_per_day int NOT NULL
);

CREATE TABLE exhibitions (
    id SERIAL,
    name varchar (255) NOT NULL,
    description text,
    begin_date date NOT NULL,
    end_date date NOT NULL,
    full_ticket_price int NOT NULL CHECK (full_ticket_price >= 0),
    exhibition_hall_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (exhibition_hall_id) REFERENCES exhibition_halls (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE exhibition_users (
    id SERIAL,
    email varchar (255) NOT NULL UNIQUE,
    pass varchar (255) NOT NULL,
    first_name varchar (255) NOT NULL,
    last_name varchar (255) NOT NULL,
    phone varchar (25) NOT NULL UNIQUE,
    role varchar (255) DEFAULT 'VISITOR',
    PRIMARY KEY (id)
);


CREATE TABLE tickets (
    id SERIAL,
    visit_date date NOT NULL,
    order_date date NOT NULL DEFAULT CURRENT_DATE,
    ticket_type varchar (20) NOT NULL,
    ticket_price int NOT NULL CHECK (ticket_price >= 0),
    is_paid boolean NOT NULL DEFAULT false,
    user_id int,
    exhibition_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES exhibition_users (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (exhibition_id) REFERENCES exhibitions (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);