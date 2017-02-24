package com.unipe.pos.banco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class ClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText login;
    private EditText CPF;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private Button cadastraConta;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        nome = (EditText) findViewById(R.id.nomeId);
        login = (EditText) findViewById(R.id.loginId);
        CPF = (EditText) findViewById(R.id.cpfId);
        email = (EditText) findViewById(R.id.emailId);
        senha = (EditText) findViewById(R.id.senhaId);
        confirmarSenha = (EditText) findViewById(R.id.confirmarSenhaId);
        cadastraConta = (Button) findViewById(R.id.cadastrarContaId);

        cadastraConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeCliente = nome.getText().toString();
                String loginCliente = login.getText().toString();
                String CPFCliente = CPF.getText().toString();
                String emailCliente = email.getText().toString();
                String senhaCliente = senha.getText().toString();
                String confirmarSenhaCliente = confirmarSenha.getText().toString();

                if (CPFCliente.isEmpty()) {
                    alert("Preencha o campo CPF");
                }

                else if (nomeCliente.isEmpty()) {
                    alert("Preencha o campo nome.");

                }

                else if (loginCliente.isEmpty()) {
                    alert("Preencha o campo login.");

                }

                else if (emailCliente.isEmpty()) {
                    alert("Preencha o campo email.");
                }

                else if (senhaCliente.isEmpty()) {
                    alert("Preencha o campo senha.");
                }

                else if (confirmarSenhaCliente.isEmpty()) {
                    alert("Preencha o campo confirmarSenha.");
                }

                else if (!confirmarSenhaCliente.equals(senhaCliente)) {
                    alert("O campo Confirmar Senha não confere com a senha digitada acima.");
                }else{
                    Cliente cliente = new Cliente(CPFCliente, nomeCliente,loginCliente, emailCliente, senhaCliente);

                    RepositorioCliente repositorioCliente = RepositorioCliente.getInstance();
                    RepositorioConta repositorioConta = RepositorioConta.getInstance();

                    if (repositorioCliente.existeCliente(cliente.getCPF())) {
                        alert("Cliente cadastrado.");
                    } else {
                        repositorioCliente.cadastrar(cliente);

                        double valorPadraoDepositado = 10.0;

                        Conta conta = new Conta(valorPadraoDepositado, repositorioCliente.getCliente(cliente.getCPF()));

                        repositorioConta.inserirConta(conta);

                        Intent intent = new Intent(ClienteActivity.this, LoginActivity.class);

                        startActivity(intent);

                        alert("Conta cadastrada.");
                    }
                }
            }
        });

    }

    public void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
