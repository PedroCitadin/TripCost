package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Hospedagem;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.EntretenimentoModel;
import com.example.gerenciaviagens.database.model.HospedagemModel;
import com.example.gerenciaviagens.database.model.PessoaModel;

import java.util.ArrayList;
import java.util.List;

public class EntretenimentoDAO extends AbstractDAO{
    public EntretenimentoDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }
    private final String[] colunas = {
            EntretenimentoModel.COLUNA_ID,
            EntretenimentoModel.COLUNA_VIAGEM,
            EntretenimentoModel.COLUNA_DESCRICAO,
            EntretenimentoModel.COLUNA_VALOR
    };
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
    public List<Entretenimento> Select(Viagem vi){
        List<Entretenimento> lista = new ArrayList<Entretenimento>();
        try{
            Open();
            Cursor cursor = db.query(EntretenimentoModel.TABELA_NOME,
                    colunas,
                    EntretenimentoModel.COLUNA_VIAGEM +" = ? ", new String[]{String.valueOf(vi.getId())},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                lista.add(CursorToStructure(cursor));
                cursor.moveToNext();
            }


        }finally {
            Close();
        }

        return lista;
    }



    public final Entretenimento CursorToStructure(Cursor cursor){
        Entretenimento e = new Entretenimento();
        e.setId(cursor.getLong(0));
        e.setViagem(new Viagem(cursor.getLong(1)));
        e.setDescricao(cursor.getString(2));
        e.setValor(cursor.getFloat(3));


        return e;
    }
}
