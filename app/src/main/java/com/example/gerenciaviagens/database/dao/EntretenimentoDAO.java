package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Hospedagem;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.EntretenimentoModel;
import com.example.gerenciaviagens.database.model.HospedagemModel;

public class EntretenimentoDAO extends AbstractDAO{
    public EntretenimentoDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }

    public long Insert(Entretenimento et){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(EntretenimentoModel.COLUNA_VIAGEM, et.getViagem().getId());
            values.put(EntretenimentoModel.COLUNA_DESCRICAO, et.getDescricao());
            values.put(EntretenimentoModel.COLUNA_VALOR, et.getValor());



            linhasAfetadas = db.insert(EntretenimentoModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }
}
