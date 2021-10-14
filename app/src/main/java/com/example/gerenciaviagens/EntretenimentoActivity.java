package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.R;
import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Hospedagem;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Refeicoes;
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;

import java.util.ArrayList;
import java.util.List;

public class EntretenimentoActivity extends AppCompatActivity {
    private EditText txtDescricaoAtividade, txtValorAtividade;
    private Button btnAddAtividade, btnProximo5, btnCancelarNovaEntretenimento;
    private ListView lista_atracoes;
    private List<Entretenimento> listaE;
    ArrayAdapter<Entretenimento> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);

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
        Refeicoes rf = new Refeicoes();
        if(vi.isRefeicoes()){
            rf = getIntent().getExtras().getParcelable("refeicoes");
        }
        Hospedagem finalHospdagem = hs;
        Gasolina finalGasolina = gas;
        Tarifa_aerea finalTarifa = ta;
        Refeicoes finalRefeicoes = rf;




        listaE = new ArrayList<Entretenimento>();
        txtDescricaoAtividade = findViewById(R.id.txtDescricaoAtividade);
        txtValorAtividade = findViewById(R.id.txtValorAtividade);
        btnAddAtividade = findViewById(R.id.btnAddAtividade);
        btnProximo5 = findViewById(R.id.btnProximo5);
        btnCancelarNovaEntretenimento = findViewById(R.id.btnCancelarNovaEntretenimento);
        lista_atracoes = findViewById(R.id.lista_atracoes);
        btnAddAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtDescricaoAtividade.getText().toString().equalsIgnoreCase("")){
                    if (!txtValorAtividade.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtValorAtividade.getText().toString())>0){
                        Entretenimento ent = new Entretenimento();
                        ent.setDescricao(txtDescricaoAtividade.getText().toString());
                        ent.setValor(Float.parseFloat(txtValorAtividade.getText().toString()));
                        listaE.add(ent);
                        adapter = new ArrayAdapter<Entretenimento>(EntretenimentoActivity.this, android.R.layout.simple_list_item_1, listaE);
                        lista_atracoes.setAdapter(adapter);
                    }else{
                        txtValorAtividade.setError("Valor inválido!");
                    }
                }else{
                    txtDescricaoAtividade.setError("Título da atividade em branco!");
                }

            }
        });
        btnProximo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Viagem.insereViagem(vi, finalGasolina, finalTarifa, finalHospdagem, finalRefeicoes, listaE, EntretenimentoActivity.this);
                Intent it = new Intent(EntretenimentoActivity.this, MainActivity.class);
                it.putExtra("pessoa", vi.getPessoa().getId());
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });
        btnCancelarNovaEntretenimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EntretenimentoActivity.this, MainActivity.class);
                it.putExtra("pessoa", vi.getPessoa().getId());
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });

    }
}
