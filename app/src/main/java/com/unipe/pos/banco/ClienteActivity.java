package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText CPF;
    private EditText email;
    private EditText senha;
    private Button cadastraCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        nome = (EditText) findViewById(R.id.nomeId);
        CPF = (EditText) findViewById(R.id.cpfId);
        email = (EditText) findViewById(R.id.emailId);
        senha = (EditText) findViewById(R.id.senhaId);
        cadastraCliente = (Button) findViewById(R.id.botaoCadastrarClienteId);

        cadastraCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeCliente = nome.getText().toString();
                String CPFCliente = CPF.getText().toString();
                String emailCliente = email.getText().toString();
                String senhaCliente = senha.getText().toString();

                if(nomeCliente.isEmpty()){
                    alert("Preencha o campo nome.");
                }

                if (CPFCliente.isEmpty()){
                    alert("Preencha o campo CPF");
                }

                if(emailCliente.isEmpty()){
                    alert("Preencha o campo email.");
                }

                if(senhaCliente.isEmpty()){
                    alert("Preencha o campo senha.");
                }

                Cliente cliente = new Cliente(CPFCliente ,nomeCliente, emailCliente, senhaCliente);

                RepositorioCliente r = new RepositorioCliente();

                if(r.existeCliente(cliente.getCPF())){
                    alert("Cliente cadastrado.");
                }else{
                    r.cadastrar(cliente);
                }

                startActivity(new Intent(ClienteActivity.this,LoginActivity.class));
            }
        });

    }

    public void alert(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
