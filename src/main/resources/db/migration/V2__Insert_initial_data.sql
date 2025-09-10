INSERT INTO od_role (id, name, description)
VALUES (1, 'ADMIN', 'Sistem yöneticisi'),
       (2, 'USER', 'Normal kullanıcı');

INSERT INTO od_city (name)
VALUES ('İstanbul'),
       ('Ankara'),
       ('İzmir'),
       ('Antalya'),
       ('Mersin'),
       ('Bursa'),
       ('Adana'),
       ('Gaziantep'),
       ('Konya'),
       ('Trabzon');

INSERT INTO od_user (username, password, first_name, last_name, city_id, role_id)
VALUES ('admin', '$2a$10$rQ.J8jX8pN5kY5KJ5gJ5ZeF5Y5pN5kY5KJ5gJ5ZeF5Y5pN5kY5KJ5g', 'System', 'Administrator', 1, 1);

INSERT INTO od_user (username, password, first_name, last_name, city_id, role_id)
VALUES ('testuser1', '$2a$10$rQ.J8jX8pN5kY5KJ5gJ5ZeF5Y5pN5kY5KJ5gJ5ZeF5Y5pN5kY5KJ5g', 'Test', 'User1', 2, 2),
       ('testuser2', '$2a$10$rQ.J8jX8pN5kY5KJ5gJ5ZeF5Y5pN5kY5KJ5gJ5ZeF5Y5pN5kY5KJ5g', 'Test', 'User2', 3, 2);

INSERT INTO od_flight (flight_number, departure_city_id, arrival_city_id, departure_time, arrival_time, created_by)
VALUES ('TK001', 1, 2, '2025-09-11 08:00:00', '2025-09-11 09:30:00', 1),
       ('TK002', 2, 3, '2025-09-11 10:00:00', '2025-09-11 11:30:00', 1),
       ('TK003', 1, 4, '2025-09-11 14:00:00', '2025-09-11 15:30:00', 1),
       ('TK004', 3, 1, '2025-09-11 16:00:00', '2025-09-11 17:30:00', 1);