create table t_clubsportiv
(
    id   integer auto_increment,
    nume varchar(50),
    nrjucatori integer,
    vechime varchar(50),
    trofee integer,
    primary key (id)
);

create table t_jucatori
(
    id             integer auto_increment,
    nume           varchar(50),
    varsta         integer,
    vechime        integer,
    id_club        integer,
    primary key (id),
    foreign key (id_club) references t_clubsportiv (id)
);