package com.invaders.datastructures;

public class SimpleList<T> {
	private SimpleNode<T> first;
	private int length;

	public SimpleList() {

	}

	public void add(SimpleNode<T> nodo) {
		if (isEmpty()) {
			add(0, nodo);
		} else {
			SimpleNode<T> temp = first;
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

	public void add(int index, SimpleNode<T> nodo) {
		if (index == 0) {
			nodo.setNext(first);
			first = nodo;
			length += 1;
		} else {
			if (index >= getLength()) {
				throw new IndexOutOfBoundsException("Index out of range");
			} else {
				SimpleNode<T> prev = first;
				SimpleNode<T> temp = first;
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
			SimpleNode<T> prev = first;
			SimpleNode<T> temp = first;

			int i = 0;
			while (index < getLength()) {
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

	public boolean remove(SimpleNode<T> nodo) {
		SimpleNode<T> prev = first;
		SimpleNode<T> temp = first;
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
		if (first.getDato() == dato) {
			first = first.getNext();
			this.length -= 1;

		} else {
			SimpleNode<T> temp = first;
			SimpleNode<T> prev = first;
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

	public int getLength() {
		return length;
	}

	public void print() {
		SimpleNode<T> temp = first;
		for (int i = 0; i < getLength(); i++) {
			System.out.println(temp.getDato());
			temp = temp.getNext();
		}
	}

	public void erase() {
		this.first = null;
		this.length = 0;
	}

	public T find(int index) {
		SimpleNode<T> temp = first;
		for (int i = 0; i <= index; i++) {
			if (i == index) {
				return temp.getDato();
			}
			temp = temp.getNext();
		}
		return null;
	}

	public SimpleNode<T> getFirst() {
		return first;
	}
}
