package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.model.Conta;
import com.unipe.pos.banco.repository.RepositorioConta;

public class NovaContaActivity extends AppCompatActivity {

    private EditText valor;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);

        valor = (EditText) findViewById(R.id.valorId);
        botaoCadastrar = (Button) findViewById(R.id.cadastrarContaId);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valorDepositado = valor.getText().toString();


                if (valorDepositado.isEmpty()) {
                    alert("Preencha o número da conta.");
                }


                Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                RepositorioConta repositorioConta = new RepositorioConta(NovaContaActivity.this);

                Conta conta = new Conta(Double.parseDouble(valorDepositado),cliente);

                repositorioConta.inserirConta(conta,NovaContaActivity.this);

                alert("Conta cadastrada.");

                Intent intent = new Intent(NovaContaActivity.this, BancoActivity.class);

                intent.putExtra("cliente", cliente);

                startActivity(intent);
            }
        });
    }

    public void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
