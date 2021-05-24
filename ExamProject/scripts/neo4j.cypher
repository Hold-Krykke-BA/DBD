//neo4j.cypher V1.0
//DDL & DML file for  initializing neo4j


//STOP DATABASE
//STOP DATABASE neo4j;
//re-create database if exists
//CREATE OR REPLACE DATABASE `neo4j`;
//start database
//START DATABASE neo4j;

return "Clearing database";
MATCH (n) DETACH DELETE n;

return "Clearing indexes and constraints";
CALL apoc.schema.assert({},{},TRUE) YIELD label, key RETURN *;

return "---Creating nodes---";
return "Creating users";
//CREATE (:User {userName:"",userPassHash: "", userEmail: ""});
CREATE (rn:User {userID: "1", userName:"rvn",userPassHash: "1234", userEmail: "cph-rn118@cphbusiness.dk"});
CREATE (cs:User {userID: "2", userName:"cvs",userPassHash: "1234", userEmail: "cph-cs340@cphbusiness.dk"});
CREATE (al:User {userID: "3", userName:"alt",userPassHash: "1234", userEmail: "cph-al217@cphbusiness.dk"});

return "Creating sessions";
//CREATE (:Session {UserID: "", timeStamp: datetime()});
CREATE (:Session {SessionID: "1", UserID: "1", timeStamp: datetime()});
CREATE (:Session {SessionID: "4", UserID: "1", timeStamp: datetime()});
CREATE (:Session {UserID: "2", timeStamp: datetime()});
CREATE (:Session {UserID: "3", timeStamp: datetime()});

return "Creating messages";
CREATE (:Message {senderUserID: "",receiverUserID: "", Content: "", timeStamp: datetime()});

return "---Creating relationships---";
MATCH
  (rn:User),
  (cs:User)
  WHERE rn.userName = 'rvn' AND cs.name = 'cvs'
CREATE (rn)-[r:FOLLOWS]->(cs) RETURN r;



return "---Creating custom indexes---";

return "---Creating custom constraints---";

return "---Verifying indexes";
