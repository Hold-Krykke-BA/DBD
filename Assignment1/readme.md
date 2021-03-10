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
