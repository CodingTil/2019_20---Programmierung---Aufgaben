public class Launcher {

	public static void main(String[] args) {
		Operation[] operations = new Operation[5];
		operations[0] = new AddTextAtOperation(0, "Hello Aachen!");
		operations[1] = new ReplaceTextSectionOperation(6, 12, "World");
		operations[2] = new UndoOperation();
		operations[3] = new ReplaceTextSectionOperation(0, 5, "Goodbye");
		operations[4] = new RemoveTextAtOperation(14, 15);

		TextDocument textDocument = new TextDocument("");

		for(int i = 0; i < operations.length; i++) {
			System.out.println(textDocument.getContent());
			System.out.println(operations[i].getDescription());
			textDocument = operations[i].apply(textDocument);
		}

		System.out.println(textDocument.getContent());
	}

}
