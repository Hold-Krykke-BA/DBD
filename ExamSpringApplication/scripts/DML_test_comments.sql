INSERT INTO postcomment(comment_id, parent_id, comment_timestamp, comment_content, comment_karma, post_id, user_id)
VALUES 
('111',NULL,'2021-05-19 21:25:00','hello',0,'276ad150-df48-4fca-8456-c1449e45dd58','30f18a0b-e052-44eb-8c24-23a032b97af3'),
('222','111','2021-05-19 21:27:01','world',2,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('333','111','2021-05-19 21:28:02','!',3,'276ad150-df48-4fca-8456-c1449e45dd58','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('444','111','2021-05-19 21:29:01','What',1,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('555','222','2021-05-19 21:28:02','a',3,'276ad150-df48-4fca-8456-c1449e45dd58','8f03dc56-43d8-460d-8409-d7d0bf225ea7'),
('666','222','2021-05-19 21:29:01','cruel',2,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('777','222','2021-05-19 21:30:02','and',5,'276ad150-df48-4fca-8456-c1449e45dd58','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('888','555','2021-05-19 21:29:02','lonely',3,'276ad150-df48-4fca-8456-c1449e45dd58','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('999','555','2021-05-19 21:30:01','place',2,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('000','555','2021-05-19 21:31:02','this',3,'276ad150-df48-4fca-8456-c1449e45dd58','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('11',NULL,'2021-05-19 21:26:30','is',77,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('22','11','2021-05-19 21:27:01','.',2,'276ad150-df48-4fca-8456-c1449e45dd58','78d1bf47-b986-46f5-90d0-17b08e7c416b'),
('33','11','2021-05-19 21:28:02','Why',2,'276ad150-df48-4fca-8456-c1449e45dd58','a3a46b84-4323-4aca-bc27-b1207a10cd16'),
('44','33','2021-05-19 21:29:01','would',1,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('55','22','2021-05-19 21:28:02','you',3,'276ad150-df48-4fca-8456-c1449e45dd58','0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('66','22','2021-05-19 21:29:01','allow',2,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('77','33','2021-05-19 21:30:02','all',3,'276ad150-df48-4fca-8456-c1449e45dd58','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('88','55','2021-05-19 21:29:02','this',3,'276ad150-df48-4fca-8456-c1449e45dd58','a3a46b84-4323-4aca-bc27-b1207a10cd16'),
('99','88','2021-05-19 21:30:01','suffering',2,'276ad150-df48-4fca-8456-c1449e45dd58','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('00','88','2021-05-19 21:31:02','?',3,'276ad150-df48-4fca-8456-c1449e45dd58','f37e74b0-5682-40a9-aa2f-0a6568de74d1');