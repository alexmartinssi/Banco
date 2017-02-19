package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

public class BancoActivity extends AppCompatActivity {

    private ImageView novaConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        novaConta = (ImageView) findViewById(R.id.novaContaId);

        novaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,ContaActivity.class);

                intent.putExtra("cliente", (Serializable) cliente);

                startActivity(intent);
            }
        });
    }
}
