package com.invaders.datastructures;

public class ListaCircular<T> {
    private NodoSimple<T> last;
    private int length;


    public ListaCircular() {

    }

    public void add(NodoSimple<T> nodo) {
        if (isEmpty()) {
            last = nodo;
            last.setNext(last);
            length += 1;
        } else {
            NodoSimple<T> temp = last;
            for (int i = 0; i < length; i++) {
                if (temp.getNext() == last) {
                    nodo.setNext(last);
                    temp.setNext(nodo);
                    length += 1;
                    break;
                }
                temp = temp.getNext();
            }
        }
    }

    public void add(int index, NodoSimple<T> nodo) {
        NodoSimple<T> temp = last;
        if (index == 0) {
            for (int i = 0; i < length; i++) {
                if (temp.getNext() == last) {
                    nodo.setNext(last);
                    last = nodo;
                    temp.setNext(last);
                    length += 1;
                    break;
                }
                temp = temp.getNext();
            }
        } else {
            int i = 0;
            NodoSimple<T> prev = last;
            while (index < length) {
                if (i == index) {
                    nodo.setNext(temp);
                    prev.setNext(nodo);
                    length += 1;
                    break;
                } else if (i > index) {
                    break;
                }
                prev = temp;
                temp = temp.getNext();
                i += 1;
            }
        }
    }

    public boolean isEmpty() {
        return last == null;
    }

    public void remove(NodoSimple<T> nodo) {
        NodoSimple<T> temp = last;
        int i;
        if (nodo == last) {
            remove(0);
        } else {
            i = 0;
            NodoSimple<T> prev = last;
            while (i < length) {
                if (nodo == temp) {
                    prev.setNext(temp.getNext());
                    length -= 1;
                    break;
                }
                prev = temp;
                temp = temp.getNext();
            }
        }

    }

    public void remove(int index) {
        NodoSimple<T> temp = last;
        int i;
        if (index == 0) {
            for (i = 0; i < length; i++) {
                if (temp.getNext() == last) {
                    last = temp.getNext().getNext();
                    temp.setNext(last);
                    length -= 1;
                    break;
                }
                temp = temp.getNext();
            }

        } else {
            NodoSimple<T> prev = last;
            i = 0;
            while (index < length) {
                if (i == index) {
                    prev.setNext(temp.getNext());
                    length -= 1;
                    break;
                } else if (i > index) {
                    break;
                }
                prev = temp;
                temp = temp.getNext();
                i++;
            }
        }
    }

    public void print() {
        NodoSimple<T> temp = last;
        for (int i = 0; i < length; i++) {
            System.out.println(temp.getDato());
            temp = temp.getNext();
        }
    }

    public int getLength() {
        return length;
    }
    
    public void erase() {
    	this.last = null;
    	this.length = 0;
    }
}
