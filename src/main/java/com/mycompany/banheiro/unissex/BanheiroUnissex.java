

package com.mycompany.banheiro.unissex;


public class BanheiroUnissex {

    static final int CAPACIDADE = 4;

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Iniciando fila");
        FilaBanheiro filaBanheiro = new FilaBanheiro(CAPACIDADE);
        filaBanheiro.iniciarFila();
        filaBanheiro.abrirBanheiro();
    }

}
