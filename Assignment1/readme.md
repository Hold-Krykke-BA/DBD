# Assignment 1 - Relational DB

Assignment: [01-relational-db](01-relational-db.pdf)  

## Discussion

### Chosing an inheritance strategy  
pros and cons for each strategy.  
- Joint-table strategy
  - each class has its table and querying a subclass entity requires joining the tables  
- Table-per-class strategy
  - all the properties of a class, are in its table, so no join is required  
- Single-table strategy
  - the entities from different classes with a common ancestor are placed in a single table  

```diff
- add details
```
## Description of solution
```diff
- add details (if any)
```
### Create an ER diagram covering the domain
We implemented the joint-table strategy.

Our initial ER-diagram can be seen here:  
![image](https://user-images.githubusercontent.com/35559774/110286590-7b6e5a00-7fe5-11eb-8d53-00160460dfe4.png)  
This was designed before implementing any scripts.

Following the database implementation, we generated an ER diagram using DBeaver:  
![img](images/DBeaver_ER.png)
```diff
- add details
```

### Conceptual level implementation

#### SQL-Script for PostgreSQL that creates the tables
The SQL script can be found in [Scripts\DDL.sql](Scripts\DDL.sql)

```diff
- add details
```

#### SQL-script for PostgreSQL that adds sample data
The SQL script can be found in [Scripts\DML.sql](Scripts\DML.sql)

```diff
- add details + (screenshot / markdown table / .csv of sample data)
```

### External level implementation

#### Create views or stored procedures to deal with the inheritance strategy
- See cats and dogs as separate views
- See all pets as in the single table strategy
- Update cats and dogs with a single SQL call, stored procedure or update on a view with a trigger.
```diff
- add details / link references
```

#### Create a script that creates a designated user for accesing the database
The SQL script can be found in [Scripts\?.sql](Scripts\?.sql)
```diff
- add details / link references
```

### Interface implementation
The program can be found in [?.java](?.java)
#### Create a java program that can, using the designated user:

- retrieve a list of pets from the database. The types of instances of Pets
in the list should reflect the actual type:
  - Pet
  - Cat
  - Dog
- insert a new Dog, Cat, and/or Pet in the database.
```diff
- add details / link references
```

## References
