package com.invaders.datastructures;

public class CircularList<T> {
	private SimpleNode<T> last;
	private int length;

	public CircularList() {

	}

	public void add(SimpleNode<T> nodo) {
		if (isEmpty()) {
			last = nodo;
			last.setNext(last);
			length += 1;
		} else {
			SimpleNode<T> temp = last;
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

	public void add(int index, SimpleNode<T> nodo) {
		SimpleNode<T> temp = last;
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
			SimpleNode<T> prev = last;
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

	public void remove(SimpleNode<T> nodo) {
		SimpleNode<T> temp = last;
		int i;
		if (nodo == last) {
			remove(0);
		} else {
			i = 0;
			SimpleNode<T> prev = last;
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

	public void remove(T dato) {
		if (last.getDato() == dato) {
			remove(0);
		} else {
			int i = 0;
			SimpleNode<T> temp = last;
			SimpleNode<T> prev = last;
			while (i < length) {
				if (temp.getDato() == dato) {
					prev.setNext(temp.getNext());
					length -= 1;
					break;
				}
				prev = temp;
				temp = temp.getNext();
				i++;
			}
		}

	}

	public void remove(int index) {
		SimpleNode<T> temp = last;
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
			SimpleNode<T> prev = last;
			i = 0;
			while (i < length) {
				if (i == index) {
					prev.setNext(temp.getNext());
					length -= 1;
					break;
				}
				prev = temp;
				temp = temp.getNext();
				i++;
			}
		}
	}

	public void addLast(SimpleNode<T> nodo) {
		this.last = nodo;
		last.setNext(last);
		length += 1;
	}

	public void replace(int index, SimpleNode<T> nodo) {
		if (index >= length) {
			throw new IndexOutOfBoundsException("Index out of range");
		} else {
			SimpleNode<T> temp = last;
			if (index == 0) {
				for (int i = 0; i < length; i++) {
					if (temp.getNext() == last) {
						nodo.setNext(last.getNext());
						last = nodo;
						temp.setNext(last);
						break;
					} 
					temp = temp.getNext();
				}
			} else {
				SimpleNode<T> prev = last;
				int i = 0;
				while (i < length) {
					if (i == index) {
						nodo.setNext(temp.getNext());
						prev.setNext(nodo);
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

	public void print() {
		SimpleNode<T> temp = last;
		for (int i = 0; i < length; i++) {
			System.out.println(temp.getDato());
			temp = temp.getNext();
		}
	}

	public int getLength() {
		return length;
	}

	public SimpleNode<T> getLast() {
		return this.last;
	}

	public void erase() {
		this.last = null;
		this.length = 0;
	}

	public T find(int index) {
		SimpleNode<T> temp = last;
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
}
