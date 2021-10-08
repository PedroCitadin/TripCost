package com.example.gerenciaviagens.database.model;

import com.example.gerenciaviagens.bean.Viagem;

public class HospedagemModel {
    public static final String
            TABELA_NOME = "tb_hospedagem";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_VIAGEM = "viagem",
            COLUNA_CUSTO_MEDIO = "custo_medio",
            COLUNA_TOT_NOITES = "tot_noites",
            COLUNA_TOT_QUARTOS = "tot_quartos",
            COLUNA_TOT_CUSTO = "tot_custo";



    public static final String CREATE_TABLE = "create table " + TABELA_NOME +"("
            +COLUNA_ID+" integer primary key autoincrement, "
            +COLUNA_VIAGEM+" integer not null, "
            +COLUNA_CUSTO_MEDIO+" float not null, "
            +COLUNA_TOT_NOITES+" integer not null, "
            +COLUNA_TOT_QUARTOS+" integer not null, "
            +COLUNA_TOT_CUSTO+" float,"
            +"foreign key("+COLUNA_VIAGEM+") references "+ViagemModel.TABELA_NOME+"("+ ViagemModel.COLUNA_ID+")  on delete cascade on update cascade"
            +")";

    public static final String DROP_TABLE = "drop table if exists "+TABELA_NOME;
}
