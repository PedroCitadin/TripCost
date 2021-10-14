package com.example.gerenciaviagens.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.gerenciaviagens.MainActivity;
import com.example.gerenciaviagens.R;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.ViagemDAO;

import java.util.List;

public class ExcluirDialog {
    private final Activity activity;
    private AlertDialog dialog;
    private Button btnConfirmarExclusao, btnCancelarExclusao;



    public ExcluirDialog(Activity activity) {
        this.activity = activity;

    }
    public void show(Viagem vi, ArrayAdapter<Viagem> adapter, ListView lista, Pessoa p){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =  inflater.inflate(R.layout.dialog_excluir, null);
        builder.setView(view);
        builder.setCancelable(false);
        btnCancelarExclusao = view.findViewById(R.id.btnCancelarExclusao);
        btnConfirmarExclusao = view.findViewById(R.id.btnConfirmarExclusao);
        btnCancelarExclusao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnConfirmarExclusao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViagemDAO vDAO = new ViagemDAO(activity);
                vDAO.Delete(vi);
                List<Viagem> viagens = vDAO.Select(p);
                ArrayAdapter<Viagem> adapter = new ArrayAdapter<Viagem>(activity, android.R.layout.simple_list_item_1, viagens);
                lista.setAdapter(adapter);
                dialog.cancel();

            }
        });
        dialog = builder.create();
        dialog.show();
        ;
    }


}
