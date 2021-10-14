package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.DBOpenHelper;

import com.example.gerenciaviagens.database.model.GasolinaModel;
import com.example.gerenciaviagens.database.model.Tarifa_aereaModel;

public class Tarifa_aereaDAO extends AbstractDAO {
    public Tarifa_aereaDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }
    private final String[] colunas = {
            Tarifa_aereaModel.COLUNA_ID,
            Tarifa_aereaModel.COLUNA_VIAGEM,
            Tarifa_aereaModel.COLUNA_CUSTO_PESSOA,
            Tarifa_aereaModel.COLUNA_ALUGUEL_VEICULO,
            Tarifa_aereaModel.COLUNA_TOT_CUSTO
    };
    public long Insert(Tarifa_aerea td){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(Tarifa_aereaModel.COLUNA_VIAGEM, td.getViagem().getId());
            values.put(Tarifa_aereaModel.COLUNA_CUSTO_PESSOA, td.getCusto_pessoa());
            values.put(Tarifa_aereaModel.COLUNA_ALUGUEL_VEICULO, td.getAluguel_veiculo());
            values.put(Tarifa_aereaModel.COLUNA_TOT_CUSTO, td.getTot_custo());


            linhasAfetadas = db.insert(Tarifa_aereaModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }

    public Tarifa_aerea Select(final Viagem vi){
        Tarifa_aerea ta = null;
        try{
            Open();
            Cursor cursor = db.query(Tarifa_aereaModel.TABELA_NOME,
                    colunas,
                    Tarifa_aereaModel.COLUNA_VIAGEM +" = ?", new String[]{String.valueOf(vi.getId())},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                ta = CursorToStructure(cursor);
                break;
            }


        }finally {
            Close();
        }

        return ta;
    }
    public final Tarifa_aerea CursorToStructure(Cursor cursor){
        Tarifa_aerea p = new Tarifa_aerea();
        p.setId(cursor.getLong(0));
        p.setViagem(new Viagem(cursor.getLong(1)));
        p.setCusto_pessoa(cursor.getFloat(2));
        p.setAluguel_veiculo(cursor.getFloat(3));
        p.setTot_custo(cursor.getFloat(4));
        return p;

    }

}
