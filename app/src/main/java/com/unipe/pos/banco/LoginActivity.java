package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private ImageView botaoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoCliente = (ImageView) findViewById(R.id.botaoClienteId);

        email = (EditText) findViewById(R.id.emailId);
        senha = (EditText) findViewById(R.id.senhaId);


        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailCliente = email.getText().toString();
                String senhaCliente = senha.getText().toString();
                RepositorioCliente r = new RepositorioCliente();

                if(emailCliente.isEmpty()){
                    alert("Preencha o campo nome.");
                }

                else if(senhaCliente.isEmpty()){
                    alert("Preencha o campo senha");
                }

                else if(!r.existeLogin(emailCliente,senhaCliente)){
                    alert("Login ou senha inválido ou não cadastrado.");
                }else{
                    startActivity(new Intent(LoginActivity.this,BancoActivity.class));
                }


            }
        });
    }

    public void alert(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
