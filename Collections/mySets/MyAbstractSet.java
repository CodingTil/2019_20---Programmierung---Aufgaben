package mySets;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

import java.lang.Iterable;
import java.lang.UnsupportedOperationException;

abstract class MyAbstractSet<T> implements Iterable<T>, Set<T> {

	private MySetElement<T> head;

	MyAbstractSet(MySetElement<T> head) {
		this.head = head;
	}

	void setHead(MySetElement<T> head) {
		this.head = head;
	}

	MySetElement<T> getHead() {
		return head;
	}

	@Override
	public MySetIterator<T> iterator() {
		return new MySetIterator<T>(head);
	}

	@Override
	public int size() {
		int count = 0;
		MySetIterator<T> iterator = iterator();
		while(iterator.hasNext()) {
			count++;
			if(count == Integer.MAX_VALUE) break;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean contains(Object o) {
		MySetIterator<T> iterator = iterator();
		while(iterator.hasNext()) {
			if(o.equals(iterator.next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public <E> E[] toArray(E[] a) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> iterator = c.iterator();
		while(iterator.hasNext()) {
			if(!this.contains(iterator.next())) {
				return false;
			}
		}
		return true;
	}

}
