package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static com.unipe.pos.banco.RepositorioConta.*;

public class BancoActivity extends AppCompatActivity{

    private ImageView depositar;
    private ImageView realizarSaque;
    private ImageView consultarSaldo;
    private ImageView transferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        depositar = (ImageView) findViewById(R.id.depositarId);
        realizarSaque = (ImageView) findViewById(R.id.saqueId);
        consultarSaldo = (ImageView) findViewById(R.id.consultarSaldoId);
        transferir = (ImageView) findViewById(R.id.tranferirId);

        depositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Conta> contas = (ArrayList<Conta>) getIntent().getSerializableExtra("contas");

                Intent intent = new Intent(BancoActivity.this,ConsultarSaldoActivity.class);

                intent.putExtra("contas", contas);

                startActivity(intent);
            }
        });

        consultarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Conta> contas = (ArrayList<Conta>) getIntent().getSerializableExtra("contas");

                Intent intent = new Intent(BancoActivity.this,ConsultarSaldoActivity.class);

                intent.putExtra("contas", contas);

                startActivity(intent);
            }
        });

        realizarSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,ConsultarSaldoActivity.class);

                intent.putExtra("cliente", cliente);

                startActivity(intent);
            }
        });

        transferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,ConsultarSaldoActivity.class);

                intent.putExtra("cliente", cliente);

                startActivity(intent);
            }
        });
    }
}
