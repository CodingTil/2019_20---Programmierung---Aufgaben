package weihnachtsmarkt.staende;

import weihnachtsmarkt.artikel.Artikel;
import io.SimpleIO;
import zufall.Zufall;

public class Weihnachtsartikelstand extends Stand implements Verschiebbar {
    private Artikel[] artikel;
    
    public Weihnachtsartikelstand() {
        super(0);
        int anzahlArtikel = Zufall.zahl(20) + 1;
        this.artikel = new Artikel[anzahlArtikel];
        for (int i = 0; i < anzahlArtikel; i++) {
            this.artikel[i] = new Artikel();
        }
        this.berechneBesucherProStunde();
    }

    public int berechneBesucherProStunde() {
        int bps = 0;
        for (int i = 0; i < artikel.length; i++) {
            if (artikel[i] != null) {
                bps += Zufall.zahl(6);
            }
        }
        this.setBesucherProStunde(bps);
        return bps;
    }
    
    public double einzelverkauf() {
        double preis = 0;
        System.out.println("Unsere Artikel sind:");
        for (int i = 0; i < artikel.length; i++) {
            if (artikel[i] != null) {
                System.out.println(i + ": " + artikel[i].toString());
            } else {
                System.out.println(i + ": ausverkauft");
            }
        }
        int i = SimpleIO.getInt("Welchen Artikel moechten Sie kaufen?");
        System.out.println("Welchen Artikel moechten Sie kaufen?");
        System.out.println(i);
        if (i < artikel.length && artikel[i] != null) {
            System.out.println(artikel[i].getName() + " wird eingepackt. Viel Spass damit!");
            preis = artikel[i].getPreis();
            artikel[i] = null;
        } else {
            System.out.println("Diesen Artikel haben wir nicht!");
        }
        return preis;
    }

    public void verschiebe(int i) {
        int bps = this.berechneBesucherProStunde();
        System.out.println("Stand " + i + " wurde verschoben und wird jetzt von " + bps +
                " Passanten pro Stunde besucht.");
        int artikelIndex = Zufall.zahl(artikel.length);
        if (artikel[artikelIndex] != null) {
            System.out.println("Dabei ist Artikel " + artikelIndex + ": "
                    + artikel[artikelIndex].toString()
                    + " leider vom Stand gefallen und kaputt gegangen.");
            artikel[artikelIndex] = null;
        }
    }
    
    public String toString() {
        return "Weihnachtsartikelstand:" + System.lineSeparator() + super.toString();
    }
}
