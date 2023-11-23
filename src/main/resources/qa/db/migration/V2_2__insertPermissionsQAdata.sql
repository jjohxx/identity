-- Inserting data into the 'permissions' table
INSERT INTO permissions (permission_name, description)
VALUES
    ('READ_USER', 'Permission to read user information'),
    ('WRITE_USER', 'Permission to modify user information'),
    ('DELETE_USER', 'Permission to delete user records'),
    ('CREATE_ROLE', 'Permission to create new roles'),
    ('DELETE_ROLE', 'Permission to delete roles');