package com.example.gerenciaviagens.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gerenciaviagens.database.model.PessoaModel;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NOME = "bancoviagens.db";
    public static final int DATABASE_VERSAO = 1;
    public DBOpenHelper(final Context contexto){
        super(contexto, DATABASE_NOME, null, DATABASE_VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(PessoaModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PessoaModel.DROP_TABLE);
        db.execSQL(PessoaModel.CREATE_TABLE);
    }
}
