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


CREATE or replace PROCEDURE update_post(p_id varchar, p_content varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	UPDATE public.post
			set post_content=p_content
			WHERE post_id = p_id;
end
$$;


CREATE or replace PROCEDURE update_comment(c_id varchar, c_content varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	UPDATE public.postcomment
			set comment_content=c_content
			WHERE comment_id = c_id;
end
$$;


CREATE or replace PROCEDURE increment_post_karma(p_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	UPDATE public.post
			set post_karma=post_karma + 1
			WHERE post_id = p_id;
end
$$;

CREATE or replace PROCEDURE decrement_post_karma(p_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	UPDATE public.post
			set post_karma=post_karma - 1
			WHERE post_id = p_id;
end
$$;


CREATE or replace PROCEDURE increment_comment_karma(c_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	UPDATE public.postcomment
			set comment_karma=comment_karma + 1
			WHERE comment_id = c_id;
end
$$;

CREATE or replace PROCEDURE decrement_comment_karma(c_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	UPDATE public.postcomment
			set comment_karma=comment_karma - 1
			WHERE comment_id = c_id;
end
$$;


CREATE or replace PROCEDURE delete_post(p_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	DELETE FROM public.post
		WHERE post_id = p_id;
end
$$;

CREATE or replace PROCEDURE delete_comment(c_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	DELETE FROM public.postcomment
		WHERE comment_id = c_id;
end
$$;

CREATE or replace PROCEDURE remove_user_follow_subreddit(u_id varchar, s_id varchar)
LANGUAGE plpgsql
security definer 
AS $$
begin
	DELETE FROM public.user_subreddit
		WHERE user_id = u_id AND subreddit_id = s_id;
end
$$;
