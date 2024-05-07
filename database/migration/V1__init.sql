CREATE TABLE "user" (
                        "id" uuid PRIMARY KEY,
                        "user_type" varchar,
                        "created_at" timestamp,
                        "name" varchar,
                        "surname" varchar,
                        "phone_number" varchar,
                        "email" varchar
);

CREATE TABLE "ticket" (
                          "id" uuid PRIMARY KEY,
                          "customer_id" uuid,
                          "screening_id" uuid,
                          "purchase_date" date,
                          "reservation_code" uuid,
                          "state" varchar
);

CREATE TABLE "seat_reservation" (
                                    "id" uuid PRIMARY KEY,
                                    "ticket_id" uuid,
                                    "seat_id" int
);

CREATE TABLE "screening_room" (
                                  "id" int PRIMARY KEY,
                                  "name" varchar
);

CREATE TABLE "seat" (
                        "id" int PRIMARY KEY,
                        "screening_room_id" int,
                        "row" varchar,
                        "column" int
);

CREATE TABLE "screening" (
                             "id" uuid PRIMARY KEY,
                             "screening_room_id" int,
                             "movie_id" uuid,
                             "start_date" date,
                             "advertisements_duration" int
);

CREATE TABLE "movie" (
                         "id" uuid PRIMARY KEY,
                         "title" varchar,
                         "description" text,
                         "duration" int,
                         "movie_poster_id" uuid
);

CREATE TABLE "movie_poster" (
                                "id" uuid PRIMARY KEY,
                                "access_url" varchar,
                                "file_name" varchar,
                                "file_path" varchar
);

ALTER TABLE "movie" ADD FOREIGN KEY ("movie_poster_id") REFERENCES "movie_poster" ("id");

ALTER TABLE "screening" ADD FOREIGN KEY ("screening_room_id") REFERENCES "screening_room" ("id");

ALTER TABLE "screening" ADD FOREIGN KEY ("movie_id") REFERENCES "movie" ("id");

ALTER TABLE "seat" ADD FOREIGN KEY ("screening_room_id") REFERENCES "screening_room" ("id");

ALTER TABLE "seat_reservation" ADD FOREIGN KEY ("seat_id") REFERENCES "seat" ("id");

ALTER TABLE "seat_reservation" ADD FOREIGN KEY ("ticket_id") REFERENCES "ticket" ("id");

ALTER TABLE "ticket" ADD FOREIGN KEY ("screening_id") REFERENCES "screening" ("id");

ALTER TABLE "ticket" ADD FOREIGN KEY ("customer_id") REFERENCES "user" ("id");
