package com.example.gerenciaviagens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

                if(p != null){
                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                    it.putExtra("pessoa", p.getId());
                    startActivity(it);
                }else{
                    Toast.makeText(LoginActivity.this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}