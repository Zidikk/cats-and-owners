CREATE TABLE cats
(
    cat_id SERIAL PRIMARY KEY,
    owner_id INTEGER,
    cat_colour CHARACTER VARYING(50),
    cat_name CHARACTER VARYING(50),
    cat_dob DATE
);

CREATE TABLE owners
(
    owner_id SERIAL PRIMARY KEY,
    user_id INTEGER,
    owner_name CHARACTER VARYING(50),
    owner_dob DATE
);

CREATE TABLE friends
(
    friends_id SERIAL PRIMARY KEY,
    friend_first INTEGER,
    friend_second INTEGER
);

CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    owner_id INTEGER,
    user_password CHARACTER VARYING(50),
    user_name CHARACTER VARYING(50),
    user_role CHARACTER VARYING(50)
);

ALTER TABLE cats
    ADD CONSTRAINT UK_l329ab0g4c1t78onljnxmbnp6
        UNIQUE (cat_id);

ALTER TABLE cats
    ADD CONSTRAINT FKmw13yfsjypiiq0i1osdkaeqpg
        FOREIGN KEY (owner_id) REFERENCES owners ON DELETE CASCADE;

ALTER TABLE friends
    ADD CONSTRAINT UK_l329ab0g4c1t78onljnxmbnp1
        UNIQUE (friends_id);

ALTER TABLE friends
    ADD CONSTRAINT FKmw10yfsjypiiq0i1osdkaeqpi
        FOREIGN KEY (friend_first) REFERENCES cats ON DELETE CASCADE;

ALTER TABLE friends
    ADD CONSTRAINT FKmw11yfsjypiiq0i1osdkaeqpo
        FOREIGN KEY (friend_second) REFERENCES cats ON DELETE CASCADE;


ALTER TABLE users
    ADD CONSTRAINT FKeotuev8ja8v0sdh29dynqj05p
        FOREIGN KEY (owner_id) REFERENCES owners on DELETE CASCADE;