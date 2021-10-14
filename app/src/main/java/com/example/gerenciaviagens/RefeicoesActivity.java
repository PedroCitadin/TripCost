package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.R;
import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Hospedagem;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Refeicoes;
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;

public class RefeicoesActivity extends AppCompatActivity {
    private EditText txtCustoRefeicao, txtTotRefeicoes;
    private Button btnProximo4, btnCancelarNovaRefeicao;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);
        txtCustoRefeicao = findViewById(R.id.txtCustoRefeicao);
        txtTotRefeicoes = findViewById(R.id.txtTotRefeicoes);
        btnProximo4 = findViewById(R.id.btnProximo4);
        btnCancelarNovaRefeicao = findViewById(R.id.btnCancelarNovaRefeicao);
        Viagem vi = getIntent().getExtras().getParcelable("viagem");
        vi.setPessoa(new Pessoa(getIntent().getLongExtra("pessoa", 0)));
        Gasolina gas = new Gasolina();
        if(vi.isGasolina()){
            gas = getIntent().getExtras().getParcelable("gasolina");
        }
        Tarifa_aerea ta = new Tarifa_aerea();
        if(vi.isTarifa_aerea()){
            ta = getIntent().getExtras().getParcelable("tarifa");
        }
        Hospedagem hs = new Hospedagem();
        if(vi.isHospedagem()){
            hs = getIntent().getExtras().getParcelable("hospedagem");
        }
        Hospedagem finalHospdagem = hs;
        Gasolina finalGasolina = gas;
        Tarifa_aerea finalTarifa = ta;
        if(Viagem.verificaUltimo(4, vi)){
            btnProximo4.setText("Salvar viagem");
        }

        btnProximo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!txtCustoRefeicao.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtCustoRefeicao.getText().toString())>0){
                   if(!txtTotRefeicoes.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtTotRefeicoes.getText().toString())>0){
                       Refeicoes rf = new Refeicoes();
                       rf.setCusto_refeicoes(Float.parseFloat(txtCustoRefeicao.getText().toString()));
                       rf.setRefeicoes_dia(Integer.parseInt(txtTotRefeicoes.getText().toString()));
                       rf.setTot_custo((rf.getCusto_refeicoes()*rf.getRefeicoes_dia())*vi.getDuracao());
                       if(Viagem.verificaUltimo(4, vi)){
                           Viagem.insereViagem(vi, finalGasolina, finalTarifa, finalHospdagem, rf,null, RefeicoesActivity.this);
                           Intent it = new Intent(RefeicoesActivity.this, MainActivity.class);
                           it.putExtra("pessoa", vi.getPessoa().getId());
                           it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(it);
                       }else{
                           Intent it = new Intent(RefeicoesActivity.this, EntretenimentoActivity.class);
                           it.putExtra("viagem", (Parcelable) vi);
                           it.putExtra("gasolina", (Parcelable) finalGasolina);
                           it.putExtra("pessoa", vi.getPessoa().getId());
                           it.putExtra("tarifa", (Parcelable) finalTarifa);
                           it.putExtra("hospedagem", (Parcelable) finalHospdagem);
                           it.putExtra("refeicoes", (Parcelable) rf);
                           startActivity(it);
                       }
                   }else{
                       txtTotRefeicoes.setError("Valor inválido!");
                   }
               }else{
                   txtCustoRefeicao.setError("Valor inválido!");
               }




            }
        });
        btnCancelarNovaRefeicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RefeicoesActivity.this, MainActivity.class);
                it.putExtra("pessoa", vi.getPessoa().getId());
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });

    }
}
