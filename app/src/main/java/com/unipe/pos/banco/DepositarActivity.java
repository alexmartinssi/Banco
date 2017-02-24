package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                ArrayList<Conta> contas = (ArrayList<Conta>) getIntent().getSerializableExtra("contas");



                Intent intent = new Intent(BancoActivity.this,ConsultarSaldoActivity.class);

                intent.putExtra("contas", contas);

                startActivity(intent);
            }
        });
    }
}
