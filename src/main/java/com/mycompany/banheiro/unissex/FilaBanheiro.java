package com.mycompany.banheiro.unissex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class FilaBanheiro {

    private final BlockingQueue<Pessoa> fila;

    private int id;
    Banheiro banheiro;

    private final int capacidade;
    private boolean executando = true;



    public FilaBanheiro(int vagas) {
        banheiro = new Banheiro(vagas);
        capacidade = vagas;
        fila = new LinkedBlockingQueue<Pessoa>();
        id = 0;

    }

    public void setExecutando(boolean executando) {
        this.executando = executando;
    }

    /**
     * Cria uma nova Thread responsável por produzir e
     * adicionar pessoas na fila para entrar no banheiro
     */
    public void iniciarFila(){
        System.out.println("Iniciando fila do banheiro");
        new Thread(() -> {
            while (true) {
                if(!executando){
                    System.out.println("Encerrando fila");
                    break;
                }
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
        System.out.println("Banheiro aberto com " + capacidade + " vagas");
        new Thread(() -> {
            while (true){

                if(!executando){
                    System.out.println("Banheiro fechado");
                    banheiro.fecharBanheiro();
                    break;
                }
                if (!(fila.size() > 0)){
                    espera(500);
                    continue;
                }
                String sexo = "";
                List<Callable<String>> pessoasParaEntrar = new ArrayList<>();
                if (fila.peek() != null) {
                    sexo = fila.peek().getSexo();

                } else {
                    System.out.println("Não é possível o funcionamento da fila sem informação de sexo");
                }

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
                    banheiro.adicionarPessoas(pessoasParaEntrar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Todas as pessoas sairam do banheiro");
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
