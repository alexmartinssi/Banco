package com.unipe.pos.banco.repository;

import android.content.Context;

import com.unipe.pos.banco.dao.BancoDAO;
import com.unipe.pos.banco.model.Cliente;
import com.unipe.pos.banco.model.Conta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexmartins on 18/02/17.
 */

public class RepositorioConta {

    private static RepositorioConta instance;
    private static List<Conta> contas;

    public RepositorioConta(Context context) {

        BancoDAO bancoDAO = new BancoDAO(context);
        contas = bancoDAO.readAccounts();
        bancoDAO.close();

    }


    public void inserirConta(Conta conta,Context context) {

        BancoDAO bancoDAO = new BancoDAO(context);
        bancoDAO.createAccount(conta);
        bancoDAO.close();

    }

    public boolean existeConta(int numConta) {
        boolean f = false;
        for (Conta conta : contas) {
            if (numConta == conta.getNumConta()) {
                f = true;
            }
        }
        return f;
    }

/*    public Conta getConta(Cliente cliente) {

        for (Conta conta : contas) {
            if (conta.getCliente().getCPF() == cliente.getCPF()) {
                return conta;
            }
        }
        return null;
    }*/

    public List<Conta> getContas() {

        return contas;
    }

    public List<Conta> getContas(Cliente cliente) {

        List<Conta> contasDoCliente = new ArrayList<Conta>();

        for (Conta conta : contas) {
            if (conta.getCliente_id() == cliente.getId()) {
                contasDoCliente.add(conta);
            }
        }
        return contas;
    }

    public boolean depositar(Long numConta, double valor,Context context) {

        for (Conta conta : contas) {
            if (conta.getNumConta() == numConta){
                conta.depositar(valor);

                BancoDAO bancoDAO = new BancoDAO(context);
                bancoDAO.updateAccount(conta);
                bancoDAO.close();

                return true;
            }
        }

        return false;
    }

    public boolean retirar(Long numConta, double valor,Context context) {

        for (Conta conta : contas) {
            if (conta.getNumConta() == numConta){
                if (conta.getValor() >= valor){
                    conta.sacar(valor);

                    BancoDAO bancoDAO = new BancoDAO(context);
                    bancoDAO.updateAccount(conta);
                    bancoDAO.close();

                    return true;
                }
            }
        }

        return false;
    }

    public boolean transferir(Long contaOrigem, Long contaDestino, double valor, Context context) {

        boolean retirado = retirar(contaOrigem,valor,context);
        boolean depositado = depositar(contaDestino,valor,context);

        if(retirado == true && depositado == true){
            return true;
        }

        return false;
    }


}
