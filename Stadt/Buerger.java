public class Buerger {
	private String name;
	
	public Buerger(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public boolean hatDiebesgut() {
		return false;
	}
	
	public void aktion(Buerger[] buerger) {
		
	}
}