drop owned by designated; --todo fix 
drop role if exists designated;

CREATE ROLE designated
	WITH LOGIN PASSWORD 'designated';

GRANT CONNECT
--ON DATABASE assignment1
ON DATABASE soft2021
TO designated;

GRANT USAGE
ON SCHEMA Vet
TO designated;

GRANT SELECT
ON table Vet.Cats
TO designated;

GRANT SELECT
ON table Vet.dogs 
TO designated;

GRANT SELECT
ON table Vet.Pets
TO designated;

GRANT EXECUTE ON PROCEDURE update_pet TO designated;
GRANT EXECUTE ON PROCEDURE insert_pet TO designated;

