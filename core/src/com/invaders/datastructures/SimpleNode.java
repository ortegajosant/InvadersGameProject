package com.invaders.datastructures;

public class SimpleNode<T> {
	private SimpleNode<T> next;
	private T dato;

	public SimpleNode(T dato) {
		this.dato = dato;
	}

	public SimpleNode<T> getNext() {
		return this.next;
	}

	public T getDato() {
		return this.dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setNext(SimpleNode<T> nodo) {
		this.next = nodo;
	}
}
