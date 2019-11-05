public class MainClass {

	private static final String TITEL = "Zinssatzrechner";
	private static final String EINGABE_AUFFORDERUNG_STARTBETRAG = "Bitte geben Sie den Startbetrag ein.";
	private static final String EINGABE_AUFFORDERUNG_ZINSSATZ = "Bitte geben Sie den Zinssatz als Prozentwert ein.";
	private static final String EINGABE_AUFFORDERUNG_AUSWAHL = "Ziel: Berechnet die Zeit, bis ein gegebener Geldbetrag angespart wurde.\nZeit: Betechnet den Betrag, der nach einer gegebenen Zeit angespart wurde.";
	private static final String EINGABE_AUFFORDERUNG_ZIELBETRAG = "Bitte geben Sie den Zielbetrag ein.";
	private static final String EINGABE_AUFFORDERUNG_ZEITWERT = "Bitte geben Sie den Zeitwert ein.";
	private static final String AUSGABE_ZIELBETRAG = "Es dauert [JAHRE] Jahre bei einem Zinssatz von [ZINSSATZ], um von [STARTBETRAG] auf den Betrag [ZIELBETRAG] zu sparen. Nach dieser Zeit hat man [BERECHNUNG].";
	private static final String AUSGABE_ZEITWERT = "Bei einem Zinssatz von [ZINSSATZ]% hat man nach [JAHRE] Jahren von [STARTBETRAG] einen Betrag von [BERECHNUNG] angespart.";
	
	public static void main(String[] args) {
		double startbetrag = 0, zinssatz = 0;
		
		startbetrag = SimpleIO.getDouble(EINGABE_AUFFORDERUNG_STARTBETRAG);
		zinssatz = SimpleIO.getDouble(EINGABE_AUFFORDERUNG_ZINSSATZ);
		
		String auswahl = SimpleIO.getString(EINGABE_AUFFORDERUNG_AUSWAHL), ausgabe = "";
		
		if(auswahl.equals("Ziel")) {
			double zielbetrag = SimpleIO.getDouble(EINGABE_AUFFORDERUNG_ZIELBETRAG);
			double geldbetrag = startbetrag;
			int jahre = 0;
			
			while(zielbetrag > geldbetrag) {
				jahre++;
				
				geldbetrag = geldbetrag * (1 + zinssatz / 100);
			}
			
			ausgabe = AUSGABE_ZIELBETRAG;
			ausgabe = ausgabe.replace("[JAHRE]", jahre + "").replace("[ZINSSATZ]", zinssatz + "").replace("[STARTBETRAG]", startbetrag + "").replace("[ZIELBETRAG]", zielbetrag + "").replace("[BERECHNUNG]", geldbetrag + "");
		}else if(auswahl.equals("Zeit")) {
			int zeitwert = SimpleIO.getInt(EINGABE_AUFFORDERUNG_ZEITWERT);
			int jahre = 0;
			double geldbetrag = startbetrag;
			
			while(jahre < zeitwert) {
				jahre++;
				
				geldbetrag = geldbetrag * (1 + zinssatz / 100);
			}
			
			ausgabe = AUSGABE_ZEITWERT;
			ausgabe = ausgabe.replace("[ZINSSATZ]", zinssatz + "").replace("[JAHRE]", zeitwert + "").replace("[STARTBETRAG]", startbetrag + "").replace("[BERECHNUNG]", geldbetrag + "");
		}else {
			SimpleIO.output("Fehler! " + auswahl + " ist keine gültige Eingabe. Programm schließt sich...", TITEL + " | ERROR");
			return;
		}
		
		SimpleIO.output(ausgabe, TITEL);
	}

}
