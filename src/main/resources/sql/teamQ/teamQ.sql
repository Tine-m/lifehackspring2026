BEGIN;


CREATE TABLE IF NOT EXISTS public.plants
(
    plant_id serial NOT NULL,
    plant_name character varying COLLATE pg_catalog."default",
    water_need bigint,
    sun_need bigint,
    CONSTRAINT plants_pkey PRIMARY KEY (plant_id)
    );

CREATE TABLE IF NOT EXISTS public.user_plants
(
    user_plant_id serial NOT NULL,
    plant_id bigint NOT NULL,
    user_id bigint NOT NULL,
    water_need bigint NOT NULL,
    sun_need bigint NOT NULL,
    CONSTRAINT user_plants_pkey PRIMARY KEY (user_plant_id)
    );

CREATE TABLE IF NOT EXISTS public.users
(
    user_id serial NOT NULL,
    user_name character(1) COLLATE pg_catalog."default" NOT NULL,
    password character(1) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
    );

ALTER TABLE IF EXISTS public.user_plants
    ADD CONSTRAINT plant_id FOREIGN KEY (plant_id)
    REFERENCES public.plants (plant_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.user_plants
    ADD CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

END;