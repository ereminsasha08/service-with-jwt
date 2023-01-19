CREATE TABLE IF NOT EXISTS news
(
    title  VARCHAR(255),
    source VARCHAR(255) NOT NULL,
    topic  VARCHAR(255) NOT NULL,
    PRIMARY KEY (source, title)
);
