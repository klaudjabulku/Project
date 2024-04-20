
create table Hotel (
hotel_id int not null primary key auto_increment,
hotel_name varchar(255) not null,
hotel_location varchar(255) not null,
NumberOfrooms int
);

create table Customer(id int auto_increment primary key, name varchar(100)not null,
surname varchar(155)not null,email varchar(155)not null,phone_number varchar(15)not null, hotel_location varchar (225));

create table room(id int auto_increment primary key,
room_capacity int not null,  price double,  availability boolean);

CREATE TABLE if not exists payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paypal VARCHAR(100),
    credit_card VARCHAR(100),
    coupon_code VARCHAR(50)
);