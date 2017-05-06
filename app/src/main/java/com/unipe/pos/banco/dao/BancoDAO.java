package com.unipe.pos.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.model.Conta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexmartins on 28/04/17.
 */

public class BancoDAO extends SQLiteOpenHelper {

    public BancoDAO(Context context) {
        super(context, "Banco", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableClients =
                "CREATE TABLE Clientes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "CPF TEXT NOT NULL,"+
                        "nome TEXT NOT NULL,"+
                        "email TEXT NOT NULL,"+
                        "senha TEXT NOT NULL,"+
                        "login TEXT NOT NULL,"+
                        "pathPhoto TEXT)";

        String sqlCreateTableAccounts =
                "CREATE TABLE Contas (" +
                        "num_conta INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "valor DOUBLE NOT NULL,"+
                        "cliente_id INTEGER NOT NULL)";

        db.execSQL(sqlCreateTableClients);
        db.execSQL(sqlCreateTableAccounts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                String sql_version1 = "ALTER TABLE Clientes ADD COLUMN pathPhoto TEXT";
                db.execSQL(sql_version1);
                break;
        }
    }

    public void createClient(Cliente client) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues clientValues = getContentValuesForClient(client);

        database.insert("Clientes", null, clientValues);
    }

    @NonNull
    private ContentValues getContentValuesForClient(Cliente client) {
        ContentValues clientValues = new ContentValues();

        clientValues.put("CPF", client.getCPF());
        clientValues.put("nome", client.getNome());
        clientValues.put("email", client.getEmail());
        clientValues.put("senha", client.getSenha());
        clientValues.put("login", client.getLogin());
        clientValues.put("pathPhoto", client.getPathPhoto());
        return clientValues;
    }

    public List<Cliente> readClients() {

        SQLiteDatabase database = getReadableDatabase();
        String sqlReadClients =
                "SELECT * FROM Clientes";

        Cursor cursorReadClients = database.rawQuery(sqlReadClients, null);

        List<Cliente> clients = new ArrayList<Cliente>();

        while (cursorReadClients.moveToNext()){

            Cliente client = new Cliente();
            client.setId(cursorReadClients.getLong(cursorReadClients.getColumnIndex("id")));
            client.setCPF(cursorReadClients.getString(cursorReadClients.getColumnIndex("CPF")));
            client.setNome(cursorReadClients.getString(cursorReadClients.getColumnIndex("nome")));
            client.setEmail(cursorReadClients.getString(cursorReadClients.getColumnIndex("email")));
            client.setSenha(cursorReadClients.getString(cursorReadClients.getColumnIndex("senha")));
            client.setLogin(cursorReadClients.getString(cursorReadClients.getColumnIndex("login")));
            client.setPathPhoto(cursorReadClients.getString(cursorReadClients.getColumnIndex("pathPhoto")));


            clients.add(client);
        }

        cursorReadClients.close();

        return clients;
    }

    public void updateClient(Cliente client) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues clientValues = getContentValuesForClient(client);
        String[] params = {client.getId().toString()};
        database.update("Clientes", clientValues, "id = ?", params);

    }

    public void deleteClient(Long id) {

        SQLiteDatabase database = getWritableDatabase();
        String[] params = {id.toString()};
        database.delete("Clientes", "id = ?", params);
    }

    public void createAccount(Conta account) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues accountValues = getContentValuesForAccount(account);

        database.insert("Contas", null, accountValues);
    }

    @NonNull
    private ContentValues getContentValuesForAccount(Conta account) {
        ContentValues accountValues = new ContentValues();
        accountValues.put("valor", account.getValor());
        accountValues.put("cliente_id", account.getCliente_id());
        return accountValues;
    }

    public List<Conta> readAccounts() {

        SQLiteDatabase database = getReadableDatabase();
        String sqlReadAccounts =
                "SELECT * FROM Contas";

        Cursor cursorReadAccounts = database.rawQuery(sqlReadAccounts, null);

        List<Conta> accounts = new ArrayList<Conta>();

        while (cursorReadAccounts.moveToNext()){

            Conta account = new Conta();
            account.setNumConta(cursorReadAccounts.getLong(cursorReadAccounts.getColumnIndex("num_conta")));
            account.setValor(cursorReadAccounts.getDouble(cursorReadAccounts.getColumnIndex("valor")));
            account.setCliente_id(cursorReadAccounts.getLong(cursorReadAccounts.getColumnIndex("cliente_id")));


            accounts.add(account);
        }

        cursorReadAccounts.close();

        return accounts;
    }

    public void updateAccount(Conta account) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues accountValues = getContentValuesForAccount(account);
        String[] params = {account.getNumConta().toString()};
        database.update("Contas", accountValues, "num_conta = ?", params);

    }

    public void deleteAccount(Long id) {

        SQLiteDatabase database = getWritableDatabase();
        String[] params = {id.toString()};
        database.delete("Contas", "num_conta = ?", params);
    }




}
