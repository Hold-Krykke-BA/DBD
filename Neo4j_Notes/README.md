# Databases for Developers

## Neo4j Notes and assignments
_____

### Assignement A1-Cypher

<details><summary>A1 <b>(click to reveal)</b></summary>
<p>
  
**1. Create a Movie node for the movie with a title Forrest Gump.**
```sql
CREATE (forrestGump:Movie {
  title:'Forrest Gump', 
  released:1995, 
  tagline:'Life is like a box of chocolates…you never know what you’re gonna get'
  })
```

**2. Add the following properties to the movie Forrest Gump:
  a. released: 1995
  b. tagline: Life is like a box of chocolates…you never know what you’re gonna get.**
  
```sql
--Done above, but:
 MATCH (FG:Movie{title:"Forrest Gump"}) 
   SET FG.released = 1995 
   SET FG.tagline = "Life is like a box of chocolates…you never know what you’re gonna get" 
   RETURN FG
```

**3. Update the released property of movie Forrest Gump, as it has actually been
released in 1994.**

```sql
MATCH (m:Movie {title: 'Forrest Gump'})
SET m.released = 1994
RETURN m
```

**4. Find the movie with the tagline Free your mind.**

```sql
MATCH (m:Movie {tagline: 'Free your mind'})
RETURN m
```

**5. Retrieve the movie The Matrix and all its relationships.**
```sql
MATCH (:Movie {title:"The Matrix"})-[r]-()
RETURN r
```

**6. Find the names and relationship type of all people who have any type of relationship
to the movie The Matrix.**
```sql
MATCH (people:Person)-[relatedTo]-(:Movie {title: "The Matrix"}) 
RETURN people.name, Type(relatedTo), relatedTo
```

**7. Find all people born in the previous century.**
```sql
MATCH (person:Person) WHERE person.born < 2000 
RETURN person.name, person.born
```

**8. Find all people who gave the movie The Da Vinci Code a rating of 65, returning their
names.**
```sql
MATCH (p:Person)-[:REVIEWED {rating:65}]->(m:Movie {title:"The Da Vinci Code"}) RETURN p.name
```

Or with a `WHERE` clause:
```sql
MATCH (movie:Movie {title:"The Da Vinci Code"})-[relationship:REVIEWED]-(reviewer) 
  WHERE relationship.rating = 65 
  RETURN reviewer 
```

**9. Find all people who follow Angela Scope and those who Angela Scope follows.**
```sql
MATCH (a:Person {name:"Angela Scope"})-[:FOLLOWS]->(p:Person)
RETURN p.name AS name
UNION
MATCH (q:Person)-[:FOLLOWS]->(a:Person {name:"Angela Scope"}) 
RETURN q.name AS name
```

**10. Find all people who follow anybody who follows Jessica Thompson returning them as
nodes.**
```sql
MATCH (p:Person)-[:FOLLOWS]->(x:Person)-[:FOLLOWS]->(JessicaThompson) RETURN p
```

**11. Tom Hanks hasn’t HELPED Gary Sinise in a research. Remove this property from
the relation.**

```diff
-- There is no relationship called HELPED. 
-- I assumed that the relationship FOLLOWS is similar, so I used that for question 11 and 12.
```
```sql
MATCH (n {name: "Paul Blythe"})-[r:FOLLOWS]->(a:Person {name:"Angela Scope"}) 
DELETE r
```

**12. Delete the whole person-to-person relationship HELPED from the graph.**
```sql
MATCH ()-[r:FOLLOWS]-() 
DELETE r
```
</p>
</details>

### Assignment A2-Street Crimes Project

<details><summary>A2 <b>(click to reveal)</b></summary>
<p>
  
**1. At this web address: [data.police.uk/data](https://data.police.uk/data/) you will find crime data collected by the
UK police. Download a data set in a csv format and use (some of) the data in it to create a graph
database.**
```sql
LOAD CSV WITH HEADERS FROM 'file:///2021-01-city-of-london-street.csv' AS row
WITH row WHERE NOT row.CrimeID IS null
MERGE (c:Crime {crimeid: row.CrimeID,crimetype: coalesce(row.Crimetype, "unknown crimetype")} )
MERGE (l:Location {location: coalesce(row.Location, "unknown")})
MERGE (c)-[:HAPPENED_IN]->(l)
```
**2. Which is the location with highest number of crimes?**  
```sql
MATCH (l)-[:HAPPENED_IN]->(c)
RETURN c, COLLECT(l) as crimelocations
ORDER BY SIZE(crimelocations) DESC LIMIT 1
```

**3. Which is the most common crime?**  
```sql
MATCH (c:Crime)
RETURN c.crimetype, COUNT(*) 
ORDER BY COUNT(*) DESC LIMIT 1
```
</p>
</details>

