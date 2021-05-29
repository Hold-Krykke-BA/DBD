INSERT INTO postcomment(comment_id, parent_id, comment_timestamp, comment_content, comment_karma, post_id, user_id)
VALUES 
('111',NULL,'2021-05-19 21:25:00','hello',0,'5104c346-25c3-421d-befb-3b9df51d7639','30f18a0b-e052-44eb-8c24-23a032b97af3'),
('222','111','2021-05-19 21:27:01','world',2,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('333','111','2021-05-19 21:28:02','!',3,'5104c346-25c3-421d-befb-3b9df51d7639','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('444','111','2021-05-19 21:29:01','What',1,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('555','222','2021-05-19 21:28:02','a',3,'5104c346-25c3-421d-befb-3b9df51d7639','8f03dc56-43d8-460d-8409-d7d0bf225ea7'),
('666','222','2021-05-19 21:29:01','cruel',2,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('777','222','2021-05-19 21:30:02','and',5,'5104c346-25c3-421d-befb-3b9df51d7639','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('888','555','2021-05-19 21:29:02','lonely',3,'5104c346-25c3-421d-befb-3b9df51d7639','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('999','555','2021-05-19 21:30:01','place',2,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('000','555','2021-05-19 21:31:02','this',3,'5104c346-25c3-421d-befb-3b9df51d7639','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('11',NULL,'2021-05-19 21:26:30','is',77,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('22','11','2021-05-19 21:27:01','.',2,'5104c346-25c3-421d-befb-3b9df51d7639','78d1bf47-b986-46f5-90d0-17b08e7c416b'),
('33','11','2021-05-19 21:28:02','Why',2,'5104c346-25c3-421d-befb-3b9df51d7639','a3a46b84-4323-4aca-bc27-b1207a10cd16'),
('44','33','2021-05-19 21:29:01','would',1,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('55','22','2021-05-19 21:28:02','you',3,'5104c346-25c3-421d-befb-3b9df51d7639','0cb981da-10b9-4dcb-8905-b70b69dbdf95'),
('66','22','2021-05-19 21:29:01','allow',2,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('77','33','2021-05-19 21:30:02','all',3,'5104c346-25c3-421d-befb-3b9df51d7639','f37e74b0-5682-40a9-aa2f-0a6568de74d1'),
('88','55','2021-05-19 21:29:02','this',3,'5104c346-25c3-421d-befb-3b9df51d7639','a3a46b84-4323-4aca-bc27-b1207a10cd16'),
('99','88','2021-05-19 21:30:01','suffering',2,'5104c346-25c3-421d-befb-3b9df51d7639','2e3ae8ec-1334-475e-b5bb-d1e89bdb416d'),
('00','88','2021-05-19 21:31:02','?',3,'5104c346-25c3-421d-befb-3b9df51d7639','f37e74b0-5682-40a9-aa2f-0a6568de74d1');

DROP FUNCTION if exists
get_Comments_Sorted(postid varchar)
cascade;

create type sortAs as (
comment_karma int,
comment_timestamp TIMESTAMP
);


CREATE OR REPLACE FUNCTION get_Comments_Sorted(postid varchar)
  RETURNS SETOF postcomment AS
$func$
BEGIN
   RETURN QUERY
   
	with recursive cte as (
	    (select t.comment_id, t.parent_id, t.comment_timestamp, t.comment_content, t.comment_karma, t.post_id, t.user_id, array[(t.comment_karma, t.comment_timestamp)::sortAs] as path
	    from postcomment as t
	    where t.post_id = postid and parent_id is null 
	    
	    )
	    
	    union all
	    
	    (select tt.comment_id, tt.parent_id, tt.comment_timestamp, tt.comment_content, tt.comment_karma, tt.post_id, tt.user_id, c.path || (tt.comment_karma, tt.comment_timestamp)::sortAs
	    from postcomment as tt
	        inner join cte as c on c.comment_id = tt.parent_id
	        ) 
	) 
	
	select cte.comment_id, cte.parent_id, cte.comment_timestamp, cte.comment_content, cte.comment_karma, cte.post_id, cte.user_id
	from cte
	order by path;
   
END
$func$
LANGUAGE plpgsql;

select * from get_Comments_Sorted('5104c346-25c3-421d-befb-3b9df51d7639');




------------------------------I THINK IT WORKS!!!---------------------------------------------

CREATE OR REPLACE FUNCTION get_Comments_Sorted(postid varchar)
  RETURNS TABLE (comment_id varchar, 
				parent_id varchar,
				comment_timestamp TIMESTAMP,
				comment_content varchar,
				comment_karma int,
				post_id varchar,
				user_id varchar,
				pppath int[],
				pppath_sort bigint[]) AS
$func$
BEGIN
   RETURN QUERY
   
		WITH RECURSIVE tree AS (
		   SELECT pp.comment_id, pp.parent_id, pp.comment_timestamp, pp.comment_content, pp.comment_karma, pp.post_id, pp.user_id, ARRAY[pp.comment_karma] AS path, 
		   		ARRAY[row_number() OVER (ORDER BY pp.comment_karma::int DESC, pp.comment_timestamp::timestamp ASC)] AS path_sort
		   FROM   postcomment pp
		   WHERE  pp.post_id = postid and pp.parent_id IS NULL
		
		   UNION ALL
		   SELECT c.comment_id, c.parent_id, c.comment_timestamp, c.comment_content, c.comment_karma, c.post_id, c.user_id, t.path || c.comment_karma,
		          t.path_sort || row_number() OVER (ORDER BY c.comment_karma::int DESC, c.comment_timestamp::timestamp ASC)
		   FROM   tree t
		   JOIN   postcomment c ON t.comment_id = c.parent_id
		   --WHERE  NOT c.comment_karma = ANY(path)
		   )
		SELECT *
		FROM   tree
		ORDER  BY path_sort;
   
END
$func$
LANGUAGE plpgsql;
