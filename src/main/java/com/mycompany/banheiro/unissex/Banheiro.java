package com.mycompany.banheiro.unissex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Banheiro {

    final ExecutorService banheiroExecutor;

    public Banheiro(int vagas) {
        banheiroExecutor = Executors.newFixedThreadPool(vagas);

    }

    public Future<String> addPessoa(Pessoa pessoa) {
        return banheiroExecutor.submit(pessoa);

    }

}
