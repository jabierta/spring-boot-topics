DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    user_name  VARCHAR(250) NOT NULL,
    password   VARCHAR(250) NOT NULL,
    role       VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS address;

CREATE TABLE address
(
    user_id INT PRIMARY KEY,
    address VARCHAR(250) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);
