package com.mycompany.banheiro.unissex;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Banheiro {

    final ExecutorService banheiroExecutor;

    public Banheiro(int vagas) {
        banheiroExecutor = Executors.newFixedThreadPool(vagas);
    }

    public List<Future<String>> adicionarPessoas(List<Callable<String>> pessoas) throws InterruptedException {
        return banheiroExecutor.invokeAll(pessoas);
    }

    public void fecharBanheiro(){
        banheiroExecutor.shutdown();
    }

}
