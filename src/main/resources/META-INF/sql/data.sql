DROP DATABASE spring_cms;

CREATE DATABASE spring_cms
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

create table article_categories
(
    article_id  bigint not null,
    category_id bigint not null
) engine = InnoDB;

create table articles
(
    id         bigint not null auto_increment,
    created_on datetime(6),
    updated_on datetime(6),
    content    TEXT,
    title      varchar(200),
    author_id  bigint,
    primary key (id)
) engine = InnoDB;

create table authors
(
    id         bigint       not null auto_increment,
    created_on datetime(6),
    updated_on datetime(6),
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    primary key (id)
) engine = InnoDB;

create table categories
(
    id          bigint not null auto_increment,
    created_on  datetime(6),
    updated_on  datetime(6),
    description varchar(255),
    name        VARCHAR(100) UNIQUE,
    primary key (id)
) engine = InnoDB;

alter table article_categories
    add constraint FK8lwbpiccld0fsuhw5xab62vl0 foreign key (category_id) references categories (id);

alter table article_categories
    add constraint FKiaa1jged75v2k1r2ongjm9hko foreign key (article_id) references articles (id);

alter table articles
    add constraint FKglvhv5e43dmjhmiovwhcax7aq foreign key (author_id) references authors (id);


INSERT INTO authors(created_on, first_name, last_name)
VALUES (NOW(), 'Adam', 'Anthem'),
       (NOW(), 'Ben', 'Bobbins'),
       (NOW(), 'Cedric', 'Corgi');

INSERT INTO categories(created_on, description, name)
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