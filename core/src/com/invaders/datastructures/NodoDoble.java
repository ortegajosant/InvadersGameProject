package com.invaders.datastructures;

public class NodoDoble<T> {
    private NodoDoble<T> next;
    private NodoDoble<T> previous;
    private T dato;

    public NodoDoble(T dato) {
        this.dato = dato;
    }

    public NodoDoble<T> getNext() {
        return next;
    }

    public NodoDoble<T> getPrevious() {
        return previous;
    }

    public T getDato() {
        return dato;
    }

    public void setNext(NodoDoble<T> next) {
        this.next = next;
    }

    public void setPrevious(NodoDoble<T> previous) {
        this.previous = previous;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}