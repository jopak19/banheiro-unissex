package com.mycompany.banheiro.unissex;
import java.util.Scanner;

public class BanheiroUnissex {

    static final int CAPACIDADE = 4;

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        FilaBanheiro filaBanheiro = new FilaBanheiro(CAPACIDADE);
        filaBanheiro.iniciarFila();
        filaBanheiro.abrirBanheiro();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\nExecutando, para parar a execução " +
                "a qualquer momento digite qualquer tecla e aperte enter\n\n\n");

        scanner.nextLine();
        filaBanheiro.setExecutando(false);

    }

}
