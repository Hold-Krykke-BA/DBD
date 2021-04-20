# Databases for Developers

## Neo4j Notes and assignments
_____

### Assignement A1-Cypher

**1. Create a Movie node for the movie with a title Forrest Gump.**
```cypher
CREATE (forrestGump:Movie {
  title:'Forrest Gump', 
  released:1995, 
  tagline:'Life is like a box of chocolates…you never know what you’re gonna get'})
```

**2. Add the following properties to the movie Forrest Gump:
  a. released: 1995
  b. tagline: Life is like a box of chocolates…you never know what you’re gonna get.**

**3. Update the released property of movie Forrest Gump, as it has actually been
released in 1994.**

**4. Find the movie with the tagline Free your mind.**

**5. Retrieve the movie The Matrix and all its relationships.**

**6. Find the names and relationship type of all people who have any type of relationship
to the movie The Matrix.**

**7. Find all people born in the previous century.**

**8. Find all people who gave the movie The Da Vinci Code a rating of 65, returning their
names.**

**9. Find all people who follow Angela Scope and those who Angela Scope follows.**

**10. Find all people who follow anybody who follows Jessica Thompson returning them as
nodes.**

**11. Tom Hanks hasn’t HELPED Gary Sinise in a research. Remove this property from
the relation.**

**12. Delete the whole person-to-person relationship HELPED from the graph.**
