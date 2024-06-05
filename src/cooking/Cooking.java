/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cooking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author emily
 */
public class Cooking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArbolB arbol = new ArbolB();
        while (true) {
            System.out.println("Digite la posicion ");
            int posicion = entrada.nextInt();
            if (arbol.raiz.posicion==-1) {
                
                Node raiz = new Node(false);
                raiz.setPosicion(posicion);
                raiz.setHoja(true);
                raiz.nodos_contenido.add(raiz);
                arbol.setRaiz(raiz);
            }else{
                ArrayList<arreglopasado>a= new ArrayList();
              arbol.insertar(new Node(posicion),3,arbol.raiz.nodos_contenido,a,0,false); 
            }
               
        }

    }

}
