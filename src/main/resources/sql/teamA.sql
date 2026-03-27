create table teama_users
(
    user_id  bigserial
        primary key,
    username varchar not null,
    password varchar not null
);

alter table teama_users
    owner to postgres;

create table teama_subscriptions
(
    subscription_id   bigserial
        primary key,
    subscription_name varchar          not null,
    subscription_cost double precision not null,
    usage_amount      integer          not null,
    user_id           bigserial
        constraint subscriptions_user_id_fkey
            references teama_users,
    category          varchar          not null
);

alter table teama_subscriptions
    owner to postgres;

create table users
(
    user_id  serial
        primary key,
    username varchar(50)              not null
        constraint unique_username
            unique,
    password varchar(50)              not null,
    role     varchar(20) default USER not null
);

alter table users
    owner to postgres;

