
CREATE OR REPLACE FUNCTION update_karma_sum_post() RETURNS TRIGGER AS
$BODY$
BEGIN
    UPDATE reddit_user
		set sum_post_karma=sum_post_karma + (new.post_karma-old.post_karma)
			where user_id=new.user_id;
			RETURN new;
END;
$BODY$
language plpgsql;

CREATE TRIGGER trigger_post_karma
     AFTER UPDATE OF post_karma ON post
     FOR EACH ROW
     EXECUTE PROCEDURE update_karma_sum_post();
	 
	 
CREATE OR REPLACE FUNCTION update_karma_sum_comment() RETURNS TRIGGER AS
$BODY$
BEGIN
    UPDATE reddit_user
		set sum_comment_karma=sum_comment_karma + (new.comment_karma-old.comment_karma)
			where user_id=new.user_id;
			RETURN new;
END;
$BODY$
language plpgsql;

CREATE TRIGGER trigger_comment_karma
     AFTER UPDATE OF comment_karma ON postcomment
     FOR EACH ROW
     EXECUTE PROCEDURE update_karma_sum_comment();	 


