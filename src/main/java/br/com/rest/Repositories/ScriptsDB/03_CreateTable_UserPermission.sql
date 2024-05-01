create table user_permission(
	id_user bigint not null,
	id_permission bigint not null,
	primary key(id_user, id_permission)
);

ALTER TABLE public.user_permission
ADD CONSTRAINT user_permission_user_permission_fk FOREIGN KEY (id_user,id_permission)
REFERENCES public.user_permission(id_user,id_permission);

insert into user_permission(id_user, id_permission)
values (1,1), (2,1), (1,2);
