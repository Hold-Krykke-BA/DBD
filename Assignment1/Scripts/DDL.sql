--drop database if exists Assignment1;
--create database Assignment1;
--\connect Assignment1;
--create schema if not exists Vet;
--SET search_path TO Vet;

drop view if exists cats;
drop view if exists dogs;
drop view if exists pets;
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

-- views

---- view for cats
CREATE or replace view Cats AS
    SELECT *
    FROM vet.cat;

---- view for dogs
CREATE or replace view Dogs AS
    SELECT *
    FROM vet.dog;

---- see all pets (excluding childs such as cat, dog)
CREATE or replace view Pets AS
    SELECT *
    from ONLY vet.pet;
    
-- stored procedure for 
CREATE or replace PROCEDURE update_pet(id integer, p_name varchar, p_age integer, v_id integer, bark_pitch integer default null, life_count integer default null)
LANGUAGE plpgsql
AS $$
begin
	if bark_pitch <> null THEN
		UPDATE vet.dog
			SET pet_name=p_name, pet_age=p_age, vet_id=v_id, barkPitch=bark_pitch where pet_id = id;
	elseif life_count <> null then
		UPDATE vet.cat
			SET pet_name=p_name, pet_age=p_age, vet_id=v_id, lifeCount=life_count where pet_id = id;
	else 
		UPDATE vet.pet
			SET pet_name=p_name, pet_age=p_age, vet_id=v_id where pet_id = id;
	end if;
end
$$;

