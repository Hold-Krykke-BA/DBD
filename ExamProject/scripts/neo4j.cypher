//neo4j.cypher V1.0
//DDL & DML file for  initializing neo4j


//STOP DATABASE
//STOP DATABASE neo4j;
//re-create database if exists
//CREATE OR REPLACE DATABASE `neo4j`;
//start database
//START DATABASE neo4j;

RETURN 'Clearing database';

MATCH (n)
DETACH DELETE n;

RETURN 'Clearing indexes and constraints';

CALL apoc.schema.assert({}, {}, true) YIELD label, key
RETURN *;

RETURN '---Creating nodes---';

RETURN 'Creating users';
//CREATE (:User {userName:"",userPassHash: "", userEmail: ""});
CREATE
  (rn:User {userID: '1', userName: 'rvn', userPassHash: '1234', userEmail: 'cph-rn118@cphbusiness.dk'})
RETURN rn;

CREATE
  (cs:User {userID: '2', userName: 'cvs', userPassHash: '1234', userEmail: 'cph-cs340@cphbusiness.dk'})
RETURN cs;

CREATE
  (al:User {userID: '3', userName: 'alt', userPassHash: '1234', userEmail: 'cph-al217@cphbusiness.dk'})
RETURN al;

RETURN 'Creating sessions';
//CREATE (:Session {UserID: "", timeStamp: datetime()});
CREATE (:Session {SessionID: '1', UserID: '1', timeStamp: datetime()});

CREATE (:Session {SessionID: '2', UserID: '2', timeStamp: datetime()});

CREATE (:Session {SessionID: '3', UserID: '3', timeStamp: datetime()});

CREATE (:Session {SessionID: '4', UserID: '1', timeStamp: datetime()});

RETURN 'Creating messages';

CREATE (:Message {senderUserID: '', receiverUserID: '', Content: '', timeStamp: datetime()});

RETURN '---Creating relationships---';

RETURN 'Creating user relationships';

MATCH
  (rn:User),
  (cs:User)
  WHERE rn.userName = 'rvn' AND cs.userName = 'cvs'
CREATE (rn)-[r:FOLLOWS]->(cs)
RETURN r;

MATCH
  (cs:User),
  (al:User)
  WHERE cs.userName = 'cvs' AND al.userName = 'alt'
CREATE (cs)-[r:FOLLOWS]->(al)
RETURN r;

MATCH
  (rn:User),
  (al:User)
  WHERE rn.userName = 'rvn' AND al.userName = 'alt'
CREATE (rn)-[r:FOLLOWS]->(al)
RETURN r;

RETURN 'Creating session relationships';

MATCH
  (rn:User),
  (ses:Session)
  WHERE rn.userName = 'rvn' AND ses.SessionID = '1'
CREATE (ses)-[r:BELONGS_TO]->(rn)
RETURN r;

MATCH
  (rn:User),
  (ses:Session)
  WHERE rn.userName = 'rvn' AND ses.SessionID = '4'
CREATE (ses)-[r:BELONGS_TO]->(rn)
RETURN r;

MATCH
  (cs:User),
  (ses:Session)
  WHERE cs.userName = 'cvs' AND ses.SessionID = '2'
CREATE (ses)-[r:BELONGS_TO]->(cs)
RETURN r;

MATCH
  (al:User),
  (ses:Session)
  WHERE al.userName = 'alt' AND ses.SessionID = '3'
CREATE (ses)-[r:BELONGS_TO]->(al)
RETURN r;


RETURN '---Creating custom constraints---';
CREATE CONSTRAINT userName_UQ IF NOT exists
ON (u:User)
ASSERT u.userName IS UNIQUE;

CREATE CONSTRAINT userEmail IF NOT exists
ON (u:User)
ASSERT u.userEmail IS UNIQUE;

RETURN "---Creating custom indexes---";
CREATE INDEX user_IDX IF NOT exists FOR (u:User) ON (u.userName);


//return "---Verifying that database was cleared";

//return "---Creating test data---";
//if more is needed

//with ("Runi", "Benjamin", "Christian", "Dima", "Camilla", "Mads", "Andreas","Claus") as names
//foreach (i in range(0, 10000) | create (:User {username:names[i%size(names)]+i}))