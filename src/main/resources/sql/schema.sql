CREATE TABLE IF NOT EXISTS Message (
    id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    text                   VARCHAR      NOT NULL
    );