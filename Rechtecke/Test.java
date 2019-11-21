public class Test {
	
	public static void main(String[] args) {
		System.out.println(new Rectangle(3, 5, 3, 4));
		System.out.println(Rectangle.union(new Rectangle(2, 5, 3, 3), new Rectangle(1, 4, 2, 3)));
		System.out.println(Rectangle.intersection(new Rectangle(2, 5, 3, 3), new Rectangle(1, 4, 2, 3)));
	}
	
}