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




