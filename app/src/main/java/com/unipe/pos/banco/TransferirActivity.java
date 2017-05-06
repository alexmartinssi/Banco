package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.repository.RepositorioConta;

public class TransferirActivity extends AppCompatActivity {

    private EditText numContaOrigem;
    private EditText numContaDestino;
    private EditText valor;
    private Button botaoTranferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);

        numContaOrigem = (EditText) findViewById(R.id.contaOrigemId);
        numContaDestino = (EditText) findViewById(R.id.contaDestinoId);
        valor = (EditText) findViewById(R.id.valorId);
        botaoTranferir = (Button) findViewById(R.id.botaoTransferirId);

        botaoTranferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numeroContaOrigem = numContaOrigem.getText().toString();
                String numeroContaDestino = numContaDestino.getText().toString();
                String valorDoDeposito = valor.getText().toString();


                if (numeroContaOrigem.isEmpty()) {
                    alert("Preencha o número da conta origem.");
                } else if (numeroContaDestino.isEmpty()) {
                    alert("Preencha o número da conta destino.");
                } else if (valorDoDeposito.isEmpty()) {
                    alert("Preencha o valor a ser transferido.");
                } else {

                    RepositorioConta repositorioConta = new RepositorioConta(TransferirActivity.this);

                    boolean tranferido = repositorioConta.transferir(Long.parseLong(numeroContaOrigem), Long.parseLong(numeroContaDestino), Double.parseDouble(valorDoDeposito),TransferirActivity.this);

                    if (tranferido) {
                        alert("Transferência realizada.");
                    } else {
                        alert("Falha na transferência.");
                    }

                    Intent intent = new Intent(TransferirActivity.this, BancoActivity.class);

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
