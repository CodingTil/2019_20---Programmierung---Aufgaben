package weihnachtsmarkt.staende;

import zufall.Zufall;

public class Suesswarenstand extends Lebensmittelstand implements Verschiebbar {
    private String suesswarenart;
    
    public Suesswarenstand() {
        super();
        this.suesswarenart = Zufall.suessware();
    }

    public void verschiebe(int i) {
        int bps = this.berechneBesucherProStunde();
        System.out.println("Stand " + i + " wurde verschoben und wird jetzt von " + bps +
                " Passanten pro Stunde besucht.");
    }
    
    public String toString() {
        return "Suesswarenstand (" + suesswarenart + "):" +
                System.lineSeparator() + super.toString();
    }
}
