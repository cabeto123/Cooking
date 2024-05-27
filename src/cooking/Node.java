/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cooking;

import java.util.ArrayList;

/**
 *
 * @author emily
 */
public class Node {

    int posicion;
    boolean hoja;
    ArrayList<Node> nodos_contenido = new ArrayList();
    ArrayList<Node> nodos_derecha = new ArrayList();
    ArrayList<Node> nodos_izquierda = new ArrayList();

    public Node() {
        posicion=-1;
    }

    
    public Node(int posicion) {
        this.posicion = posicion;
        hoja=true;
    }

    public Node(boolean hoja) {
        this.hoja = hoja;
    }
    
    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public ArrayList<Node> getNodos_contenido() {
        return nodos_contenido;
    }

    public void setNodos_contenido(ArrayList<Node> nodos_contenido) {
        this.nodos_contenido = nodos_contenido;
    }

    public ArrayList<Node> getNodos_derecha() {
        return nodos_derecha;
    }

    public void setNodos_derecha(ArrayList<Node> nodos_derecha) {
        this.nodos_derecha = nodos_derecha;
    }

    public ArrayList<Node> getNodos_izquierda() {
        return nodos_izquierda;
    }

    public void setNodos_izquierda(ArrayList<Node> nodos_izquierda) {
        this.nodos_izquierda = nodos_izquierda;
    }
    

}
