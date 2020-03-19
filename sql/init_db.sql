-- CREATE DATABASE IF NOT EXISTS resume;

CREATE TABLE IF NOT EXISTS technology
(
    id   bigserial    not null
        constraint pkey_technology primary key,
    name varchar(255) not null
        constraint uq_technology_name unique
);

CREATE TABLE IF NOT EXISTS resume
(
    id          bigserial    not null
        constraint pkey_resume primary key,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255),
    dob         date         not null,
    gender      varchar(255) not null
);

CREATE TABLE IF NOT EXISTS contact
(
    id        bigserial    not null
        constraint pkey_contact primary key,
    text      varchar(255) not null,
    resume_id bigint       not null
        constraint fk_contact_resume references resume on delete cascade
);

CREATE TABLE IF NOT EXISTS resume_technology
(
    resume_id     bigint not null
        constraint fk_resume_technology_resume references resume on delete cascade,
    technology_id bigint not null
        constraint fk_resume_technology_technology references technology on delete cascade
);

INSERT INTO technology(id, name)
VALUES (1, 'Git'),
       (2, 'Spring Boot'),
       (3, 'HTML'),
       (4, 'JavaEE'),
       (5, 'Java Core'),
       (6, 'Maven'),
       (7, 'REST'),
       (8, 'Spring');

INSERT INTO resume(id, first_name, last_name, middle_name, dob, gender)
VALUES (1, 'Петр', 'Петров', 'Петрович', '1986-12-12', 'мужчина'),
       (2, 'Иван', 'Иванов', 'Иванович', '1997-4-4', 'мужчина'),
       (3, 'Морская', 'Мария', 'Васильевна', '1999-11-7', 'женщина');

INSERT INTO resume_technology(resume_id, technology_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 4),
       (2, 5),
       (3, 6),
       (3, 7),
       (3, 8);

INSERT INTO contact(id, text, resume_id)
VALUES (1, '+375(29)123-45-67', 1),
       (2, 'http://github.com/petya', 1),
       (3, 'petrovich@gmail.com', 1),
       (4, '+375(29)87-65-43', 2),
       (5, 'http://github.com/vanya', 2),
       (6, 'skype:ivanko', 2),
       (7, '+375(29)999-99-99', 3),
       (8, 'https://www.linkedin.com/in/mariya/', 3);