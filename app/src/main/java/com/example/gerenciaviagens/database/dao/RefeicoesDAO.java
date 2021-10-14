package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Refeicoes;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.GasolinaModel;
import com.example.gerenciaviagens.database.model.HospedagemModel;
import com.example.gerenciaviagens.database.model.RefeicoesModel;

public class RefeicoesDAO extends AbstractDAO{
    public RefeicoesDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }
    private final String[] colunas = {
            RefeicoesModel.COLUNA_ID,
            RefeicoesModel.COLUNA_VIAGEM,
            RefeicoesModel.COLUNA_CUSTO_REFEICOES,
            RefeicoesModel.COLUNA_REFEICOES_DIA,
            RefeicoesModel.COLUNA_TOT_CUSTO,
    };
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
    public Refeicoes Select(final Viagem vi){
        Refeicoes g = null;
        try{
            Open();
            Cursor cursor = db.query(RefeicoesModel.TABELA_NOME,
                    colunas,
                    RefeicoesModel.COLUNA_VIAGEM +" = ?", new String[]{String.valueOf(vi.getId())},
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


    public final Refeicoes CursorToStructure(Cursor cursor){
        Refeicoes r = new Refeicoes();
        r.setId(cursor.getLong(0));
        r.setViagem(new Viagem(cursor.getLong(1)));
        r.setCusto_refeicoes(cursor.getFloat(2));
        r.setRefeicoes_dia(cursor.getInt(3));
        r.setTot_custo(cursor.getFloat(4));


        return r;
    }
}
