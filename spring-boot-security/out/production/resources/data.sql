INSERT INTO user (first_name, last_name, user_name, password, role)
VALUES
    -- Password: password_doe , b64: cGFzc3dvcmRfZG9l
    ('John', 'Doe', 'john_doe', '$2y$12$iuJEakvx7Huv2YmdP/CRaeWR/CPFU7wwdnq4pe.eWwsfO6cKcV6sO',
     'ADMIN'),
    -- Password: password_smith, b64: cGFzc3dvcmRfc21pdGg=
    ('Bill', 'Smith', 'bill_smith',
     '$2y$12$UfPj8q7wsXSaSUoKxMfs0.rEgI6UhMWw2VnG7lXWPi6dtb2mV53uS', 'USER'),
    -- Password: password_miller
    ('Karen', 'Miller', 'karen_miller',
     '$2y$12$953XgXS1A8SU7ltjn9nJCem2Lq89fG5dgCihoJDDc80NSzvIThIxW', 'USER'),
    -- Password: password_sharpe
    ('Joe', 'Sharpe', 'joe_sharpe',
     '$2y$12$fEZYcS0Ks.ZJcrecZlecC.67xC09sVY73/P8fHoflwP6AYIX4J3Ha', 'USER'),
    -- Password: password_davis
    ('Jack', 'Davis', 'jack_davis',
     '$2y$12$qcETrCEkumxoDntz9WHnYO38JE.l4pvBswo4DELFurdE09fiOzfvS', 'USER'),
    -- Password: password_brown
    ('Jeremy', 'Brown', 'jeremy_brown',
     '$2y$12$J0YEfGNIfgLOuO3NRT6rA.tWKKIqSvMkNvwKk6LQABZ5XKjv/c4O2', 'USER');


INSERT INTO address (user_id, address)
VALUES (1, 'address1'),
       (2, 'address2'),
       (3, 'address3'),
       (4, 'address4'),
       (5, 'address5'),
       (6, 'address6');