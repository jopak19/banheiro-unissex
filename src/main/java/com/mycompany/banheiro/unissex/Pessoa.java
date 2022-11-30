package com.mycompany.banheiro.unissex;

import java.util.concurrent.Callable;

public class Pessoa implements Callable<String> {

    private final String sexo;
    private final int id;

    public Pessoa(String sexo, int id) {
        this.sexo = sexo;
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public int getId() {
        return id;
    }

    private void usarBanheiro() {
        int random = (int) (Math.random() * (5 - 1)) + 1;
        try {
            Thread.sleep(random * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        System.out.println("Pessoa " + id + " do sexo " + sexo + " entrou no banheiro");
        usarBanheiro();
        System.out.println("Pessoa " + id + " do sexo " + sexo + " saiu do banheiro");
        return "Pessoa " + id + " do sexo " + sexo + " saiu do banheiro";
    }
}
