create or replace FUNCTION all_userIDs()
  returns TABLE (user_id varchar) 
AS
$func$
  SELECT * 
  FROM public.reddit_user;
$func$ 
LANGUAGE sql;


create or replace FUNCTION all_userIDs()
  returns TABLE (user_id varchar) 
AS
$func$
  SELECT * 
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