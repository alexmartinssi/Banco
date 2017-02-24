package com.unipe.pos.banco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarSaldoActivity extends AppCompatActivity {

    private ListView listarContasDoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_saldo);

        listarContasDoCliente = (ListView) findViewById(R.id.listarContasId);

        RepositorioConta repositorioConta = RepositorioConta.getInstance();
        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        System.out.print(cliente.getNome());
        ArrayList<Conta> contas = repositorioConta.getContas(cliente);

        ArrayAdapter<Conta> adapter = new ArrayAdapter<Conta>(this,
                android.R.layout.simple_list_item_1,contas);

        listarContasDoCliente.setAdapter(adapter);


    }

}
