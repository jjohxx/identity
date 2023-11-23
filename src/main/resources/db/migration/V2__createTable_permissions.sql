-- Table: permissions
-- Description: Stores the permissions granted to roles or users

CREATE TABLE permissions (
                             permission_id SERIAL PRIMARY KEY,
                             permission_name VARCHAR(100) NOT NULL,
                             description TEXT
);

COMMENT ON TABLE permissions IS 'Stores the permissions granted to roles or users';

COMMENT ON COLUMN permissions.permission_id IS 'Unique identifier for each permission';
COMMENT ON COLUMN permissions.permission_name IS 'Name of the permission';
COMMENT ON COLUMN permissions.description IS 'Additional description or details about the permission';