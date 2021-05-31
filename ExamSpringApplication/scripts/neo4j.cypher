//neo4j.cypher V1.4
//DDL & DML file for  initializing neo4j

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
//CREATE (:Session {sessionID: "", userID: "", timestamp: localdatetime()});
CREATE (ses:Session {sessionID: '1', userID: '1', timestamp: localdatetime()})
WITH ses
CALL apoc.ttl.expireIn(ses, 24, 'h')
RETURN ses;

CREATE (ses:Session {sessionID: '2', userID: '2', timestamp: localdatetime()})
WITH ses
CALL apoc.ttl.expireIn(ses, 24, 'h')
RETURN ses;

CREATE (ses:Session {sessionID: '3', userID: '3', timestamp: localdatetime()})
WITH ses
CALL apoc.ttl.expireIn(ses, 24, 'h')
RETURN ses;

CREATE (ses:Session {sessionID: '4', userID: '1', timestamp: localdatetime()})
WITH ses
CALL apoc.ttl.expireIn(ses, 24, 'h')
RETURN ses;

RETURN 'Creating chats';

MATCH
  (rn:User),
  (cs:User)
  WHERE rn.userName = 'rvn' AND cs.userName = 'cvs'
CREATE (ch:Chat {chatID: 1, senderUserID: "1", receiverUserID: "2"})
CREATE (rn)-[r1:PARTICIPATES_IN]->(ch)
CREATE (cs)-[r2:PARTICIPATES_IN]->(ch)
RETURN r1, r2;

//MATCH
//  (rn:User),
//  (cs:User)
//  WHERE rn.userName = 'rvn' AND cs.userName = 'cvs'
//WITH rn, cs
//MERGE https://neo4j.com/docs/cypher-manual/current/clauses/merge/

RETURN 'Creating messages';
//CREATE (:Message {senderUserID: '', receiverUserID: '', content: '', timeStamp: localdatetime()});
MATCH(ch:Chat{chatID: 1})
WITH ch
CREATE
  (msg:Message {messageID: '1', senderUserID: '1', receiverUserID: '2', content: 'yoyoyo', timestamp: localdatetime()})-[:CHAT_PARENT]->(ch);

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

RETURN 'Creating userSession relationships';

MATCH
  (rn:User),
  (ses:Session)
  WHERE rn.userName = 'rvn' AND ses.sessionID = '1'
CREATE (ses)-[r:BELONGS_TO]->(rn)
RETURN r;

MATCH
  (rn:User),
  (ses:Session)
  WHERE rn.userName = 'rvn' AND ses.sessionID = '4'
CREATE (ses)-[r:BELONGS_TO]->(rn)
RETURN r;

MATCH
  (cs:User),
  (ses:Session)
  WHERE cs.userName = 'cvs' AND ses.sessionID = '2'
CREATE (ses)-[r:BELONGS_TO]->(cs)
RETURN r;

MATCH
  (al:User),
  (ses:Session)
  WHERE al.userName = 'alt' AND ses.sessionID = '3'
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
CREATE INDEX user_IDX_userName IF NOT exists FOR (u:User) ON (u.userName);
CREATE INDEX user_IDX_userID IF NOT exists FOR (u:User) ON (u.userID);

//return "---Creating test data---";
//if more is needed

//with ("Runi", "Benjamin", "Christian", "Dima", "Camilla", "Mads", "Andreas","Claus") as names
//foreach (i in range(0, 10000) | create (:User {username:names[i%size(names)]+i}))