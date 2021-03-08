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

---- some dogs
INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Fido', 1, 1, 9);

INSERT INTO vet.dog
(pet_name, pet_age, vet_id, barkpitch)
VALUES('Pluto', 3, 2, 4);


---- some randoms
INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Jerry', 2, 1);

--INSERT INTO vet.pet
--(pet_name, pet_age, vet_id)
--VALUES('Mathilde', 12, 2);

INSERT INTO vet.pet
(pet_name, pet_age, vet_id)
VALUES('Muh', 1, 2);


--ten caretakers with common pets
--INSERT INTO vet.caretaker
--(caretaker_name, phone, street, city_zipcode)
--VALUES('J�rgen Hansen', 12883522, 'Jordhulsvej 22', 2900);


INSERT INTO vet.caretaker
(caretaker_name, phone, street, city_zipcode)
VALUES('Beathe Knudsen', 33883422, 'Jordhulsvej 24', 2900);

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
