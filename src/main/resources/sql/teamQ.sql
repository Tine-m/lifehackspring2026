BEGIN;


CREATE TABLE IF NOT EXISTS public.dating_query
(
    id integer NOT NULL DEFAULT nextval('tutorial_site_id_seq'::regclass),
    weekly_title character varying(255) COLLATE pg_catalog."default",
    content text COLLATE pg_catalog."default",
    like_counter integer DEFAULT 0,
    CONSTRAINT tutorial_site_pkey PRIMARY KEY (id)
    );
END;