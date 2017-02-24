package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RealizarSaqueActivity extends AppCompatActivity {

    private EditText numConta;
    private EditText valor;
    private Button botaoRealizarSaque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_saque);

        numConta = (EditText) findViewById(R.id.numContaId);
        valor = (EditText) findViewById(R.id.valorId);
        botaoRealizarSaque = (Button) findViewById(R.id.botaoRealizarSaqueId);

        botaoRealizarSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numeroConta = numConta.getText().toString();
                String valorDoSaque = valor.getText().toString();


                if (numeroConta.isEmpty()) {
                    alert("Preencha o número da conta.");
                } else if (valorDoSaque.isEmpty()) {
                    alert("Preencha o valor a ser retirado.");

                } else {

                    RepositorioConta repositorioConta = RepositorioConta.getInstance();

                    boolean saqueRealizado = repositorioConta.retirar(Integer.parseInt(numeroConta), Double.parseDouble(valorDoSaque));

                    if (saqueRealizado) {
                        alert("Saque realizado.");
                    } else {
                        alert("Falha no saque.");
                    }

                    Intent intent = new Intent(RealizarSaqueActivity.this, BancoActivity.class);

                    Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                    intent.putExtra("cliente", cliente);

                    startActivity(intent);
                }
            }
        });
    }

    public void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
