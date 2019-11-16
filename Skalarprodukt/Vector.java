public class Vector {
	private static final String EINGABE_AUFFORDERUNG_DIMENSION = "Geben Sie die Dimension der Vektoren ein:";
	private static final String EINGABE_AUFFORDERUNG_COMPONENTEN = "Geben Sie die Komponenenten des [vector] Vektors ein:";
	private static final String EINGABE_AUFFORDERUNG_COMPONENTE = "Geben Sie die [n]-te Komponente ein:";
	private static final String AUSGABE_SKALARPRODUKT = "Das Skalarprodukt der beiden Vektoren ist: [sp]";
	
	private double[] vector;
	
	public void setVector(double[] vector) {
		this.vector = vector;
	}
	
	public double[] getVector() {
		return vector;
	}
	
	public double scalarproduct(Vector q) {
		if(getVector().length != q.getVector().length) {
			SimpleIO.output("Vectoren ungleich groß!", "ERROR");
			return 0;
		}
		
		double scalarproduct = 0d;
		for(int i = 0; i < getVector().length - 1; i++) {
			scalarproduct += getVector()[i] * q.getVector()[i];
		}
		return scalarproduct;
	}
	
	public void readComponentsFromUserInput() {
		for(int i = 0; i < getVector().length; i++) {
			getVector()[i] = SimpleIO.getDouble(EINGABE_AUFFORDERUNG_COMPONENTE.replace("[n]", (i + 1)  + ""));
		}
	}

	public static Vector newWithDimension(int n) {
		Vector vector = new Vector();
		vector.setVector(new double[n]);
		return vector;
	}
	
	public static void main(String[] args) {
		int dimension = Integer.MIN_VALUE;
		while(dimension < 0) {
			dimension = SimpleIO.getInt(EINGABE_AUFFORDERUNG_DIMENSION);
		}
		
		Vector p = Vector.newWithDimension(dimension);
		Vector q = Vector.newWithDimension(dimension);
		
		SimpleIO.output(EINGABE_AUFFORDERUNG_COMPONENTEN.replace("[vector]", "ersten"));
		p.readComponentsFromUserInput();
		
		SimpleIO.output(EINGABE_AUFFORDERUNG_COMPONENTEN.replace("[vector]", "zweiten"));
		q.readComponentsFromUserInput();
		
		double scalarproduct = p.scalarproduct(q);
		SimpleIO.output(AUSGABE_SKALARPRODUKT.replace("[sp]", scalarproduct + ""));
	}
}