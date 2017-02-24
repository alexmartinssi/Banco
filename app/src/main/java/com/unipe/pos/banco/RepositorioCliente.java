package com.unipe.pos.banco;

import java.util.ArrayList;

/**
 * Created by alexmartins on 18/02/17.
 */

public class  RepositorioCliente {

    private static RepositorioCliente instance;
    private static ArrayList<Cliente> clientes;

    // Construtor privado (suprime o construtor público padrão).
    private RepositorioCliente() {
        clientes = new ArrayList<Cliente>();
    }

    // Método público estático de acesso único ao objeto!
    public static RepositorioCliente getInstance() {
        if (instance == null)
            instance = new RepositorioCliente();
        return instance;
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

    public void cadastrar(Cliente c){
        clientes.add(c);
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

    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
}
