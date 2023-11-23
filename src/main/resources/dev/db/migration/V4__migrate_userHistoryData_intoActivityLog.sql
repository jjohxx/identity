-- Inserting activity logs based on existing users' data
-- Description: This script generates activity log entries for each user creation in the users table

INSERT INTO activity_log (user_id, activity_description, activity_timestamp)
SELECT
    id AS user_id,
    'User created: ' || username AS activity_description,
    created_at AS activity_timestamp
FROM public.users;
