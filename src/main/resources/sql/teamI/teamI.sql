CREATE TABLE IF NOT EXISTS public.account
(
    user_id integer NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS public.coffeetypes
(
    coffeetype character varying(30) COLLATE pg_catalog."default" NOT NULL,
    milk_ml integer,
    water_ml integer,
    beans_g integer,
    CONSTRAINT coffeetypes_pkey PRIMARY KEY (coffeetype)
    );

CREATE TABLE IF NOT EXISTS public.favorits
(
    user_id integer NOT NULL,
    "coffeeName" character varying COLLATE pg_catalog."default" NOT NULL,
    milk integer,
    water integer NOT NULL,
    bean integer NOT NULL,
    brand character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT favorits_pkey PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS public.users
(
    user_id serial NOT NULL,
    firstname character varying COLLATE pg_catalog."default" NOT NULL,
    lastname character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_email_key UNIQUE (email)
    );

ALTER TABLE IF EXISTS public.account
    ADD CONSTRAINT fk_account_user FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS account_pkey
    ON public.account(user_id);


ALTER TABLE IF EXISTS public.favorits
    ADD CONSTRAINT fk_favorits_user FOREIGN KEY (user_id)
    REFERENCES public.account (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS favorits_pkey
    ON public.favorits(user_id);

END;