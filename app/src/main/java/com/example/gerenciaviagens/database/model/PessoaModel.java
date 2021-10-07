package com.example.gerenciaviagens.database.model;

public class PessoaModel {
    public static final String
    TABELA_NOME = "tb_pessoa";

    public static final String
    COLUNA_ID = "_id",
    COLUNA_NOME = "nome",
    COLUNA_CPF = "cpf",
    COLUNA_EMAIL = "email",
    COLUNA_TELEFONE = "telefone",
    COLUNA_SENHA = "senha";



    public static final String CREATE_TABLE = "create table " + TABELA_NOME +"("
            +COLUNA_ID+" integer primary key autoincrement, "
            +COLUNA_NOME+" text not null, "
            +COLUNA_CPF+" text not null, "
            +COLUNA_EMAIL+" text not null, "
            +COLUNA_TELEFONE+" text not null, "
            +COLUNA_SENHA+" text not null "
            +")";

    public static final String DROP_TABLE = "drop table if exists "+TABELA_NOME;
}
