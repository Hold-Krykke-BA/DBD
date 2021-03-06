CREATE TABLE subreddit (
  subreddit_id VARCHAR (50) UNIQUE NOT NULL,
  subreddit_name VARCHAR UNIQUE NOT NULL,
  PRIMARY KEY (subreddit_id)
);

CREATE TABLE reddit_user (
  user_id VARCHAR (50) UNIQUE NOT NULL,
  sum_comment_karma int DEFAULT 0,
  sum_post_karma int DEFAULT 0,
  PRIMARY KEY (user_id)
);


CREATE TABLE user_subreddit (
  user_id VARCHAR (50) NOT NULL,
  subreddit_id VARCHAR (50) NOT NULL,
  PRIMARY KEY (user_id, subreddit_id),
  FOREIGN KEY (user_id)
    REFERENCES reddit_user (user_id),
  FOREIGN KEY (subreddit_id)
    REFERENCES subreddit (subreddit_id)
);

CREATE TABLE post (
  post_id VARCHAR (50) UNIQUE NOT NULL,
  post_url_identifier VARCHAR(25) NOT NULL,
  post_title VARCHAR (255) NOT NULL,
  post_timestamp TIMESTAMP NOT NULL,
  post_content VARCHAR (1000) NOT NULL,
  post_karma int NOT NULL,
  user_id VARCHAR (50) NOT NULL,
  subreddit_id VARCHAR (50) NOT NULL,
  PRIMARY KEY (post_id),
  FOREIGN KEY (user_id)
    REFERENCES reddit_user (user_id),
  FOREIGN KEY (subreddit_id)
    REFERENCES subreddit (subreddit_id)
);

CREATE TABLE postcomment (
  comment_id VARCHAR (50) UNIQUE NOT NULL,
  parent_id VARCHAR (50),
  comment_timestamp TIMESTAMP NOT NULL,
  comment_content VARCHAR (1000) NOT NULL,
  comment_karma int NOT NULL,
  post_id VARCHAR (50) NOT NULL,
  user_id VARCHAR (50) NOT NULL,
  PRIMARY KEY (comment_id),
  FOREIGN KEY (post_id)
    REFERENCES post (post_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id)
    REFERENCES reddit_user (user_id), 
  FOREIGN KEY (parent_id)
	REFERENCES postcomment (comment_id) ON DELETE CASCADE
);






