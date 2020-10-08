INSERT INTO authority(name) VALUES ('ROLE_ADMIN');
INSERT INTO authority(name) VALUES ('ROLE_USER');

-- Admin Role
INSERT INTO user_info(email, enabled, password, provider, username)
VALUES ('admin@bootware.jp', 'true', '$2a$10$zgPrRttBhV9k8kI5AOv2P.dVXX6HFdynM.7lXp4RJkLFaywzAxIOy', 'LOCAL', 'admin');
INSERT INTO user_authority(user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority(user_id, authority_id) VALUES (1, 2);

-- User Role
INSERT INTO user_info(email, enabled, password, provider, username)
VALUES ('user@bootware.jp', 'true', '$2a$10$zgPrRttBhV9k8kI5AOv2P.dVXX6HFdynM.7lXp4RJkLFaywzAxIOy', 'LOCAL', 'user');
INSERT INTO user_authority(user_id, authority_id) VALUES (2, 2);
