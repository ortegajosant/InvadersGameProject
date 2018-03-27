package com.invaders.datastructures;

public class ListaSimple<T> {
	private NodoSimple<T> first;
	private int length;

	public ListaSimple() {

	}

	public void add(NodoSimple<T> nodo) {
		if (isEmpty()) {
			add(0, nodo);
		} else {
			NodoSimple<T> temp = first;
			for (int i = 0; i < length; i++) {
				if (temp.getNext() == null) {
					temp.setNext(nodo);
					this.length += 1;
					break;
				} else {
					temp = temp.getNext();
				}
			}
		}
	}

	public void add(int index, NodoSimple<T> nodo) {
		if (index == 0) {
			nodo.setNext(first);
			first = nodo;
			length += 1;
		} else {
			if (index >= getlength()) {
				throw new IndexOutOfBoundsException("Index out of range");
			} else {
				NodoSimple<T> prev = first;
				NodoSimple<T> temp = first;
				int i = 0;
				while (i <= index) {
					if (i == index) {
						nodo.setNext(temp);
						prev.setNext(nodo);
						length += 1;
						break;
					} else {
						prev = temp;
						temp = temp.getNext();
						i += 1;
					}
				}
			}
		}
	}

	public void remove(int index) {
		if (index == 0) {
			first = first.getNext();
			length -= 1;
		} else {
			NodoSimple<T> prev = first;
			NodoSimple<T> temp = first;

			int i = 0;
			while (index < getlength()) {
				if (i == index) {
					prev.setNext(temp.getNext());
					this.length -= 1;
				} else if (i < index) {
					prev = temp;
					temp = temp.getNext();
					i += 1;
					continue;
				}
				break;
			}
		}
	}

	public boolean remove(NodoSimple<T> nodo) {
		NodoSimple<T> prev = first;
		NodoSimple<T> temp = first;
		for (int i = 0; i < length; i++) {
			if (temp == nodo) {
				prev.setNext(temp.getNext());
				this.length -= 1;
			} else {
				prev = temp;
				temp = temp.getNext();
				i += 1;
			}
		}
		return false;
	}

	public void remove(T dato) {
		NodoSimple<T> temp = first;
		if (first.getDato() == dato) {
			if (length == 1) {
				erase();
			} else {
				for (int i = 0; i < length; i++) {
					if(temp.getNext() == first) {
						this.first = first.getNext();
						temp.setNext(first);
						length -= 1;
						break;
					}
					temp = temp.getNext();
				}
			}
		} else {
			NodoSimple<T> prev = first;
			for (int i = 0; i < length; i++) {
				if (temp.getDato() == dato) {
					prev.setNext(temp.getNext());
					this.length -= 1;
					break;
				} else {
					prev = temp;
					temp = temp.getNext();
				}
			}

		}
	}

	public boolean isEmpty() {
		return this.first == null;

	}

	public int getlength() {
		return length;
	}

	public void print() {
		NodoSimple<T> temp = first;
		for (int i = 0; i < getlength(); i++) {
			System.out.println(temp.getDato());
			temp = temp.getNext();
		}
	}

	public void erase() {
		this.first = null;
		this.length = 0;
	}

	public T find(int index) {
		NodoSimple<T> temp = first;
		for (int i = 0; i <= index; i++) {
			if (i == index) {
				return temp.getDato();
			}
			temp = temp.getNext();
		}
		return null;
	}
}
