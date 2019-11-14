/**
 * Ein Objekt der Klasse Rectangle repräsentiert ein Rechteck.
 */
public class Rectangle {
	
	/*
	 * Koordinaten des linken oberen Punktes, Breite und Höhe
	 */
	private int x, y, width, height;
	
	/**
	 * Erstellt ein neues Objekt der Klasse Rectangle
	 * @param xInput X-Koordinate
	 * @param yInput Y-Koordinate
	 * @param widthInput Breite
	 * @param heightInput Höhe
	 */
	public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {
		setX(xInput);
		setY(yInput);
		setWidth(widthInput);
		setHeight(heightInput);
	}
	
	/**
	 * Erstellt ein neues Objekt der Klasse Rectangle
	 * @param xInput X-Koordinate
	 * @param yInput Y-Koordinate
	 * @param sidelegthInput Länge aller Seiten (Breite und Höhe)
	 */
	public Rectangle(int xInput, int yInput, Integer sidelegthInput) {
		this(xInput, yInput, sidelegthInput.intValue(), sidelegthInput.intValue());
	}
	
	/**
	 * Kopiert das aktuelle Objekt
	 * @return Das kopierte Objekt
	 */
	public Rectangle copy() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Gibt den Wert der X-Koordinate
	 * @return X-Koordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt den Wert der Y-Koordinate
	 * @return Y-Koordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gibt den Wert der Breite
	 * @return Breite
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gibt den Wert der Höhe
	 * @return Höhe
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Setze den Wert der X-Koordinate neu
	 * @param x Neuer Wert der X-Koordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setze den Wert der Y-Koordinate neu
	 * @param y Neuer Wert der Y-Koordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Setze den Wert der Breite neu
	 * @param width Neuer Wert der Breite
	 */
	public void setWidth(int width) {
		if(x < 0) {
			Utils.error("Die Breite darf nicht negativ sein!");
		}else {
			this.width = width;
		}
	}
	
	/**
	 * Setze den Wert der X-Koordinate neu
	 * @param height Neuer Wert der Höhe
	 */
	public void setHeight(int height) {
		if(x < 0) {
			Utils.error("Die Höhe darf nicht negativ sein!");
		}else {
			this.height = height;
		}
	}
	
	/**
	 * Berechnet das kleinstmögliche Rechteck, welches eine gegebene Menge an Rechtecken einschließt
	 * @param rectangles Menge an Rechtecken, welche eingeschloßen werden sollen
	 * @return Das Rechteck, welches alle anderen Rechtecke einschließt
	 */
	public static Rectangle union(Rectangle... rectangles) {
		if(rectangles == null || rectangles.length == 0) return null; 
		
		Rectangle rectangle = rectangles[0];
		for(int i = 1; i < rectangles.length; i++) {
			rectangle = union(rectangle, rectangles[i]);
		}
		
		return rectangle;
	}
	

	/*
	 * Berechnet das kleinstmögliche Rechteck, welches 2 andere einschließt
	 */
	private static Rectangle union(Rectangle r1, Rectangle r2) {
		int x, y, width, height;
		
		x = Utils.min(r1.getX(), r2.getX());
		y = Utils.min(r1.getY(), r2.getY());
		width = Utils.max(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth()) - x;
		height = Utils.max(r1.getY() + r1.getHeight(), r2.getY() + r2.getHeight()) - y;
		
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Berechnet Schnittrechteck, welches eine gegebene Menge an Rechtecken schneidet
	 * @param rectangles Menge an Rechtecken, welche geschnitten werden sollen
	 * @return Das Schnittrechteck, welche alle anderen schneidet
	 */
	public static Rectangle intersection(Rectangle... rectangles) {
		if(rectangles == null || rectangles.length == 0) return null; 
		
		Rectangle rectangle = rectangles[0];
		for(int i = 0; i < rectangles.length; i++) {
			rectangle = intersection(rectangle, rectangles[i]);
		}
		
		return rectangle;
	}
	
	/*
	 * Berechnet das Schnittrechteck zweier Rechtecke
	 */
	private static Rectangle intersection(Rectangle r1, Rectangle r2) {
		int x, y, width, height;
		
		x = Utils.max(r1.getX(), r2.getX());
		y = Utils.max(r1.getY(), r2.getY());
		width = Utils.min(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth()) - x;
		height = Utils.min(r1.getY() + r1.getHeight(), r2.getY() + r2.getHeight()) - y;
				
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Bestimmt die Art des Rechtecks und gibt diese zurück
	 * @return Art des Rechtecks
	 */
	public RectangleSpecies determineSpecies() {
		if(getWidth() == 0 || getHeight() == 0) {
			if(getWidth() != 0) {
				return RectangleSpecies.HLINE;
			}
			if(getHeight() != 0) {
				return RectangleSpecies.VLINE;
			}
			return RectangleSpecies.POINT;
		}else {
			if(getWidth() == getHeight()) {
				if(getWidth() == 1) {
					return RectangleSpecies.PIXEL;
				}
				return RectangleSpecies.SQUARE;
			}
			return RectangleSpecies.OTHER;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "(" + (getX() + getWidth()) + "|" + (getY() - getHeight()) + "),(" + (getX() + getWidth()) + "|" + getY() + "),(" + getX() + "|" + getY() + "),(" + getX() + "|" + (getY() - getHeight()) + ")";
	}
	
}
