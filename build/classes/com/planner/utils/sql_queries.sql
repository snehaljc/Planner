-- Create database
CREATE DATABASE plannerdb;

-- Use created database
USE plannerdb;

-- Create table for users
create table users (
 id int NOT NULL AUTO_INCREMENT,
 fname varchar(120) NOT NULL,
 lname varchar(120) NOT NULL,
 email varchar(220) NOT NULL,
 pwd varchar(220) NOT NULL,
 PRIMARY KEY (id)
);

