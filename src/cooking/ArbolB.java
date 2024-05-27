package cooking;

import cooking.Node;
import cooking.arreglopasado;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author emily
 */
public class ArbolB {

    Node raiz = new Node();

    public ArbolB(Node d) {
        raiz = d;
    }

    public ArbolB() {
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }

    public void insertar(Node registroinsertar, int k) {
        boolean insertado = false;
        ArrayList<arreglopasado> listanodospasados = new ArrayList();
        ArrayList<Node> listanodoscomparacion = raiz.getNodos_contenido();

        if (raiz.getPosicion() == -1) {//cuando el arbol esta vacio entonces se ingresa una raiz

            raiz = registroinsertar;
            raiz.nodos_contenido.add(raiz);

        } else {//el arbol no esta vacio
            while (insertado == false) {//hasta que el registro este insertado
                for (int i = 0; i < listanodoscomparacion.size(); i++) {
                    if (listanodoscomparacion.get(i).isHoja()) {
                        if (listanodoscomparacion.size() < k) {
                            listanodoscomparacion.add(registroinsertar);
                            listanodoscomparacion = ordenar(listanodoscomparacion);
                            //listanodoscomparacion = ordenarreferencias(listanodoscomparacion); 
                            registroinsertar.nodos_contenido = listanodoscomparacion;
                            imprimir(listanodoscomparacion);
                            insertado = true;
                            break;
                        } else {
                            if (listanodoscomparacion.size() == k) {
                                listanodoscomparacion.add(registroinsertar);
                                listanodoscomparacion = ordenar(listanodoscomparacion);
                                int indice = k;
                                if (impar(indice)) {
                                    indice = ((k + 1) / 2) - 1;
                                } else {
                                    indice = (k / 2) - 1;
                                }

                                Node centro = new Node(false);
                                centro.setPosicion(listanodoscomparacion.get(indice).getPosicion());

                                for (int j = 0; j < listanodoscomparacion.size(); j++) {//se hace izquierda y derecha
                                    if (j < indice) {

                                        centro.nodos_izquierda.add(listanodoscomparacion.get(j));
                                    } else if (j > indice) {
                                        centro.nodos_derecha.add(listanodoscomparacion.get(j));
                                    }

                                }
                                for (int j = 0; j < centro.nodos_derecha.size(); j++) {
                                    centro.nodos_derecha.get(j).setNodos_contenido(centro.nodos_derecha);
                                }
                                for (int j = 0; j < centro.nodos_izquierda.size(); j++) {
                                    centro.nodos_izquierda.get(j).setNodos_contenido(centro.nodos_izquierda);
                                }

                                Node centropasado = centro;
                                boolean hubovuelta = false;
                                Node centronuevo = new Node(false);
                                for (int j = listanodospasados.size() - 1; j >= 0; j--) {//se introduce
                                    if (listanodospasados.get(j).arreglospasados.size() == k && j == 0) {
                                        centronuevo = new Node(false);
                                        listanodospasados.get(j).arreglospasados.add(centropasado);
                                        listanodospasados.get(j).arreglospasados = ordenarreferenciasalreves(listanodospasados.get(j).arreglospasados);
                                        centronuevo.setPosicion(listanodospasados.get(j).arreglospasados.get(indice).getPosicion());
                                        for (int p = 0; p < listanodospasados.get(j).arreglospasados.size(); p++) {//se hace izquierda y derecha
                                            if (p < indice) {
                                                centronuevo.nodos_izquierda.add(listanodospasados.get(j).arreglospasados.get(p));
                                            } else if (p > indice) {
                                                centronuevo.nodos_derecha.add(listanodospasados.get(j).arreglospasados.get(p));
                                            }

                                        }
                                        for (int f = 0; f < centronuevo.nodos_derecha.size(); f++) {
                                            centronuevo.nodos_izquierda.get(f).setNodos_contenido(centronuevo.nodos_izquierda);
                                        }
                                        for (int f = 0; f < centronuevo.nodos_izquierda.size(); j++) {
                                            centronuevo.nodos_derecha.get(f).setNodos_contenido(centronuevo.nodos_derecha);
                                        }
                                        raiz = centronuevo;
                                        centronuevo.setHoja(false);
                                        centronuevo.nodos_contenido.add(centronuevo);
                                        insertado = true;

                                        break;
                                    } else if (listanodospasados.get(j).arreglospasados.size() == k) {
                                        centronuevo = new Node(false);
                                        listanodospasados.get(j).arreglospasados.add(centropasado);
                                        listanodospasados.get(j).arreglospasados = ordenarreferenciasalreves(listanodospasados.get(j).arreglospasados);
                                        centronuevo.setPosicion(listanodospasados.get(j).arreglospasados.get(indice).getPosicion());
                                        for (int p = 0; p < listanodospasados.get(j).arreglospasados.size(); p++) {//se hace izquierda y derecha
                                            if (p < indice) {
                                                centronuevo.nodos_izquierda.add(listanodospasados.get(j).arreglospasados.get(p));
                                            } else if (p > indice) {
                                                centronuevo.nodos_derecha.add(listanodospasados.get(j).arreglospasados.get(p));
                                            }

                                        }
                                        for (int f = 0; f < centronuevo.nodos_derecha.size(); f++) {
                                            centronuevo.nodos_izquierda.get(f).setNodos_contenido(centronuevo.nodos_izquierda);
                                        }
                                        for (int f = 0; f < centronuevo.nodos_izquierda.size(); j++) {
                                            centronuevo.nodos_derecha.get(f).setNodos_contenido(centronuevo.nodos_derecha);
                                        }

                                    } else {
                                        if (hubovuelta == false) {
                                            listanodospasados.get(j).arreglospasados.add(centropasado);
                                            listanodospasados.get(j).arreglospasados = ordenar(listanodospasados.get(j).arreglospasados);
                                            listanodospasados.get(j).arreglospasados = ordenarreferencias(listanodospasados.get(j).arreglospasados);
                                            imprimir(raiz.getNodos_contenido());

                                            insertado = true;

                                        }
//                                        else {
//                                            listanodospasados.get(j).arreglospasados.add(centronuevo);
//                                            listanodospasados.get(j).arreglospasados = ordenar(listanodospasados.get(j).arreglospasados);
//                                            listanodospasados.get(j).arreglospasados = ordenarreferencias(listanodospasados.get(j).arreglospasados);
//                                            insertado = true;
//
//                                        }
                                        break;

                                    }
                                    centropasado = new Node(false);
                                    centropasado = centronuevo;
                                }
                                if (listanodospasados.size() == 0) {
                                    raiz = centro;
                                    centro.setHoja(false);
                                    centro.nodos_contenido.add(centro);
                                    insertado = true;

                                    break;
                                }

                            }

                        }
                    } else {
                        if ((registroinsertar.getPosicion() > listanodoscomparacion.get(i).getPosicion()) == false) {
                            listanodospasados.add(new arreglopasado(listanodoscomparacion));
                            listanodoscomparacion = listanodoscomparacion.get(i).getNodos_izquierda();
                            break;
                        } else if ((registroinsertar.getPosicion() > listanodoscomparacion.get(i).getPosicion()) && (i == listanodoscomparacion.size() - 1)) {
                            listanodospasados.add(new arreglopasado(listanodoscomparacion));
                            listanodoscomparacion = listanodoscomparacion.get(i).getNodos_derecha();
                            break;
                        }

                    }

                }

            }

        }

    }

