--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

-- Started on 2016-01-28 23:47:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2170 (class 1262 OID 16414)
-- Name: file_system; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE file_system WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_Canada.1252' LC_CTYPE = 'English_Canada.1252';


ALTER DATABASE file_system OWNER TO postgres;

\connect file_system

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16415)
-- Name: data; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA data;


ALTER SCHEMA data OWNER TO postgres;

--
-- TOC entry 192 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 192
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = data, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16427)
-- Name: computers; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE computers (
    id bigint NOT NULL,
    mac_address text,
    bios_serial text,
    operating_system text,
    log_date date
);


ALTER TABLE computers OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16439)
-- Name: computers_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

CREATE SEQUENCE computers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE computers_id_seq OWNER TO postgres;

--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 183
-- Name: computers_id_seq; Type: SEQUENCE OWNED BY; Schema: data; Owner: postgres
--

ALTER SEQUENCE computers_id_seq OWNED BY computers.id;


--
-- TOC entry 191 (class 1259 OID 16502)
-- Name: downloads; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE downloads (
    id bigint NOT NULL,
    file_id integer,
    ua_id integer,
    ip_address text,
    download_date date,
    download_time time without time zone
);


ALTER TABLE downloads OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16500)
-- Name: downloads_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

CREATE SEQUENCE downloads_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE downloads_id_seq OWNER TO postgres;

--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 190
-- Name: downloads_id_seq; Type: SEQUENCE OWNED BY; Schema: data; Owner: postgres
--

ALTER SEQUENCE downloads_id_seq OWNED BY downloads.id;


--
-- TOC entry 185 (class 1259 OID 16454)
-- Name: files; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE files (
    id bigint NOT NULL,
    user_id integer,
    computer_id integer,
    file_name text,
    file_size bigint,
    file_status integer,
    file_access integer,
    upload_date date
);


ALTER TABLE files OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16452)
-- Name: files_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

CREATE SEQUENCE files_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE files_id_seq OWNER TO postgres;

--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 184
-- Name: files_id_seq; Type: SEQUENCE OWNED BY; Schema: data; Owner: postgres
--

ALTER SEQUENCE files_id_seq OWNED BY files.id;


--
-- TOC entry 187 (class 1259 OID 16475)
-- Name: reports; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE reports (
    id bigint NOT NULL,
    file_id integer,
    details text,
    report_date date,
    report_time time without time zone
);


ALTER TABLE reports OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16473)
-- Name: reports_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

CREATE SEQUENCE reports_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reports_id_seq OWNER TO postgres;

--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 186
-- Name: reports_id_seq; Type: SEQUENCE OWNED BY; Schema: data; Owner: postgres
--

ALTER SEQUENCE reports_id_seq OWNED BY reports.id;


--
-- TOC entry 189 (class 1259 OID 16491)
-- Name: useragents; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE useragents (
    id bigint NOT NULL,
    ua_raw text,
    ua_hash text
);


ALTER TABLE useragents OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16489)
-- Name: useragents_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

CREATE SEQUENCE useragents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE useragents_id_seq OWNER TO postgres;

--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 188
-- Name: useragents_id_seq; Type: SEQUENCE OWNED BY; Schema: data; Owner: postgres
--

ALTER SEQUENCE useragents_id_seq OWNED BY useragents.id;


