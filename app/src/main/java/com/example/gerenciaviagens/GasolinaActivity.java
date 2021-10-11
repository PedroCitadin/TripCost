package com.example.gerenciaviagens;

import android.os.Bundle;
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

        btnProximo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gasolina gas = new Gasolina();
                gas.setTot_km(Float.parseFloat(txtTotKm.getText().toString()));
                gas.setMedia_litro(Float.parseFloat(txtKmLitro.getText().toString()));
                gas.setCusto_litro(Float.parseFloat(txtCustoMedioLitro.getText().toString()));
                gas.setTot_veiculo(Integer.parseInt(txtTotVeiculos.getText().toString()));
                float total = ((gas.getTot_km()/gas.getMedia_litro())*gas.getCusto_litro())*gas.getTot_veiculo();
                gas.setTot_custo(total);


                if(Viagem.verificaUltimo(1, vi)){
                    ViagemDAO vDAO = new ViagemDAO(GasolinaActivity.this);
                    GasolinaDAO gDAO = new GasolinaDAO(GasolinaActivity.this);
                    long idviagem = vDAO.Insert(vi);
                    vi.setId(idviagem);
                    gas.setViagem(vi);
                    gDAO.Insert(gas);
                    finish();

                }else{
                    System.out.println("aqui");
                }
            }
        });


    }
}
