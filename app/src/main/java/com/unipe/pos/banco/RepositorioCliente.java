package com.unipe.pos.banco;

import java.util.ArrayList;

/**
 * Created by alexmartins on 18/02/17.
 */

public class  RepositorioCliente {

    private static ArrayList<Cliente>  clientes;

    public RepositorioCliente(){
        clientes = new ArrayList<Cliente>();
    }

    public boolean existeCliente(String cpf){

        boolean f = true;

        for (Cliente c: clientes) {

            if(cpf.equals(c.getCPF())){
                f = false;
                break;
            }
        }

        return f;
    }

    public boolean existeLogin(String email, String senha){

        boolean f = false;

        for (Cliente c: clientes) {

            if(email.equals(c.getEmail()) && senha.equals(c.getSenha())){
                f = true;
                break;
            }
        }

        return f;
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
}
