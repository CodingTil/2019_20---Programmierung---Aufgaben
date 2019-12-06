package weihnachtsmarkt.staende;

import io.SimpleIO;
import zufall.Zufall;

public abstract class Stand {
    private String verkaeufer;
    private int besucherProStunde;
    
    public Stand() {
        this.verkaeufer = Zufall.name();
        this.berechneBesucherProStunde();
    }
    
    public Stand(int bps) {
        this.verkaeufer = Zufall.name();
        this.besucherProStunde = bps;
    }
    
    public int berechneBesucherProStunde() {
        int bps = Zufall.zahl(101);
        this.besucherProStunde = bps;
        return bps;
    }
    
    public abstract double einzelverkauf();
    
    public int getBesucherProStunde() {
        return this.besucherProStunde;
    }
    
    public void setBesucherProStunde(int bps) {
        this.besucherProStunde = bps;
    }
    
    public String toString() {
        return "Verkaeufer: " + this.verkaeufer + System.lineSeparator() +
                "Besucher pro Stunde: " + this.besucherProStunde +
                System.lineSeparator();
    }
    
    public void verkaufe() {
        System.out.println("Guten Tag!");
        double preis = 0;
        boolean verkaufe = true;
        while (verkaufe) {
            preis += this.einzelverkauf();
            verkaufe = SimpleIO.getBoolean("Darf es sonst noch etwas sein?");
            System.out.println("Darf es sonst noch etwas sein?");
            System.out.println(verkaufe);
        }
        System.out.println(preis + " Euro, bitte.");
    }
}
