-- roles - IGNORE para que no falle si ya existen-
INSERT IGNORE INTO roles (id, nombre) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_CLIENTE');

-- usuarios clave
-- hash $2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1cnR.MOpn2.xW equivale a la contraseña
INSERT IGNORE INTO usuarios (id, username, password) VALUES
(1, 'Chuck_Norris', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1cnR.MOpn2.xW'),
(2, 'Yordy_olivares', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1cnR.MOpn2.xW'),
(3, 'Emmanuel_Correa', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1cnR.MOpn2.xW');

-- roles Chuck=Admin
INSERT IGNORE INTO usuarios_roles (usuario_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 2);