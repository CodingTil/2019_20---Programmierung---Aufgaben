import java.lang.Math;

/**
 * Program for drawing a Pythagoras tree.
 */
public class Pythagoras {

    /*public static void main(String[] args) {
        Canvas c = new Canvas();
        c.square(50);
        int n = 63;
        for (int i = 0; i < n; ++i) {
            c.move(50,50);
            c.rotate(360/n);
            c.square(50);
        }
    }*/

    /**
     * @param args Contains the parameters for the drawing. The first 
     *             (mandatory) is the recursion level. All further parameters 
     *             are optional and have default values in case they are not 
     *             given. Second is the base length of the first square, third 
     *             and fourth are the angles of the left and right subtrees, 
     *             respectively, and fifth is the length at which to switch 
     *             colors from brown to green. Default values are 100, 45, 45, 
     *             and 10. The two angles must not sum up to more than 120 and 
     *             each parameter must be positive. Moreover, both angles must 
     *             be less than 90.
     */
    public static void main(String[] args) {
        int level;
        int length = 100;
        int leftAngle = 45;
        int rightAngle = 45;
        int switchLength = 10;
        switch (args.length) {
            case 5:
                switchLength = Integer.parseInt(args[4]);
                // fall-through
            case 4:
                rightAngle = Integer.parseInt(args[3]);
                // fall-through
            case 3:
                leftAngle = Integer.parseInt(args[2]);
                // fall-through
            case 2:
                length = Integer.parseInt(args[1]);
                // fall-through
            case 1:
                level = Integer.parseInt(args[0]);
                break;
            default:
                System.out.println(
                    "Es muessen zwischen 1 und 5 Parameter angegeben werden "
                    + "(Level, Basislaenge, linker Winkel, rechter Winkel, "
                    + "Laenge fuer Farbwechsel)!"
                );
                return;
        }
        if (level < 1) {
            System.out.println("Das Rekursionslevel muss positiv sein!");
            return;
        }
        if (length < 1) {
            System.out.println("Die Basislaenge muss positiv sein!");
            return;
        }
        if (leftAngle < 1) {
            System.out.println("Der linke Winkel muss positiv sein!");
            return;
        }
        if (rightAngle < 1) {
            System.out.println("Der rechte Winkel muss positiv sein!");
            return;
        }
        if (leftAngle >= 90) {
            System.out.println("Der linke Winkel muss kleiner als 90 sein!");
            return;
        }
        if (rightAngle >= 90) {
            System.out.println("Der rechte Winkel muss kleiner als 90 sein!");
            return;
        }
        if (leftAngle + rightAngle > 120) {
            System.out.println(
                "Die beiden Winkel duerfen zusammen nicht mehr als 120 ergeben!"
            );
            return;
        }
        Canvas c = new Canvas();
        Pythagoras.paintPythagorasTree(
            c,
            level,
            length,
            leftAngle,
            rightAngle,
            switchLength
        );
        c.refresh();
    }

    /**
	 * Draws a pythagoras tree fractal on the specified canvas with the specified recursion level, angles, base length and color switch length, which changes the color, whenever the base length is smaller that it
     * @param c The canvas to draw the tree on.
	 * @param level The current recursion level.
	 * @param length The current base length.
	 * @param leftAngle The global angle, which builds up the next left branch
	 * @param rightAngle The global angke, which builds up the next right branch
	 * @param switchLength The length threshold, where the color changes from brown (branches) to green (leaves)
     */
    private static void paintPythagorasTree(
        Canvas c,
        int level,
        double length,
        int leftAngle,
        int rightAngle,
        int switchLength
    ) {
		if(level <= 0 || leftAngle <= 0 || rightAngle <= 0 || leftAngle + rightAngle > 120) return;
		
		if(length > switchLength) {
			c.chooseColor(Canvas.BROWN);
		}else {
			c.chooseColor(Canvas.GREEN);
		}
		
		c.square(length);
		
		double move_x, move_y;
		double new_length;
		
		//move to upper left corner
		c.move(-length / 2, -length / 2);
		
		//upper left corner
		new_length = calculateLength(length, leftAngle, rightAngle);
		c.rotate(-leftAngle);
		c.move(new_length / 2, -new_length / 2);	
		Pythagoras.paintPythagorasTree(c, level - 1, new_length, leftAngle, rightAngle, switchLength);
		c.move(- new_length / 2, new_length / 2);
		c.rotate(leftAngle);
		
		//move to upper right corner
		c.move(length, 0);
		
		//upper right tree
		new_length = calculateLength(length, rightAngle, leftAngle);
		c.rotate(rightAngle);
		c.move(- new_length / 2, - new_length / 2);
		Pythagoras.paintPythagorasTree(c, level - 1, new_length, leftAngle, rightAngle, switchLength);
		c.move(new_length / 2, new_length / 2);
		c.rotate(-rightAngle);
		
		//move back to center
		c.move(-length / 2, length / 2);
	}
	
	/**
	 *
	 *
	 *
	 */
	private static double calculateLength(double length, int alpha, int beta) {
		return length * Math.sin(Math.toRadians(beta)) / Math.sin(Math.toRadians(180 - alpha - beta));
	}

}
