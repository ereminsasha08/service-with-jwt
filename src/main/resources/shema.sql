DROP TABLE IF EXISTS news;

CREATE TABLE IF NOT EXISTS news
(
    title  VARCHAR PRIMARY KEY,
    source VARCHAR NOT NULL,
    topic  VARCHAR NOT NULL
);
