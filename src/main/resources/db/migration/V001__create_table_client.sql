CREATE TABLE public.client
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL,
    fone character varying(15),
    email character varying(50),
    CONSTRAINT pk_client PRIMARY KEY (id)
);