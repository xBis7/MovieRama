DROP DATABASE IF EXISTS MovieRamaDB;
CREATE DATABASE MovieRamaDB;
USE MovieRamaDB;

CREATE TABLE User 
(
	UserId bigint NOT NULL AUTO_INCREMENT,  
	Username varchar(255) NOT NULL UNIQUE,
    Email varchar(255) NOT NULL,
    HashedPassword varchar(255) NOT NULL,
    Salt varchar(1024),
    PRIMARY KEY (UserId)
);

CREATE TABLE Movie 
(
	MovieId bigint NOT NULL AUTO_INCREMENT,
    Title varchar(255) NOT NULL DEFAULT 'No title',
    Description longtext NOT NULL,
    UserId bigint,
    Date timestamp DEFAULT CURRENT_TIMESTAMP,
    Likes bigint DEFAULT 0,
    Hates bigint DEFAULT 0,
    PRIMARY KEY (MovieId),
    FOREIGN KEY (UserId) REFERENCES User(UserId)
);

CREATE TABLE Vote
(
	VoteId bigint NOT NULL AUTO_INCREMENT,
    MovieId bigint,
    UserId bigint,
    Positive boolean,
    PRIMARY KEY (VoteId),
    FOREIGN KEY (MovieId) REFERENCES Movie(MovieId),
    FOREIGN KEY (UserId) REFERENCES User(UserId),
    CONSTRAINT movie_likes UNIQUE(MovieId, UserId)
);
