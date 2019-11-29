public class Polizist extends Buerger {
	public Polizist(String name) {
		super(name);
	}
	
	@Override
	public void aktion(Buerger[] buerger) {
		System.out.println("Polizist " + getName() + " geht auf Verbrecherjagt.");
		for(int i = 0; i < buerger.length; i++) {
			Buerger b = buerger[i];
			if(b.hatDiebesgut()) {
				System.out.println("Polizist " + getName() + " entlarvt Dieb " + b + ".");
				buerger[i] = new Gefangener(b.getName());
				System.out.println("Dieb " + b + " wurde eingesperrt.");
			}
		}
	}
}