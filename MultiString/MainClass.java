public class MainClass {
	
	public static void main(String args[]) {
		int natürlicheZahl = 0;
		
		while(natürlicheZahl <= 0) {
			natürlicheZahl = SimpleIO.getInt("Gebe eine natürliche Zahl ein!");
		}
		
		String text = SimpleIO.getString("Gib einen Text ein");
		String output = "";
		
		for(int i = 0; i < natürlicheZahl; i++) {
			output += text;
		}
		
		SimpleIO.output(output);
	}
	
}
