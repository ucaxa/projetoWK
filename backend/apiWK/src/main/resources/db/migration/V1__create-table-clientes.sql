/* comandos no postges
 
create table clientes(
    id bigserial primary key ,
    nome varchar(100) not null,
    email varchar(100) not null,
    telefone varchar(11) not null,
    dataCadastro timestamp not null
 );*/

/*comando no mysql*/
create table clientes(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20) not null,
    primary key(id)
);