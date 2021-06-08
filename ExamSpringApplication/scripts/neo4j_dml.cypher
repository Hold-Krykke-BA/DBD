CREATE (u0:User {userID:'30f18a0b-e052-44eb-8c24-23a032b97af3', userName: "melanym", userPassHash: '1234', userEmail: 'Melany.Mills@deondre.io'}), 
(u1:User {userID:'a643196f-6a35-496e-a206-774c4bdc1d7c', userName: "skylarz", userPassHash: '1234', userEmail: 'Skylar.Erdman@antonia.net'})
        -[:FOLLOWS]->(u0), 
(u2:User {userID:'458df730-bd64-4e5d-a0cf-85f21e5a7839', userName: "cic6", userPassHash: '1234', userEmail: 'Cicero@annabel.co.uk'})
        -[:FOLLOWS]->(u0), 
(u3:User {userID:'8f03dc56-43d8-460d-8409-d7d0bf225ea7', userName: "michMaster", userPassHash: '1234', userEmail: 'Michaela@kayla.net'})
        -[:FOLLOWS]->(u2), 
(u4:User {userID:'a3a46b84-4323-4aca-bc27-b1207a10cd16', userName: "abn3r", userPassHash: '1234', userEmail: 'Abner@savanna.biz'})
        -[:FOLLOWS]->(u2), 
(u5:User {userID:'f37e74b0-5682-40a9-aa2f-0a6568de74d1', userName: "aa_ron", userPassHash: '1234', userEmail: 'Aaron.Gleason@ludwig.me'})
        -[:FOLLOWS]->(u0), 
(u6:User {userID:'2e3ae8ec-1334-475e-b5bb-d1e89bdb416d', userName: "verlieyo", userPassHash: '1234', userEmail: 'Verlie_Yundt@opal.info'})
        -[:FOLLOWS]->(u4), 
