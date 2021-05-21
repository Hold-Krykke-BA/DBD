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


CREATE or replace PROCEDURE insert_post(p_id varchar, p_url_id varchar, p_title varchar, p_timestamp timestamp, p_content varchar, p_karma int, u_id varchar, s_id varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.post (post_id, post_url_identifier, post_title, post_timestamp, post_content, post_karma, user_id, subreddit_id) 
	VALUES (p_id, p_url_id, p_title, p_timestamp, p_content, p_karma, u_id, s_id);
end
$$;


CREATE or replace PROCEDURE insert_postcomment(c_id varchar, par_id varchar, c_timestamp timestamp, c_content varchar, c_karma int, post_id varchar, u_id varchar)
LANGUAGE plpgsql
security definer
AS $$
begin
	INSERT INTO public.postcomment (comment_id, parent_id, comment_timestamp, comment_content, comment_karma, post_id, user_id) 
	VALUES (c_id, par_id, c_timestamp, c_content, c_karma, post_id, u_id);
end
$$;

















AS $$
begin
	if (bark_pitch is not null) THEN
		insert into vet.dog (pet_name, pet_age, vet_id, barkpitch) VALUES
			 (p_name, p_age, v_id, bark_pitch);
	elseif (life_count is not null) then
		INSERT INTO vet.cat (pet_name, pet_age, vet_id, lifecount)	values
			(p_name, p_age, v_id, life_count);
	else 
		INSERT INTO vet.pet (pet_name, pet_age, vet_id)	values
			(p_name, p_age, v_id);
	end if;
end
$$;