    public int menor(ArrayList<Node> c) {
        int menor = c.get(0).getPosicion();
        int p = 0;
        for (int i = 1; i < c.size(); i++) {
            if (c.get(i).getPosicion() < menor) {
                menor = c.get(i).getPosicion();
                p = i;
            }
        }
        return p;
    }

    public boolean impar(int numero) {
        if (numero % 2 == 0) {
            return false;
        }
        return true;
    }

    public ArrayList<Node> ordenar(ArrayList<Node> o) {
        int n = o.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (o.get(j).posicion > o.get(j + 1).posicion) {
                    Node d = o.get(j);
                    o.set(j, o.get(j + 1));
                    o.set(j + 1, d);
                }
            }
        }
        return o;
    }

    public ArrayList<Node> ordenarreferencias(ArrayList<Node> c) {
        for (int i = 0; i < c.size(); i++) {
            if (i < (c.size() - 2)) {
                c.get(i + 1).setNodos_izquierda(c.get(i).getNodos_derecha());
            }
        }

        return c;
    }

    public ArrayList<Node> ordenarreferenciasalreves(ArrayList<Node> c) {
        for (int i = 0; i < c.size(); i++) {
            if (i < (c.size() - 2)) {
                c.get(i).setNodos_derecha(c.get(i + 1).getNodos_izquierda());
            }
        }

        return c;
    }

    public void imprimir(ArrayList<Node> a) {
        for (int i = 0; i < a.size(); i++) {

            if (i == 0) {
                System.out.print("[" + a.get(i).getPosicion() + ",");
            } else if (i == a.size() - 1) {
                System.out.print(a.get(i).getPosicion() + "]");
            } else {
                System.out.print(a.get(i).getPosicion() + ",");
            }

        }

        System.out.println("");

    }
}
