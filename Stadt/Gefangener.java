public class Gefangener extends Dieb {
	public Gefangener(String name) {
		super(name);
	}
	
	@Override
	public void aktion(Buerger[] buerger) {
		System.out.println("Gefangener " + getName() + " aergert sich im Gefaengnis.");
	}
}