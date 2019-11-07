public class Main {
  public static void main(String[] args) {
	Pilz steinpilz = new Pilz();
	steinpilz.name = "Steinpilz";

	Pilz champignon =  new Pilz();
	champignon.name = "Champignon";

	Pilz pfifferling =  new Pilz();
	pfifferling.name = "Pfifferling";

	Mensch bonnie = new Mensch();
	bonnie.name = "Bonnie";
	bonnie.korb = new Pilz[3];

	Mensch clyde = new Mensch();
	clyde.name = "Clyde";
	clyde.korb = new Pilz[4];

	Pilz[] wald = new Pilz[] {steinpilz, champignon, champignon, pfifferling, steinpilz, pfifferling, champignon};
	
	for(Pilz pilz : wald) {
		if(bonnie.hatPlatz()) {
			bonnie.korb[bonnie.anzahl++] = pilz;
		}else if(clyde.hatPlatz()) {
			clyde.korb[clyde.anzahl++] = pilz;
		}
		bonnie.ausgabe();
		clyde.ausgabe();
		System.out.println("---\n");
	}
  }
}
