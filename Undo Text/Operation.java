public class Operation {

	public TextDocument apply(TextDocument current) {
		return current.noob();
	}

	public String getDescription() {
		return "does not modify the document";
	}

}
