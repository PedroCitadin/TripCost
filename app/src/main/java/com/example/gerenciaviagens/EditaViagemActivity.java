package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


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
import com.example.gerenciaviagens.database.dao.ViagemDAO;
import com.example.gerenciaviagens.dialog.AddEntretenimentoDialog;
import com.example.gerenciaviagens.dialog.ExcluirEntretenimentoDialog;

import java.util.List;

public class EditaViagemActivity extends AppCompatActivity {
    private EditText txtEditTitulo, txtEditNumViajantes,
            txtEditDuracaoViagem,
            txtEditDistanciaViagem,
            txtEditKmporLitro,
            txtEditCustoLitro,
            txtEditTotVeiculos,
            txtEditCustoPessoaTarifa,
            txtEditAluguelVeiculo,
            txtEditCustoPorNoite,
            txtEditTotNoites,
            txtEditTotQuartos,
            txtEditCustoRefeicao,
            txtEditRefeicoesDia;
    private LinearLayout llEditGasolina, llEditTarifaAerea, llEditHospedagem, llEditRefeicoes, llEditEntretenimento;
    private Button btnSalvarViagem, btnAddEditAtividade, btnCancelarEditar;
    private Viagem viagem;
    private Gasolina gasolina;
    private Tarifa_aerea tarifa_aerea;
    private Hospedagem hospedagem;
    private Refeicoes refeicoes;
    private ListView lista_gastos;
    List<Entretenimento> entretenimentos;
    ListaEntretenimentoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_viagem);
        btnCancelarEditar = findViewById(R.id.btnCancelarEditar);
        btnAddEditAtividade = findViewById(R.id.btnAddEditAtividade);
        lista_gastos = findViewById(R.id.lista_gastos);
        txtEditTitulo = findViewById(R.id.txtEditTitulo);
        txtEditNumViajantes = findViewById(R.id.txtEditNumViajantes);
        txtEditDuracaoViagem = findViewById(R.id.txtEditDuracaoViagem);
        txtEditDistanciaViagem = findViewById(R.id.txtEditDistanciaViagem);
        txtEditKmporLitro = findViewById(R.id.txtEditKmporLitro);
        txtEditCustoLitro = findViewById(R.id.txtEditCustoLitro);
        txtEditTotVeiculos = findViewById(R.id.txtEditTotVeiculos);
        txtEditCustoPessoaTarifa = findViewById(R.id.txtEditCustoPessoaTarifa);
        txtEditAluguelVeiculo = findViewById(R.id.txtEditAluguelVeiculo);
        txtEditCustoPorNoite = findViewById(R.id.txtEditCustoPorNoite);
        txtEditTotNoites = findViewById(R.id.txtEditTotNoites);
        txtEditTotQuartos = findViewById(R.id.txtEditTotQuartos);
        txtEditCustoRefeicao = findViewById(R.id.txtEditCustoRefeicao);
        txtEditRefeicoesDia = findViewById(R.id.txtEditRefeicoesDia);
        Viagem vi = getIntent().getExtras().getParcelable("viagem");
        vi.setPessoa(new Pessoa(getIntent().getLongExtra("pessoa", 0)));
        llEditGasolina = findViewById(R.id.llEditGasolina);
        llEditTarifaAerea = findViewById(R.id.llEditTarifaAerea);
        llEditHospedagem = findViewById(R.id.llEditHospedagem);
        llEditRefeicoes = findViewById(R.id.llEditRefeicoes);
        llEditEntretenimento = findViewById(R.id.llEditEntretenimento);
        btnSalvarViagem = findViewById(R.id.btnSalvarViagem);

        viagem = vi;

        txtEditTitulo.setText(vi.getTitulo());
        txtEditNumViajantes.setText(String.valueOf(vi.getTot_viajantes()));
        txtEditDuracaoViagem.setText(String.valueOf(vi.getDuracao()));

        if (vi.isGasolina()){
            GasolinaDAO gDAO = new GasolinaDAO(EditaViagemActivity.this);
            Gasolina gas = gDAO.Select(vi);
            gasolina = gas;
            txtEditDistanciaViagem.setText(String.valueOf(gas.getTot_km()));
            txtEditKmporLitro.setText(String.valueOf(gas.getMedia_litro()));
            txtEditCustoLitro.setText(String.valueOf(gas.getCusto_litro()));
            txtEditTotVeiculos.setText(String.valueOf(gas.getTot_veiculo()));
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llEditGasolina.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llEditGasolina.setLayoutParams(params);
            llEditGasolina.setVisibility(View.INVISIBLE);
        }
        if(vi.isTarifa_aerea()){
            Tarifa_aereaDAO taDAO = new Tarifa_aereaDAO(EditaViagemActivity.this);
            Tarifa_aerea ta = taDAO.Select(vi);
            tarifa_aerea = ta;
            txtEditCustoPessoaTarifa.setText(String.valueOf(ta.getCusto_pessoa()));
            txtEditAluguelVeiculo.setText(String.valueOf(ta.getAluguel_veiculo()));
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llEditTarifaAerea.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llEditTarifaAerea.setLayoutParams(params);
            llEditTarifaAerea.setVisibility(View.INVISIBLE);
        }
        if(vi.isHospedagem()){
            HospedagemDAO hDAO = new HospedagemDAO(EditaViagemActivity.this);
            Hospedagem hs = hDAO.Select(vi);
            hospedagem = hs;
            txtEditCustoPorNoite.setText(String.valueOf(hs.getCusto_medio()));
            txtEditTotNoites.setText(String.valueOf(hs.getTot_noites()));
            txtEditTotQuartos.setText(String.valueOf(hs.getTot_quartos()));
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llEditHospedagem.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llEditHospedagem.setLayoutParams(params);
            llEditHospedagem.setVisibility(View.INVISIBLE);
        }
        if(vi.isRefeicoes()){
            RefeicoesDAO rfDAO = new RefeicoesDAO(EditaViagemActivity.this);
            Refeicoes rf= rfDAO.Select(vi);
            refeicoes = rf;
            txtEditCustoRefeicao.setText(String.valueOf(rf.getCusto_refeicoes()));
            txtEditRefeicoesDia.setText(String.valueOf(rf.getRefeicoes_dia()));
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llEditRefeicoes.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llEditRefeicoes.setLayoutParams(params);
            llEditRefeicoes.setVisibility(View.INVISIBLE);
        }
        if (vi.isEntretenimento()){
            EntretenimentoDAO enDAO = new EntretenimentoDAO(EditaViagemActivity.this);
            List<Entretenimento> lista = enDAO.Select(vi);
            adapter = new ListaEntretenimentoAdapter(EditaViagemActivity.this, lista);
            lista_gastos.setAdapter(adapter);
            entretenimentos = lista;
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llEditEntretenimento.getLayoutParams();
            params.height = 0;
            params.width = 0;
            llEditEntretenimento.setLayoutParams(params);
            llEditEntretenimento.setVisibility(View.INVISIBLE);
        }
        btnAddEditAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lista_gastos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ExcluirEntretenimentoDialog dialog = new ExcluirEntretenimentoDialog(EditaViagemActivity.this);

                entretenimentos = dialog.show(entretenimentos, adapter, lista_gastos, (Entretenimento) lista_gastos.getItemAtPosition(position));

                return false;
            }
        });
        btnAddEditAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddEntretenimentoDialog dialog = new AddEntretenimentoDialog(EditaViagemActivity.this);

                entretenimentos = dialog.show(entretenimentos, adapter, lista_gastos, viagem);
            }
        });
        btnSalvarViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtEditTitulo.getText().toString().equalsIgnoreCase("")){
                    viagem.setTitulo(txtEditTitulo.getText().toString());
                }else{
                    txtEditTitulo.setError("Título em branco");
                }
                if (!txtEditNumViajantes.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtEditNumViajantes.getText().toString())>0){
                    viagem.setTot_viajantes(Integer.parseInt(txtEditNumViajantes.getText().toString()));
                }else{
                    txtEditTitulo.setError("Número de viajantes inválido");
                }
                if(!txtEditDuracaoViagem.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtEditDuracaoViagem.getText().toString())>0){
                    viagem.setDuracao(Integer.parseInt(txtEditDuracaoViagem.getText().toString()));
                }else{
                    txtEditDuracaoViagem.setError("Duração da viagem inválida!");
                }
                if (viagem.isGasolina()){
                    if (!txtEditDistanciaViagem.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtEditDistanciaViagem.getText().toString())>0){
                        if (!txtEditKmporLitro.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtEditKmporLitro.getText().toString())>0){
                            if (!txtEditCustoLitro.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtEditCustoLitro.getText().toString())>0){
                                if (!txtEditTotVeiculos.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtEditTotVeiculos.getText().toString())>0){
                                    gasolina.setViagem(viagem);
                                    gasolina.setTot_km(Float.parseFloat(txtEditDistanciaViagem.getText().toString()));
                                    gasolina.setMedia_litro(Float.parseFloat(txtEditKmporLitro.getText().toString()));
                                    gasolina.setCusto_litro(Float.parseFloat(txtEditCustoLitro.getText().toString()));
                                    gasolina.setTot_veiculo(Integer.parseInt(txtEditTotVeiculos.getText().toString()));
                                    gasolina.setTot_custo(Gasolina.calculaValorFinal(gasolina));
                                    GasolinaDAO gDAO = new GasolinaDAO(EditaViagemActivity.this);
                                    gDAO.Update(gasolina);
                                }else{
                                    txtEditTotVeiculos.setError("Valor inválido!");
                                }
                            }else{
                                txtEditCustoLitro.setError("Valor inválido!");
                            }
                        }else{
                            txtEditKmporLitro.setError("Valor inválido!");
                        }
                    }else{
                        txtEditDistanciaViagem.setError("Distância inválida!");
                    }
                }
                if (viagem.isTarifa_aerea()){
                    if (!txtEditCustoPessoaTarifa.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtEditCustoPessoaTarifa.getText().toString())>0){
                        tarifa_aerea.setViagem(viagem);
                        tarifa_aerea.setCusto_pessoa(Float.parseFloat(txtEditCustoPessoaTarifa.getText().toString()));
                        tarifa_aerea.setAluguel_veiculo(Float.parseFloat(txtEditAluguelVeiculo.getText().toString()));
                        tarifa_aerea.setTot_custo(Tarifa_aerea.calculaValorFinal(tarifa_aerea));
                        Tarifa_aereaDAO taDAO = new Tarifa_aereaDAO(EditaViagemActivity.this);
                        taDAO.Update(tarifa_aerea);

                    }else{
                        txtEditCustoPessoaTarifa.setError("Valor inválido!");
                    }
                }
                if (viagem.isHospedagem()){
                    if (!txtEditCustoPorNoite.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtEditCustoPorNoite.getText().toString())>0){
                        if (!txtEditTotNoites.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtEditTotNoites.getText().toString())>0){
                            if(!txtEditTotQuartos.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtEditTotQuartos.getText().toString())>0){
                                hospedagem.setViagem(viagem);
                                hospedagem.setCusto_medio(Float.parseFloat(txtEditCustoPorNoite.getText().toString()));
                                hospedagem.setTot_noites(Integer.parseInt(txtEditTotNoites.getText().toString()));
                                hospedagem.setTot_quartos(Integer.parseInt(txtEditTotQuartos.getText().toString()));
                                hospedagem.setTot_custo(Hospedagem.calculaValorFinal(hospedagem));
                                HospedagemDAO hDAO = new HospedagemDAO(EditaViagemActivity.this);
                                hDAO.Update(hospedagem);
                            }
                        }else{
                            txtEditTotNoites.setError("Valor inválido!");
                        }
                    }else{
                        txtEditCustoPorNoite.setError("Valor inválido!");
                    }
                }
                if (viagem.isRefeicoes()){
                    if (!txtEditCustoRefeicao.getText().toString().equalsIgnoreCase("")&&Float.parseFloat(txtEditCustoRefeicao.getText().toString())>0){
                        if (!txtEditRefeicoesDia.getText().toString().equalsIgnoreCase("")&&Integer.parseInt(txtEditRefeicoesDia.getText().toString())>0){
                            refeicoes.setViagem(viagem);
                            refeicoes.setCusto_refeicoes(Float.parseFloat(txtEditCustoRefeicao.getText().toString()));
                            refeicoes.setRefeicoes_dia(Integer.parseInt(txtEditRefeicoesDia.getText().toString()));
                            refeicoes.setTot_custo(Refeicoes.calculaValorFinal(refeicoes));
                            RefeicoesDAO rfDAO = new RefeicoesDAO(EditaViagemActivity.this);
                            rfDAO.Update(refeicoes);
                        }else{
                            txtEditRefeicoesDia.setError("Valor inválido!");
                        }
                    }else{
                        txtEditCustoRefeicao.setError("Valor inválido!");
                    }
                }
                if (viagem.isEntretenimento()){
                    EntretenimentoDAO eDAO = new EntretenimentoDAO(EditaViagemActivity.this);
                    eDAO.Delete(viagem);
                    for (Entretenimento e : entretenimentos){
                        eDAO.Insert(e);
                    }

                }
                viagem.setCusto_total(Viagem.calculaTotCusto(viagem, gasolina, tarifa_aerea, hospedagem, refeicoes, entretenimentos));
                viagem.setCusto_pessoa(viagem.getCusto_total()/viagem.getTot_viajantes());

                ViagemDAO vDAO = new ViagemDAO(EditaViagemActivity.this);
                vDAO.Update(viagem);

                Intent it2 = new Intent(EditaViagemActivity.this, ViagemActivity.class);
                it2.putExtra("pessoa", viagem.getPessoa().getId());
                it2.putExtra("viagem", (Parcelable) viagem);
                it2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(it2);
            }
        });
        btnCancelarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
