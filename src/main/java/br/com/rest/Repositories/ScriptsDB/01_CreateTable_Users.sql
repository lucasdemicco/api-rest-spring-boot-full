CREATE TABLE public.users (
	id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
	user_name varchar(255) DEFAULT NULL NULL,
	full_name varchar(255) DEFAULT NULL NULL,
	password varchar(255) DEFAULT NULL NULL,
	account_non_expired bit(1) DEFAULT NULL NULL,
	account_non_locked bit(1) DEFAULT NULL NULL,
	credentials_non_expired bit(1) DEFAULT NULL NULL,
	enabled bit(1) DEFAULT NULL NULL
);

insert into users(user_name, full_name, "password", account_non_expired, account_non_locked, credentials_non_expired, enabled)
values ('lucas', 'Lucas De micco', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719b