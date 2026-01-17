CREATE TABLE student
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(50),
    std        VARCHAR(3),
    section    VARCHAR(3),
    address_id INT NOT NULL,
    CONSTRAINT fk_student_address
    FOREIGN KEY (address_id) REFERENCES address (address_id)
);

CREATE TABLE address
(
    address_id SERIAL PRIMARY KEY,
    street     varchar(30),
    city       VARCHAR(30),
    state      VARCHAR(40),
    zip        VARCHAR(10),
    country    VARCHAR(40)
);

DROP TABLE student;
DROP TABLE address;
