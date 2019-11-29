public class Dieb extends Buerger {
	private int diebesgut = 0;
	
	public Dieb(String name) {
		super(name);
	}
	
	@Override
	public boolean hatDiebesgut() {
		return this.diebesgut > 0;
	}
	
	@Override
	public void aktion(Buerger[] buerger) {
		System.out.println("Dieb " + getName() + " sucht nach Diebegut.");
		for(int i = 0; i<5; i++) {
			Buerger b = buerger[Zufall.zahl(buerger.length)];
			if(b instanceof ReicherBuerger) {
				ReicherBuerger reicherBuerger = (ReicherBuerger)b;
				if(reicherBuerger.getReichtum() <= 1) {
					continue;
				}
				int geld = Zufall.zahl(reicherBuerger.getReichtum());
				this.diebesgut += geld;
				reicherBuerger.setReichtum(reicherBuerger.getReichtum() - geld);
				System.out.println("Dieb " + getName() + " klaut " + reicherBuerger.getName() + " " + geld + " Euro.");
				return;
			}
			if(b instanceof Polizist) {
				System.out.println("Dieb " + getName() + " bricht die Suche ab.");
				return;
			}
		}
	}
}