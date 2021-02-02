DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    user_name  VARCHAR(250) NOT NULL,
    password   VARCHAR(250) NOT NULL,
    role       VARCHAR(250) NOT NULL,
);

INSERT INTO user (first_name, last_name, user_name, password, role)
VALUES
    -- Password: password_doe
    ('John', 'Doe', 'john_doe', '$2y$12$iuJEakvx7Huv2YmdP/CRaeWR/CPFU7wwdnq4pe.eWwsfO6cKcV6sO',
     'ADMIN'),
    -- Password: password_smith
    ('Bill', 'Smith', 'bill_smith',
     '$2y$12$UfPj8q7wsXSaSUoKxMfs0.rEgI6UhMWw2VnG7lXWPi6dtb2mV53uS ', 'ADMIN'),
    -- Password: password_miller
    ('Karen', 'Miller', 'karen_miller',
     '$2y$12$953XgXS1A8SU7ltjn9nJCem2Lq89fG5dgCihoJDDc80NSzvIThIxW ', 'ADMIN'),
    -- Password: password_sharpe
    ('Joe', 'Sharpe', 'joe_sharpe',
     '$2y$12$fEZYcS0Ks.ZJcrecZlecC.67xC09sVY73/P8fHoflwP6AYIX4J3Ha ', 'ADMIN'),
    -- Password: password_davis
    ('Jack', 'Davis', 'jack_davis',
     '$2y$12$qcETrCEkumxoDntz9WHnYO38JE.l4pvBswo4DELFurdE09fiOzfvS ', 'ADMIN'),
    -- Password: password_brown
    ('Jeremy', 'Brown', 'jeremy_brown',
     '$2y$12$J0YEfGNIfgLOuO3NRT6rA.tWKKIqSvMkNvwKk6LQABZ5XKjv/c4O2 ', 'ADMIN');

CREATE TABLE address
(
    user_id INT PRIMARY KEY,
    address VARCHAR(250) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

INSERT INTO address (user_id, address)
VALUES (1, 'address1'),
       (2, 'address2'),
       (3, 'address3'),
       (4, 'address4'),
       (5, 'address5'),
       (6, 'address6');