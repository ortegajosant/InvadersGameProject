package dataStructures;

public class NodoSimple<T> {
    private NodoSimple<T> next;
    private T dato;

    public NodoSimple(T dato) {
        this.dato = dato;
    }

    public NodoSimple<T> getNext() {
        return this.next;
    }

    public T getDato() {
        return this.dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setNext(NodoSimple<T> nodo) {
        this.next = nodo;
    }
}
