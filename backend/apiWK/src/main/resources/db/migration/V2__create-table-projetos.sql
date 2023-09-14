 

 /*postgreSQl
create table projetos(
    id bigserial primary key ,
    nome varchar(100) not null,
    status varchar(1) not null,
    dataCriacao timestamp not null
 );*/

/*mysql*/
create table projetos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    status varchar(12) not null,
    data_criacao datetime not null,
    primary key(id)
);