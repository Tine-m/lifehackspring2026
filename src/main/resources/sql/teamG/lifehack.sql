--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.4

-- Started on 2026-03-26 11:24:38 UTC

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 24664)
-- Name: hack; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hack (
    hack_id integer NOT NULL,
    videolink character varying,
    kategori character varying,
    title character varying,
    description text
);


ALTER TABLE public.hack OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24663)
-- Name: hack_hack_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hack_hack_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.hack_hack_id_seq OWNER TO postgres;

--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 215
-- Name: hack_hack_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.hack_hack_id_seq OWNED BY public.hack.hack_id;


--
-- TOC entry 219 (class 1259 OID 24698)
-- Name: saved; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.saved (
    user_id integer NOT NULL,
    hack_id integer NOT NULL
);


ALTER TABLE public.saved OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24690)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying,
    password character varying,
    role character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24689)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 3212 (class 2604 OID 24667)
-- Name: hack hack_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hack ALTER COLUMN hack_id SET DEFAULT nextval('public.hack_hack_id_seq'::regclass);


--
-- TOC entry 3213 (class 2604 OID 24693)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 3366 (class 0 OID 24664)
-- Dependencies: 216
-- Data for Name: hack; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (1, 'https://youtu.be/W_zXoIJwBj4', 'school', 'Custom Notebook', 'Customize your notebook!');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (2, 'https://youtu.be/Q3UWBvu_7lY?t=16', 'food', 'Nutella o/', 'Take a nearly empty nutella glass and shake it up until it magically fills with white chocolate, and dip an ice cream in it. :0');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (11, 'https://youtu.be/XEWZIP98VW8?t=16', 'school', 'Øjen Briller', 'Snyd din lærer til at tro du er vågen');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (12, 'https://youtu.be/XEWZIP98VW8?t=53', 'school', 'Husk månederne', 'Sådan husker du hvor mange dage der er i hver måned');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (3, 'https://youtu.be/a4sVBmG-yxU?t=64', 'food', 'Fiskeskind', 'Nem og hurtig måde at fjerne skind fra fisk');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (4, 'https://youtu.be/VWUJQqljHzI?t=60', 'food', 'Kartoffelskrælning', 'En nem og hurtig måde at skrælle kartofler');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (5, 'https://youtu.be/2J2YERCzcT0?t=263', 'food', 'Dåseåbner', 'Ny måde at åbne en dåse uden en åbner');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (6, 'https://youtu.be/VWUJQqljHzI?t=243', 'food', 'Citronpresser', 'Brug en tang hvis du ikke har en citronpresser');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (7, 'https://youtu.be/dQw4w9WgXcQ', 'prank', 'Best prank', 'En prank du kan lave på dine venner');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (8, 'https://youtu.be/xY46caoQVC0?t=40', 'everyday', 'Klamme AirPods', 'Sådan rengør du dine AirPods');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (9, 'https://youtu.be/inCfwKUl5g8?t=42', 'food', 'Pil solsikkekerner', 'Nem måde at pille solsikkekerner med en tøjklemme');
INSERT INTO public.hack (hack_id, videolink, kategori, title, description) VALUES (10, 'https://www.youtube.com/shorts/kXoW45Ie62c?feature=share', 'everyday', 'Insektfælde', 'En simpel insektfælde med kun to ting');


--
-- TOC entry 3369 (class 0 OID 24698)
-- Dependencies: 219
-- Data for Name: saved; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3368 (class 0 OID 24690)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (user_id, username, password, role) VALUES (1, 'lars', '1234', NULL);
INSERT INTO public.users (user_id, username, password, role) VALUES (2, 'peter', '1111', NULL);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 215
-- Name: hack_hack_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hack_hack_id_seq', 2, true);


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);


--
-- TOC entry 3215 (class 2606 OID 24671)
-- Name: hack hack_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hack
    ADD CONSTRAINT hack_pkey PRIMARY KEY (hack_id);


--
-- TOC entry 3219 (class 2606 OID 24702)
-- Name: saved saved_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saved
    ADD CONSTRAINT saved_pkey PRIMARY KEY (user_id, hack_id);


--
-- TOC entry 3217 (class 2606 OID 24697)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3220 (class 2606 OID 24708)
-- Name: saved fk_hack; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saved
    ADD CONSTRAINT fk_hack FOREIGN KEY (hack_id) REFERENCES public.hack(hack_id) ON DELETE CASCADE;


--
-- TOC entry 3221 (class 2606 OID 24703)
-- Name: saved fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saved
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


-- Completed on 2026-03-26 11:24:39 UTC

--
-- PostgreSQL database dump complete
--

