public class MainClass {
	
	public static void main(String args[]) {
		int nat�rlicheZahl = 0;
		
		while(nat�rlicheZahl <= 0) {
			nat�rlicheZahl = SimpleIO.getInt("Gebe eine nat�rliche Zahl ein!");
		}
		
		String text = SimpleIO.getString("Gib einen Text ein");
		String output = "";
		
		for(int i = 0; i < nat�rlicheZahl; i++) {
			output += text;
		}
		
		SimpleIO.output(output);
	}
	
}
