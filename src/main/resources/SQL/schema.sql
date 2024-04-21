
create table Hotel (
id int not null primary key auto_increment,
name varchar(255) not null,
location varchar(255) not null,
number_of_rooms int
);

create table Customer(id int auto_increment primary key, name varchar(100)not null,
surname varchar(155)not null,email varchar(155) not null,
phone_number varchar(15)not null, location varchar (225));

create table room(id int, room_capacity int, price double, availability boolean, hotel_id int);

CREATE TABLE if not exists payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paypal VARCHAR(100),
    credit_card VARCHAR(100),
    coupon_code VARCHAR(50)
);
create table reservations(room_id int,customer_id int,start_date date,
  end_date date,total_price double,status boolean);
