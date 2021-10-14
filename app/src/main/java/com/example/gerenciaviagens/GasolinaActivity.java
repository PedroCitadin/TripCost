package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.GasolinaDAO;
import com.example.gerenciaviagens.database.dao.ViagemDAO;

public class GasolinaActivity extends AppCompatActivity {
    private EditText txtTotKm, txtKmLitro, txtCustoMedioLitro, txtTotVeiculos;
    private Button btnProximo1, btnCancelarNovaGasolina;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gasolina);
        Viagem vi = getIntent().getExtras().getParcelable("viagem");
        vi.setPessoa(new Pessoa(getIntent().getLongExtra("pessoa", 0)));
        txtTotKm = findViewById(R.id.txtTotKm);
        txtKmLitro= findViewById(R.id.txtKmLitro);
        txtCustoMedioLitro = findViewById(R.id.txtCustoMedioLitro);
        txtTotVeiculos = findViewById(R.id.txtTotVeiculos);
        btnProximo1 = findViewById(R.id.btnProximo1);
        btnCancelarNovaGasolina = findViewById(R.id.btnCancelarNovaGasolina);
        if(Viagem.verificaUltimo(1, vi)){
            btnProximo1.setText("Salvar viagem");
        }

        btnProximo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtTotKm.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtTotKm.getText().toString())>0){
                    if(!txtKmLitro.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtKmLitro.getText().toString())>0){
                        if(!txtCustoMedioLitro.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtCustoMedioLitro.getText().toString())>0){
                            if(!txtTotVeiculos.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtTotVeiculos.getText().toString())>0){
                                Gasolina gas = new Gasolina();
                                gas.setTot_km(Float.parseFloat(txtTotKm.getText().toString()));
                                gas.setMedia_litro(Float.parseFloat(txtKmLitro.getText().toString()));
                                gas.setCusto_litro(Float.parseFloat(txtCustoMedioLitro.getText().toString()));
                                gas.setTot_veiculo(Integer.parseInt(txtTotVeiculos.getText().toString()));
                                float total = ((gas.getTot_km()/gas.getMedia_litro())*gas.getCusto_litro())*gas.getTot_veiculo();
                                gas.setTot_custo(total);


                                if(Viagem.verificaUltimo(1, vi)){
                                    Viagem.insereViagem(vi, gas, null, null,null,null, GasolinaActivity.this);
                                    Intent it = new Intent(GasolinaActivity.this, MainActivity.class);
                                    it.putExtra("pessoa", vi.getPessoa().getId());
                                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(it);

                                }else{
                                    if(vi.isTarifa_aerea()){
                                        Intent it = new Intent(GasolinaActivity.this, Tarifa_AereaActivity.class);
                                        it.putExtra("viagem", (Parcelable) vi);
                                        it.putExtra("gasolina", (Parcelable) gas);
                                        it.putExtra("pessoa", vi.getPessoa().getId());
                                        startActivity(it);

                                    }else{
                                        if(vi.isHospedagem()){
                                            Intent it = new Intent(GasolinaActivity.this, HospedagemActivity.class);
                                            it.putExtra("viagem", (Parcelable) vi);
                                            it.putExtra("gasolina", (Parcelable) gas);
                                            it.putExtra("pessoa", vi.getPessoa().getId());
                                            startActivity(it);

                                        }else{
                                            if(vi.isRefeicoes()){
                                                Intent it = new Intent(GasolinaActivity.this, RefeicoesActivity.class);
                                                it.putExtra("viagem", (Parcelable) vi);
                                                it.putExtra("gasolina", (Parcelable) gas);
                                                it.putExtra("pessoa", vi.getPessoa().getId());
                                                startActivity(it);

                                            }else{
                                                if(vi.isEntretenimento()){
                                                    Intent it = new Intent(GasolinaActivity.this, EntretenimentoActivity.class);
                                                    it.putExtra("viagem", (Parcelable) vi);
                                                    it.putExtra("gasolina", (Parcelable) gas);
                                                    it.putExtra("pessoa", vi.getPessoa().getId());
                                                    startActivity(it);

                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                txtTotVeiculos.setError("Valor inválido!");
                            }
                        }else{
                            txtCustoMedioLitro.setError("Valor inválido!");
                        }
                    }else{
                        txtKmLitro.setError("Valor inválido!");
                    }
                }else{
                    txtTotKm.setError("Distancia da viagem inválida!");
                }
            }
        });
        btnCancelarNovaGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(GasolinaActivity.this, MainActivity.class);
                it.putExtra("pessoa", vi.getPessoa().getId());
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });

    }
}
