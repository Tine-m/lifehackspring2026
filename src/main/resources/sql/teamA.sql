CREATE TABLE IF NOT EXISTS public.teama_subscriptions
(
    subscription_id bigserial NOT NULL,
    subscription_name character varying COLLATE pg_catalog."default" NOT NULL,
    subscription_cost double precision NOT NULL,
    usage_amount integer NOT NULL,
    user_id bigserial NOT NULL,
    category character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT teama_subscriptions_pkey PRIMARY KEY (subscription_id)
    );

CREATE TABLE IF NOT EXISTS public.teamA_users
(
    user_id bigserial NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT teama_users_pkey PRIMARY KEY (user_id)
    );

ALTER TABLE IF EXISTS public.teama_subscriptions
    ADD CONSTRAINT subscriptions_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.teama_users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;