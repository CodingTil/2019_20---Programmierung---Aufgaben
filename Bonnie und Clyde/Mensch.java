public class Mensch {
  String name;
  Pilz[] korb;
  int anzahl = 0;
  
  public boolean hatPlatz() {
	return anzahl < korb.length;
  }
  
  public void ausgabe() {
	System.out.println(name + ":");
	for(Pilz pilz : korb) {
		if(pilz != null) System.out.println(pilz.name);
	}
	System.out.print('\n');
  }
  
}
