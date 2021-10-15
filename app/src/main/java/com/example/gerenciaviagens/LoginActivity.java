package com.example.gerenciaviagens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.database.dao.PessoaDAO;

public class LoginActivity extends AppCompatActivity {
    private Button btnCadastro;
    private EditText txtLoginEmail;
    private EditText txtLoginSenha;
    private Button btnLogin;
    private PessoaDAO dao;
    private CheckBox ckbManter;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        ckbManter = findViewById(R.id.ckbManter);

        if (sp.getBoolean("manter", false)) {

            Intent it = new Intent(LoginActivity.this, MainActivity.class);
            it.putExtra("pessoa", sp.getLong("usuario", 0));
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
        }
        dao = new PessoaDAO(LoginActivity.this);
        txtLoginEmail = findViewById(R.id.txtLoginEmail);
        txtLoginSenha = findViewById(R.id.txtLoginSenha);
        btnCadastro = findViewById(R.id.btnCadastrar);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pessoa p = dao.Select(txtLoginEmail.getText().toString(), txtLoginSenha.getText().toString());
                if (ckbManter.isChecked()){
                    editor = sp.edit();
                    editor.putBoolean("manter", true);
                    editor.putLong("usuario", p.getId());
                }
                if(p != null){
                    if (ckbManter.isChecked()){
                        editor = sp.edit();
                        editor.putBoolean("manter", true);
                        editor.putLong("usuario", p.getId());
                        editor.apply();
                    }
                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                    it.putExtra("pessoa", p.getId());
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(it);
                }else{
                    Toast.makeText(LoginActivity.this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}