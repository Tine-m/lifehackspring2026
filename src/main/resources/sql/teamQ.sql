BEGIN;

CREATE TABLE IF NOT EXISTS public.teamq_dating_query
(
    id bigserial NOT NULL,
    weekly_title character varying COLLATE pg_catalog."default",
    content character varying COLLATE pg_catalog."default",
    like_counter integer DEFAULT 0,
    CONSTRAINT teamq_dating_query_pkey PRIMARY KEY (id)
    );

INSERT INTO public.teamq_dating_query (id, weekly_title, content, like_counter)
VALUES (1, 'is_bf_into_boys', 'date: 20/03/26', 0);

END;