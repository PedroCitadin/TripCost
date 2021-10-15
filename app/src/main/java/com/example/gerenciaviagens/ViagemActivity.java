package com.example.gerenciaviagens;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.bean.Entretenimento;
import com.example.gerenciaviagens.bean.Gasolina;
import com.example.gerenciaviagens.bean.Hospedagem;
import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Refeicoes;
import com.example.gerenciaviagens.bean.Tarifa_aerea;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.EntretenimentoDAO;
import com.example.gerenciaviagens.database.dao.GasolinaDAO;
import com.example.gerenciaviagens.database.dao.HospedagemDAO;
import com.example.gerenciaviagens.database.dao.RefeicoesDAO;
import com.example.gerenciaviagens.database.dao.Tarifa_aereaDAO;

import java.util.List;

public class ViagemActivity extends AppCompatActivity {
    private TextView lblTitulo, lblNumViajantes,
            lblDuracaoViagem,
            lblDistanciaViagem,
            lblKmporLitro,
            lblCustoLitro,
            lblTotVeiculos,
            lblCustoPessoaTarifa,
            lblAluguelVeiculo,
            lblCustoPorNoite,
            lblTotNoites,
            lblTotQuartos,
            lblCustoRefeicao,
            lblRefeicoesDia,
            lblCustoPessoaTotal,
            lblCustoTotal;
    private LinearLayout llGasolina, llTarifaAerea, llHospedagem, llRefeicoes, llEntretenimento;
    private ListView lista_ent;
    private Button btnEditarViagem;
    private ImageButton btnVoltar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnEditarViagem = findViewById(R.id.btnEditarViagem);
        lblTitulo = findViewById(R.id.lblTitulo);
        lblNumViajantes = findViewById(R.id.lblNumViajantes);
        lblDuracaoViagem = findViewById(R.id.lblDuracaoViagem);
        lblDistanciaViagem = findViewById(R.id.lblDistanciaViagem);
        lblKmporLitro = findViewById(R.id.lblKmporLitro);
        lblCustoLitro = findViewById(R.id.lblCustoLitro);
        lblTotVeiculos = findViewById(R.id.lblTotVeiculos);
        lblCustoPessoaTarifa = findViewById(R.id.lblCustoPessoaTarifa);
        lblAluguelVeiculo = findViewById(R.id.lblAluguelVeiculo);
        lblCustoPorNoite = findViewById(R.id.lblCustoPorNoite);
        lblTotNoites = findViewById(R.id.lblTotNoites);
        lblTotQuartos = findViewById(R.id.lblTotQuartos);
        lblCustoRefeicao = findViewById(R.id.lblCustoRefeicao);
        lblRefeicoesDia = findViewById(R.id.lblRefeicoesDia);
        lblCustoPessoaTotal = findViewById(R.id.lblCustoPessoaTotal);
        lblCustoTotal = findViewById(R.id.lblCustoTotal);
        llGasolina = findViewById(R.id.llGasolina);
        llTarifaAerea = findViewById(R.id.llTarifaAerea);
        llHospedagem = findViewById(R.id.llHospedagem);
        llRefeicoes = findViewById(R.id.llRefeicoes);
        llEntretenimento = findViewById(R.id.llEntretenimento);
        lista_ent = findViewById(R.id.lista_entretenimento);
        Viagem vi = getIntent().getExtras().getParcelable("viagem");
        vi.setPessoa(new Pessoa(getIntent().getLongExtra("pessoa", 0)));
        
        lblTitulo.setText(vi.getTitulo());
        lblNumViajantes.setText(vi.getTot_viajantes()+" pessoa(s)");
        lblDuracaoViagem.setText(vi.getDuracao()+" dia(s)");
        if (vi.isGasolina()){
            GasolinaDAO gDAO = new GasolinaDAO(ViagemActivity.this);
            Gasolina gas = gDAO.Select(vi);
            lblDistanciaViagem.setText(gas.getTot_km()+" km");
            lblKmporLitro.setText(gas.getMedia_litro()+" km/l");
            lblCustoLitro.setText("R$ "+gas.getCusto_litro());
            lblTotVeiculos.setText(gas.getTot_veiculo()+" veículo(s)");
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llGasolina.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llGasolina.setLayoutParams(params);
            llGasolina.setVisibility(View.INVISIBLE);
        }
        if(vi.isTarifa_aerea()){
            Tarifa_aereaDAO taDAO = new Tarifa_aereaDAO(ViagemActivity.this);
            Tarifa_aerea ta = taDAO.Select(vi);
            lblCustoPessoaTarifa.setText("R$ "+ta.getCusto_pessoa());
            lblAluguelVeiculo.setText("R$ "+ta.getAluguel_veiculo());
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llTarifaAerea.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llTarifaAerea.setLayoutParams(params);
            llTarifaAerea.setVisibility(View.INVISIBLE);
        }
        if(vi.isHospedagem()){
            HospedagemDAO hDAO = new HospedagemDAO(ViagemActivity.this);
            Hospedagem hs = hDAO.Select(vi);
            lblCustoPorNoite.setText("R$ "+hs.getCusto_medio());
            lblTotNoites.setText(hs.getTot_noites()+" noite(s)");
            lblTotQuartos.setText(hs.getTot_quartos()+" quarto(s)");
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llHospedagem.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llHospedagem.setLayoutParams(params);
            llHospedagem.setVisibility(View.INVISIBLE);
        }
        if(vi.isRefeicoes()){
            RefeicoesDAO rfDAO = new RefeicoesDAO(ViagemActivity.this);
            Refeicoes rf= rfDAO.Select(vi);
            lblCustoRefeicao.setText("R$ "+rf.getCusto_refeicoes());
            lblRefeicoesDia.setText(rf.getRefeicoes_dia()+" refeições");
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llRefeicoes.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llRefeicoes.setLayoutParams(params);
            llRefeicoes.setVisibility(View.INVISIBLE);
        }
        if (vi.isEntretenimento()){
            EntretenimentoDAO enDAO = new EntretenimentoDAO(ViagemActivity.this);
            List<Entretenimento> lista = enDAO.Select(vi);
            ListaEntretenimentoAdapter adapter = new ListaEntretenimentoAdapter(ViagemActivity.this, lista);
            lista_ent.setAdapter(adapter);

        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llEntretenimento.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llEntretenimento.setLayoutParams(params);
            llEntretenimento.setVisibility(View.INVISIBLE);
        }
        lblCustoPessoaTotal.setText("R$ "+vi.getCusto_pessoa());
        lblCustoTotal.setText("R$ "+vi.getCusto_total());
        btnEditarViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ViagemActivity.this, EditaViagemActivity.class);
                in.putExtra("pessoa", vi.getPessoa().getId());
                in.putExtra("viagem", (Parcelable) vi);

                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it2 = new Intent(ViagemActivity.this, MainActivity.class);
                it2.putExtra("pessoa", vi.getPessoa().getId());
                it2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(it2);
            }
        });

    }
}
