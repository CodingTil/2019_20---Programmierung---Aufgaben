package mySets;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.lang.UnsupportedOperationException;

class MySetIterator<T> implements Iterator<T> {

	private MySetElement<T> current;

	MySetIterator(MySetElement<T> current) {
		this.current = current;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public T next() throws NoSuchElementException{
		if(current == null) throw new NoSuchElementException("Der Aktuelle Wert is nicht vorhanden.");
		T temp = current.getValue();
		current = current.getNext();
		return temp;
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Methode nicht implementiert.");
	}

}
