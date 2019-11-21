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
	 * Kopiert das Übergebene Rechteck
	 * @param toCopy Das zu kopierende Rechteck
	 * @return Das kopierte Rechteck
	 */
	public static Rectangle copy(Rectangle toCopy) {
		if(toCopy == null) {
			Utils.error("Das zu kopierende Rechteck ist nicht initailisiert");
			return null;
		}else {
			return new Rectangle(toCopy.getX(), toCopy.getY(), toCopy.getWidth(), toCopy.getHeight());
		}
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
	public static Rectangle union(Rectangle... rectangles) { //Statisch, da diese Methode ein komplett neues Rechteck erstellt, was von mehreren anderen abhängig ist (Parameter) und nicht nur einem.
		if(rectangles == null || rectangles.length == 0) return null; 
		
		Rectangle rectangle = Rectangle.copy(rectangles[0]);
		for(int i = 1; i < rectangles.length; i++) {
			rectangle = getUnion(rectangle, rectangles[i]);
		}
		
		return rectangle;
	}
	
	/*
	 * Berechnet das kleinstmögliche Rechteck, welches 2 andere einschließt
	 */
	private static Rectangle getUnion(Rectangle r1, Rectangle r2) {
		if(r1 == null || r2 == null) return null;
		
		int x, y, x_width, y_height;
		
		x = Utils.min(r1.getX(), r2.getX());
		y = Utils.max(r1.getY(), r2.getY());
		x_width = Utils.max(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth());
		y_height = Utils.min(r1.getY() - r1.getHeight(), r2.getY() - r2.getHeight());
		
		return new Rectangle(x, y, x_width - x, y - y_height);
	}
	
	/**
	 * Berechnet Schnittrechteck, welches eine gegebene Menge an Rechtecken schneidet
	 * @param rectangles Menge an Rechtecken, welche geschnitten werden sollen
	 * @return Das Schnittrechteck, welche alle anderen schneidet
	 */
	public static Rectangle intersection(Rectangle... rectangles) { //Statisch, da diese Methode ein komplett neues Rechteck erstellt, was von mehreren anderen abhängig ist (Parameter) und nicht nur einem.
		if(rectangles == null || rectangles.length == 0) return null; 
		
		Rectangle rectangle = rectangles[0];
		for(int i = 0; i < rectangles.length; i++) {
			rectangle = getIntersection(rectangle, rectangles[i]);
		}
		
		return rectangle;
	}
	
	/*
	 * Berechnet das Schnittrechteck zweier Rechtecke
	 */
	private static Rectangle getIntersection(Rectangle r1, Rectangle r2) {
		if(r1 == null || r2 == null) return null;
		
		int x, y, x_width, y_height;
		
		x = Utils.max(r1.getX(), r2.getX());
		y = Utils.min(r1.getY(), r2.getY());
		x_width = Utils.min(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth());
		y_height = Utils.max(r1.getY() - r1.getHeight(), r2.getY() - r2.getHeight());		
				
		return new Rectangle(x, y, x_width - x, y - y_height);
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
	 * Überschreibt die toString()-Methode von Object.class
	 * Methode, die eine schriftliche Darstellung des Rechtecks erzeugt in Form einer Auflistung aller Eck-Koordinaten
	 */
	@Override
	public String toString() {
		return "(" + (getX() + getWidth()) + "|" + (getY() - getHeight()) + "),(" + (getX() + getWidth()) + "|" + getY() + "),(" + getX() + "|" + getY() + "),(" + getX() + "|" + (getY() - getHeight()) + ")";
	}
	
}
