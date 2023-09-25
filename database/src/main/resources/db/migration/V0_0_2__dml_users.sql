INSERT INTO permission (name) VALUES ('PERM_VIEW_USERS');
INSERT INTO permission (name) VALUES ('PERM_MANAGE_USERS');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role_permission_x (role_id, permission_id) VALUES ((SELECT id FROM role WHERE name='ADMIN'), (SELECT id FROM permission WHERE name='PERM_VIEW_USERS'));
INSERT INTO role_permission_x (role_id, permission_id) VALUES ((SELECT id FROM role WHERE name='ADMIN'), (SELECT id FROM permission WHERE name='PERM_MANAGE_USERS'));
INSERT INTO user (username, password, enabled, non_expired, non_locked, credentials_non_expired) VALUES ('admin', '$2a$12$SbnE7V9.L/PngYgeIDeggOx8vXiVG2Ex1dbDv4n/Yl288UQSLVXnu', 1, 1, 1, 1);
INSERT INTO user_role_x (user_id, role_id) VALUES ((SELECT id FROM user WHERE username='admin'), (SELECT id FROM role WHERE name='ADMIN'));
