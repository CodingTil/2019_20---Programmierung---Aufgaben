package masterplan.impl;

import masterplan.Bereich;

public class Seminar extends LehrveranstaltungMitBereichszuordnung {

	public Seminar(String title, Bereich bereich) {
		super(4, title, "Seminar", bereich);
	}

}
