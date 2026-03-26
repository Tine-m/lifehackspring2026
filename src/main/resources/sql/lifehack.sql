--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


CREATE SCHEMA public;
ALTER SCHEMA public OWNER TO pg_database_owner;
COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE public.users (
                              user_id integer NOT NULL,
                              username character varying(50) NOT NULL,
                              password character varying(50) NOT NULL,
                              role character varying(20) DEFAULT USER NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);

INSERT INTO public.users VALUES (1, 'jon', '1234', 'user');
INSERT INTO public.users VALUES (2, 'bingo', '1234', 'admin');
INSERT INTO public.users VALUES (3, 'ole', '1234', 'postgres');
INSERT INTO public.users VALUES (5, 'dennis', '1234', 'postgres');

SELECT pg_catalog.setval('public.users_user_id_seq', 5, true);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_username UNIQUE (username);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


