-- Table: activity_log
-- Description: Records activities or events in the system

CREATE TABLE activity_log (
                              log_id SERIAL PRIMARY KEY,
                              user_id INT,-
                              activity_description TEXT NOT NULL,
                              activity_timestamp TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE activity_log IS 'Records activities or events in the system';

COMMENT ON COLUMN activity_log.log_id IS 'Unique identifier for each activity log entry';
COMMENT ON COLUMN activity_log.user_id IS 'ID of the user associated with the activity';
COMMENT ON COLUMN activity_log.activity_description IS 'Description of the activity or event';
COMMENT ON COLUMN activity_log.activity_timestamp IS 'Timestamp when the activity occurred';