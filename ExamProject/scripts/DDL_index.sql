CREATE INDEX idx_sort_post_id ON public.postcomment (post_id ASC);
CREATE INDEX idx_sort_subreddit_id ON public.post (subreddit_id ASC);