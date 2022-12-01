package com.mycompany.banheiro.unissex;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Banheiro {
    protected final ExecutorService banheiroExecutor;
	

    public Banheiro(int vagas) {
        banheiroExecutor = Executors.newFixedThreadPool(vagas);

    }

    public void addPessoa(Pessoa pessoa) {
         banheiroExecutor.submit(pessoa);

    }
}

