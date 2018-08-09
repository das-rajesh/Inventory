drop database  if exists gearupnepal ;
create database gearupnepal;

use gearupnepal

create table login(
id int primary key auto_increment,
user_name varchar(100) not null,
password varchar(100) not null);


create table user(
id int primary key auto_increment,
name varchar(50) not null,
full_name varchar(50) not null,
password varchar(50) not null, 
usertype varchar(20) not null,
email varchar(20) not null,
mob_no varchar(20) not null,
dob varchar(20) not null ,
created_by_id int ,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);


alter table user add foreign key(created_by_id) references login(id);






create table category(
id int primary key auto_increment,
name varchar(50) not null,
photo LONGBLOB NOT NULL,
quantity int ,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);


create table sub_category(
id int primary key auto_increment,
category_id int,
name varchar(50) not null,
quantity int ,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);


alter table sub_category add foreign key(category_id) references category(id);




create table sub_category_model_name(
id int primary key auto_increment,
sub_category_id int,
name varchar(50) not null,
price float,
photo LONGBLOB NOT NULL,
size int ,
color varchar(20) not null,
quantity int ,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);


alter table sub_category_model_name add foreign key(sub_category_id) references sub_category(id);


create table customer(
id int primary key auto_increment,
name varchar(50) not null,
location varchar(50) not null, 
mob_no varchar(20) not null,
email varchar(20) not null,
photo LONGBLOB NOT NULL,
sub_category_model_name_id int,
size int,
quantity int,
price float,
dob varchar(20) not null ,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);




create table bill(id int primary key auto_increment,
customer_id int,
sub_category_model_name_id int,
quantity int,
discount int,
vat float,
delivery_charge int,
description  varchar(20) not null,
 created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);



alter table bill add foreign key(customer_id) references customer(id);
alter table bill add foreign key(sub_category_model_name_id ) references sub_category_model_name(id);




create table vendor(id int primary key auto_increment,
name varchar(50) not null,
type varchar(50) not null,
country varchar(50) not null,
contact_no varchar(20) not null,
email varchar(20) not null,
website varchar(100) not null,
sub_category_model_name_id int,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);



create table purchase(id int primary key auto_increment,
sub_category_model_name_id int,
vendor_id int,
unit_price float,
quantity int,
total_price float,
size int,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);


alter table purchase add foreign key(sub_category_model_name_id) references sub_category_model_name(id);
alter table purchase add foreign key(vendor_id) references vendor(id);


create table sales(id int primary key auto_increment,
sub_category_model_name_id int,
customer_id int,
unit_price float,
quantity int,
total_price float,
size int,
created_by varchar(20) not null,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);


alter table sales add foreign key(sub_category_model_name_id) references sub_category_model_name(id);
alter table sales add foreign key(customer_id) references customer(id);

create table report(id int primary key auto_increment,
sub_category_model_name_id int);





