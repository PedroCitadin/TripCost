package com.example.gerenciaviagens.database.model;

public class EntretenimentoModel {
    public static final String
            TABELA_NOME = "tb_entretenimento";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_VIAGEM = "viagem",
            COLUNA_DESCRICAO = "descricao",
            COLUNA_VALOR = "valor";




    public static final String CREATE_TABLE = "create table " + TABELA_NOME +"("
            +COLUNA_ID+" integer primary key autoincrement, "
            +COLUNA_VIAGEM+" integer not null, "
            +COLUNA_DESCRICAO+" text not null, "
            +COLUNA_VALOR+" float not null, "

            +"foreign key("+COLUNA_VIAGEM+") references "+ViagemModel.TABELA_NOME+"("+ ViagemModel.COLUNA_ID+") on delete cascade on update cascade "
            +")";

    public static final String DROP_TABLE = "drop table if exists "+TABELA_NOME;
}
