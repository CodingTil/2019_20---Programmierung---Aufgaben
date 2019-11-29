public class TextDocument {

	private String content;

	public TextDocument(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public TextDocument undo() {
		return this;
	}

	public ModifiedTextDocument noob() {
		return new ModifiedTextDocument(content, this);
	}

	public TextDocument replaceTextSection(int beginIndex, int endIndex, String replacement) {
		if(beginIndex > endIndex || content.length() < endIndex || replacement == null) return null;

		String newContent = content.substring(0, beginIndex) + replacement + content.substring(endIndex);

		return new ModifiedTextDocument(newContent, this);
	}

	public TextDocument addTextAt(int position, String addition) {
		return replaceTextSection(position, position, addition);
	}

	public TextDocument removeTextAt(int beginIndex, int endIndex) {
		return replaceTextSection(beginIndex, endIndex, "");
	}

}
