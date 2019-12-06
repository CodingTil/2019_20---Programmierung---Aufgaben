package weihnachtsmarkt;

import io.SimpleIO;
import weihnachtsmarkt.staende.*;
import zufall.Zufall;

public class Weihnachtsmarkt {
    private Stand[] staende;
    
    public Weihnachtsmarkt(int anzahlStaende) {
        this.staende = new Stand[anzahlStaende];
        for (int i = 0; i < this.staende.length; i++) {
            switch (Zufall.zahl(4)) {
            case 0:
                staende[i] = new Weihnachtsartikelstand();
                break;
            case 1:
                staende[i] = new Suesswarenstand();
                break;
            case 2:
                staende[i] = new Gluehweinstand();
                break;
            case 3:
            default:
                staende[i] = new Flammkuchenstand();
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        Weihnachtsmarkt markt = new Weihnachtsmarkt(5);
        boolean ende = false;
        while (!ende) {
            System.out.println("Der Weihnachtsmarkt besteht aus folgenden Staenden:" +
                               System.lineSeparator());
            for (int i = 0; i < markt.staende.length; i++) {
                System.out.println(i + ": " + markt.staende[i].toString());
            }
            int besuchsstand = SimpleIO.getInt("Welchen Stand moechten Sie besuchen?");
            System.out.println("Welchen Stand moechten Sie besuchen?");
            System.out.println(besuchsstand);
            markt.staende[besuchsstand].verkaufe();
            for (int i = 0; i < markt.staende.length; i++) {
                if (markt.staende[i] instanceof Verschiebbar) {
                    if (markt.staende[i].getBesucherProStunde() < 30) {
                        ((Verschiebbar) markt.staende[i]).verschiebe(i);
                    }
                }
            }
            ende = SimpleIO.getBoolean("Moechten Sie den Weihnachtsmarkt verlassen?");
            System.out.println("Moechten Sie den Weihnachtsmarkt verlassen?");
            System.out.println(ende);
        }
    }
}
