package com.example.gerenciaviagens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.PessoaDAO;
import com.example.gerenciaviagens.database.dao.ViagemDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Pessoa pessoa;
    private PessoaDAO pDAO;
    private ViagemDAO vDAO;
    private ListView lista_viagens;
    private FloatingActionButton btnAddViagem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent it = getIntent();
        long id = it.getLongExtra("pessoa", 0);
        vDAO = new ViagemDAO(MainActivity.this);
        pDAO = new PessoaDAO(MainActivity.this);
        pessoa = pDAO.Select(String.valueOf(id));
        List<Viagem> viagens = vDAO.Select(pessoa);
        lista_viagens = findViewById(R.id.lista_viagens);

        ArrayAdapter<Viagem> adapter = new ArrayAdapter<Viagem>(MainActivity.this, android.R.layout.simple_list_item_1, viagens);
        lista_viagens.setAdapter(adapter);
        lista_viagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Viagem v =(Viagem) lista_viagens.getItemAtPosition(position);

            }
        });
        btnAddViagem = findViewById(R.id.btnAddViagem);
        btnAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, NovaViagemActivity.class);
                it.putExtra("pessoa", id);
                startActivity(it);

            }
        });
    }
}