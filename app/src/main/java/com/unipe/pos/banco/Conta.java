package com.unipe.pos.banco;

/**
 * Created by alexmartins on 18/02/17.
 */

public class Conta {

    private static int contador = 1000;
    private int numConta;
    private double valor;
    private Cliente cliente;

    public Conta(double valor, Cliente cliente){
        this.numConta = Conta.contador++;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Conta (){}

    public int getNumConta() {
        return numConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double ConsultaSaldo(){
        return getValor();
    }

    public void depositar(double depositar){
        this.valor += depositar;
    }

    public void sacar(double sacar){
        this.valor -= sacar;
    }

    public void transferir(Conta conta, double valor){
        conta.depositar(valor);
    }


}
