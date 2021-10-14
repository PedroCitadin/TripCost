package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.Tarifa_aereaDAO;
import com.example.gerenciaviagens.database.dao.ViagemDAO;

public class Tarifa_AereaActivity extends AppCompatActivity {
    private EditText txtCustoPorPassagem, txtAluguelVeiculo;
    private Switch swtNovoAluguel;
    private Button btnProximo2, btnCancelarNovaTarifa;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);
        Viagem vi = getIntent().getExtras().getParcelable("viagem");
        vi.setPessoa(new Pessoa(getIntent().getLongExtra("pessoa", 0)));
        Gasolina gas = new Gasolina();
        if(vi.isGasolina()){
            gas = getIntent().getExtras().getParcelable("gasolina");

        }
        txtCustoPorPassagem = findViewById(R.id.txtCustoPorPassagem);
        txtAluguelVeiculo = findViewById(R.id.txtAluguelVeiculo);
        swtNovoAluguel = findViewById(R.id.swtNovoAluguel);
        swtNovoAluguel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    txtAluguelVeiculo.setEnabled(true);
                }else{
                    txtAluguelVeiculo.setEnabled(false);
                }
            }
        });
        btnProximo2 = findViewById(R.id.btnProximo2);
        btnCancelarNovaTarifa = findViewById(R.id.btnCancelarNovaTarifa);
        Gasolina finalGas = gas;
        if(Viagem.verificaUltimo(2, vi)){
            btnProximo2.setText("Salvar viagem");
        }
        btnProximo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtCustoPorPassagem.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtCustoPorPassagem.getText().toString())>0){
                    if(swtNovoAluguel.isChecked()){
                        if(!txtAluguelVeiculo.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtAluguelVeiculo.getText().toString())>0){
                            Tarifa_aerea ta = new Tarifa_aerea();
                            ta.setCusto_pessoa(Float.parseFloat(txtCustoPorPassagem.getText().toString()));
                            if (swtNovoAluguel.isChecked()){
                                ta.setAluguel_veiculo(Float.parseFloat(txtAluguelVeiculo.getText().toString()));
                            }else{
                                ta.setAluguel_veiculo(0);
                            }
                            ta.setTot_custo((vi.getTot_viajantes()*ta.getCusto_pessoa())+ta.getAluguel_veiculo());
                            if (Viagem.verificaUltimo(2, vi)){
                                Viagem.insereViagem(vi, finalGas, ta, null, null, null,Tarifa_AereaActivity.this);
                                Intent it = new Intent(Tarifa_AereaActivity.this, MainActivity.class);
                                it.putExtra("pessoa", vi.getPessoa().getId());
                                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(it);
                            }else {
                                if (vi.isHospedagem()) {
                                    Intent it = new Intent(Tarifa_AereaActivity.this, HospedagemActivity.class);
                                    it.putExtra("viagem", (Parcelable) vi);
                                    it.putExtra("gasolina", (Parcelable) finalGas);
                                    it.putExtra("pessoa", vi.getPessoa().getId());
                                    it.putExtra("tarifa", (Parcelable) ta);
                                    startActivity(it);

                                } else {
                                    if (vi.isRefeicoes()) {
                                        Intent it = new Intent(Tarifa_AereaActivity.this, RefeicoesActivity.class);
                                        it.putExtra("viagem", (Parcelable) vi);
                                        it.putExtra("gasolina", (Parcelable) finalGas);
                                        it.putExtra("pessoa", vi.getPessoa().getId());
                                        it.putExtra("tarifa", (Parcelable) ta);
                                        startActivity(it);


                                    } else {
                                        if (vi.isEntretenimento()) {
                                            Intent it = new Intent(Tarifa_AereaActivity.this, EntretenimentoActivity.class);
                                            it.putExtra("viagem", (Parcelable) vi);
                                            it.putExtra("gasolina", (Parcelable) finalGas);
                                            it.putExtra("pessoa", vi.getPessoa().getId());
                                            it.putExtra("tarifa", (Parcelable) ta);
                                            startActivity(it);


                                        }
                                    }
                                }
                            }
                        }else{
                            txtAluguelVeiculo.setError("Valor inválido!");
                        }
                    }else{
                        Tarifa_aerea ta = new Tarifa_aerea();
                        ta.setCusto_pessoa(Float.parseFloat(txtCustoPorPassagem.getText().toString()));
                        if (swtNovoAluguel.isChecked()){
                            ta.setAluguel_veiculo(Float.parseFloat(txtAluguelVeiculo.getText().toString()));
                        }else{
                            ta.setAluguel_veiculo(0);
                        }
                        ta.setTot_custo((vi.getTot_viajantes()*ta.getCusto_pessoa())+ta.getAluguel_veiculo());
                        if (Viagem.verificaUltimo(2, vi)){
                            Viagem.insereViagem(vi, finalGas, ta, null, null, null,Tarifa_AereaActivity.this);
                            Intent it = new Intent(Tarifa_AereaActivity.this, MainActivity.class);
                            it.putExtra("pessoa", vi.getPessoa().getId());
                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                        }else {
                            if (vi.isHospedagem()) {
                                Intent it = new Intent(Tarifa_AereaActivity.this, HospedagemActivity.class);
                                it.putExtra("viagem", (Parcelable) vi);
                                it.putExtra("gasolina", (Parcelable) finalGas);
                                it.putExtra("pessoa", vi.getPessoa().getId());
                                it.putExtra("tarifa", (Parcelable) ta);
                                startActivity(it);

                            } else {
                                if (vi.isRefeicoes()) {
                                    Intent it = new Intent(Tarifa_AereaActivity.this, RefeicoesActivity.class);
                                    it.putExtra("viagem", (Parcelable) vi);
                                    it.putExtra("gasolina", (Parcelable) finalGas);
                                    it.putExtra("pessoa", vi.getPessoa().getId());
                                    it.putExtra("tarifa", (Parcelable) ta);
                                    startActivity(it);


                                } else {
                                    if (vi.isEntretenimento()) {
                                        Intent it = new Intent(Tarifa_AereaActivity.this, EntretenimentoActivity.class);
                                        it.putExtra("viagem", (Parcelable) vi);
                                        it.putExtra("gasolina", (Parcelable) finalGas);
                                        it.putExtra("pessoa", vi.getPessoa().getId());
                                        it.putExtra("tarifa", (Parcelable) ta);
                                        startActivity(it);


                                    }
                                }
                            }
                        }
                    }
                }else{
                    txtCustoPorPassagem.setError("Valor inválido!");
                }

            }
        });
        btnCancelarNovaTarifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tarifa_AereaActivity.this, MainActivity.class);
                it.putExtra("pessoa", vi.getPessoa().getId());
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });


    }
}
