package com.example.gerenciaviagens.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.gerenciaviagens.ListaEntretenimentoAdapter;
import com.example.gerenciaviagens.R;
import com.example.gerenciaviagens.ViagemActivity;
import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.EntretenimentoDAO;
import com.example.gerenciaviagens.database.dao.ViagemDAO;

import java.util.List;

public class ExcluirEntretenimentoDialog {
    private final Activity activity;
    private AlertDialog dialog;
    private Button btnEditConfirmarExclusao, btnEditCancelarExclusao;



    public ExcluirEntretenimentoDialog(Activity activity) {
        this.activity = activity;

    }
    public List<Entretenimento> show(List<Entretenimento> liE, ListaEntretenimentoAdapter adapter, ListView lista, Entretenimento en){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =  inflater.inflate(R.layout.dialog_excluir_entretenimento, null);
        builder.setView(view);
        builder.setCancelable(false);
        btnEditCancelarExclusao = view.findViewById(R.id.btnEditCancelarExclusao);
        btnEditConfirmarExclusao = view.findViewById(R.id.btnEditConfirmarExclusao);
        btnEditCancelarExclusao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnEditConfirmarExclusao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liE.remove(en);
                ListaEntretenimentoAdapter adapter = new ListaEntretenimentoAdapter(activity, liE);
                lista.setAdapter(adapter);
                dialog.cancel();

            }
        });
        dialog = builder.create();
        dialog.show();

        return liE;
    }


}
