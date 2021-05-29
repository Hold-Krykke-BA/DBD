




--UPDATE public.postcomment
--		set comment_content=c_content
--		WHERE comment_id = c_id;

--UPDATE calculated_points 
--	SET points = (SELECT SUM(points)
--					 FROM user_points
--					 WHERE user_id = NEW.user_id)
--	 WHERE user_id = NEW.user_id;

--RETURN NEW;

CREATE OR REPLACE FUNCTION update_karma_sum() RETURNS TRIGGER AS
$BODY$
BEGIN
    UPDATE 
		set sum_post_karma=(sum_post_karma + new.post_karma
			where user_id=new.user_id;
			RETURN new;
END;
$BODY$
language plpgsql;

CREATE TRIGGER trigger_post_karma
     AFTER UPDATE OF post_karma ON post
     FOR EACH STATEMENT
     EXECUTE PROCEDURE update_karma_sum();














