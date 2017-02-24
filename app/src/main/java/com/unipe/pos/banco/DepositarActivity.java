package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DepositarActivity extends AppCompatActivity {

    private EditText numConta;
    private EditText valor;
    private Button botaoDepositar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositar);

        numConta = (EditText) findViewById(R.id.numContaId);
        valor = (EditText) findViewById(R.id.valorId);
        botaoDepositar = (Button) findViewById(R.id.botaoDepositarId);

        botaoDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numeroConta = numConta.getText().toString();
                String valorDoDeposito = valor.getText().toString();


                if (numeroConta.isEmpty()) {
                    alert("Preencha o número da conta.");
                } else if (valorDoDeposito.isEmpty()) {
                    alert("Preencha o valor a ser depositado.");

                }else{

                    RepositorioConta repositorioConta = RepositorioConta.getInstance();

                    boolean depositado = repositorioConta.depositar(Integer.parseInt(numeroConta), Double.parseDouble(valorDoDeposito));

                    if (depositado) {
                        alert("Deposito realizado.");
                    } else {
                        alert("Falha no deposito.");
                    }

                    Intent intent = new Intent(DepositarActivity.this, BancoActivity.class);

                    Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                    intent.putExtra("cliente",cliente);

                    startActivity(intent);
                }
            }
        });
    }

    public void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
