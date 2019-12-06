package masterplan.impl;

import masterplan.Bereich;

public class Wahlpflichtvorlesung extends LehrveranstaltungMitBereichszuordnung {

	public Wahlpflichtvorlesung(int creditPoints, String title, Bereich bereich) {
		super(creditPoints, title, "Wahlpflichtvorlesung", bereich);
	}

}
