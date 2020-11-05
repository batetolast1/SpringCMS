DROP DATABASE spring_cms;

CREATE DATABASE spring_cms
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

CREATE TABLE article_categories
(
    article_id  bigint NOT NULL,
    category_id bigint NOT NULL
) ENGINE = InnoDB;


CREATE TABLE articles
(
    id         bigint NOT NULL AUTO_INCREMENT,
    created_on datetime(6),
    updated_on datetime(6),
    content    TEXT,
    title      varchar(200),
    author_id  bigint,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE authors
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    created_on datetime(6),
    updated_on datetime(6),
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE categories
(
    id          bigint NOT NULL AUTO_INCREMENT,
    created_on  datetime(6),
    updated_on  datetime(6),
    description varchar(255),
    name        VARCHAR(100) UNIQUE,
    PRIMARY KEY (id)
) ENGINE = InnoDB;


ALTER TABLE article_categories
    ADD CONSTRAINT FK8lwbpiccld0fsuhw5xab62vl0
        FOREIGN KEY (category_id) REFERENCES categories (id);


ALTER TABLE article_categories
    ADD CONSTRAINT FKiaa1jged75v2k1r2ongjm9hko
        FOREIGN KEY (article_id) REFERENCES articles (id);


ALTER TABLE articles
    ADD CONSTRAINT FKglvhv5e43dmjhmiovwhcax7aq
        FOREIGN KEY (author_id) REFERENCES authors (id);

INSERT INTO authors(created_on, first_name, last_name)
VALUES (NOW(), 'Adam', 'Anthem'),
       (NOW(), 'Ben', 'Bobbins'),
       (NOW(), 'Cedric', 'Corgi');

INSERT INTO categories(created_on, name, description)
VALUES (NOW(), 'sport', 'news about sport events'),
       (NOW(), 'politics', 'things that really matter'),
       (NOW(), 'health', 'it\'s all about covid now');

INSERT INTO articles(created_on, content, title, author_id)
VALUES (NOW(), 'article 1 content', 'article 1 title', 1),
       (NOW(), 'article 2 content', 'article 2 title', 2),
       (NOW(), 'article 3 content', 'article 3 title', 1),
       (NOW(), 'article 4 content', 'article 4 title', 1),
       (NOW(), 'article 5 content', 'article 5 title', 2),
       (NOW(), 'article 6 content', 'article 6 title', 1);

INSERT INTO article_categories
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 1),
       (4, 2),
       (5, 1),
       (6, 1),
       (6, 2),
       (6, 3);