package com.example.gerenciaviagens.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.gerenciaviagens.ListaEntretenimentoAdapter;
import com.example.gerenciaviagens.R;
import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Viagem;

import java.util.List;

public class AddEntretenimentoDialog {
    private final Activity activity;
    private AlertDialog dialog;
    private Button btnSalvarAtividade, btnCancelarAddAtividade;
    private EditText txtEditValorAtividade, txtEditDescricaoAtividade;



    public AddEntretenimentoDialog(Activity activity) {
        this.activity = activity;

    }
    public List<Entretenimento> show(List<Entretenimento> liE, ListaEntretenimentoAdapter adapter, ListView lista, Viagem vi){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =  inflater.inflate(R.layout.dialog_add_entretenimento, null);
        builder.setView(view);
        builder.setCancelable(false);
        btnSalvarAtividade = view.findViewById(R.id.btnSalvarAtividade);
        txtEditValorAtividade = view.findViewById(R.id.txtEditValorAtividade);
        txtEditDescricaoAtividade = view.findViewById(R.id.txtEditDescricaoAtividade);
        btnCancelarAddAtividade = view.findViewById(R.id.btnCancelarAddAtividade);
        btnCancelarAddAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnSalvarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entretenimento e = new Entretenimento();
                e.setDescricao(txtEditDescricaoAtividade.getText().toString());
                e.setValor(Float.parseFloat(txtEditValorAtividade.getText().toString()));
                e.setViagem(vi);
                liE.add(e);
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
