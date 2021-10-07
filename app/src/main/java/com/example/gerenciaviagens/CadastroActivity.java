package com.example.gerenciaviagens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciaviagens.bean.Pessoa;
import com.example.gerenciaviagens.database.dao.PessoaDAO;
import com.example.gerenciaviagens.util.MaskEditUtil;

public class CadastroActivity extends AppCompatActivity {
    private Button btnCancelar;
    private Button btnFinalizaCadastro;
    private EditText txtNome, txtCpf, txtEmail, txtTelefone, txtSenha, txtConfirmaSenha;
    private PessoaDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);
        dao = new PessoaDAO(CadastroActivity.this);
        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);
        txtCpf.addTextChangedListener(MaskEditUtil.mask(txtCpf, MaskEditUtil.FORMAT_CPF));
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtTelefone.addTextChangedListener(MaskEditUtil.mask(txtTelefone, MaskEditUtil.FORMAT_FONE));
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmaSenha = findViewById(R.id.txtConfirmaSenha);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnFinalizaCadastro = findViewById(R.id.btnFinalizarCadastro);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnFinalizaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Pessoa.verificaSenha(txtSenha.getText().toString(), txtConfirmaSenha.getText().toString())){
                    Pessoa p = new Pessoa(txtNome.getText().toString(), txtCpf.getText().toString(), txtEmail.getText().toString(), txtTelefone.getText().toString(), txtSenha.getText().toString());
                    if(dao.Insert(p)!=-1){
                        Toast.makeText(CadastroActivity.this, "Usuário salvo", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }else{
                    txtConfirmaSenha.setError("Senhas não correspondem!");
                }
            }
        });
    }
}