package com.mycompany.banheiro.unissex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class FilaBanheiro {

    private final BlockingQueue<Pessoa> fila;

    private int id;
    Banheiro banheiro;

    public FilaBanheiro(int tamanho) {
        banheiro = new Banheiro(tamanho);
        fila = new LinkedBlockingQueue<Pessoa>();
        id = 0;

    }

    /**
     * Cria uma nova Thread responsável por produzir e
     * adicionar pessoas na fila para entrar no banheiro
     */
    public void iniciarFila(){
        new Thread(() -> {
            while (true) {
                espera(400);
                String sexo = getSexoAleatorio();
                Pessoa pessoa = new Pessoa(sexo, id);
                addPessoa(pessoa);
                id++;
            }
        }).start();
    }

    /*
     * Organiza a entrada ao banheiro, conforme as regras de restrição
     */ 
    public void abrirBanheiro(){
        System.out.println("Abrindo banheiro");
        new Thread(() -> {
            while (true){

                if (!(fila.size() > 0)){
                    espera(500);
                    continue;
                }
                String sexo = "";
                List<Callable<String>> pessoasParaEntrar = new ArrayList<>();
                List<Future<String>> status = new ArrayList<>();
                System.out.println("tamanho da fila: " + fila.size());

                if (fila.peek() != null) {
                    sexo = fila.peek().getSexo();

                } else {
                    System.out.println("Não é possível o funcionamento da fila sem informação de sexo");
                }
                // Adiciona todas as pessoas do mesmo sexo da ponta da fila como pessoas para entrar
                while (true){
                    if (fila.peek() == null){
                        break;
                    }
                    if (!sexo.equals(fila.peek().getSexo())){
                        break;
                    }
                    pessoasParaEntrar.add(fila.poll());
                }
                
                try {
                    /* Invoca todos as pessoas com permissão de entrada (do mesmo sexo),
                     * deixa a organização das vagas do banheiro com a ThreadPool de tamanho fixo e 
                     * aguarda a execução de todas elas
                     */
                    status = banheiro.banheiroExecutor.invokeAll(pessoasParaEntrar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                

                System.out.println("todas as pessoas sairam do banheiro");
            }
        }).start();
    }

    
    /** 
     * @param pessoa
     */
    public void addPessoa(Pessoa pessoa){
        fila.add(pessoa);
    }

    
    /** 
     * @param milisegundos
     */
    private void espera(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @return String
     */
    private String getSexoAleatorio(){
        int random = (int) (Math.random() * 2) + 1;
        if (random == 1){
            return "M";
        } else {
            return "F";
        }
    }

}
