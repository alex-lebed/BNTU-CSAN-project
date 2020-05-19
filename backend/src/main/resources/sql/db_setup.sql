CREATE TABLE question
(
    id            SERIAL,
    question_text text,

    PRIMARY KEY (id)
);

CREATE TABLE answer
(
    id          SERIAL,
    question_id integer NOT NULL,
    answer_text varchar(255),
    is_correct  bool DEFAULT false,

    PRIMARY KEY (id),
    FOREIGN KEY (question_id) REFERENCES question (id)
);

CREATE TABLE quiz_admin
(
    login          varchar(255),
    admin_password varchar(255),
    admin_name     varchar(255),

    PRIMARY KEY (login)
);

CREATE TABLE quiz_winner
(
    id          SERIAL,
    game_date   timestamp,
    player_name varchar(255),
    score       integer,

    PRIMARY KEY (id)
);