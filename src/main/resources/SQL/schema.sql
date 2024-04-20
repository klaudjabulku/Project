create database hotel;
use hotel;
create table Hotel (
hotel_id int not null primary key auto_increment,
hotel_name varchar(255) not null,
hotel_location varchar(255) not null,
NumberOfrooms int
);

create table customer(id int auto_increment primary key, name varchar(100)not null,
surname varchar(155)not null,email varchar(155)not null,phone_number varchar(15)not null);