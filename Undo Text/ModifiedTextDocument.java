public class ModifiedTextDocument extends TextDocument {

	TextDocument oldTextDocument;

	public ModifiedTextDocument(String content, TextDocument oldTextDocument) {
		super(content);
		this.oldTextDocument = oldTextDocument;
	}

	@Override
	public TextDocument undo() {
		return oldTextDocument;
	}

}
