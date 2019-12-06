package weihnachtsmarkt.staende;

import io.SimpleIO;
import zufall.Zufall;

public class Lebensmittelstand extends Stand {
    private double preisPro100g;
    
    public Lebensmittelstand() {
        super();
        this.preisPro100g = (Zufall.zahl(300) + 1) / 100.0;
    }
    
    public double einzelverkauf() {
        int menge = SimpleIO.getInt("Wie viel Gramm moechten Sie?");
        System.out.println("Wie viel Gramm moechten Sie?");
        System.out.println(menge);
        System.out.println(menge + " Gramm fuer Sie. Lassen Sie es sich schmecken!");
        return menge/100.0 * preisPro100g;
    }
    
    public String toString() {
        return "Preis pro 100g: " + this.preisPro100g + " Euro" +
                System.lineSeparator() + super.toString();
    }

}
