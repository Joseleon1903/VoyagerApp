--liquibase formatted sql

--changeset yinfante:1
CREATE SEQUENCE hibernate_sequence
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START WITH 1
CACHE 1;


--changeset jedeleon:2
 CREATE TABLE profile
 (
   id         BIGINT PRIMARY KEY NOT NULL,
   first_Name   VARCHAR(255) NOT NULL,
   last_Name   VARCHAR(255) NOT NULL,
   email       VARCHAR(255) NOT NULL
 );
--changeset jedeleon:3
 CREATE TABLE role
 (
   id         BIGINT PRIMARY KEY NOT NULL,
   name      VARCHAR(255) NOT NULL
 );

--changeset jedeleon:4
 CREATE TABLE usersys
 (
   id         BIGINT PRIMARY KEY NOT NULL,
   username   VARCHAR(255) NOT NULL,
   encripted_password   VARCHAR(255) NOT NULL,
   profile_id  BIGINT NOT NULL,
   CONSTRAINT fk_user_profile FOREIGN KEY (profile_id) REFERENCES profile(id)
 );

--changeset jedeleon:5
  CREATE TABLE user_role
 (
   id              BIGINT PRIMARY KEY NOT NULL,
   usersys_id      BIGINT  NOT NULL,
   role_id         BIGINT  NOT NULL,
   CONSTRAINT fk_role_usersys FOREIGN KEY (role_id) REFERENCES role(id),
   CONSTRAINT fk_usersys_role FOREIGN KEY (usersys_id) REFERENCES usersys(id)
 );

 --changeset jedeleon:6
insert into profile values(1, 'admin', 'admin', 'admin@localhost.com');
insert into role values(1, 'ROLE_ADMIN');
insert into role values(2, 'ROLE_USER');
insert into role values(3, 'ROLE_GUEST');
insert into usersys values(1, 'administrador', 'encriptpass', 1);
insert into user_role values(1, 1, 1);


