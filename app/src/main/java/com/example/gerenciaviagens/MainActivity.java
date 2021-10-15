package com.example.gerenciaviagens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.bean.Viagem;
import com.example.gerenciaviagens.database.dao.PessoaDAO;
import com.example.gerenciaviagens.database.dao.ViagemDAO;
import com.example.gerenciaviagens.dialog.ExcluirDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Pessoa pessoa;
    private PessoaDAO pDAO;
    private ViagemDAO vDAO;
    private ListView lista_viagens;
    private FloatingActionButton btnAddViagem;
    private ImageButton btnLogoff;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent it = getIntent();
        long id = it.getLongExtra("pessoa", 0);
        sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
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

                Viagem vi =(Viagem) lista_viagens.getItemAtPosition(position);

                Intent it2 = new Intent(MainActivity.this, ViagemActivity.class);
                it2.putExtra("pessoa", pessoa.getId());
                it2.putExtra("viagem", (Parcelable) vi);
                startActivity(it2);

            }
        });
        lista_viagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Viagem v =(Viagem) lista_viagens.getItemAtPosition(position);
                ExcluirDialog dialog = new ExcluirDialog(MainActivity.this);
                dialog.show(v, adapter, lista_viagens, pessoa);





                return true;
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
        btnLogoff = findViewById(R.id.btnLogoff);
        btnLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sp.edit();
                editor.putBoolean("manter", false);
                editor.apply();
                Intent it2 = new Intent(MainActivity.this, LoginActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it2);
            }
        });

    }
}