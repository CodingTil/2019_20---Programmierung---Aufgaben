public class ReplaceTextSectionOperation extends Operation {

	private int beginIndex, endIndex;
	private String replacement;

	public ReplaceTextSectionOperation(int beginIndex, int endIndex, String replacement) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.replacement = replacement;
	}

	@Override
	public TextDocument apply(TextDocument current) {
		return current.replaceTextSection(beginIndex, endIndex, replacement);
	}

	@Override
	public String getDescription() {
		return "replaces the text section from " + beginIndex + " to " + endIndex + " by: " + replacement;
	}

}
