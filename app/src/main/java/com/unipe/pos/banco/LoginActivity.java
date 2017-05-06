package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.repository.RepositorioCliente;

public class LoginActivity extends AppCompatActivity{

    private EditText login;
    private EditText senha;
    private Button botaoLogar;
    private Button botaoCriarConta;
    private RepositorioCliente repositorioCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoLogar = (Button) findViewById(R.id.logarId);
        botaoCriarConta = (Button) findViewById(R.id.criarContaId);
        login = (EditText) findViewById(R.id.loginId);
        senha = (EditText) findViewById(R.id.valorId);
        repositorioCliente = new RepositorioCliente(LoginActivity.this);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginCliente = login.getText().toString();
                String senhaCliente = senha.getText().toString();


                if(loginCliente.isEmpty()){
                    alert("Preencha o campo login.");
                }

                else if (senhaCliente.isEmpty()) {
                    alert("Preencha o campo senha");
                } else {

                    Cliente cliente = repositorioCliente.existeLogin(loginCliente, senhaCliente);

                    if (cliente == null) {
                        alert("Login ou senha inválido.");
                    } else {

                        Intent intent = new Intent(LoginActivity.this, BancoActivity.class);

                        intent.putExtra("cliente",cliente);

                        startActivity(intent);

                    }
                }
            }
        });

        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,ClienteActivity.class));

            }
        });
    }

    public void alert(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
