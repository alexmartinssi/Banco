package com.unipe.pos.banco;

import java.util.ArrayList;

/**
 * Created by alexmartins on 18/02/17.
 */

public class RepositorioConta {

    private static RepositorioConta instance;

    private static ArrayList<Conta> contas;

    // Construtor privado (suprime o construtor público padrão).
    private RepositorioConta() {
        contas = new ArrayList<Conta>();
    }

    // Método público estático de acesso único ao objeto!
    public static RepositorioConta getInstance() {
        if (instance == null)
            instance = new RepositorioConta();
        return instance;
    }

    public void inserirConta(Conta conta) {

        contas.add(conta);

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

    public Conta getConta(Cliente cliente) {

        for (Conta conta : contas) {
            if (conta.getCliente().getCPF() == cliente.getCPF()) {
                return conta;
            }
        }
        return null;
    }

    public ArrayList<Conta> getContas() {

        return contas;
    }

    public ArrayList<Conta> getContas(Cliente cliente) {

        ArrayList<Conta> contasDoCliente = new ArrayList<Conta>();

        for (Conta conta : contas) {
            if (conta.getCliente().getCPF().equals(cliente.getCPF())) {
                contasDoCliente.add(conta);
            }
        }
        return contasDoCliente;
    }

    public boolean depositar(int numConta, double valor) {

        for (Conta conta : contas) {
            if (conta.getNumConta() == numConta){
                conta.depositar(valor);
                return true;
            }
        }

        return false;
    }

    public boolean retirar(int numConta, double valor) {

        for (Conta conta : contas) {
            if (conta.getNumConta() == numConta){
                conta.sacar(valor);
                return true;
            }
        }

        return false;
    }

    public boolean transferir(int contaOrigem, int contaDestino, double valor) {

        boolean retirado = retirar(contaOrigem,valor);
        boolean depositado = depositar(contaDestino,valor);

        if(retirado == true && depositado == true){
            return true;
        }

        return false;
    }


}
