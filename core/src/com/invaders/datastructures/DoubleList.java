package com.invaders.datastructures;

public class DoubleList<T> {
	private DoubleNode<T> first;
	private int length;

	public DoubleList() {

	}

	public void add(DoubleNode<T> nodo) {
		if (isEmpty()) {
			addFirst(nodo);
		} else {
			DoubleNode<T> temp = first;
			for (int i = 0; i < length; i++) {
				if (temp.getNext() == null) {
					nodo.setPrevious(temp);
					temp.setNext(nodo);
					this.length += 1;
					break;
				}
				temp = temp.getNext();
			}
		}
	}

	public void add(int index, DoubleNode<T> nodo) {
		if (index == 0) {
			if (isEmpty()) {
				addFirst(nodo);
			} else {
				nodo.setNext(first);
				first.setPrevious(nodo);
				first = nodo;
				length += 1;
			}
		} else {
			if (index >= length) {
				throw new IndexOutOfBoundsException("Index out of range");
			} else {
				DoubleNode<T> prev = first;
				DoubleNode<T> temp = first;
				int i = 0;
				while (i <= index) {
					if (i == index) {
						nodo.setNext(temp);
						nodo.setPrevious(prev);
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

	private void addFirst(DoubleNode<T> nodo) {
		first = nodo;
		length += 1;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void print() {
		DoubleNode<T> temp = first;
		for (int i = 0; i < length; i++) {
			System.out.println(temp.getDato());
			temp = temp.getNext();
		}
	}

	public void remove(DoubleNode<T> nodo) {
		if (nodo == first) {
			first = first.getNext();
			first.setPrevious(null);
			length -= 1;
		} else {
			DoubleNode<T> prev = first;
			DoubleNode<T> temp = first;
			for (int i = 0; i < length; i++) {
				if (nodo == temp) {
					prev.setNext(temp.getNext());
					temp.getNext().setNext(prev);
					length -= 1;
					break;
				}
				prev = temp;
				temp = temp.getNext();
			}
		}
	}

	public void remove(int index) {
		if (index == 0) {
			first = first.getNext();
			first.setPrevious(null);
			length -= 1;
		} else {
			DoubleNode<T> prev = first;
			DoubleNode<T> temp = first;
			int i = 0;
			while (index < length) {
				if (i == index) {
					prev.setNext(temp.getNext());
					temp.getNext().setPrevious(prev);
					length -= 1;
					break;
				} else {
					prev = temp;
					temp = temp.getNext();
					i += 1;
				}
			}
		}
	}

	public void erase() {
		this.first = null;
		this.length = 0;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}

	public T find(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
