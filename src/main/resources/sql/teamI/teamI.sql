
CREATE SEQUENCE users_user_id_seq;


CREATE TABLE IF NOT EXISTS public.teami_account
(
    user_id integer NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS public.teami_coffeetypes
(
    coffeetype character varying(30) COLLATE pg_catalog."default" NOT NULL,
    milk integer,
    water integer,
    bean integer,
    CONSTRAINT coffeetypes_pkey PRIMARY KEY (coffeetype)
    );

CREATE TABLE IF NOT EXISTS public.teami_favorits
(
    user_id integer NOT NULL,
    coffeetype character varying COLLATE pg_catalog."default" NOT NULL,
    milk integer,
    water integer NOT NULL,
    bean integer NOT NULL,
    brand character varying COLLATE pg_catalog."default",
    CONSTRAINT teami_favorits_pkey PRIMARY KEY (user_id, coffeetype)
    );

CREATE TABLE IF NOT EXISTS public.teami_users
(
    user_id integer NOT NULL DEFAULT nextval('users_user_id_seq'::regclass),
    firstname character varying COLLATE pg_catalog."default" NOT NULL,
    lastname character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_email_key UNIQUE (email)
    );

ALTER TABLE IF EXISTS public.teami_account
    ADD CONSTRAINT fk_account_user FOREIGN KEY (user_id)
    REFERENCES public.teami_users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS account_pkey
    ON public.teami_account(user_id);


ALTER TABLE IF EXISTS public.teami_favorits
    ADD CONSTRAINT userid_favorits FOREIGN KEY (user_id)
    REFERENCES public.teami_account (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

INSERT INTO teami_coffeetypes (coffeetype, milk, water, bean)
VALUES
    ('Mocha', 200, 60, 18),
    ('Macchiato', 15, 30, 9),
    ('Lungo', 0, 60, 9),
    ('Latte_Macchiato', 30, 250, 9),
    ('Flat_White', 150, 60, 18),
    ('Espresso', 0, 60, 9),
    ('Cortado', 40, 30, 9),
    ('Cappuccino', 150, 30, 9),
    ('Caffe_Latte', 240, 60, 18),
    ('Americano', 0, 150, 18);
