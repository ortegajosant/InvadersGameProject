package com.invaders.datastructures;

public class CircularDoubleList<T> {

	private DoubleNode<T> first;
	private int length;

	public CircularDoubleList() {
	}

	private void addFirst(DoubleNode<T> nodo) {
		this.first = nodo;
		first.setNext(first);
		first.setPrevious(first);
		this.length += 1;
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	public void add(DoubleNode<T> nodo) {
		if (isEmpty()) {
			addFirst(nodo);
		} else {
			nodo.setNext(first);
			nodo.setPrevious(first.getPrevious());
			first.getPrevious().setNext(nodo);
			first.setPrevious(nodo);
			this.length += 1;
		}
	}

	public void add(int index, DoubleNode<T> nodo) {
		if (index == 0) {
			nodo.setNext(first);
			nodo.setPrevious(first.getPrevious());
			first.getPrevious().setPrevious(nodo);
			first.setPrevious(nodo);
			first = nodo;
			this.length += 1;
		} else {
			DoubleNode<T> temp = first;
			for (int i = 0; i < this.length; i++) {
				if (i == index) {
					nodo.setNext(temp);
					nodo.setPrevious(temp.getPrevious());
					temp.getPrevious().setNext(nodo);
					temp.setPrevious(nodo);
					this.length += 1;
					break;
				}
				temp = temp.getNext();
			}
		}
	}

	public void remove(T dato) {
		if (first.getDato().equals(dato)) {
			remove(0);
		} else {
			DoubleNode<T> temp = first;
			for (int i = 0; i < this.length; i++) {
				if (temp.getDato().equals(dato)) {
					remove(i);
					break;
				}
				temp = temp.getNext();
			}
		}
	}

	public void remove(DoubleNode<T> nodo) {
		if (nodo == first) {
			remove(0);
		} else {
			DoubleNode<T> temp = first;
			for (int i = 0; i < this.length; i++) {
				if (nodo == temp) {
					remove(i);
					break;
				}
				temp = temp.getNext();
			}
		}
	}

	public void remove(int index) {
		if (index == 0) {
			first.getPrevious().setNext(first.getNext());
			first.getNext().setPrevious(first.getPrevious());
			first = first.getNext();
			this.length -= 1;
		} else {
			DoubleNode<T> temp = first;
			for (int i = 0; i < this.length; i++) {
				if (i == index) {
					temp.getNext().setPrevious(temp.getPrevious());
					temp.getPrevious().setNext(temp.getNext());
					this.length -= 1;
					break;
				}
				temp = temp.getNext();
			}
		}
	}

	public void replace(int index, DoubleNode<T> nodo) {
		DoubleNode<T> temp = first;
		for (int i = 0; i < length; i++) {
			if (i == index) {
				nodo.setNext(temp.getNext());
				nodo.setPrevious(temp.getPrevious());
				temp.getPrevious().setNext(nodo);
				temp.getNext().setPrevious(nodo);
				if (i == 0) {
					first = nodo;
				}
				break;
			}
			temp = temp.getNext();
		}
	}

	public T find(int index) {
		DoubleNode<T> temp = first;
		for (int i = 0; i < length; i++) {
			if (i == index) {
				return temp.getDato();
			}
			temp = temp.getNext();
		}
		return null;
	}

	public int getLength() {
		return length;
	}

	public DoubleNode<T> getFirst() {
		return this.first;

	}

}
