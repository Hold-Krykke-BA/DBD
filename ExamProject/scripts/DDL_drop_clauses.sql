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
get_FollowedSubreddits(userid varchar),
update_karma_sum_post(),
update_karma_sum_comment(),
get_Comments_Sorted(postid varchar),
get_user_with_karma(uid varchar)
cascade;

DROP INDEX IF exists 
idx_sort_subreddit_id, 
idx_sort_post_id
CASCADE;

drop trigger if exists 
trigger_post_karma on post;
drop trigger if exists 
trigger_comment_karma on postcomment;