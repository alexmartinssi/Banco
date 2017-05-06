package com.unipe.pos.banco;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.repository.RepositorioCliente;

import java.io.File;

public class ClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText login;
    private EditText CPF;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private Button cadastraConta;
    private RepositorioCliente repositorioCliente;
    private GoogleApiClient client;
    public static final int CODE_PHOTO = 567;
    private String pathPhotoButton;
    private ImageView pathPhoto;
    private ImageView imageViewPhoto;
    private Button buttonPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        nome = (EditText) findViewById(R.id.nomeId);
        login = (EditText) findViewById(R.id.loginId);
        CPF = (EditText) findViewById(R.id.cpfId);
        email = (EditText) findViewById(R.id.emailId);
        senha = (EditText) findViewById(R.id.senhaId);
        confirmarSenha = (EditText) findViewById(R.id.confirmarSenhaId);
        pathPhoto = (ImageView) findViewById(R.id.studentInsert_imageViewPhoto);

        buttonPhoto = (Button) findViewById(R.id.studentInsert_buttonPhoto);

        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCaptureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pathPhotoButton = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(pathPhotoButton);
                intentCaptureImage.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto));
                startActivityForResult(intentCaptureImage,CODE_PHOTO);
            }
        });

        cadastraConta = (Button) findViewById(R.id.cadastrarContaId);

        cadastraConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeCliente = nome.getText().toString();
                String loginCliente = login.getText().toString();
                String CPFCliente = CPF.getText().toString();
                String emailCliente = email.getText().toString();
                String senhaCliente = senha.getText().toString();
                String confirmarSenhaCliente = confirmarSenha.getText().toString();

                if (CPFCliente.isEmpty()) {
                    alert("Preencha o campo CPF");
                }

                else if (nomeCliente.isEmpty()) {
                    alert("Preencha o campo nome.");

                }

                else if (loginCliente.isEmpty()) {
                    alert("Preencha o campo login.");

                }

                else if (emailCliente.isEmpty()) {
                    alert("Preencha o campo email.");
                }

                else if (senhaCliente.isEmpty()) {
                    alert("Preencha o campo senha.");
                }

                else if (confirmarSenhaCliente.isEmpty()) {
                    alert("Preencha o campo confirmarSenha.");
                }

                else if (!confirmarSenhaCliente.equals(senhaCliente)) {
                    alert("O campo Confirmar Senha não confere com a senha digitada acima.");
                }else{

                    Cliente cliente = new Cliente(CPFCliente, nomeCliente,loginCliente, emailCliente, senhaCliente);

                    String path = (String) pathPhoto.getTag();
                    cliente.setPathPhoto(path);

                    repositorioCliente = new RepositorioCliente(ClienteActivity.this);

                    if (repositorioCliente.existeCliente(cliente.getCPF())) {
                        alert("Cliente cadastrado.");
                    } else {

                        repositorioCliente.cadastrar(cliente,ClienteActivity.this);

                        Intent intent = new Intent(ClienteActivity.this, LoginActivity.class);

                        startActivity(intent);

                        alert("Conta cadastrada.");
                    }
                }
            }
        });

    }

    public void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CODE_PHOTO) {
                imageViewPhoto = (ImageView) findViewById(R.id.studentInsert_imageViewPhoto);
                Bitmap bitmap = BitmapFactory.decodeFile(pathPhotoButton);
                Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                imageViewPhoto.setImageBitmap(bitmapReduce);
                imageViewPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
                imageViewPhoto.setTag(pathPhotoButton);

            }
        }
    }
}
