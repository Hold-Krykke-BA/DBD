--drop database if exists Assignment1;
--create database Assignment1;
--\connect Assignment1;
--create schema if not exists Vet;
--SET search_path TO Vet;


drop table if exists Pet_caretaker;
drop table if exists CAT;
drop table if exists DOG;
drop table if exists Pet;
drop table if exists CARETAKER;
drop table if exists VET;
drop table if exists CITY;


CREATE TABLE IF NOT EXISTS City (
   city_zipCode int primary key,
   cityName varchar(25)
);

CREATE TABLE IF NOT EXISTS Caretaker (
   caretaker_id SERIAL primary key,
   caretaker_name varchar(50) not null,
   phone int not NULL,
   street varchar(50) not null,
   city_zipCode int references City not null 
);

CREATE TABLE IF NOT EXISTS Vet (
   vet_id SERIAL primary key,
   cvr int not null,
   vet_name varchar(50) not NULL,
   phone int not NULL,
   street varchar(50) not null,
   city_zipCode int references City not null 
);


create table if not exists Pet ( 
  pet_id SERIAL primary key,
  pet_name varchar(20) not null,
  pet_age int not null,
  vet_id int references Vet not null 
);

--compound table
create table if not exists Pet_caretaker ( 
  pet_id int references Pet not null,
  caretaker_id int references Caretaker not null 
 );


create table if not exists Cat ( 
 lifeCount int not null default 9
) inherits (Pet);

create table if not exists Dog ( 
 barkPitch int not null
) inherits (Pet);