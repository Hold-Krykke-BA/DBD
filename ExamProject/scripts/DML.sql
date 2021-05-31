INSERT INTO reddit_user(user_id) 
VALUES ('30f18a0b-e052-44eb-8c24-23a032b97af3'),
('a643196f-6a35-496e-a206-774c4bdc1d7c'),
('62eabb1d-10e1-4e43-ae48-6889835a678d'),
('458df730-bd64-4e5d-a0cf-85f21e5a7839'),
('8f03dc56-43d8-460d-8409-d7d0bf225ea7'),
('a3a46b84-4323-4aca-bc27-b1207a10cd16'),
('0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('78d1bf47-b986-46f5-90d0-17b08e7c416b')
RETURNING *;

INSERT INTO subreddit(subreddit_id, subreddit_name) 
VALUES ('609f1f9f-dba7-44c8-838b-c00bb5d3e7ac','funny'),
('4db2d45c-ceab-4924-9933-c770b75f9ab8','wsb'),
('61871f72-fc8c-499e-9fbc-57d000b0b025','iama'),
('bcedc54f-1a26-4ff3-b300-8f3b37a11e3c','aita'),
('6d117b01-9755-48d0-8557-71d11a929563','cs'),
('280c2631-bed6-4500-9fc0-abe386d2eea0','photography'),
('f1843571-aa55-418d-9a43-9bc2054452fa','legaladvice'),
('a01ae092-bcc7-417f-911a-2446c296543f','programminghumor'),
('a98eb449-82d1-4578-babb-eaf0ec799e17','lpt'),
('908addc7-5980-417c-b7c4-7776c3ecfe8d','asoiaf')
RETURNING *;

INSERT INTO user_subreddit(user_id, subreddit_id) 
VALUES ('30f18a0b-e052-44eb-8c24-23a032b97af3','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'),
('30f18a0b-e052-44eb-8c24-23a032b97af3','4db2d45c-ceab-4924-9933-c770b75f9ab8'),
('30f18a0b-e052-44eb-8c24-23a032b97af3','61871f72-fc8c-499e-9fbc-57d000b0b025'),
('a643196f-6a35-496e-a206-774c4bdc1d7c','f1843571-aa55-418d-9a43-9bc2054452fa'),
('62eabb1d-10e1-4e43-ae48-6889835a678d','a01ae092-bcc7-417f-911a-2446c296543f'),
('458df730-bd64-4e5d-a0cf-85f21e5a7839','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'),
('458df730-bd64-4e5d-a0cf-85f21e5a7839','a98eb449-82d1-4578-babb-eaf0ec799e17'),
('8f03dc56-43d8-460d-8409-d7d0bf225ea7','a01ae092-bcc7-417f-911a-2446c296543f'),
('a3a46b84-4323-4aca-bc27-b1207a10cd16','bcedc54f-1a26-4ff3-b300-8f3b37a11e3c'),
('a3a46b84-4323-4aca-bc27-b1207a10cd16','908addc7-5980-417c-b7c4-7776c3ecfe8d'),
('a3a46b84-4323-4aca-bc27-b1207a10cd16','a01ae092-bcc7-417f-911a-2446c296543f'),
('0cb981da-10b9-4dcb-8905-b70b69dbdf95','280c2631-bed6-4500-9fc0-abe386d2eea0'),
('f37e74b0-5682-40a9-aa2f-0a6568de74d1','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'),
('2e3ae8ec-1334-475e-b5bb-d1e89bdb416d','6d117b01-9755-48d0-8557-71d11a929563'),
('2e3ae8ec-1334-475e-b5bb-d1e89bdb416d','4db2d45c-ceab-4924-9933-c770b75f9ab8')
RETURNING *;

INSERT INTO post(post_id, post_url_identifier, post_title, post_timestamp, post_content, post_karma, user_id, subreddit_id)
VALUES ('c9fd943c-e1b8-4391-ade4-3c9f35561384','1YjAR','Click this','2021-05-19 20:20:12','Totally not boring content in this post, not at all.',0,'0cb981da-10b9-4dcb-8905-b70b69dbdf95','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'), 
('9ca5ac3f-8f9f-4ed7-a6fd-5b7037d1592f','IbNwN','Click here','2021-05-19 20:21:12','Totally not funny content in this post, not at all.',0,'0cb981da-10b9-4dcb-8905-b70b69dbdf95','4db2d45c-ceab-4924-9933-c770b75f9ab8'), 
('f496a876-1dd7-442f-8e43-2b1d26cd1998','7llf6','Click on me','2021-05-19 20:22:12','Totally not cute content in this post, not at all.',0,'0cb981da-10b9-4dcb-8905-b70b69dbdf95','61871f72-fc8c-499e-9fbc-57d000b0b025'), 
('6dd17ca4-6c53-4eac-ad08-1eb45e06ca32','L8azq','Click bait','2021-05-19 20:23:12','Totally not awesome content in this post, not at all.',0,'62eabb1d-10e1-4e43-ae48-6889835a678d','bcedc54f-1a26-4ff3-b300-8f3b37a11e3c'), 
('dd78af08-156f-4a10-8389-3305c2f232b9','LiTET','Click bait - not','2021-05-19 20:24:12','Totally not sad content in this post, not at all.',0,'8f03dc56-43d8-460d-8409-d7d0bf225ea7','6d117b01-9755-48d0-8557-71d11a929563'), 
('8772e835-c2fa-46de-bd52-816afa8ae9bb','2Hgnv','Click here to help','2021-05-19 20:25:12','Totally not happy content in this post, not at all.',0,'8f03dc56-43d8-460d-8409-d7d0bf225ea7','280c2631-bed6-4500-9fc0-abe386d2eea0'), 
('5104c346-25c3-421d-befb-3b9df51d7639','VwuU3','Click here to learn','2021-05-19 20:26:12','Totally not nice content in this post, not at all.',0,'62eabb1d-10e1-4e43-ae48-6889835a678d','f1843571-aa55-418d-9a43-9bc2054452fa'), 
('826a5869-dfff-4c92-a197-070ecb3224e8','aPQQc','Click to see','2021-05-19 20:27:12','Totally not interesting content in this post, not at all.',0,'78d1bf47-b986-46f5-90d0-17b08e7c416b','a01ae092-bcc7-417f-911a-2446c296543f'), 
('276ad150-df48-4fca-8456-c1449e45dd58','7kKVr','Click here to see','2021-05-19 20:28:12','Totally not exciting content in this post, not at all.',0,'62eabb1d-10e1-4e43-ae48-6889835a678d','a98eb449-82d1-4578-babb-eaf0ec799e17'), 
('f3ca3be2-5fb3-42db-9228-749edf8db69b','Wu3RZ','Click to get karma','2021-05-19 20:29:12','Totally not random content in this post, not at all.',0,'458df730-bd64-4e5d-a0cf-85f21e5a7839','908addc7-5980-417c-b7c4-7776c3ecfe8d'), 
('2a75023b-b9f9-4bdf-b6c5-998d017b1429','NeX5x','Click farming karma','2021-05-19 20:30:12','Totally not likeable content in this post, not at all.',0,'458df730-bd64-4e5d-a0cf-85f21e5a7839','4db2d45c-ceab-4924-9933-c770b75f9ab8'), 
('25e33115-3858-42a9-9188-f58d9544a48f','UEqmW','Click not here','2021-05-19 20:31:12','Totally not general content in this post, not at all.',0,'78d1bf47-b986-46f5-90d0-17b08e7c416b','4db2d45c-ceab-4924-9933-c770b75f9ab8'), 
('eb69a0b7-74df-4162-9550-4e1961f5f644','6oYO8','Click AMA','2021-05-19 20:32:12','Totally not relevent content in this post, not at all.',0,'458df730-bd64-4e5d-a0cf-85f21e5a7839','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'), 
('5911e638-9944-498f-bc67-81680e9ed085','RXgqu','Click','2021-05-19 20:33:12','Totally not productive content in this post, not at all.',0,'0cb981da-10b9-4dcb-8905-b70b69dbdf95','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'), 
('aaec91f9-2e7a-401e-9247-1312a82c673f','bHWr6','Cli...','2021-05-19 20:34:12','Totally not great content in this post, not at all.',0,'78d1bf47-b986-46f5-90d0-17b08e7c416b','609f1f9f-dba7-44c8-838b-c00bb5d3e7ac') 
RETURNING *;

INSERT INTO postcomment(comment_id, parent_id, comment_timestamp, comment_content, comment_karma, post_id, user_id)
VALUES ('44d442e3-8b6f-4947-8a68-cfacc1dedcb9',NULL,'2021-05-19 21:26:00','first',0,'c9fd943c-e1b8-4391-ade4-3c9f35561384','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('dbe0cc0e-0ad3-4d83-aafe-d238a46f92ce',NULL,'2021-05-19 21:26:01','second',0,'9ca5ac3f-8f9f-4ed7-a6fd-5b7037d1592f','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('19825ce1-359a-4b9f-a6f6-ab4a48e71510',NULL,'2021-05-19 21:26:02','third',0,'f496a876-1dd7-442f-8e43-2b1d26cd1998','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('a553a27b-dba3-46f9-b8e2-8f1f923eb206',NULL,'2021-05-19 21:26:03','fourth',0,'6dd17ca4-6c53-4eac-ad08-1eb45e06ca32','0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('618afe83-6458-40f3-b15f-de1a4d4c89ee',NULL,'2021-05-19 21:26:04','fifth',0,'dd78af08-156f-4a10-8389-3305c2f232b9','a3a46b84-4323-4aca-bc27-b1207a10cd16'),
('c127abe5-f7e8-4ed0-872b-5eb8ebf99c50','618afe83-6458-40f3-b15f-de1a4d4c89ee','2021-05-19 21:27:12','sixth',0,'dd78af08-156f-4a10-8389-3305c2f232b9','62eabb1d-10e1-4e43-ae48-6889835a678d'),
('fc0b3f3d-c27a-4db4-a011-1d7d3aed440e',NULL,'2021-05-19 21:26:05','seventh',0,'8772e835-c2fa-46de-bd52-816afa8ae9bb','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('6a32021e-ccfe-4ab8-9284-4c4c90b1bd64','fc0b3f3d-c27a-4db4-a011-1d7d3aed440e','2021-05-19 21:28:12','eight',0,'8772e835-c2fa-46de-bd52-816afa8ae9bb','0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('0f573457-3067-4bba-97b8-eeee00f2d76b','fc0b3f3d-c27a-4db4-a011-1d7d3aed440e','2021-05-19 21:29:12','ninth',0,'8772e835-c2fa-46de-bd52-816afa8ae9bb','62eabb1d-10e1-4e43-ae48-6889835a678d'),
('bc5a738e-fd0b-4140-a7b6-abbd648f38a6',NULL,'2021-05-19 21:26:06','tenth',0,'5104c346-25c3-421d-befb-3b9df51d7639','8f03dc56-43d8-460d-8409-d7d0bf225ea7'),
('2468cf74-8ac8-490f-9b04-bd9f7593e648',NULL,'2021-05-19 21:26:07','omg',0,'826a5869-dfff-4c92-a197-070ecb3224e8','62eabb1d-10e1-4e43-ae48-6889835a678d'),
('2a5ebe33-1cf9-4b24-be6d-5463604d6f82',NULL,'2021-05-19 21:26:08','lol',0,'276ad150-df48-4fca-8456-c1449e45dd58','78d1bf47-b986-46f5-90d0-17b08e7c416b'),
('23779bb7-0f25-4a79-9117-81b07e1d4a27','bc5a738e-fd0b-4140-a7b6-abbd648f38a6','2021-05-19 21:27:12','looool',0,'5104c346-25c3-421d-befb-3b9df51d7639','0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('80503b6a-602e-4534-a5b0-92245e18a132',NULL,'2021-05-19 21:26:09','fml',0,'f3ca3be2-5fb3-42db-9228-749edf8db69b','62eabb1d-10e1-4e43-ae48-6889835a678d'),
('703d593d-9daf-4870-9046-c23a132cbd14',NULL,'2021-05-19 21:26:10','yw',0,'2a75023b-b9f9-4bdf-b6c5-998d017b1429','62eabb1d-10e1-4e43-ae48-6889835a678d'),
('67bf7893-5534-4fad-8cc1-361d002555bf','bc5a738e-fd0b-4140-a7b6-abbd648f38a6','2021-05-19 21:28:12','haha',0,'5104c346-25c3-421d-befb-3b9df51d7639','78d1bf47-b986-46f5-90d0-17b08e7c416b'),
('01e58df6-5fd3-4c1c-a6fb-9b6e1ecd6c27',NULL,'2021-05-19 21:27:11','thanks',0,'25e33115-3858-42a9-9188-f58d9544a48f','0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('0302bb5d-537b-4703-9124-d2da63d67780',NULL,'2021-05-19 21:28:12','great',0,'eb69a0b7-74df-4162-9550-4e1961f5f644','8f03dc56-43d8-460d-8409-d7d0bf225ea7'),
('9dbcb33a-dcf8-41ce-9bd5-1663d6f049a4',NULL,'2021-05-19 21:29:13','omglol',0,'5911e638-9944-498f-bc67-81680e9ed085','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('6666c9d9-92d5-4d4c-8aeb-0047663efbb1',NULL,'2021-05-19 21:30:14','nice',0,'aaec91f9-2e7a-401e-9247-1312a82c673f','78d1bf47-b986-46f5-90d0-17b08e7c416b')
RETURNING *;