--
-- TOC entry 181 (class 1259 OID 16418)
-- Name: users; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE users (
    id bigint NOT NULL,
    username text,
    password_salt text,
    password_hash text,
    email text,
    registration_date text
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN users.id; Type: COMMENT; Schema: data; Owner: postgres
--

COMMENT ON COLUMN users.id IS 'User ID';


--
-- TOC entry 180 (class 1259 OID 16416)
-- Name: users_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 180
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: data; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 2018 (class 2604 OID 16441)
-- Name: id; Type: DEFAULT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY computers ALTER COLUMN id SET DEFAULT nextval('computers_id_seq'::regclass);


--
-- TOC entry 2022 (class 2604 OID 16505)
-- Name: id; Type: DEFAULT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY downloads ALTER COLUMN id SET DEFAULT nextval('downloads_id_seq'::regclass);


--
-- TOC entry 2019 (class 2604 OID 16457)
-- Name: id; Type: DEFAULT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY files ALTER COLUMN id SET DEFAULT nextval('files_id_seq'::regclass);


--
-- TOC entry 2020 (class 2604 OID 16478)
-- Name: id; Type: DEFAULT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY reports ALTER COLUMN id SET DEFAULT nextval('reports_id_seq'::regclass);


--
-- TOC entry 2021 (class 2604 OID 16494)
-- Name: id; Type: DEFAULT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY useragents ALTER COLUMN id SET DEFAULT nextval('useragents_id_seq'::regclass);


--
-- TOC entry 2017 (class 2604 OID 16421)
-- Name: id; Type: DEFAULT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2156 (class 0 OID 16427)
-- Dependencies: 182
-- Data for Name: computers; Type: TABLE DATA; Schema: data; Owner: postgres
--

COPY computers (id, mac_address, bios_serial, operating_system, log_date) FROM stdin;
\.


--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 183
-- Name: computers_id_seq; Type: SEQUENCE SET; Schema: data; Owner: postgres
--

SELECT pg_catalog.setval('computers_id_seq', 1, false);


--
-- TOC entry 2165 (class 0 OID 16502)
-- Dependencies: 191
-- Data for Name: downloads; Type: TABLE DATA; Schema: data; Owner: postgres
--

COPY downloads (id, file_id, ua_id, ip_address, download_date, download_time) FROM stdin;
\.


--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 190
-- Name: downloads_id_seq; Type: SEQUENCE SET; Schema: data; Owner: postgres
--

SELECT pg_catalog.setval('downloads_id_seq', 1, false);


--
-- TOC entry 2159 (class 0 OID 16454)
-- Dependencies: 185
-- Data for Name: files; Type: TABLE DATA; Schema: data; Owner: postgres
--

COPY files (id, user_id, computer_id, file_name, file_size, file_status, file_access, upload_date) FROM stdin;
\.


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 184
-- Name: files_id_seq; Type: SEQUENCE SET; Schema: data; Owner: postgres
--

SELECT pg_catalog.setval('files_id_seq', 1, false);


--
-- TOC entry 2161 (class 0 OID 16475)
-- Dependencies: 187
-- Data for Name: reports; Type: TABLE DATA; Schema: data; Owner: postgres
--

COPY reports (id, file_id, details, report_date, report_time) FROM stdin;
\.


--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 186
-- Name: reports_id_seq; Type: SEQUENCE SET; Schema: data; Owner: postgres
--

SELECT pg_catalog.setval('reports_id_seq', 1, false);


--
-- TOC entry 2163 (class 0 OID 16491)
-- Dependencies: 189
-- Data for Name: useragents; Type: TABLE DATA; Schema: data; Owner: postgres
--

COPY useragents (id, ua_raw, ua_hash) FROM stdin;
\.


--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 188
-- Name: useragents_id_seq; Type: SEQUENCE SET; Schema: data; Owner: postgres
--

SELECT pg_catalog.setval('useragents_id_seq', 1, false);


--
-- TOC entry 2155 (class 0 OID 16418)
-- Dependencies: 181
-- Data for Name: users; Type: TABLE DATA; Schema: data; Owner: postgres
--

COPY users (id, username, password_salt, password_hash, email, registration_date) FROM stdin;
1	SkyLlama	123123	fgs33fv5	compaqxp@gmail.com	Jan. 21, 2015
\.


--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 180
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: data; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 1, false);


--
-- TOC entry 2026 (class 2606 OID 16449)
-- Name: computer_pk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY computers
    ADD CONSTRAINT computer_pk PRIMARY KEY (id);


--
-- TOC entry 2034 (class 2606 OID 16510)
-- Name: downloads_fk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY downloads
    ADD CONSTRAINT downloads_fk PRIMARY KEY (id);


--
-- TOC entry 2028 (class 2606 OID 16462)
-- Name: file_pk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY files
    ADD CONSTRAINT file_pk PRIMARY KEY (id);


--
-- TOC entry 2030 (class 2606 OID 16483)
-- Name: reports_pk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY reports
    ADD CONSTRAINT reports_pk PRIMARY KEY (id);


--
-- TOC entry 2032 (class 2606 OID 16499)
-- Name: ua_pk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY useragents
    ADD CONSTRAINT ua_pk PRIMARY KEY (id);


--
-- TOC entry 2024 (class 2606 OID 16451)
-- Name: user_pk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2036 (class 2606 OID 16468)
-- Name: computer_id_fk; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY files
    ADD CONSTRAINT computer_id_fk FOREIGN KEY (computer_id) REFERENCES computers(id);


--
-- TOC entry 2038 (class 2606 OID 16511)
-- Name: download_file_pk; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY downloads
    ADD CONSTRAINT download_file_pk FOREIGN KEY (id) REFERENCES files(id);


--
-- TOC entry 2039 (class 2606 OID 16516)
-- Name: downloads_ua_pk; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY downloads
    ADD CONSTRAINT downloads_ua_pk FOREIGN KEY (id) REFERENCES useragents(id);


--
-- TOC entry 2035 (class 2606 OID 16463)
-- Name: file_user_fk; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY files
    ADD CONSTRAINT file_user_fk FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2037 (class 2606 OID 16484)
-- Name: reports_file_pk; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY reports
    ADD CONSTRAINT reports_file_pk FOREIGN KEY (file_id) REFERENCES files(id);


-- Completed on 2016-01-28 23:47:14

--
-- PostgreSQL database dump complete
--

