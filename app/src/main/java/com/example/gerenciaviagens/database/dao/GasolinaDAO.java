package com.example.gerenciaviagens.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.DBOpenHelper;
import com.example.gerenciaviagens.database.model.GasolinaModel;
import com.example.gerenciaviagens.database.model.PessoaModel;
import com.example.gerenciaviagens.database.model.ViagemModel;

public class GasolinaDAO extends AbstractDAO {
    public GasolinaDAO(final Context contexto){
        db_helper = new DBOpenHelper(contexto);
    }
    private final String[] colunas = {
            GasolinaModel.COLUNA_ID,
            GasolinaModel.COLUNA_VIAGEM,
            GasolinaModel.COLUNA_TOT_KM,
            GasolinaModel.COLUNA_MEDIA_LITRO,
            GasolinaModel.COLUNA_CUSTO_LITRO,
            GasolinaModel.COLUNA_TOT_VEICULOS,
            GasolinaModel.COLUNA_TOT_CUSTO


    };

    public long Insert(Gasolina gas){
        long linhasAfetadas;
        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(GasolinaModel.COLUNA_VIAGEM, gas.getViagem().getId());
            values.put(GasolinaModel.COLUNA_TOT_KM, gas.getTot_km());
            values.put(GasolinaModel.COLUNA_MEDIA_LITRO, gas.getMedia_litro());
            values.put(GasolinaModel.COLUNA_CUSTO_LITRO, gas.getCusto_litro());
            values.put(GasolinaModel.COLUNA_TOT_VEICULOS, gas.getTot_veiculo());
            values.put(GasolinaModel.COLUNA_TOT_CUSTO, gas.getTot_custo());

            linhasAfetadas = db.insert(GasolinaModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }
        return linhasAfetadas;
    }
    public Gasolina Select(final Viagem vi){
        Gasolina g = null;
        try{
            Open();
            Cursor cursor = db.query(GasolinaModel.TABELA_NOME,
                    colunas,
                    GasolinaModel.COLUNA_VIAGEM +" = ?", new String[]{String.valueOf(vi.getId())},
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

    public final Gasolina CursorToStructure(Cursor cursor){
        Gasolina p = new Gasolina();
        p.setId(cursor.getLong(0));
        p.setViagem(new Viagem(cursor.getLong(1)));
        p.setTot_km(cursor.getFloat(2));
        p.setMedia_litro(cursor.getFloat(3));
        p.setCusto_litro(cursor.getFloat(4));
        p.setTot_veiculo(cursor.getInt(5));
        p.setTot_custo(cursor.getFloat(6));
        return p;

    }
}
