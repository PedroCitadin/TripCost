package com.example.gerenciaviagens.database.model;

public class GasolinaModel {
    public static final String
            TABELA_NOME = "tb_gasolina";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_VIAGEM = "viagem",
            COLUNA_TOT_KM = "tot_km",
            COLUNA_MEDIA_LITRO = "media_litro",
            COLUNA_CUSTO_LITRO = "custo_litro",
            COLUNA_TOT_VEICULOS = "tot_veiculos",
            COLUNA_TOT_CUSTO = "tot_custo";



    public static final String CREATE_TABLE = "create table " + TABELA_NOME +"("
            +COLUNA_ID+" integer primary key autoincrement, "
            +COLUNA_VIAGEM+" integer not null, "
            +COLUNA_TOT_KM+" float not null, "
            +COLUNA_MEDIA_LITRO+" float not null, "
            +COLUNA_CUSTO_LITRO+" float not null, "
            +COLUNA_TOT_VEICULOS+" integer not null, "
            +COLUNA_TOT_CUSTO+" float,"
            +"foreign key("+COLUNA_VIAGEM+") references "+ViagemModel.TABELA_NOME+"("+ ViagemModel.COLUNA_ID+") on delete cascade on update cascade "
            +")";

    public static final String DROP_TABLE = "drop table if exists "+TABELA_NOME;
}
