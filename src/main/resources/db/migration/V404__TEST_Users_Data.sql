INSERT INTO users_schema.users (username, email, password_hash, role_id, full_name)
VALUES
    (
        'admin',
        'admin@example.com',
        '$2a$10$zWrfCHfwV32J4ePlrUmMgOYJPu05EMwzEVfZj.Afcsfp3K7CRbb2C', -- admin123
        (SELECT id FROM users_schema.roles WHERE name = 'ADMIN'),
        'Admin User'
    ),
    (
        'student',
        'user@example.com',
        '$2a$10$3EmL/wKW9LJMbItpbM.NAOn9KaAh5Btlu4YF2Z/q9ljjEZq99llse', -- user123
        (SELECT id FROM users_schema.roles WHERE name = 'STUDENT'),
        'Student Student'
    ),
    (
        'teacher',
        'moderator@example.com',
        '$2a$10$kcL0tbOJJ6aDg5oxlWyS..iCUF9ZupFZxF4wKhq9a2XndB3yVOE0y', -- moderator123
        (SELECT id FROM users_schema.roles WHERE name = 'TEACHER'),
        'Teacher Teacher'
    );
