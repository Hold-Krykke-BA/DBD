--city
INSERT INTO vet.city
(city_zipcode, cityname)
VALUES(2200, 'Noerrebro');

INSERT INTO vet.city
(city_zipcode, cityname)
VALUES(2900, 'Hellerup');


-- two veterinarians
INSERT INTO vet.vet
(cvr, vet_name, phone, street, city_zipcode)
VALUES(12345678, 'Blagaards Dyreklinik', 25202520, 'Blaagaards Plads 12', 2200);
INSERT INTO vet.vet
(cvr, vet_name, phone, street, city_zipcode)
VALUES(55555500, 'Strandvejens rehabiliteringscenter', 00343434, 'Strandvejen 88', 2900);

-- twenty pets
---- some cats
INSERT INTO vet.cat
(pet_name, pet_age, vet_id, lifecount)
VALUES('Misser', 5, 1, 8);

INSERT INTO vet.cat
(pet_name, pet_age, vet_id, lifecount)
VALUES('Tom', 2, 2, 9);

INSERT INTO vet.cat
(pet_name, pet_age, vet_id, lifecount)
VALUES('Silke', 1, 2, 7);

INSERT INTO vet.cat
(pet_name, pet_age, vet_id, lifecount)
VALUES('Blackie', 7, 2, 9);

INSERT INTO vet.cat
(pet_name, pet_age, vet_id, lifecount)
VALUES('Ursula', 13, 1, 1);

INSERT INTO vet.cat
(pet_name, pet_age, vet_id, lifecount)
VALUES('Princess', 3, 1, 9);

---- some dogs
INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Fido', 1, 1, 9);

INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Pluto', 3, 2, 4);

INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Sofus', 5, 2, 5);

INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Lady', 2, 2, 3);

INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Summer', 10, 1, 2);

INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Ghost', 7, 1, 1);

---- some randoms
INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Jerry', 2, 1);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Mathilde', 12, 2);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Atramedes', 3, 2);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Ultraxion', 5, 2);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Ysera', 4, 1);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Alexstrasa', 10, 1);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Neltharion', 17, 1);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Onyxia', 11, 1);


--ten caretakers 
INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Beathe Knudsen', 33883422, 'Jordhulsvej 24', 2900);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Orla Hansen', 33123422, 'Jordhulsvej 21', 2900);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Ole Olsen', 33233422, 'Jordhulsvej 29', 2900);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Erling Smith', 333483422, 'Jordhulsvej 3', 2900);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Connie Laursen', 33453422, 'Jordhulsvej 56', 2900);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Susan Holst', 33563422, 'Jordhulsvej 15', 2900);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Lene Bonde', 33673422, 'Stengade 60', 2200);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Karla Madsen', 33781322, 'Stengade 62', 2200);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Ulla Steffensen', 33892322, 'Stengade 64', 2200);

INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Viggo Borg', 33885622, 'Stengade 24', 2200);

--compound table pet_caretaker
--INSERT INTO vet.pet_caretaker
--(pet_id, caretaker_id)
--VALUES((select pet_id from vet.pet where pet_name = 'Mathilde'), (select caretaker_id from vet.caretaker where caretaker_name = 'J�rgen Hansen'));

WITH rows1 AS (
   INSERT INTO vet.caretaker
	(caretaker_name, phone, street, city_zipcode)
	VALUES('Joergen Hansen', 12883522, 'Jordhulsvej 22', 2900)
    RETURNING caretaker_id
),
rows2 AS (
       INSERT INTO vet.pet
		(pet_name, pet_age, vet_id)
		VALUES('Mathilde', 12, 2)
		returning pet_id
)

INSERT INTO vet.pet_caretaker (pet_id, caretaker_id)
    SELECT pet_id, caretaker_id
    FROM rows2, rows1;

CALL update_pet(5, 'Jerry 2', 22, 1); 
call update_pet(1, 'Misser 2', 75, 1, null, 2);
call update_pet(3, 'Fido 2', 175, 2, 2); 
