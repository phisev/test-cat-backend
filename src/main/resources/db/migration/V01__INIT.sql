CREATE TABLE cats
(
    id         uuid         NOT NULL,

    created_on timestamp    NOT NULL,
    updated_on timestamp    NOT NULL,

    name       varchar(255) NOT NULL,
    status     varchar(255) NOT NULL,

    CONSTRAINT cat_pkey PRIMARY KEY (id)
);