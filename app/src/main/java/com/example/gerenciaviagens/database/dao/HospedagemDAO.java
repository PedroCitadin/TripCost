package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.gerenciaviagens.bean.Hospedagem;

import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.HospedagemModel;


public class HospedagemDAO extends AbstractDAO{
    public HospedagemDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }

    public long Insert(Hospedagem hs){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_VIAGEM, hs.getViagem().getId());
            values.put(HospedagemModel.COLUNA_CUSTO_MEDIO, hs.getCusto_medio());
            values.put(HospedagemModel.COLUNA_TOT_NOITES, hs.getTot_noites());
            values.put(HospedagemModel.COLUNA_TOT_QUARTOS, hs.getTot_noites());
            values.put(HospedagemModel.COLUNA_TOT_CUSTO, hs.getTot_custo());


            linhasAfetadas = db.insert(HospedagemModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }
}
