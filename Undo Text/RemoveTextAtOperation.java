public class RemoveTextAtOperation extends Operation {

	private int beginIndex, endIndex;

	public RemoveTextAtOperation(int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	@Override
	public TextDocument apply(TextDocument current) {
		return current.removeTextAt(beginIndex, endIndex);
	}

	@Override
	public String getDescription() {
		return "removes the text section from " + beginIndex + " to " + endIndex;
	}

}
