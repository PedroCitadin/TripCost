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
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;

public class HospedagemActivity extends AppCompatActivity {
    private EditText txtCustoPorNoite, txtTotNoites, txtTotQuartos;
    private Button btnProximo3, btnCancelarNovaHospedagem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);
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
        Gasolina finalGasolina = gas;
        Tarifa_aerea finalTarifa = ta;
        txtCustoPorNoite = findViewById(R.id.txtCustoPorNoite);
        txtTotNoites = findViewById(R.id.txtTotNoites);
        txtTotQuartos = findViewById(R.id.txtTotQuartos);
        btnProximo3 = findViewById(R.id.btnProximo3);
        btnCancelarNovaHospedagem = findViewById(R.id.btnCancelarNovaHospedagem);
        if(Viagem.verificaUltimo(3, vi)){
            btnProximo3.setText("Salvar viagem");
        }
        btnProximo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtCustoPorNoite.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtCustoPorNoite.getText().toString())>0){
                    if(!txtTotNoites.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtTotNoites.getText().toString())>0){
                        if (!txtTotQuartos.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtTotQuartos.getText().toString())>0){
                            Hospedagem hs = new Hospedagem();
                            hs.setCusto_medio(Float.parseFloat(txtCustoPorNoite.getText().toString()));
                            hs.setTot_noites(Integer.parseInt(txtTotNoites.getText().toString()));
                            hs.setTot_quartos(Integer.parseInt(txtTotQuartos.getText().toString()));
                            hs.setTot_custo((hs.getCusto_medio()*hs.getTot_quartos())*hs.getTot_noites());
                            if (Viagem.verificaUltimo(3, vi)){
                                Viagem.insereViagem(vi, finalGasolina, finalTarifa, hs, null,null, HospedagemActivity.this);
                                Intent it = new Intent(HospedagemActivity.this, MainActivity.class);
                                it.putExtra("pessoa", vi.getPessoa().getId());
                                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(it);
                            }else{
                                if(vi.isRefeicoes()){
                                    Intent it = new Intent(HospedagemActivity.this, RefeicoesActivity.class);
                                    it.putExtra("viagem", (Parcelable) vi);
                                    it.putExtra("gasolina", (Parcelable) finalGasolina);
                                    it.putExtra("pessoa", vi.getPessoa().getId());
                                    it.putExtra("tarifa", (Parcelable) finalTarifa);
                                    it.putExtra("hospedagem", (Parcelable) hs);
                                    startActivity(it);
                                }else{
                                    Intent it = new Intent(HospedagemActivity.this, EntretenimentoActivity.class);
                                    it.putExtra("viagem", (Parcelable) vi);
                                    it.putExtra("gasolina", (Parcelable) finalGasolina);
                                    it.putExtra("pessoa", vi.getPessoa().getId());
                                    it.putExtra("tarifa", (Parcelable) finalTarifa);
                                    it.putExtra("hospedagem", (Parcelable) hs);
                                    startActivity(it);
                                }

                            }
                        }else{
                            txtTotQuartos.setError("Valor inválido!");
                        }
                    }else{
                        txtTotNoites.setError("Valor inválido!");
                    }
                }else{
                    txtCustoPorNoite.setError("Valor inválido!");
                }
            }
        });
        btnCancelarNovaHospedagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HospedagemActivity.this, MainActivity.class);
                it.putExtra("pessoa", vi.getPessoa().getId());
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });

    }
}
