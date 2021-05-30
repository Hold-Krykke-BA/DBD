create or replace FUNCTION all_userIDs()
  returns TABLE (user_id varchar) 
AS
$func$
  SELECT user_id 
  FROM public.reddit_user;
$func$ 
LANGUAGE sql;


CREATE OR REPLACE FUNCTION get_FPitem(subID varchar)
  RETURNS TABLE (post_title varchar, 
				post_id varchar,
				post_url_identifier varchar,
				post_timestamp TIMESTAMP,
				user_id varchar,
				subreddit_name varchar,
				post_karma int,
				comments bigint) AS
$func$
BEGIN
   RETURN QUERY
	select p.post_title, p.post_id, p.post_url_identifier, p.post_timestamp, p.user_id, s.subreddit_name, p.post_karma, count(c.comment_id) as comments
	from subreddit s left join post p on s.subreddit_id = p.subreddit_id 
	left join postcomment c on p.post_id = c.post_id 
	where s.subreddit_id = subID
	group by p.post_id, s.subreddit_name;                   
END
$func$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_Post(subname varchar, p_url varchar)
  returns setof post AS
	'select p from post p left join subreddit s on p.subreddit_id = s.subreddit_id 
	where p.post_url_identifier = p_url and s.subreddit_name = subname;'
LANGUAGE sql;

CREATE OR REPLACE FUNCTION get_Comments(postid varchar)
  RETURNS SETOF postcomment AS
$func$
BEGIN
   RETURN QUERY
	select * from postcomment c  
	where c.post_id = postid;              
END
$func$
LANGUAGE plpgsql;


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
		   )
		SELECT *
		FROM   tree
		ORDER  BY path_sort;
   
END
$func$
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_FollowedSubreddits(userid varchar)
    RETURNS SETOF subreddit AS
$func$
BEGIN
   RETURN QUERY
	select s.subreddit_id, s.subreddit_name from subreddit s left join user_subreddit us on s.subreddit_id = us.subreddit_id
	where us.user_id = userid;              
END
$func$
LANGUAGE plpgsql;











