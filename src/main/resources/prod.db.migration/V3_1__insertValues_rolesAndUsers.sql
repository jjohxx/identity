-- Inserting data into the 'users' table
INSERT INTO public.users (id, created_at, deleted, email, password, username)
VALUES
    (1, now(), false, 'user1@example.com', 'password1', 'user1'),
    (2, now(), false, 'user2@example.com', 'password2', 'user2'),
    (3, now(), false, 'user3@example.com', 'password3', 'user3');

-- Inserting data into the 'role' table
INSERT INTO public.role (id, description, name)
VALUES
    (1, 'Administrator role', 'admin'),
    (2, 'Regular user role', 'user'),
    (3, 'Manager role', 'manager');