
package com.mycompany.banheiro.unissex;


public class BanheiroUnissex {


    public final static int CAPACIDADE = 5;

    public static void main(String[] args) {

        System.out.println("Iniciando fila");
        FilaBanheiro filaBanheiro = new FilaBanheiro(CAPACIDADE);
        filaBanheiro.iniciarFila();
        filaBanheiro.abrirBanheiro();
    }

}