(u7:User {userID:'78d1bf47-b986-46f5-90d0-17b08e7c416b', userName: "nicedude", userPassHash: '1234', userEmail: 'Osbaldo@therese.name'})
        -[:FOLLOWS]->(u3);
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "30f18a0b-e052-44eb-8c24-23a032b97af3" AND u2.userID = "78d1bf47-b986-46f5-90d0-17b08e7c416b"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "d1202ccb-74c6-40b1-8908-43c8e715ad69"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "a643196f-6a35-496e-a206-774c4bdc1d7c" AND u2.userID = "a3a46b84-4323-4aca-bc27-b1207a10cd16"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "42a12532-0579-4741-b3e3-2640deb6205d"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "458df730-bd64-4e5d-a0cf-85f21e5a7839" AND u2.userID = "78d1bf47-b986-46f5-90d0-17b08e7c416b"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "383c2cf6-c0fe-44d0-9e14-afd74bebeb5f"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "8f03dc56-43d8-460d-8409-d7d0bf225ea7" AND u2.userID = "f37e74b0-5682-40a9-aa2f-0a6568de74d1"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "fbdf15c2-531f-447f-9ea4-08d978d90224"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "a3a46b84-4323-4aca-bc27-b1207a10cd16" AND u2.userID = "a3a46b84-4323-4aca-bc27-b1207a10cd16"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "b0b1884b-b40f-4f3a-b1cc-bc9b8ef90c42"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "f37e74b0-5682-40a9-aa2f-0a6568de74d1" AND u2.userID = "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "cf67d64d-1fdf-4497-94b1-94f1d95254f5"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d" AND u2.userID = "a643196f-6a35-496e-a206-774c4bdc1d7c"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "669fb5cf-7b1a-4345-92c2-16ff411e6c48"
;
MATCH
(u1:User),
(u2:User)
WHERE u1.userID = "78d1bf47-b986-46f5-90d0-17b08e7c416b" AND u2.userID = "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d"
MERGE (u1)-[:PARTICIPATES_IN]->(ch:Chat)<-[:PARTICIPATES_IN]-(u2) 
ON CREATE
SET ch.timestamp = localdatetime(),
ch.chatID = "7b804b48-d7e7-4de5-a9d9-12eaaa5bee13"
;
MATCH (u:User {userID: "30f18a0b-e052-44eb-8c24-23a032b97af3" })
                              WITH u
                           CREATE (ses:Session {sessionID: "d5023679-5b64-44ff-93c6-296a43b0ae86", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "a643196f-6a35-496e-a206-774c4bdc1d7c" })
                              WITH u
                           CREATE (ses:Session {sessionID: "efc4c4b3-b470-4d96-8132-9e1314b8789d", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "458df730-bd64-4e5d-a0cf-85f21e5a7839" })
                              WITH u
                           CREATE (ses:Session {sessionID: "3ec5aa4d-8480-4523-9483-a26e647690a3", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "8f03dc56-43d8-460d-8409-d7d0bf225ea7" })
                              WITH u
                           CREATE (ses:Session {sessionID: "2fe2ea02-3d9e-4109-8379-1d1b6a10f5a2", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "a3a46b84-4323-4aca-bc27-b1207a10cd16" })
                              WITH u
                           CREATE (ses:Session {sessionID: "84b2c853-da25-43fa-b456-79a0bc09c66a", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "f37e74b0-5682-40a9-aa2f-0a6568de74d1" })
                              WITH u
                           CREATE (ses:Session {sessionID: "1daf45ed-97ef-4e09-a0ef-16c932ec637a", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d" })
                              WITH u
                           CREATE (ses:Session {sessionID: "68a86616-f51b-40e2-8d18-8261fc2713ab", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH (u:User {userID: "78d1bf47-b986-46f5-90d0-17b08e7c416b" })
                              WITH u
                           CREATE (ses:Session {sessionID: "d5d7aa30-62a2-42e0-8b7d-c7d1daf1c184", userID:u.userID , timestamp: localdatetime()})-[:BELONGS_TO]->(u)
                           WITH ses
                           call apoc.ttl.expireIn(ses, 24, 'h') RETURN ses;
MATCH(ch:Chat {chatID: "d1202ccb-74c6-40b1-8908-43c8e715ad69"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "086b2dd4-385b-4245-97a2-acafffe5e1b5",
                                 senderUserID: "78d1bf47-b986-46f5-90d0-17b08e7c416b",
                                content: "aliquam nisi et incidunt architecto quia quia consequatur",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "b85660a7-2655-4312-9682-4a30a69a8f4c",
                                 senderUserID: "78d1bf47-b986-46f5-90d0-17b08e7c416b",
                                content: "consequuntur nihil assumenda quia ut",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "42a12532-0579-4741-b3e3-2640deb6205d"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "d452454c-60c8-452e-bb4c-3667d1ae1830",
                                 senderUserID: "a3a46b84-4323-4aca-bc27-b1207a10cd16",
                                content: "consequuntur nihil assumenda quia ut",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "639bf8e5-f698-4c1f-aa01-03e2572613c4",
                                 senderUserID: "a3a46b84-4323-4aca-bc27-b1207a10cd16",
                                content: "culpa nulla consectetur voluptates quas cum",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "383c2cf6-c0fe-44d0-9e14-afd74bebeb5f"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "fcbd407d-a7f0-45fc-8f7d-20584ac6024d",
                                 senderUserID: "78d1bf47-b986-46f5-90d0-17b08e7c416b",
                                content: "culpa nulla consectetur voluptates quas cum",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "eb80eb17-fb12-4a60-91a8-95fd1d33f679",
                                 senderUserID: "78d1bf47-b986-46f5-90d0-17b08e7c416b",
                                content: "tempora autem non ad at qui est est",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "fbdf15c2-531f-447f-9ea4-08d978d90224"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "d518707c-713b-472a-a1ec-a2f598fef17d",
                                 senderUserID: "f37e74b0-5682-40a9-aa2f-0a6568de74d1",
                                content: "quam qui voluptatum amet omnis",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "5fed17da-e8df-49a6-8405-700b1f0f7daf",
                                 senderUserID: "f37e74b0-5682-40a9-aa2f-0a6568de74d1",
                                content: "temporibus voluptas assumenda ipsa qui eum",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "b0b1884b-b40f-4f3a-b1cc-bc9b8ef90c42"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "2af99223-fb70-4dc2-9d96-cfb5650015cf",
                                 senderUserID: "a3a46b84-4323-4aca-bc27-b1207a10cd16",
                                content: "tempora autem non ad at qui est est",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "677b5ebe-3e9c-4b15-aa73-c38e6a2a1675",
                                 senderUserID: "a3a46b84-4323-4aca-bc27-b1207a10cd16",
                                content: "culpa nulla consectetur voluptates quas cum",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "cf67d64d-1fdf-4497-94b1-94f1d95254f5"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "1ca06efd-0948-4f64-a6fd-9296ede88e2d",
                                 senderUserID: "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d",
                                content: "alias atque maxime eos excepturi et ipsum facilis",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "fa476de1-3569-4739-b836-dbaf7a5abd94",
                                 senderUserID: "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d",
                                content: "qui aut autem tenetur aut deleniti et",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "669fb5cf-7b1a-4345-92c2-16ff411e6c48"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "5409fb5b-985b-4cf6-8ae2-354c2db59e38",
                                 senderUserID: "a643196f-6a35-496e-a206-774c4bdc1d7c",
                                content: "qui aut autem tenetur aut deleniti et",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "50ef74cb-6236-430e-8703-900a92800850",
                                 senderUserID: "a643196f-6a35-496e-a206-774c4bdc1d7c",
                                content: "consequuntur nihil assumenda quia ut",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
MATCH(ch:Chat {chatID: "7b804b48-d7e7-4de5-a9d9-12eaaa5bee13"})
                                WITH ch
                                CREATE
                                  (:Message {messageID: "a8efed94-6636-4a5b-98d7-4c814a1251c6",
                                 senderUserID: "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d",
                                content: "temporibus voluptas assumenda ipsa qui eum",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch),
                                (:Message {messageID: "d9862bee-3cf3-409c-b0eb-4e504e14e203",
                                 senderUserID: "2e3ae8ec-1334-475e-b5bb-d1e89bdb416d",
                                content: "consequuntur nihil assumenda quia ut",
                                timestamp: localdatetime()})-[:CHAT_PARENT]->(ch)
                                ;
