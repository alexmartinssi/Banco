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

public class  RepositorioCliente {

    private static RepositorioCliente instance;
    private static List<Cliente> clientes;
    private RepositorioConta repositorioConta;

    public RepositorioCliente(Context context) {

        BancoDAO bancoDAO = new BancoDAO(context);
        clientes = bancoDAO.readClients();
        bancoDAO.close();
    }

    public boolean existeCliente(String cpf){

        boolean existe = false;

        for (Cliente c: clientes) {

            if(cpf.equals(c.getCPF())){
                existe = true;
                return existe;
            }
        }

        return existe;
    }

    public Cliente getCliente(String cpf){

        for (Cliente c: clientes) {

            if(cpf.equals(c.getCPF())){
                return c;
            }
        }

        return null;
    }

    public Cliente existeLogin(String login, String senha){

        for (Cliente c: clientes) {

            if((login.equals(c.getLogin()) ||  login.equals(c.getEmail())) && senha.equals(c.getSenha())){
                return c;
            }
        }

        return null;
    }

    public boolean cadastrar(Cliente c, Context context){

        if(verificarCPF(c)){

            BancoDAO bancoDAO = new BancoDAO(context);
            bancoDAO.createClient(c);
            clientes = bancoDAO.readClients();
            bancoDAO.close();

            Conta conta = new Conta(getCliente(c.getCPF()));
            repositorioConta = new RepositorioConta(context);
            repositorioConta.inserirConta(conta,context);

            return true;
        }



        return false;
    }

    public void alterarCliente(String cpf, String nome, String email, String senha){

        for (Cliente c: clientes) {

            if(cpf.equals(c.getCPF())){
                c.setNome(nome);
                c.setEmail(email);
                c.setSenha(senha);
                break;
            }
        }
    }

    public void adicionarClientes(ArrayList<Cliente> clientes){
        this.clientes = clientes;
    }

    public List<Cliente> getClientes(){
        return clientes;
    }

    public boolean verificarCPF(Cliente cliente){
        return cliente.isCPF(cliente.getCPF());
    }
}
