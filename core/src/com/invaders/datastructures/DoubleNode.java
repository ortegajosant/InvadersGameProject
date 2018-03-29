package com.invaders.datastructures;

public class DoubleNode<T> {
	private DoubleNode<T> next;
	private DoubleNode<T> previous;
	private T dato;

	public DoubleNode(T dato) {
		this.dato = dato;
	}

	public DoubleNode<T> getNext() {
		return next;
	}

	public DoubleNode<T> getPrevious() {
		return previous;
	}

	public T getDato() {
		return dato;
	}

	public void setNext(DoubleNode<T> next) {
		this.next = next;
	}

	public void setPrevious(DoubleNode<T> previous) {
		this.previous = previous;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
}