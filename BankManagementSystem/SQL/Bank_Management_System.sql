create database bankmanagementsystem;

use bankmanagementsystem;

CREATE TABLE signup (
    formno VARCHAR(20),
    name VARCHAR(50),
    fname VARCHAR(50),
    dob VARCHAR(20),
    gender VARCHAR(10),
    email VARCHAR(50),
    marital VARCHAR(20),
    address VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    pincode VARCHAR(10)
);
desc signup;
select * from signup;
truncate table signup;


create table signuptwo(formno varchar(20), religion varchar(20), category varchar(20), income varchar(20), education varchar(20), occupation varchar(20), pan varchar(20), aadhar varchar(20), seniorcitizen varchar(20), existingaccount varchar(20));
select * from signuptwo;
desc signuptwo;
truncate table signuptwo;

create table signupthree(formno varchar(20), accountType varchar(60), cardnumber varchar(25), pin varchar(10), facility varchar(200));
drop table signupthree;
desc signupthree;
select * from signupthree;
truncate table signupthree;

create table login(formno varchar(20), cardnumber varchar(25), pin varchar(10));
desc login;
select * from login;
truncate table login;

create table bank(pin varchar(10), date varchar(50),type varchar(20),amount varchar(20));
desc bank;
drop table bank;
select * from bank;
truncate table bank;
SET SQL_SAFE_UPDATES = 0; -- cant delete and update without execute this query.
UPDATE bank SET pin = '4800' WHERE pin = '152021';
delete from bank where pin = '7982';
SET SQL_SAFE_UPDATES = 1;  -- cant delete and update after execute this query.