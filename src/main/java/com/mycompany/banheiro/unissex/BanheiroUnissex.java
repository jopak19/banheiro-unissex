package com.mycompany.banheiro.unissex;
import java.util.Scanner;

public class BanheiroUnissex {


    
    /** 
     * @param args Argumentos de inicialização
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 's' se quiser determinar a quantidade de vagas " +
                "no banheiro ou qualquer outra tecla para usar o padrão de 4 vagas: ");
        String opcao = scanner.nextLine();
        int capacidade = 4;
        if(opcao.equals("s")){
            System.out.println("Digite a quantidade de vagas no banheiro: ");
            try {
                capacidade = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Valor inválido, será utilizado o valor padrão de 4 vagas");
            }
        }

        FilaBanheiro filaBanheiro = new FilaBanheiro(capacidade);
        filaBanheiro.iniciarFila();
        filaBanheiro.abrirBanheiro();

        //se não colocar uma espera o programa encerra antes de executar
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("\n\n\nExecutando, para parar a execução " +
                "a qualquer momento digite qualquer tecla e aperte enter\n\n\n");

        scanner.nextLine();
        filaBanheiro.setExecutando(false);

    }

}
