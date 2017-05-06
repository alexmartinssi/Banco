package com.unipe.pos.banco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unipe.pos.banco.model.Cliente;

public class BancoActivity extends AppCompatActivity{

    private ImageView depositar;
    private ImageView realizarSaque;
    private ImageView consultarSaldo;
    private ImageView transferir;
    private TextView logout;
    private TextView login;
    private ImageView novaConta;
    private ImageView clienteFoto;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        depositar = (ImageView) findViewById(R.id.depositarId);
        realizarSaque = (ImageView) findViewById(R.id.saqueId);
        consultarSaldo = (ImageView) findViewById(R.id.consultarSaldoId);
        transferir = (ImageView) findViewById(R.id.tranferirId);
        novaConta = (ImageView) findViewById(R.id.novaContaId);
        logout = (TextView) findViewById(R.id.sairId);
        login = (TextView) findViewById(R.id.loginId);
        clienteFoto = (ImageView) findViewById(R.id.fotoId);


        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        if (cliente.getPathPhoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(cliente.getPathPhoto());
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            clienteFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            clienteFoto.setImageBitmap(bitmapReduce);
        }

        login.setText(cliente.getLogin()+",");

        depositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,DepositarActivity.class);

                intent.putExtra("cliente",cliente);

                startActivity(intent);
            }
        });

        consultarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,ConsultarSaldoActivity.class);

                intent.putExtra("cliente",cliente);

                startActivity(intent);
            }
        });

        realizarSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,RealizarSaqueActivity.class);

                intent.putExtra("cliente",cliente);

                startActivity(intent);
            }
        });

        transferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,TransferirActivity.class);

                intent.putExtra("cliente",cliente);

                startActivity(intent);
            }
        });

        novaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente = (Cliente) getIntent().getSerializableExtra("cliente");

                Intent intent = new Intent(BancoActivity.this,NovaContaActivity.class);

                intent.putExtra("cliente",cliente);

                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BancoActivity.this,LoginActivity.class);

                startActivity(intent);
            }
        });
    }
}
