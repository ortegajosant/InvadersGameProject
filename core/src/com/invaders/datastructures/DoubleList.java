package com.invaders.datastructures;

public class DoubleList<T> {
	private DoubleNode<T> first;
	private int length;

	public DoubleList() {

	}

	public DoubleNode<T> getNode(int index) {
		int i = 0;
		DoubleNode<T> temp = first;
		while (index < length) {
			if (i == index) {
				return temp;
			} else if (i < index){
				temp = temp.getNext();
				i++;
			}
			break;
			
		}
		return null;
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

	public void replace(DoubleNode<T> nodo, int index) {
		if (index == 0) {
			if (isEmpty()) {
				addFirst(nodo);
			} else {
				nodo.setNext(first.getNext());
				first.getNext().setPrevious(nodo);
				first = nodo;
			}
		} else {
			if (index >= length) {
				throw new IndexOutOfBoundsException("Index out of range");
			} else {
				DoubleNode<T> prev = first;
				DoubleNode<T> temp = first;
				int i = 0;
				while (i <= index) {
					if (i == index && i < length - 1) {
						prev.setNext(nodo);
						nodo.setPrevious(prev);
						nodo.setNext(temp.getNext());
						temp.getNext().setPrevious(nodo);
						break;
					} else if (i == index){
						nodo.setNext(temp.getNext());
						nodo.setPrevious(prev);
						prev.setNext(nodo);
						break;
					} else if (i < index) {
						prev = temp;
						temp = temp.getNext();
						i += 1;
					} else {
						break;
					}
				}
			}
		}
	}

	public void erase() {
		this.first = null;
		this.length = 0;
	}

	public int getLength() {
		return this.length;
	}

	public T find(int index) {
		DoubleNode<T> temp = first;
		if (index < length) {
			for (int i = 0; i <= index; i++) {
				if (i == index) {
					return temp.getDato();
				}
				temp = temp.getNext();
			}
		}
		return null;
	}

	public DoubleNode<T> getFirst() {
		// TODO Auto-generated method stub
		return first;
	}

	public void remove(T dato) {
		if (dato == first.getDato()) {
			first = first.getNext();
			first.setPrevious(null);
			length -= 1;
		} else {
			DoubleNode<T> prev = first;
			DoubleNode<T> temp = first;
			for (int i = 0; i < length; i++) {
				if (dato == temp.getDato() && i < length - 1) {
					temp.getNext().setPrevious(prev);
					prev.setNext(temp.getNext());
					length -= 1;
					break;
				} else if (dato == temp.getDato()) {
					prev.setNext(temp.getNext());
					length -= 1;
					break;
				}
				prev = temp;
				temp = temp.getNext();
			}
		}
	}
}
