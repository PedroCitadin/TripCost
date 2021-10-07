package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.PessoaModel;

import java.util.List;

public class PessoaDAO extends AbstractDAO{
    private final String[] colunas = {
            PessoaModel.COLUNA_ID,
            PessoaModel.COLUNA_NOME,
            PessoaModel.COLUNA_CPF,
            PessoaModel.COLUNA_EMAIL,
            PessoaModel.COLUNA_TELEFONE,
            PessoaModel.COLUNA_SENHA


    };
    public PessoaDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }

    public long Insert(Pessoa p){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(PessoaModel.COLUNA_NOME, p.getNome());
            values.put(PessoaModel.COLUNA_CPF, p.getCpf());
            values.put(PessoaModel.COLUNA_EMAIL, p.getEmail());
            values.put(PessoaModel.COLUNA_TELEFONE, p.getTelefone());
            values.put(PessoaModel.COLUNA_SENHA, p.getSenha());

            linhasAfetadas = db.insert(PessoaModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;



    }
    public int Update(){
        return 0;
    }
    public int Delete(){
        return 0;
    }
    public Pessoa Select(final String email, final String senha){
        Pessoa p = null;
        try{
            Open();
            Cursor cursor = db.query(PessoaModel.TABELA_NOME,
                    colunas,
                    PessoaModel.COLUNA_EMAIL +" = ? and "+PessoaModel.COLUNA_SENHA+" = ?", new String[]{email, senha},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                p = CursorToStructure(cursor);
                break;
            }


        }finally {
            Close();
        }

        return p;
    }
    public List<Pessoa> Select(){
        return null;
    }
    public final Pessoa CursorToStructure(Cursor cursor){
        Pessoa p = new Pessoa();
        p.setId(cursor.getLong(0));
        p.setNome(cursor.getString(1));
        p.setCpf(cursor.getString(2));
        p.setEmail(cursor.getString(3));
        p.setTelefone(cursor.getString(4));
        p.setSenha(cursor.getString(5));
        return p;

    }

}
