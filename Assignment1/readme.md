# Assignment 1 - Relational DB

Assignment: [01-relational-db](01-relational-db.pdf)  

## Discussion

### Chosing an inheritance strategy  
pros and cons for each strategy.  
- **Joint-table strategy**
  - each class has its table and querying a subclass entity requires joining the tables  
  - **PRO** Changes in a super class does not demand complex changes in database.
  - **CON** The disadvantage of this inheritance mapping method is that retrieving entities requires joins between tables, which can result in lower performance for large numbers of records. The number of joins is higher when querying the parent class as it will join with every single related child – so performance is more likely to be affected the higher up the hierarchy we want to retrieve records.
- **Table-per-class strategy**
  - all the properties of a class, are in its table, so no join is required  
  - **PRO** Simple style to implement inheritance mapping. 
  - **CON** It provides poor support for polymorphic relationships and typically requires that SQL UNION queries (or a separate SQL query per subclass) be issued for queries that are intended to range over the class hierarchy.
- **Single-table strategy**
  - the entities from different classes with a common ancestor are placed in a single table  
  - **PRO** This strategy has the advantage of polymorphic query performance since only one table needs to be accessed when querying parent entities. 
  - **CON** This also means that we can no longer use NOT NULL constraints on sub-class entity properties.

* If you require the best performance and need to use polymorphic queries and relationships, you should choose the single table strategy. But be aware, that you can’t use not null constraints on subclass attributes which increase the risk of data inconsistencies.
* If data consistency is more important than performance and you need polymorphic queries and relationships, the joined strategy is probably your best option.
* If you don’t need polymorphic queries or relationships, the table per class strategy is most likely the best fit. It allows you to use constraints to ensure data consistency and provides an option of polymorphic queries. But keep in mind, that polymorphic queries are very complex for this table structure and that you should avoid them.

**Sources**  
[Explanation](https://www.baeldung.com/hibernate-inheritance)  
[Several decent answers on this Stackoverflow post](https://stackoverflow.com/questions/8162233/table-per-subclass-vs-table-per-concrete-class-in-hibernate)  
[Thorough explanation](https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/)   

## Description of solution
We chose the **Joint-table strategy** for this implementation. 

### Create an ER diagram covering the domain
Our initial ER-diagram can be seen here:  
![image](https://user-images.githubusercontent.com/35559774/110286590-7b6e5a00-7fe5-11eb-8d53-00160460dfe4.png)  
This was designed before implementing any scripts.

Following the database implementation, we generated an ER diagram using DBeaver:  
![image](https://user-images.githubusercontent.com/35559774/110612049-1c007d80-8190-11eb-8951-304330db1efd.png)


### Conceptual level implementation
The SQL script can be found in the [Scripts](https://github.com/Hold-Krykke-BA/DBD/tree/main/Assignment1/Scripts) folder.   
#### SQL-Script for PostgreSQL that creates the tables
* [DDL](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/Scripts/DDL.sql)

#### SQL-script for PostgreSQL that adds sample data
* [DML](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/Scripts/DML.sql)


### External level implementation

#### Create views or stored procedures to deal with the inheritance strategy
- See cats and dogs as separate views, and ll pets as in the single table strategy
  - [The vies are created here in the DDL](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/Scripts/DDL.sql#L66)
- Update cats and dogs with a single SQL call, stored procedure or update on a view with a trigger.
  - The stored procedure are defined [here in the DDL](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/Scripts/DDL.sql#L81)


#### Create a script that creates a designated user for accesing the database
* [Role designated user](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/Scripts/User.sql)
* [Stored procedure](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/Scripts/DDL.sql#L101) for the user designated user to insert new data without accessing the tables directly.

### Interface implementation
The Java program can be found in [src](https://github.com/Hold-Krykke-BA/DBD/tree/main/Assignment1/src).  
We've implemented the three pet-classes in the following manner:
* Pet is a superclass and have the fields shared between all pets.
* Cat and Dog both extends Pet and each have a unique field relating to their pet type. 

#### Create a java program that can, using the designated user:
- retrieve a list of pets from the database. The types of instances of Pets
in the list should reflect the actual type:
  - Pet
  - Cat
  - Dog  
This is solved by the [getPets](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/src/Main.java#L46) and [printPets](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/src/Main.java#L26). printPets takes a pettype in a String ad calls getPets with the correct type. getPets sets the properties for the designated user, and creates a preparedStatement with the given sql. 
- insert a new Dog, Cat, and/or Pet in the database.  
This is solved by the [insertPet](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/src/Main.java#L76) method. insertPet sets the properties for the designated user and creates a preparedStatement or a modified CallableStatement ([see comment](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment1/src/Main.java#L82)). 


## References
[Baeldung](https://www.baeldung.com/hibernate-inheritance)  
[Stackoverflow](https://stackoverflow.com/questions/8162233/table-per-subclass-vs-table-per-concrete-class-in-hibernate)  
[Thorben-Janssen](https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/)   
