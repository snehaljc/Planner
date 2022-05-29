-- Create database
CREATE DATABASE plannerdb;

-- CREATE DATABASE plannerdb
USE plannerdb;

create table Users (
 id int NOT NULL AUTO_INCREMENT,
 Fname varchar(120) NOT NULL,
 Lname varchar(120) NOT NULL,
 Email varchar(220) NOT NULL,
 Pwd varchar(220) NOT NULL,
 PRIMARY KEY (id)
);

create table Locations (
 id int NOT NULL AUTO_INCREMENT,
 City varchar(100) NOT NULL,
 Area varchar(100) NOT NULL,
 PRIMARY KEY (id)
);

CREATE TABLE Events (
    id INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(200) NOT NULL,
    LocationId INT NOT NULL,
    CreatedBy INT NOT NULL,
    Address VARCHAR(400) NOT NULL,
    Date DATE NOT NULL,
    Time DATETIME NOT NULL,
    Description VARCHAR(800) NOT NULL,
    Fee INT NULL,
    Public BOOL DEFAULT TRUE,
    CreatedOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastModifiedOn DATETIME NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_LocationId FOREIGN KEY (LocationId)
        REFERENCES Locations (id),
    CONSTRAINT FK_UserId FOREIGN KEY (CreatedBy)
        REFERENCES users (id)
);


