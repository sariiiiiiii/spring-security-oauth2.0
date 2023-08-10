create table `user`
(
    id       varchar(20),
    password varchar(100),
    nickname varchar(100),
    state    char(1)
);

insert into `user`
values ('yoolee', '{noop}yoolee', 'corin', 'Y');
