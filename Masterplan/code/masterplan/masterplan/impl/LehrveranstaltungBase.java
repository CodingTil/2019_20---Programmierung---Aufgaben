package masterplan.impl;

import masterplan.Lehrveranstaltung;
import masterplan.Bereich;

public abstract class LehrveranstaltungBase implements Lehrveranstaltung {

	int creditPoints;
	String description;

	public LehrveranstaltungBase(int creditPoints, String title, String type) {
		this.creditPoints = creditPoints;
		this.description = title + " (" + type + ")";
	}

	public int getCreditPoints() {
		return creditPoints;
	}

	public String getDescription() {
		return description;
	}

}
