public class Test {
	
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(3, 5, 3, 4);
		System.out.println(rectangle.toString());
		
		Rectangle r1 = new Rectangle(1, 1, 0, 1);
		System.out.println(r1.determineSpecies());
	}
	
}