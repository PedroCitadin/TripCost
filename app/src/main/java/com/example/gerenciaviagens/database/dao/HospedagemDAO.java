package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Hospedagem;

import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.GasolinaModel;
import com.example.gerenciaviagens.database.model.HospedagemModel;


public class HospedagemDAO extends AbstractDAO{
    public HospedagemDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }
    private final String[] colunas = {
            HospedagemModel.COLUNA_ID,
            HospedagemModel.COLUNA_VIAGEM,
            HospedagemModel.COLUNA_CUSTO_MEDIO,
            HospedagemModel.COLUNA_TOT_NOITES,
            HospedagemModel.COLUNA_TOT_QUARTOS,
            HospedagemModel.COLUNA_TOT_CUSTO,
    };
    public long Insert(Hospedagem hs){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_VIAGEM, hs.getViagem().getId());
            values.put(HospedagemModel.COLUNA_CUSTO_MEDIO, hs.getCusto_medio());
            values.put(HospedagemModel.COLUNA_TOT_NOITES, hs.getTot_noites());
            values.put(HospedagemModel.COLUNA_TOT_QUARTOS, hs.getTot_quartos());
            values.put(HospedagemModel.COLUNA_TOT_CUSTO, hs.getTot_custo());


            linhasAfetadas = db.insert(HospedagemModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }
    public Hospedagem Select(final Viagem vi){
        Hospedagem g = null;
        try{
            Open();
            Cursor cursor = db.query(HospedagemModel.TABELA_NOME,
                    colunas,
                    HospedagemModel.COLUNA_VIAGEM +" = ?", new String[]{String.valueOf(vi.getId())},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                g = CursorToStructure(cursor);
                break;
            }


        }finally {
            Close();
        }

        return g;
    }



    public final Hospedagem CursorToStructure(Cursor cursor){
        Hospedagem h = new Hospedagem();
        h.setId(cursor.getLong(0));
        h.setViagem(new Viagem(cursor.getLong(1)));
        h.setCusto_medio(cursor.getFloat(2));
        h.setTot_noites(cursor.getInt(3));
        h.setTot_quartos(cursor.getInt(4));
        h.setTot_custo(cursor.getFloat(5));


        return h;
    }
}
