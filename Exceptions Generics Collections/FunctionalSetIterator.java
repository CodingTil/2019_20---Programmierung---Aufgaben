import java.util.*;

public class FunctionalSetIterator<E> implements Iterator<E> {
	
	private SimpleFunctionalSet<E> current;
	
	private E recentElem;
	
	private boolean removable;
	
	private final FunctionalSet<E> set;
	
	private final Set<Object> used;
	
	public FunctionalSetIterator (FunctionalSet<E> functionalSet, SimpleFunctionalSet<E> head) {
		this.current = head;
		this.recentElem = null;
		this.removable = false;
		this.set = functionalSet;
		this.used = new FunctionalSet<Object>();
		this.forwardToNextUnusedSet();
	}
	
	@Override
	public boolean hasNext() {
		return !(this.current instanceof EmptySet);
	}
	
	@Override
	public E next() {
		if(this.hasNext()) {
			E elem = ((AddSet<E>) this.current).getElement();
			this.used.add(elem);
			this.recentElem = elem;
			this.removable = true;
			this.current = this.current.getRemainingSet();
			this.forwardToNextUnusedSet();
			return elem;
		}else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public void remove() {
		if(this.removable) {
			this.set.remove(this.recentElem);
			this.removable = false;
		}else {
			throw new IllegalStateException("The next method has not been called before this remove operation!");
		}
	}
	
	private void forwardToNextUnusedSet() {
		boolean loop = true;
		while(loop) {
			loop = false;
			while(this.current instanceof RemoveSet) {
				this.used.add(((RemoveSet<E>) this.current).getObject());
				this.current = this.current.getRemainingSet();
			}
			if(this.current instanceof AddSet && this.used.contains(((AddSet<E>) this.current).getElement())) {
				loop = true;
				this.current = this.current.getRemainingSet();
			}
		}
	}
	
}







