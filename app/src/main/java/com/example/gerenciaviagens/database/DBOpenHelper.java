package com.example.gerenciaviagens.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Hospedagem;
import com.example.gerenciaviagens.bean.Refeicoes;
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.model.EntretenimentoModel;
import com.example.gerenciaviagens.database.model.GasolinaModel;
import com.example.gerenciaviagens.database.model.HospedagemModel;
import com.example.gerenciaviagens.database.model.PessoaModel;
import com.example.gerenciaviagens.database.model.RefeicoesModel;
import com.example.gerenciaviagens.database.model.Tarifa_aereaModel;
import com.example.gerenciaviagens.database.model.ViagemModel;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NOME = "bancoviagens.db";
    public static final int DATABASE_VERSAO = 1;
    public DBOpenHelper(final Context contexto){
        super(contexto, DATABASE_NOME, null, DATABASE_VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(PessoaModel.CREATE_TABLE);
            db.execSQL(ViagemModel.CREATE_TABLE);
            db.execSQL(HospedagemModel.CREATE_TABLE);
            db.execSQL(EntretenimentoModel.CREATE_TABLE);
            db.execSQL(GasolinaModel.CREATE_TABLE);
            db.execSQL(RefeicoesModel.CREATE_TABLE);
            db.execSQL(Tarifa_aereaModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PessoaModel.DROP_TABLE);
        db.execSQL(PessoaModel.CREATE_TABLE);
        db.execSQL(ViagemModel.DROP_TABLE);
        db.execSQL(ViagemModel.CREATE_TABLE);
        db.execSQL(HospedagemModel.DROP_TABLE);
        db.execSQL(HospedagemModel.CREATE_TABLE);
        db.execSQL(EntretenimentoModel.DROP_TABLE);
        db.execSQL(EntretenimentoModel.CREATE_TABLE);
        db.execSQL(GasolinaModel.DROP_TABLE);
        db.execSQL(GasolinaModel.CREATE_TABLE);
        db.execSQL(RefeicoesModel.DROP_TABLE);
        db.execSQL(RefeicoesModel.CREATE_TABLE);
        db.execSQL(Tarifa_aereaModel.DROP_TABLE);
        db.execSQL(Tarifa_aereaModel.CREATE_TABLE);
    }
}
