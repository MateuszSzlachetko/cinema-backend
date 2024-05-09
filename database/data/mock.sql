INSERT INTO "user" ("id", "user_type", "created_at", "name", "surname", "phone_number", "email")
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'customer', '2024-05-09 14:35:00', 'Alice', 'Johnson', '123-456-7890', 'alice.johnson@example.com'),
    ('123e4567-e89b-12d3-a456-426614174001', 'customer', '2024-05-09 14:35:00', 'Bob', 'Smith', '123-456-7891', 'bob.smith@example.com');

INSERT INTO "screening_room" ("id", "name")
VALUES
    (1, 'Main Hall'),
    (2, 'Side Hall');

-- Insert seats for Main Hall
INSERT INTO "seat" ("id", "screening_room_id", "row", "column")
VALUES
    (1, 1, 'A', 1),
    (2, 1, 'A', 2),
    (3, 1, 'A', 3),
    (4, 1, 'A', 4),
    (5, 1, 'A', 5),
    (6, 1, 'A', 6),
    (7, 1, 'A', 7),
    (8, 1, 'A', 8),
    (9, 1, 'A', 9),
    (10, 1, 'A', 10),
    (11, 1, 'B', 1),
    (12, 1, 'B', 2),
    (13, 1, 'B', 3),
    (14, 1, 'B', 4),
    (15, 1, 'B', 5),
    (16, 1, 'B', 6),
    (17, 1, 'B', 7),
    (18, 1, 'B', 8),
    (19, 1, 'B', 9),
    (20, 1, 'B', 10),
    (21, 1, 'C', 1),
    (22, 1, 'C', 2),
    (23, 1, 'C', 3),
    (24, 1, 'C', 4),
    (25, 1, 'C', 5),
    (26, 1, 'C', 6),
    (27, 1, 'C', 7),
    (28, 1, 'C', 8),
    (29, 1, 'C', 9),
    (30, 1, 'C', 10),
    (31, 1, 'D', 1),
    (32, 1, 'D', 2),
    (33, 1, 'D', 3),
    (34, 1, 'D', 4),
    (35, 1, 'D', 5),
    (36, 1, 'D', 6),
    (37, 1, 'D', 7),
    (38, 1, 'D', 8),
    (39, 1, 'D', 9),
    (40, 1, 'D', 10),
    (41, 1, 'E', 1),
    (42, 1, 'E', 2),
    (43, 1, 'E', 3),
    (44, 1, 'E', 4),
    (45, 1, 'E', 5),
    (46, 1, 'E', 6),
    (47, 1, 'E', 7),
    (48, 1, 'E', 8),
    (49, 1, 'E', 9),
    (50, 1, 'E', 10);

-- Insert seats for Side Hall
INSERT INTO "seat" ("id", "screening_room_id", "row", "column")
VALUES
    (51, 2, 'A', 1),
    (52, 2, 'A', 2),
    (53, 2, 'A', 3),
    (54, 2, 'A', 4),
    (55, 2, 'A', 5),
    (56, 2, 'A', 6),
    (57, 2, 'A', 7),
    (58, 2, 'A', 8),
    (59, 2, 'A', 9),
    (60, 2, 'A', 10),
    (61, 2, 'A', 11),
    (62, 2, 'A', 12),
    (63, 2, 'A', 13),
    (64, 2, 'A', 14),
    (65, 2, 'A', 15),
    (66, 2, 'B', 1),
    (67, 2, 'B', 2),
    (68, 2, 'B', 3),
    (69, 2, 'B', 4),
    (70, 2, 'B', 5),
    (71, 2, 'B', 6),
    (72, 2, 'B', 7),
    (73, 2, 'B', 8),
    (74, 2, 'B', 9),
    (75, 2, 'B', 10),
    (76, 2, 'B', 11),
    (77, 2, 'B', 12),
    (78, 2, 'B', 13),
    (79, 2, 'B', 14),
    (80, 2, 'B', 15),
    (81, 2, 'C', 1),
    (82, 2, 'C', 2),
    (83, 2, 'C', 3),
    (84, 2, 'C', 4),
    (85, 2, 'C', 5),
    (86, 2, 'C', 6),
    (87, 2, 'C', 7),
    (88, 2, 'C', 8),
    (89, 2, 'C', 9),
    (90, 2, 'C', 10),
    (91, 2, 'C', 11),
    (92, 2, 'C', 12),
    (93, 2, 'C', 13),
    (94, 2, 'C', 14),
    (95, 2, 'C', 15),
    (96, 2, 'D', 1),
    (97, 2, 'D', 2),
    (98, 2, 'D', 3),
    (99, 2, 'D', 4),
    (100, 2, 'D', 5),
    (101, 2, 'D', 6),
    (102, 2, 'D', 7),
    (103, 2, 'D', 8),
    (104, 2, 'D', 9),
    (105, 2, 'D', 10),
    (106, 2, 'D', 11),
    (107, 2, 'D', 12),
    (108, 2, 'D', 13),
    (109, 2, 'D', 14),
    (110, 2, 'D', 15);

INSERT INTO "movie_poster" ("id", "access_url", "file_name", "file_path")
VALUES
    ('123e4567-e89b-12d3-a456-426614174002', '/images/the-great-adventure-poster.jpg', 'the-great-adventure-poster.jpg', '/images/the-great-adventure-poster.jpg'),
    ('123e4567-e89b-12d3-a456-426614174003', '/images/mystery-in-the-woods-poster.jpg', 'mystery-in-the-woods-poster.jpg', '/images/mystery-in-the-woods-poster.jpg');

INSERT INTO "movie" ("id", "title", "description", "duration", "movie_poster_id")
VALUES
    ('123e4567-e89b-12d3-a456-426614174004', 'The Great Adventure', 'A journey of discovery.', 120, '123e4567-e89b-12d3-a456-426614174002'),
    ('123e4567-e89b-12d3-a456-426614174005', 'Mystery in the Woods', 'A suspenseful thriller.', 90, '123e4567-e89b-12d3-a456-426614174003');

INSERT INTO "screening" ("id", "screening_room_id", "movie_id", "start_date", "advertisements_duration")
VALUES
    ('123e4567-e89b-12d3-a456-426614174006', 1, '123e4567-e89b-12d3-a456-426614174004', '2024-05-10 14:35:00', 10),
    ('123e4567-e89b-12d3-a456-426614174007', 2, '123e4567-e89b-12d3-a456-426614174005', '2024-05-11 14:35:00', 15);

INSERT INTO "ticket" ("id", "customer_id", "screening_id", "purchase_date", "reservation_code", "state")
VALUES
    ('123e4567-e89b-12d3-a456-426614174008', '123e4567-e89b-12d3-a456-426614174000', '123e4567-e89b-12d3-a456-426614174006', '2024-05-09', '123e4567-e89b-12d3-a456-426614174010', 'confirmed'),
    ('123e4567-e89b-12d3-a456-426614174009', '123e4567-e89b-12d3-a456-426614174001', '123e4567-e89b-12d3-a456-426614174007', '2024-05-09', '123e4567-e89b-12d3-a456-426614174011', 'reserved');

INSERT INTO "seat_reservation" ("id", "ticket_id", "seat_id")
VALUES
    ('123e4567-e89b-12d3-a456-426614174012', '123e4567-e89b-12d3-a456-426614174008', 1),
    ('123e4567-e89b-12d3-a456-426614174013', '123e4567-e89b-12d3-a456-426614174009', 2);