DROP TABLE IF EXISTS 
   subreddit,
   reddit_user,
   user_subreddit,
   post,
   postcomment   
CASCADE;

DROP FUNCTION if exists
get_fpitem(character varying),
all_userIDs(),
get_Post(subname varchar, p_url varchar),
get_Comments(postid varchar),
get_FollowedSubreddits(userid varchar)
CASCADE;

DROP INDEX IF exists 
idx_sort_subreddit_id, 
idx_sort_post_id
CASCADE;