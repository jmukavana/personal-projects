CREATE TABLE bookmarks
(
    id          BIGINT                       NOT NULL,
    title       VARCHAR(200)                 NOT NULL,
    url         VARCHAR(500)                 NOT NULL,
    created_at  datetime     DEFAULT NOW()   NOT NULL,
    updated_at  datetime                     NULL,
    status      VARCHAR(255) DEFAULT 'DRAFT' NOT NULL,
    category_id BIGINT                       NULL,
    CONSTRAINT pk_bookmarks PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

ALTER TABLE bookmarks
    ADD CONSTRAINT FK_BOOKMARKS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);