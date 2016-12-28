CREATE TABLE companies
(
  id bigserial NOT NULL,
  name character varying(255),
  description character varying(255),
  CONSTRAINT company_pkey PRIMARY KEY (id)
);