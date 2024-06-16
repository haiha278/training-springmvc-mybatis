CREATE TABLE User
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(100),
    age      INT,
    gender   VARCHAR(10),
    dob      DATE,
    role_id  INT,
    FOREIGN KEY (role_id) REFERENCES role (id)
);

create table Role
(
    id        int primary key auto_increment,
    role_name VARCHAR(50) NOT NULL unique
)