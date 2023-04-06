create table auth.user
(
    id       int auto_increment,
    name     VARCHAR(256) not null,
    password VARCHAR(64)  null,
    constraint user_pk
    primary key (id)
);

