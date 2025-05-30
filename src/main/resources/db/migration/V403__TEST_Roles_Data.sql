INSERT INTO users_schema.roles (name) VALUES
                                          ('ADMIN'),
                                          ('TEACHER'),
                                          ('STUDENT')
ON CONFLICT (name) DO NOTHING;