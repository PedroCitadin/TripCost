package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;


import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.database.DBOpenHelper;

import com.example.gerenciaviagens.database.model.Tarifa_aereaModel;

public class Tarifa_aereaDAO extends AbstractDAO {
    public Tarifa_aereaDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }

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
}
