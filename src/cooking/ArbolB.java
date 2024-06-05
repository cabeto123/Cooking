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

    public void insertar(Node registroinsertar, int k, ArrayList<Node> nodoscontenido, ArrayList<arreglopasado> nodospasados, int i, boolean insertado) {
         imprimir(raiz.nodos_contenido);
        if (nodoscontenido.get(0).hoja) {
            if (nodoscontenido.size() < k) {
                nodoscontenido.add(registroinsertar);
                ordenar(nodoscontenido);
                for (int j = 0; j < nodoscontenido.size(); j++) {
                    nodoscontenido.get(j).setNodos_contenido(nodoscontenido);
                    nodoscontenido.get(j).setHoja(true);
                }
                imprimir(nodoscontenido);
                insertado=true;
            } else {

                nodoscontenido.add(registroinsertar);
                ordenar(nodoscontenido);
                Node centronuevo = new Node(false);
                Node nodopasado = split(nodoscontenido, k);
                
                if (nodospasados.size() > 0) {
                    for (int j = nodospasados.size(); j >= 0; j--) {
                        if (nodospasados.get(j).arreglospasados.size() == k && j == 0) {
                            centronuevo = new Node(false);
                            nodospasados.get(j).arreglospasados.add(nodopasado);
                            ordenar(nodospasados.get(j).arreglospasados);
                            ordenarreferenciasalreves(nodospasados.get(j).arreglospasados);
                            centronuevo = split(nodospasados.get(j).arreglospasados, k);
                            raiz = centronuevo;
                            centronuevo.setHoja(false);
                            centronuevo.nodos_contenido.add(centronuevo);
                        } else if (nodospasados.get(j).arreglospasados.size() == k) {
                            centronuevo = new Node(false);
                            nodospasados.get(j).arreglospasados.add(nodopasado);
                            nodospasados.get(j).arreglospasados = ordenarreferenciasalreves(nodospasados.get(j).arreglospasados);
                            centronuevo = split(nodospasados.get(j).arreglospasados, k);
                        } else {
                            nodospasados.get(j).arreglospasados.add(nodopasado);
                            ordenar(nodospasados.get(j).arreglospasados);
                            ordenarreferenciasalreves(nodospasados.get(j).arreglospasados);
                            nodopasado.setNodos_contenido(nodospasados.get(j).arreglospasados);
                            //nodospasados.get(j).arreglospasados = ordenar(nodospasados.get(j).arreglospasados);
                            //nodospasados.get(j).arreglospasados = ordenarreferencias(nodospasados.get(j).arreglospasados);
                            imprimir(raiz.getNodos_contenido());
                            System.out.println("Hola");
                            insertado = true;
                            break;
                        }
                        nodopasado = new Node(false);
                        nodopasado = centronuevo;
                    }
                } else {
                    raiz = nodopasado;
                    nodopasado.setHoja(false);
                    nodopasado.nodos_contenido.add(nodopasado);
                    insertado = true;
                    
                }

            }
        } else if (registroinsertar.posicion < nodoscontenido.get(i).posicion) {

            nodospasados.add(new arreglopasado(nodoscontenido));
            insertar(registroinsertar, k, nodoscontenido.get(i).nodos_izquierda, nodospasados, 0, false);
        } else if (registroinsertar.posicion > nodoscontenido.get(i).posicion && nodoscontenido.size() ==i) {
            nodospasados.add(new arreglopasado(nodoscontenido));
            insertar(registroinsertar, k, nodoscontenido.get(i).nodos_izquierda, nodospasados, 0, false);
        }
        if (insertado==false) {
            if (i<nodoscontenido.size()-1) {
            i++;
            insertar(registroinsertar, k, nodoscontenido, nodospasados, i, false);     
            }
           
        }

    }

    public void ayuda(ArrayList<Node> listanodoscomparacion, Node registroinsertar, int k, boolean insertado, ArrayList<arreglopasado> listanodospasados) {
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
            centro.nodos_derecha.get(j).setHoja(true);
        }
        for (int j = 0; j < centro.nodos_izquierda.size(); j++) {
            centro.nodos_izquierda.get(j).setNodos_contenido(centro.nodos_izquierda);
            centro.nodos_izquierda.get(j).setHoja(true);
        }

        Node centropasado = new Node();
        centropasado.setHoja(centro.isHoja());
        centropasado.setNodos_contenido(centro.getNodos_contenido());
        centropasado.setNodos_izquierda(centro.getNodos_izquierda());
        centropasado.setNodos_derecha(centro.getNodos_derecha());
        centropasado.setPosicion(indice);
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
                centronuevo.nodos_contenido.add(centronuevo);
            } else {
                if (hubovuelta == false) {
                    centropasado.setNodos_contenido(listanodospasados.get(j).arreglospasados);
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
            System.out.println(centro.hoja);

        }

    }

    public Node split(ArrayList<Node> listanodoscomparacion, int k) {

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
            centro.nodos_derecha.get(j).setHoja(true);
        }
        for (int j = 0; j < centro.nodos_izquierda.size(); j++) {
            centro.nodos_izquierda.get(j).setNodos_contenido(centro.nodos_izquierda);
            centro.nodos_izquierda.get(j).setHoja(true);
        }

        return centro;
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

    public void imprimirarbol(ArrayList<Listasparaimprimir> array, int indice, ArrayList<Node> contenido) {
        if (contenido.get(0).isHoja()) {
            array.add(new Listasparaimprimir(contenido));
            for (int i = 0; i < array.size(); i++) {
                for (int j = 0; j < array.get(i).lista.size(); j++) {
                    if (j == 0) {
                        System.out.print("[" + array.get(i).lista.get(j).posicion + ",");
                    } else if (j == array.get(i).lista.size() - 1) {
                        System.out.print(array.get(i).lista.get(j).posicion + "]");
                    } else {
                        System.out.print(array.get(i).lista.get(j).posicion + ",");
                    }

                }
                System.out.println("");
            }
        } else {
            array.add(new Listasparaimprimir(contenido));
            for (int i = 0; i < contenido.size(); i += 2) {
                if (i < contenido.size()) {

                    contenido.addAll(contenido.get(i).nodos_izquierda);
                    contenido.addAll(contenido.get(i).nodos_derecha);
                }
            }
            //   imprimir(array.get(indice).lista);
            indice += 1;

            imprimirarbol(array, indice, contenido);
        }
    }
}
