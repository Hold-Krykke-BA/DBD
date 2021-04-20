# Databases for Developers

## Neo4j Notes and assignments
_____

### Assignement A1-Cypher

**1. Create a Movie node for the movie with a title Forrest Gump.**
```
CREATE (forrestGump:Movie {
  title:'Forrest Gump', 
  released:1995, 
  tagline:'Life is like a box of chocolates…you never know what you’re gonna get'
  })
```

**2. Add the following properties to the movie Forrest Gump:
  a. released: 1995
  b. tagline: Life is like a box of chocolates…you never know what you’re gonna get.**
  
```
See above
```

**3. Update the released property of movie Forrest Gump, as it has actually been
released in 1994.**

```
MATCH (m:Movie {title: 'Forrest Gump'})
SET m.released = 1994
RETURN m
```

**4. Find the movie with the tagline Free your mind.**

```
MATCH (m:Movie {tagline: 'Free your mind'})
RETURN m
```

**5. Retrieve the movie The Matrix and all its relationships.**
```
MATCH (:Movie {title:"The Matrix"})-[r]-()
RETURN r
```

**6. Find the names and relationship type of all people who have any type of relationship
to the movie The Matrix.**
```
MATCH (people:Person)-[relatedTo]-(:Movie {title: "The Matrix"}) 
RETURN people.name, Type(relatedTo), relatedTo
```

**7. Find all people born in the previous century.**
```
MATCH (person:Person) WHERE person.born < 2000 
RETURN person.name, person.born
```

**8. Find all people who gave the movie The Da Vinci Code a rating of 65, returning their
names.**
```
MATCH (p:Person)-[:REVIEWED {rating:65}]->(m:Movie {title:"The Da Vinci Code"}) RETURN p.name
```

**9. Find all people who follow Angela Scope and those who Angela Scope follows.**
```
MATCH (a:Person {name:"Angela Scope"})-[:FOLLOWS]->(p:Person)
RETURN p.name AS name
UNION
MATCH (q:Person)-[:FOLLOWS]->(a:Person {name:"Angela Scope"}) 
RETURN q.name AS name
```

**10. Find all people who follow anybody who follows Jessica Thompson returning them as
nodes.**
```
MATCH (p:Person)-[:FOLLOWS]->(x:Person)-[:FOLLOWS]->(JessicaThompson) RETURN p
```

**11. Tom Hanks hasn’t HELPED Gary Sinise in a research. Remove this property from
the relation.**

```diff
-- There is no relationship called HELPED. I assumed that the relationship FOLLOWS is similar, so I used that for the next two questions.
```
```
MATCH (n {name: "Paul Blythe"})-[r:FOLLOWS]->(a:Person {name:"Angela Scope"}) 
DELETE r
```

**12. Delete the whole person-to-person relationship HELPED from the graph.**
```
MATCH ()-[r:FOLLOWS]-() 
DELETE r
```
