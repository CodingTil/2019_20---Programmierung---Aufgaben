public class ReicherBuerger extends Buerger {
	private int reichtum;
	
	public ReicherBuerger(String name, int reichtum) {
		super(name);
		this.reichtum = reichtum;
	}
	
	public void setReichtum(int reichtum) {
		this.reichtum = reichtum;
	}
	
	public int getReichtum() {
		return reichtum;
	}
	
	@Override
	public void aktion(Buerger[] buerger) {
		int bestechungsgeld = Zufall.zahl(this.reichtum);
		System.out.println("Reicher Buerger " + getName() + " besticht einen Politiker mit " + 
					bestechungsgeld + " Euro.");
		this.reichtum -= bestechungsgeld;
	}
}