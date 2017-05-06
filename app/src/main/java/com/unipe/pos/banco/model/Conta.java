package com.unipe.pos.banco.model;

import java.io.Serializable;

/**
 * Created by alexmartins on 18/02/17.
 */

public class Conta implements Serializable {

    private Long numConta;
    private double valor;
    private Long cliente_id;

    public Conta(Cliente cliente){
        this.valor = 10.0;
        this.cliente_id = cliente.getId();
    }

    public Conta(double valor, Cliente cliente){
        this.valor = valor;
        this.cliente_id = cliente.getId();
    }

    public Conta (){}

    public Long getNumConta() {
        return numConta;
    }

    public Long setNumConta(Long numConta) {return this.numConta = numConta; }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
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

    @Override
    public String toString() {
        return "Número da Conta: " + this.numConta + "\nSaldo: R$" +
                this.valor;
    }


}
