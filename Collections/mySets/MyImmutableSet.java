package mySets;

import java.util.Collection;

import java.lang.UnsupportedOperationException;

class MyImmutableSet<T> extends MyAbstractSet<T> implements MyMinimalSet<T> {

	MyImmutableSet(MySetElement<T> head) {
		super(head);
	}

	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}

	@Override
	public MySetIterator<T> iterator() {
		return super.iterator();
	}

	@Override
	public void addAllTo(Collection<T> col) throws UnmodifiableCollectionException {
		MySetIterator<T> iterator = iterator();
		while(iterator.hasNext()) {
			try{
				col.add(iterator.next());
			}catch (UnsupportedOperationException exp){
				throw new UnmodifiableCollectionException("Zur Collection kann nichts hinzugefügt werden.");
			}
		}
	}

	@Override
	public boolean add(T t) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public boolean remove(Object o) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

	@Override
	public void clear() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Nicht implementiert.");
	}

}
