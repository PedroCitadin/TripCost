package com.example.gerenciaviagens.database.model;

public class RefeicoesModel {
    public static final String
            TABELA_NOME = "tb_refeicoes";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_VIAGEM = "viagem",
            COLUNA_CUSTO_REFEICOES = "custo_refeicoes",
            COLUNA_REFEICOES_DIA = "refeicoes_dia",
            COLUNA_TOT_CUSTO = "tot_custo";



    public static final String CREATE_TABLE = "create table " + TABELA_NOME +"("
            +COLUNA_ID+" integer primary key autoincrement, "
            +COLUNA_VIAGEM+" integer not null, "
            +COLUNA_CUSTO_REFEICOES+" float not null, "
            +COLUNA_REFEICOES_DIA+" integer not null, "
            +COLUNA_TOT_CUSTO+" float,"
            +"foreign key("+COLUNA_VIAGEM+") references "+ViagemModel.TABELA_NOME+"("+ ViagemModel.COLUNA_ID+") on delete cascade on update cascade "
            +")";

    public static final String DROP_TABLE = "drop table if exists "+TABELA_NOME;
}
