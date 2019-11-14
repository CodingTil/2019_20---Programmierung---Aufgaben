/**
 * Ein Objekt der Klasse Circle repräsentiert einen Kreis.
 */
public class Circle {

    private int x, y, radius;

    /**
     * Beschreibung der Methode newCircle(int x, int y, int radius)
     * @param x
     * @param y
     * @param radius
     * @return
     */
    public static Circle newCircle(int x, int y, int radius) {
		Circle circle = new Circle();
        circle.setX(x);
		circle.setY(y);
		circle.setRadius(radius);
		return circle;
	}

    /**
     * Beschreibung der Methode newCircle(int radius)
     * @param radius
     * @return
     */
    public static Circle newCircle(int radius) {
		Circle circle = new Circle();
        circle.setX(0);
		circle.setY(0);
		circle.setRadius(radius);
		return circle;
	}

    /**
     * Beschreibung der Methode clone()
     * @return
     */
    public Circle clone() {
        return newCircle(getX(), getY(), getRadius());
    }

    /**
     * Beschreibung der Methode getRadius()
     * @return
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Beschreibung der Methode setRadius(int radius)
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
     * Beschreibung der Methode getX()
     * @return
     */
    public int getX() {
		return x;
    }

    /**
     * Beschreibung der Methode setX(int x)
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Beschreibung der Methode getY()
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Beschreibung der Methode setY(int y)
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * Grobe Beschreibung der Methode (private, muss nicht ausführlich dokumentiert werden)
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
     * Beschreibung der Methode circumscriber(int x, int y, Circle... circles)
     * @param x
     * @param y
     * @param circles
     * @return
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
     * Beschreibung der Methode performAction()
     * @param action
     */
    public void performAction(CircleAction action) {
        switch(action) {
			case UP:
				setY(getY() + 10);
				break;
			case DOWN:
				setY(getY() - 10);
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
