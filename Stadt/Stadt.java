public class Stadt {
	private Buerger[] buerger;
	
	public Stadt(int anzahl) {
		this.buerger = new Buerger[anzahl];
		for(int i = 0; i < this.buerger.length; i++) {
			String name = Zufall.name();
			Buerger b;
			switch(Zufall.zahl(4)) {
			case 0:
				b = new Dieb(name);
				break;
			case 1:
				b = new ReicherBuerger(name, Zufall.zahl(1000) +1);
				break;
			case 2:
				b = new Polizist(name);
				break;
			case 3:
			default:
				b = new Gefangener(name);
			}
			this.buerger[i] = b;
		}
	}
	
	public static void main(String[] args) {
		Stadt stadt = new Stadt(10);
		for(int i = 0; i < 10; i++) {
			int zufallsIndex = Zufall.zahl(stadt.buerger.length);
			stadt.buerger[zufallsIndex].aktion(stadt.buerger);
		}
	}
}