create table if not exists users_schema.users
(
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       role_id int not null ,
                       full_name VARCHAR(100),
                       created_at TIMESTAMP DEFAULT now(),
                       updated_at TIMESTAMP DEFAULT now()
);
ALTER TABLE users_schema.users ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES users_schema.roles(id);
