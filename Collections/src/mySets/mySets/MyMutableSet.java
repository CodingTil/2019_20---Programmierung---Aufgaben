package mySets;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

import java.lang.UnsupportedOperationException;

public class MyMutableSet<T> extends MyAbstractSet<T> implements Set<T> {

	public MyMutableSet() {
		super(null);
	}

	@Override
	public boolean add(T t) {
		if(contains(t)) return false;

		MySetElement<T> current = getHead();
		if(current == null) {
			setHead(new MySetElement<T>(t, null));
			return true;
		}else {
			while(current.getNext() != null)  current = current.getNext();
			current.setNext(new MySetElement<T>(t, null));
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		if(!contains(o)) return false;

		MySetElement<T> current = getHead();
		if(current.equals(o)) {
			setHead(current.getNext());
			return true;
		}else {
			while(!current.getNext().equals(o)) current = current.getNext();
			current.setNext(current.getNext().getNext());
			return true;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		Iterator<?> iterator = c.iterator();
		while(iterator.hasNext()) {
			result = remove(iterator.next()) | result;
		}
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean result = false;
		Iterator<? extends T> iterator = c.iterator();
		while(iterator.hasNext()) {
			result = add(iterator.next()) | result;
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public void clear() {
		setHead(null);
	}

	public MyMinimalSet<T> freezeAndClear() {
		MyImmutableSet<T> set = new MyImmutableSet<T>(getHead());
		setHead(null);
		return set;
	}

	//TESTING
	public static void main(String[] args) {
		
	}

}
