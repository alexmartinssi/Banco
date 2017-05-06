package com.unipe.pos.banco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.model.Conta;
import com.unipe.pos.banco.repository.RepositorioConta;

import java.util.List;

public class ConsultarSaldoActivity extends AppCompatActivity {

    private ListView listarContasDoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_saldo);

        listarContasDoCliente = (ListView) findViewById(R.id.listarContasId);

        RepositorioConta repositorioConta = new RepositorioConta(ConsultarSaldoActivity.this);
        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        List<Conta> contas = repositorioConta.getContas(cliente);

        ArrayAdapter<Conta> adapter = new ArrayAdapter<Conta>(this,
                android.R.layout.simple_list_item_1,contas);

        listarContasDoCliente.setAdapter(adapter);


    }

}
