--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: fnpageinationexample(integer, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.fnpageinationexample(pagenumber integer, pagesize integer) RETURNS TABLE(user_id bigint, first_name character varying, last_name character varying, email character varying, is_active boolean, is_verified boolean, date_created timestamp without time zone, total_pages integer)
    LANGUAGE plpgsql
    AS $$
DECLARE
    total_count BIGINT;
BEGIN
    -- 1∩╕ÅΓâú Count total rows
    SELECT COUNT(*) INTO total_count FROM users;

    -- 2∩╕ÅΓâú Return paginated rows with total_pages added to each
    RETURN QUERY
    SELECT 
        u.user_id,
        u.first_name,
        u.last_name,
        u.email,
        u.is_active,
        u.is_verified,
        u.date_created,
        CEIL(total_count::NUMERIC / pageSize)::INT AS total_pages
    FROM users u
    ORDER BY u.user_id
    LIMIT pageSize 
    OFFSET (pageNumber - 1) * pageSize;
END;
$$;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    category_id bigint NOT NULL,
    name character varying(255),
    user_id bigint,
    is_global boolean DEFAULT false,
    is_deleted boolean DEFAULT false,
    date_created timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(100),
    date_modified timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(100)
);


--
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.categories ALTER COLUMN category_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.categories_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: expenses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.expenses (
    expense_id bigint NOT NULL,
    title character varying(255),
    details character varying(500),
    amount numeric(10,2),
    category character varying(100),
    user_id bigint,
    is_group boolean DEFAULT false,
    is_deleted boolean DEFAULT false,
    date_created timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(100),
    date_modified timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(100)
);


--
-- Name: expenses_expense_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.expenses ALTER COLUMN expense_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.expenses_expense_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: mail_configuration; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.mail_configuration (
    configuration_id bigint NOT NULL,
    sender character varying,
    receiver character varying,
    username character varying,
    password character varying,
    mail_server character varying,
    is_active boolean,
    mail_template character varying
);


--
-- Name: mail_configuration_configuration_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.mail_configuration ALTER COLUMN configuration_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.mail_configuration_configuration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(100),
    password character varying(200),
    is_active boolean DEFAULT true,
    is_verified boolean DEFAULT false,
    verification_code character varying(6),
    profile_pic_url character varying(200),
    is_deleted boolean DEFAULT false,
    date_created timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(100),
    modified_by character varying(100),
    date_modified timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.categories (category_id, name, user_id, is_global, is_deleted, date_created, created_by, date_modified, modified_by) FROM stdin;
1	Breakfast	18	t	f	\N	\N	\N	\N
2	Breakfast	18	t	f	\N	\N	\N	\N
3	Lunch	18	t	f	\N	\N	\N	\N
4	Treat	18	t	f	\N	\N	\N	\N
5	Tea	18	t	f	\N	\N	\N	\N
\.


--
-- Data for Name: expenses; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.expenses (expense_id, title, details, amount, category, user_id, is_group, is_deleted, date_created, created_by, date_modified, modified_by) FROM stdin;
1	shyshtem myshtem	at dinner at hotel	500.00	Eating	2	f	f	2025-06-28 18:39:20.096	string	2025-11-15 22:54:16.647714	\N
2	karahi	at dinner at hotel	500.00	Eating	2	f	f	2025-06-28 18:39:20.096	string	2025-11-15 22:54:30.904545	\N
3	karahi	at dinner at hotel	300.00	Eating	2	f	f	2025-06-28 18:39:20.096	string	2025-11-15 22:54:35.765498	\N
4	karahi	at dinner at hotel	300.00	Eating	1	f	f	2025-06-28 18:39:20.096	string	2025-11-15 22:54:39.649528	\N
5	soda	at dinner at luck soda	150.00	Eating	1	t	t	2025-11-15 17:58:16.973	postman	2025-11-15 22:58:17.003137	\N
\.


--
-- Data for Name: mail_configuration; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.mail_configuration (configuration_id, sender, receiver, username, password, mail_server, is_active, mail_template) FROM stdin;
1	devbotsolutions.com	sheraz321089@gmail.com	sherazakhtarmalik@gmail.com	gmph ildd dfpr dnyo	Gmail	t	<!DOCTYPE html>\n<html lang="en">\n<head>\n    <meta charset="UTF-8">\n    <title>Verify Your Email</title>\n    <style>\n        body {\n            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n            background-color: #f4f4f7;\n            margin: 0;\n            padding: 0;\n        }\n        .email-container {\n            max-width: 600px;\n            margin: 40px auto;\n            background-color: #ffffff;\n            border-radius: 8px;\n            overflow: hidden;\n            box-shadow: 0 2px 8px rgba(0,0,0,0.1);\n        }\n        .email-header {\n            background-color: #4A90E2;\n            color: white;\n            text-align: center;\n            padding: 24px;\n        }\n        .email-body {\n            padding: 24px;\n            color: #333;\n        }\n        .email-body h2 {\n            margin-top: 0;\n        }\n        .verify-button {\n            display: inline-block;\n            margin-top: 24px;\n            background-color: #4A90E2;\n            color: white;\n            padding: 12px 24px;\n            text-decoration: none;\n            border-radius: 6px;\n            font-weight: bold;\n        }\n        .email-footer {\n            font-size: 12px;\n            text-align: center;\n            padding: 16px;\n            color: #888;\n        }\n        @media (max-width: 600px) {\n            .email-container {\n                margin: 20px;\n            }\n        }\n    </style>\n</head>\n<body>\n\n<div class="email-container">\n    <div class="email-header">\n        <h1>Verify Your Email</h1>\n    </div>\n    <div class="email-body">\n        <h2>Hello ≡ƒæï</h2>\n        <p>Thanks for signing up! Please verify your email address to activate your account.</p>\n        <p>Here is your Verification Code:</p>\n        <h3>{verificationCode}</h3>\n        <p>If you didnΓÇÖt create this account, you can safely ignore this email.</p>\n    </div>\n    <div class="email-footer">\n        &copy; 2025 DevbotSolutions. All rights reserved.\n    </div>\n</div>\n\n</body>\n</html>\n
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (user_id, first_name, last_name, email, password, is_active, is_verified, verification_code, profile_pic_url, is_deleted, date_created, created_by, modified_by, date_modified) FROM stdin;
1	Sheraz	Akhtar	sheraz321089@gmail.com	$2a$10$2VXpEoWPBJZ4/tD3sp61IuHwNl8FTC7EZ9la6DQvvF7IRg0hshLU6	\N	\N	758348	\N	f	\N	\N	\N	\N
\.


--
-- Name: categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.categories_category_id_seq', 5, true);


--
-- Name: expenses_expense_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.expenses_expense_id_seq', 5, true);


--
-- Name: mail_configuration_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.mail_configuration_configuration_id_seq', 1, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, true);


--
-- PostgreSQL database dump complete
--

