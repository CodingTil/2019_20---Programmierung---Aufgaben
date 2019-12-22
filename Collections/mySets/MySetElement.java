package mySets;

class MySetElement<T> {

	private T value;
	private MySetElement<T> next;

	MySetElement(T value, MySetElement<T> next) {
		this.value = value;
		this.next = next;
	}

	T getValue() {
		return value;
	}

	MySetElement<T> getNext() {
		return next;
	}

	void setNext(MySetElement<T> next) {
		this.next = next;
	}

}
