DROP TABLE IF EXISTS public.teamO_films CASCADE;
DROP SEQUENCE IF EXISTS public.teamO_films_film_id_seq CASCADE;

CREATE TABLE public.teamO_films (
    film_id integer NOT NULL,
    title character varying(100) NOT NULL,
    year integer
);

CREATE SEQUENCE public.teamO_films_film_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.teamO_films_film_id_seq
    OWNED BY public.teamO_films.film_id;

ALTER TABLE ONLY public.teamO_films
    ALTER COLUMN film_id SET DEFAULT nextval('public.teamO_films_film_id_seq'::regclass);

ALTER TABLE ONLY public.teamO_films
    ADD CONSTRAINT teamO_films_pkey PRIMARY KEY (film_id);

INSERT INTO public.teamO_films (film_id, title, year) VALUES
    (1, 'The Matrix', 1999),
    (2, 'Inception', 2010),
    (3, 'The Shawshank Redemption', 1994),
    (4, 'Pulp Fiction', 1994),
    (5, 'The Dark Knight', 2008),
    (6, 'Forrest Gump', 1994),
    (7, 'Fight Club', 1999),
    (8, 'Interstellar', 2014),
    (9, 'The Lord of the Rings: The Fellowship of the Ring', 2001),
    (10, 'Gladiator', 2000);

SELECT pg_catalog.setval('public.teamO_films_film_id_seq', 10, true);