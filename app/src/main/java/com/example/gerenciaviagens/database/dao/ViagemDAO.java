package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.PessoaModel;
import com.example.gerenciaviagens.database.model.ViagemModel;

import java.util.ArrayList;
import java.util.List;

public class ViagemDAO extends AbstractDAO{

    private final String[] colunas = {
            ViagemModel.COLUNA_ID,
            ViagemModel.COLUNA_PESSOA,
            ViagemModel.COLUNA_TITULO,
            ViagemModel.COLUNA_TOT_VIAJANTES,
            ViagemModel.COLUNA_DURACAO,
            ViagemModel.COLUNA_CUSTO_TOTAL,
            ViagemModel.COLUNA_CUSTO_POR_PESSOA,
            ViagemModel.COLUNA_ENTRETENIMENTO,
            ViagemModel.COLUNA_REFEICOES,
            ViagemModel.COLUNA_GASOLINA,
            ViagemModel.COLUNA_HOSPEDAGEM,
            ViagemModel.COLUNA_TARIFA_AEREA,

    };

    public ViagemDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }
    public long Insert(Viagem v){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_PESSOA, v.getPessoa().getId());
            values.put(ViagemModel.COLUNA_TITULO, v.getTitulo());
            values.put(ViagemModel.COLUNA_TOT_VIAJANTES, v.getTot_viajantes());
            values.put(ViagemModel.COLUNA_DURACAO, v.getDuracao());
            values.put(ViagemModel.COLUNA_CUSTO_TOTAL, v.getCusto_total());
            values.put(ViagemModel.COLUNA_CUSTO_POR_PESSOA, v.getCusto_pessoa());
            values.put(ViagemModel.COLUNA_ENTRETENIMENTO, v.isEntretenimento());
            values.put(ViagemModel.COLUNA_REFEICOES, v.isRefeicoes());
            values.put(ViagemModel.COLUNA_GASOLINA, v.isGasolina());
            values.put(ViagemModel.COLUNA_HOSPEDAGEM, v.isHospedagem());
            values.put(ViagemModel.COLUNA_TARIFA_AEREA, v.isTarifa_aerea());

            linhasAfetadas = db.insert(ViagemModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }
    public void Update(){

    }
    public void Delete(Viagem v){
        Open();
       db.delete(ViagemModel.TABELA_NOME, ViagemModel.COLUNA_ID + "=?", new String[]{String.valueOf(v.getId())});
       Close();
    }
    public List<Viagem> Select(Pessoa p){
        List<Viagem> v = new ArrayList<>();
        try{
            Open();
            Cursor cursor = db.query(ViagemModel.TABELA_NOME,
                    colunas,
                    ViagemModel.COLUNA_PESSOA +" = ? ", new String[]{String.valueOf(p.getId())},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                v.add( CursorToStructure(cursor));
                cursor.moveToNext();
            }


        }finally {
            Close();
        }

        return v;
    }
    public final Viagem CursorToStructure(Cursor cursor){
        Viagem v = new Viagem();
        v.setId(cursor.getLong(0));
        v.setPessoa(new Pessoa(cursor.getInt(1)));
        v.setTitulo(cursor.getString(2));
        v.setTot_viajantes(cursor.getInt(3));
        v.setDuracao(cursor.getInt(4));
        v.setCusto_total(cursor.getFloat(5));
        v.setCusto_pessoa(cursor.getFloat(6));
        v.setEntretenimento(Viagem.converterInt(cursor.getInt(7)));
        v.setRefeicoes(Viagem.converterInt(cursor.getInt(8)));
        v.setGasolina(Viagem.converterInt(cursor.getInt(9)));
        v.setHospedagem(Viagem.converterInt(cursor.getInt(10)));
        v.setTarifa_aerea(Viagem.converterInt(cursor.getInt(11)));
        return v;

    }
}
