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

    public FilaBanheiro(int capacidade) {
        banheiro = new Banheiro(capacidade);
        this.id = 0;
		fila = new LinkedBlockingQueue<Pessoa>(); //Lista de pessoas para entrar 
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
                String sexo = ""; //garantir que so chegue depois de terminar
                List<Callable<String>> noBanheiro = new  ArrayList<>();
                
                do{
                    if(fila.peek().getSexo().equals(sexo) || sexo.equals("")){
                        sexo = fila.peek().getSexo();
                        noBanheiro.add(fila.poll());
                    }else{
                        try {
                            if(noBanheiro != null){
                                List<Future<String>> status = banheiro.banheiroExecutor.invokeAll(noBanheiro);
                                noBanheiro.clear();
                                sexo = "";
                            }  
                        } catch (InterruptedException e) {
                            e.printStackTrace();   
                        }
                    }
                   }while(fila.peek() != null);
            }
        }
        ).start();
    }

    public void addPessoa(Pessoa pessoa){
        fila.add(pessoa);
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