package com.unipe.pos.banco;

import java.util.ArrayList;

/**
 * Created by alexmartins on 18/02/17.
 */

public class RepositorioConta {

    private static RepositorioConta instance;

    // Construtor privado (suprime o construtor público padrão).
    private RepositorioConta() {}

    // Método público estático de acesso único ao objeto!
    public static RepositorioConta getInstance() {
        if (instance == null)
            instance = new RepositorioConta();
        return instance;
    }

    private static ArrayList<Conta>  contas = new ArrayList<Conta>();


   public void inserirConta(Conta conta){

       contas.add(conta);

   }

    public boolean existeConta(int numConta) {
        boolean f = false;
        for(Conta conta : contas){
            if(numConta == conta.getNumConta()){
                f = true;
            }
        }
        return f;
    }

    public Conta getConta(Cliente cliente) {

        for(Conta conta : contas){
            if(conta.getCliente().getCPF() == cliente.getCPF()){
                return conta;
            }
        }
        return null;
    }

    public ArrayList<Conta> getContas(Cliente cliente) {

        ArrayList<Conta>  contasDoCliente = new ArrayList<Conta>();

        for(Conta conta : contas){
            if(conta.getCliente().getCPF() == cliente.getCPF()){
                contasDoCliente.add(conta);
            }
        }
        return contasDoCliente;
    }

    
}
