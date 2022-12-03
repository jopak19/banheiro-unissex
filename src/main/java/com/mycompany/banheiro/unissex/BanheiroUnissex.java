package com.mycompany.banheiro.unissex;
import java.util.Scanner;

public class BanheiroUnissex {


    static final int CAPACIDADE = 4;
    /** 
     * @param args Argumentos de inicialização
     */
    public static void main(String[] args) {

        

        FilaBanheiro filaBanheiro = new FilaBanheiro(CAPACIDADE);
        filaBanheiro.iniciarFila();
        filaBanheiro.abrirBanheiro();


        System.out.println("\n\n\nExecutando, para parar a execução " +
                "a qualquer momento digite qualquer tecla e aperte enter\n\n\n");
        Scanner scanner = new Scanner(System.in);

      
        try {
            scanner.nextLine();
            filaBanheiro.setExecutando(false);
        } catch (Exception e) {
            System.out.println("Não obteve entrada para fim de execução");
        }
        scanner.close();
        
    }
        
        

    

}
