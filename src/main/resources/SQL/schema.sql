
create table Hotel (
id int not null primary key auto_increment,
name varchar(255) not null,
location varchar(255) not null,
number_of_rooms int
);

create table Customer(id int auto_increment primary key, name varchar(100)not null,
surname varchar(155)not null,email varchar(155) not null,
phone_number varchar(15)not null, location varchar (225));

create table room(id int auto_increment primary key,
room_capacity int not null,  price double,  availability boolean);

CREATE TABLE if not exists payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paypal VARCHAR(100),
    credit_card VARCHAR(100),
    coupon_code VARCHAR(50)
);

ALTER TABLE `hotel_reservation`.`room`
ADD COLUMN `hotel_id` INT NULL AFTER `availability`,
ADD INDEX `hotel_fk_idx` (`hotel_id` ASC) VISIBLE;
;
ALTER TABLE `hotel_reservation`.`room`
ADD CONSTRAINT `hotel_fk`
  FOREIGN KEY (`hotel_id`)
  REFERENCES `hotel_reservation`.`hotel` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
