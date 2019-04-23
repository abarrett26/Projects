drop database if exists GameNight;
create database GameNight;

use GameNight;

create table users (
userId int primary key auto_increment,
username varchar(30) not null unique,
`password` varchar(100) not null,
enabled boolean not null,
imagePath varchar(50)
);

create table characters(
characterId int primary key auto_increment,
userId int not null,
foreign key (userId) references users (userId),
`name` varchar(30) not null,
`description` varchar(100) not null,
`type` varchar(30) not null,
`level` int unsigned not null,
specialAbility varchar(50) not null,
equipment varchar(50) not null,
imagePath varchar(100)
);

create table games(
gameId int primary key auto_increment,
`name` varchar(100) not null,
`description` varchar(100) not null,
`active` boolean not null
);

create table `groups` (
groupId int primary key auto_increment,
`name` varchar(100) not null,
`description` varchar(100) not null,
acceptingPlayers boolean not null
);

create table characterGroups (
characterGroupId int primary key auto_increment,
groupId int not null,
foreign key (groupId) references `groups` (groupId),
 characterId int not null,
 foreign key (characterId) references characters (characterId)
);

create table groupUsers (
groupUserId int primary key auto_increment,
groupId int not null,
foreign key (groupId) references `groups` (groupId),
userId int not null,
foreign key (userId) references users (userId)
);

create table gameUsers (
gameUserId int primary key auto_increment,
gameId int not null,
foreign key (gameId) references games (gameId),
userId int not null,
foreign key (userId) references users (userId)
);

create table `roles`(
roleId int primary key auto_increment,
`role` varchar(30) not null
);

create table userRoles(
userId int not null,
roleId int not null,
primary key(userId,roleId),
foreign key (userId) references users (userId),
foreign key (roleId) references roles (roleId));

insert into users (userId, username, `password`, enabled, imagePath)
    values(1,"admin","password",true,"images/admin.jpg"),
        (2,"user","password",true,"images/french.jpg"),
        (3,"CharTest","password",true,"images/cat.jpg"),
        (4,"Erin","password", true,"images/dabbing-mermaid.jpg"),
        (5,"Alex","password", true,"images/alien.jpg"),
        (6,"Thomas","password", true,"images/dance.gif"),
        (7,"imgTest","test",true,"images/test.png");

insert into characters(userId, `name`, `description`, `type`, `level`, specialAbility, equipment, imagePath)
	values(2,"Tiamat", "Fearsome multi-headed winged beast", "Dragon", 50, "Fire-Breathing and Flight", "Claws", "images/tiamat.jpg"),
    (3,"Ducky", "It's a floating yellow duck", "Duck", 2, "Floats, non-judgemental listener", "Quack", "images/duck.jpg");

insert into `roles`(roleId,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_USER");
    
insert into userRoles (userId, roleId)
    values(1,1),(1,2),(2,2),(3,2),(4,1),(5,2),(6,1);
    
insert into `groups` (groupId, `name`, `description`, acceptingPlayers) values (1, "Erin's group","Group that meets at Erin's house once a week", 1);
insert into `groups` (groupId, `name`, `description`, acceptingPlayers) values (2, "Alex's group","Group that meets at Alex's house once a week", 0);
insert into `groups` (groupId, `name`, `description`, acceptingPlayers) values (3, "Thomas' group","Group that meets at Thomas' house once a week", 1);

insert into `games` (gameId, `name`, `description`, `active`) values (1, "Mission Impossible", "A very difficult game!", 1);
insert into `games` (gameId, `name`, `description`, `active`) values (2, "Mission Almost Possible", "A moderately difficult game!", 1);
insert into `games` (gameId, `name`, `description`, `active`) values (3, "Mission Easy Peasy Lemon Squeezy", "A very easy game!", 0);
    
insert into characterGroups (characterGroupId, groupId, characterId) values (1, 1, 1);
insert into characterGroups (characterGroupId, groupId, characterId) values (2, 2, 2);
insert into characterGroups (characterGroupId, groupId, characterId) values (3, 3, 1);

insert into groupUsers (groupUserId, groupId, userId) values(1, 1, 2);
insert into groupUsers (groupUserId, groupId, userId) values(2, 2, 3);
insert into groupUsers (groupUserId, groupId, userId) values(3, 3, 2);

insert into gameUsers (gameUserId, gameId, userId) values (1, 1, 1);
insert into gameUsers (gameUserId, gameId, userId) values (2, 2, 2);    
insert into gameUsers (gameUserId, gameId, userId) values (3, 3, 3);    
    
UPDATE users SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE userId = 1;
UPDATE users SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE userId = 2;
UPDATE users SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE userId = 3;
UPDATE users SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE userId = 4;
UPDATE users SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE userId = 5;
UPDATE users SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE userId = 6;