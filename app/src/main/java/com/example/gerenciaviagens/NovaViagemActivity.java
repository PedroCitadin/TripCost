package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.ViagemDAO;

import java.io.Serializable;

public class NovaViagemActivity extends AppCompatActivity {
    private EditText txtTituloViagem, txtNumViajantes, txtDuracao;
    private Switch swtGasolina, swtTarifaAerea, swtHospedagem, swtRefeicoes, swtEntretenimento;
    private Button btnProximo, btnCancelarNovaViagem;
    private Pessoa p;
    private ViagemDAO vDAO;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);
        vDAO = new ViagemDAO(NovaViagemActivity.this);
        Intent it = getIntent();
        long idPessoa = it.getLongExtra("pessoa", 0);
        p = new Pessoa(idPessoa);
        txtTituloViagem = findViewById(R.id.txtTituloViagem);
        txtNumViajantes = findViewById(R.id.txtNumViajantes);
        txtDuracao = findViewById(R.id.txtDuracao);
        swtGasolina = findViewById(R.id.swtGasolina);
        swtTarifaAerea = findViewById(R.id.swtTarifaAerea);
        swtHospedagem = findViewById(R.id.swtHospedagem);
        swtRefeicoes = findViewById(R.id.swtRefeicoes);
        swtEntretenimento = findViewById(R.id.swtEntretenimento);
        btnProximo = findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Viagem vi = new Viagem();

                vi.setPessoa(p);
                vi.setTitulo(txtTituloViagem.getText().toString());
                vi.setTot_viajantes(Integer.parseInt(txtNumViajantes.getText().toString()));
                vi.setDuracao(Integer.parseInt(txtDuracao.getText().toString()));
                vi.setGasolina(swtGasolina.isChecked());
                vi.setTarifa_aerea(swtTarifaAerea.isChecked());
                vi.setHospedagem(swtHospedagem.isChecked());
                vi.setRefeicoes(swtRefeicoes.isChecked());
                vi.setEntretenimento(swtEntretenimento.isChecked());



                Intent it2 = null;
                if (swtGasolina.isChecked()){
                    it2 = new Intent(NovaViagemActivity.this, GasolinaActivity.class);
                }else{
                  if(swtTarifaAerea.isChecked()){
                      it2 = new Intent(NovaViagemActivity.this, Tarifa_AereaActivity.class);
                  }else{
                      if(swtHospedagem.isChecked()){
                          it2 = new Intent(NovaViagemActivity.this, HospedagemActivity.class);
                      }else{
                          if(swtRefeicoes.isChecked()){
                              it2 = new Intent(NovaViagemActivity.this, RefeicoesActivity.class);
                          }else{
                              if (swtEntretenimento.isChecked()){
                                  it2 = new Intent(NovaViagemActivity.this, EntretenimentoActivity.class);
                              }else{
                                  vDAO.Insert(vi);
                                  finish();
                              }
                          }
                      }
                  }
                }
                it2.putExtra("pessoa", idPessoa);
                it2.putExtra("viagem", (Parcelable) vi);
                startActivity(it2);
            }
        });
        btnCancelarNovaViagem = findViewById(R.id.btnCancelarNovaViagem);
        btnCancelarNovaViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
