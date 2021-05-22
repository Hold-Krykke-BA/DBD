CREATE or replace PROCEDURE insert_userID(u_id varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.reddit_user (user_id) VALUES (u_id);
end
$$;


CREATE or replace PROCEDURE insert_subreddit(s_id varchar, s_name varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.subreddit (subreddit_id, subreddit_name) VALUES (s_id, s_name);
end
$$;


CREATE or replace PROCEDURE insert_user_subreddit(u_id varchar, s_id varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.user_subreddit (user_id, subreddit_id) VALUES (u_id, s_id);
end
$$;


CREATE or replace PROCEDURE insert_post(p_id varchar, p_url_id varchar, p_title varchar, p_timestamp TIMESTAMP, p_content varchar, p_karma int, u_id varchar, s_id varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.post (post_id, post_url_identifier, post_title, post_timestamp, post_content, post_karma, user_id, subreddit_id) 
	VALUES (p_id, p_url_id, p_title, p_timestamp, p_content, p_karma, u_id, s_id);
end
$$;


CREATE or replace PROCEDURE insert_postcomment(c_id varchar, par_id varchar, c_timestamp TIMESTAMP, c_content varchar, c_karma int, post_id varchar, u_id varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.postcomment (comment_id, parent_id, comment_timestamp, comment_content, comment_karma, post_id, user_id) 
	VALUES (c_id, par_id, c_timestamp, c_content, c_karma, post_id, u_id);
end
$$;


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
















--***********************************
DROP FUNCTION if exists
get_Post(subname varchar, p_url varchar)
CASCADE;

CREATE OR REPLACE FUNCTION get_Post(subname varchar, p_url)
  RETURNS SETOF post AS
$func$
BEGIN
   RETURN QUERY
	select p from post p left join subreddit s on p.subreddit_id = s.subreddit_id 
	where p.post_url_identifier = p_url and s.subreddit_name = subname;              
END
$func$
LANGUAGE plpgsql;

SELECT * FROM get_Post('funny', '1YjAR');


select p from post p left join subreddit s on p.subreddit_id = s.subreddit_id 
where p.post_url_identifier = '1YjAR' and s.subreddit_name = 'funny';



select p.post_title, p.post_id, p.post_url_identifier, p.post_timestamp, p.user_id, s.subreddit_name, count(c.comment_id) as comments
from subreddit s left join post p on s.subreddit_id = p.subreddit_id 
left join postcomment c on p.post_id = c.post_id 
where s.subreddit_id = '609f1f9f-dba7-44c8-838b-c00bb5d3e7ac'
group by p.post_id, s.subreddit_name;