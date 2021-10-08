package com.example.gerenciaviagens.database.model;

public class ViagemModel {
    public static final String
            TABELA_NOME = "tb_viagem";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_PESSOA = "pessoa",
            COLUNA_TITULO = "titulo",
            COLUNA_TOT_VIAJANTES = "tot_viajantes",
            COLUNA_DURACAO = "duracao",
            COLUNA_CUSTO_TOTAL = "custo_total",
            COLUNA_CUSTO_POR_PESSOA = "custo_por_pessoa",
            COLUNA_ENTRETENIMENTO = "entretenimento",
            COLUNA_REFEICOES = "refeicoes",
            COLUNA_GASOLINA = "gasolina",
            COLUNA_HOSPEDAGEM = "hospedagem",
            COLUNA_TARIFA_AEREA = "tarifa_aerea";



    public static final String CREATE_TABLE = "create table " + TABELA_NOME +"("
            +COLUNA_ID+" integer primary key autoincrement, "
            +COLUNA_PESSOA+" integer not null, "
            +COLUNA_TITULO+" text not null, "
            +COLUNA_TOT_VIAJANTES+" int not null, "
            +COLUNA_DURACAO+" integer, "
            +COLUNA_CUSTO_TOTAL+" float , "
            +COLUNA_CUSTO_POR_PESSOA+" float, "
            +COLUNA_ENTRETENIMENTO+" boolean not null, "
            +COLUNA_REFEICOES+" boolean not null, "
            +COLUNA_GASOLINA+" boolean not null, "
            +COLUNA_HOSPEDAGEM+" boolean not null, "
            +COLUNA_TARIFA_AEREA+" boolean not null, "

            +"foreign key("+COLUNA_PESSOA+") references "+PessoaModel.TABELA_NOME+"("+PessoaModel.COLUNA_ID+") on delete cascade on update cascade"
            +")";

    public static final String DROP_TABLE = "drop table if exists "+TABELA_NOME;
}
