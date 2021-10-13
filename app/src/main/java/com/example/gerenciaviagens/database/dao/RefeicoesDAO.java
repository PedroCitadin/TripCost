package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;


import com.example.gerenciaviagens.bean.Refeicoes;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.HospedagemModel;
import com.example.gerenciaviagens.database.model.RefeicoesModel;

public class RefeicoesDAO extends AbstractDAO{
    public RefeicoesDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }

    public long Insert(Refeicoes rf){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(RefeicoesModel.COLUNA_VIAGEM, rf.getViagem().getId());
            values.put(RefeicoesModel.COLUNA_CUSTO_REFEICOES, rf.getCusto_refeicoes());
            values.put(RefeicoesModel.COLUNA_REFEICOES_DIA, rf.getRefeicoes_dia());

            values.put(RefeicoesModel.COLUNA_TOT_CUSTO, rf.getTot_custo());


            linhasAfetadas = db.insert(RefeicoesModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }
}
