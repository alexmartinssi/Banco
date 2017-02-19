package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContaActivity extends AppCompatActivity {

    private EditText valor;
    private Button botaoCadastrarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);

        valor = (EditText) findViewById(R.id.valorId);
        botaoCadastrarConta = (Button) findViewById(R.id.botaoCadastrarId);

        botaoCadastrarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                String valorInformado = valor.getText().toString();

                if(valorInformado.isEmpty()){
                    alert("Preencha o campo valor.");
                }

                Conta conta = new Conta(Double.parseDouble(valorInformado),cliente);

                RepositorioConta r = new RepositorioConta();

                r.inserirConta(conta);

                startActivity(new Intent(ContaActivity.this,BancoActivity.class));

            }
        });

    }

    public void alert(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
