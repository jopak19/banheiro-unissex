package com.mycompany.banheiro.unissex;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Banheiro {

    final ExecutorService banheiroExecutor;

    /**
     * Construtor parametrizado do banheiro
     * @param vagas Quantidade de vagas do banheiro representado como fixed Thread Pool
     */
    public Banheiro(int vagas) {
        banheiroExecutor = Executors.newFixedThreadPool(vagas);
    }

    
    /** 
     * @param pessoas Pessoas que terão sua tarefa executadas (usar banheiro) na ThreadPool 
     * @return List Lista de retorno das tarefas executadas
     * @throws InterruptedException Caso haja problemas na execução
     */
    public List<Future<String>> adicionarPessoas(List<Callable<String>> pessoas) throws InterruptedException {
        return banheiroExecutor.invokeAll(pessoas);
    }

    /**
     * Desliga a ThreadPool para finalizar o programa
     */
    public void fecharBanheiro(){
        banheiroExecutor.shutdown();
    }

}
