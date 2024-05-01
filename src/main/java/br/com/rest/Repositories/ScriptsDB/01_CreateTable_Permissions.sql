CREATE TABLE public.permissions (
	id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
	description varchar(255) DEFAULT NULL NULL
);

insert into permissions(description)
values ('ADMIN'),('MANAGER'), ('COMMON_USER');