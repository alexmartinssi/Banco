package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BancoActivity extends AppCompatActivity {

    private ImageView botaoConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        botaoConta = (ImageView) findViewById(R.id.botaoContaId);


        botaoConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(BancoActivity.this,ContaActivity.class));
            }
        });
    }
}
