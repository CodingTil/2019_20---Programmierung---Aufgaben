/**
 * Ein Objekt der Klasse Circle repräsentiert einen Kreis.
 * @author Til Mohr
 */
public class Circle {

    private int x, y, radius;

    /**
     * Erstellen eines neuen Circle-Objekts
     * @param x
     * @param y
     * @param radius
     * @return Das neue Circle-Objekt
     */
    public static Circle newCircle(int x, int y, int radius) {
		Circle circle = new Circle();
        circle.setX(x);
		circle.setY(y);
		circle.setRadius(radius);
		return circle;
	}

    /**
     * Erstellen eines neuen Circle-Objekts
     * @param radius
     * @return Das neue Circle-Objekt
     */
    public static Circle newCircle(int radius) {
		Circle circle = new Circle();
        circle.setX(0);
		circle.setY(0);
		circle.setRadius(radius);
		return circle;
	}

    /**
     * Klont ein Circle-Objekt
     * @return Gibt das geklonte Circle-Object zurück
     */
    public Circle clone() {
        return newCircle(getX(), getY(), getRadius());
    }

    /**
	 * Bekomme den Radius
     * @return Radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Setze den Radius
     * @param radius
     */
    public void setRadius(int radius) {
        if(radius >= 0) {
			this.radius = radius;
		}else {
			Utils.error("Radius cannot be a negative value!");
		}
    }

    /**
	 * Bekomme die X-Koordinate
     * @return X-Koordinate
     */
    public int getX() {
		return x;
    }

    /**
     * Setze die X-Koordinate
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Bekomme die Y-Koordinate
     * @return Y-Koordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Setze die Y-Koordinate
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * Sagt, ob der Kreis den anderen Kreis vollständig beinhaltet.
     */
    private boolean containsOne(Circle that) {
        return Utils.dist(that.getX(), that.getY(), getX(), getY()) + that.getRadius() >= getRadius();
    }

    /**
     * Prüft, ob alle andere Kreise in diesem Kreis enthalten sind.
     * @param others
     * @return Wahrheitswert der Frage, ob alle übrigen Kreise in diesem Kreis enthalten sind.
     */
    public boolean contains(Circle... others) {
        boolean result = false;
		
		for(Circle circle : others) {
			if(containsOne(circle)) {
				result = true;
				break;
			}
		}
		
		return result;
    }

    /**
     * Erstellt einen neuen Kreis an der Stelle (x|y) und minimalem radius, sodass alle anderen Kreis enthalten sind.
     * @param x
     * @param y
     * @param circles
     * @return den Kreis (x|y), minimaler radius
     */
    public static Circle circumscriber(int x, int y, Circle... circles) {
        int radius = 0;
		Circle circle = newCircle(x, y, radius);
		do{
			circle = newCircle(x, y, radius);
			radius++;
		}while(circle.contains(circles));
		return circle;
    }

    /**
     * Represents x, y and radius as a String
     * @return String-Representation
     */
    public String toString() {
        return "(" + getX() + "|" + getY() + ")," + getRadius();
    }

    /**
     * Zur Kontrolle eines Kreises: Verschieben in x-/y-Richtung und Radiusvergrößerung/-verkleinerung
     * @param action
     */
    public void performAction(CircleAction action) {
        switch(action) {
			case UP:
				setY(getY() - 10);
				break;
			case DOWN:
				setY(getY() + 10);
				break;
			case LEFT:
				setX(getX() - 10);
				break;
			case RIGHT:
				setX(getX() + 10);
				break;
			case BIGGER:
				setRadius(getRadius() + 10);
				break;
			case SMALLER:
				setRadius(getRadius() - 10);
				break;
		}
    }
    
}
