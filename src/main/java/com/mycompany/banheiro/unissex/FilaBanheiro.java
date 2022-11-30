package com.mycompany.banheiro.unissex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Future;

public class FilaBanheiro {

    private final Queue<Pessoa> fila;

    private int id;
    Banheiro banheiro;

    public FilaBanheiro(int tamanho) {
        banheiro = new Banheiro(4);
        fila = new LinkedList<>();
        id = 0;

    }

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

    public void abrirBanheiro(){
        System.out.println("Abrindo banheiro");
        new Thread(() -> {
            while (true){

                if (!(fila.size() > 0)){
                    espera(500);
                    continue;
                }
                String sexo = "";
                List<Pessoa> pessoasParaEntrar = new ArrayList<>();
                List<Future<String>> status = new ArrayList<>();
                System.out.println("tamanho da fila: " + fila.size());

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

                for (Pessoa pessoa : pessoasParaEntrar) {
                    status.add(banheiro.addPessoa(pessoa));
                }

                for (Future<String> future : status) {
                    try {
                        String threadStatus = future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("todas as pessoas sairam do banheiro");
            }
        }).start();
    }

    public void addPessoa(Pessoa pessoa){
        fila.add(pessoa);
    }

    private void espera(int min, int max){
        int random = (int) (Math.random() * (max - min)) + min;
        try {
            Thread.sleep(random * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void espera(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getSexoAleatorio(){
        int random = (int) (Math.random() * 2) + 1;
        if (random == 1){
            return "M";
        } else {
            return "F";
        }
    }

}
