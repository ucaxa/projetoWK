

create table atividades (
    id bigint not null auto_increment,
    projeto_id bigint not null,
    cliente_id bigint not null,
    descricao varchar(100) not null,
    data_cadastro datetime not null,
    data_inicio datetime null,
    data_conclusao datetime null,
    primary key(id),
    constraint fk_atividades_cliente_id foreign key(cliente_id) references clientes(id),
    constraint fk_atividades_projeto_id foreign key(projeto_id) references projetos(id)
);

