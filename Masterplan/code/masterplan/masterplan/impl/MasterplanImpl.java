package masterplan.impl;

import masterplan.Masterplan;
import masterplan.Lehrveranstaltung;

public class MasterplanImpl implements Masterplan {

	private Lehrveranstaltung[][] lehrveranstaltungen;

	public MasterplanImpl(Lehrveranstaltung[][] lehrveranstaltungen) throws IllegalArgumentException {
		if(lehrveranstaltungen == null) {
			throw new IllegalArgumentException("Die angegebenen Lehrveranstaltungen dürfen nicht null sein!");
		}
		this.lehrveranstaltungen = lehrveranstaltungen;
	}

	@Override
	public int getNumberOfSemesters() {
		return lehrveranstaltungen.length;
	}

	@Override
	public int getNumberOfLehrveranstaltungen(int semester) throws IllegalArgumentException {
		if(lehrveranstaltungen.length <= semester) {
			throw new IllegalArgumentException("Es gibt nur " + lehrveranstaltungen.length + " Semester. Ungültige Eingabe: " + semester);
		}
		if(semester < 0) {
			throw new IllegalArgumentException("Das angegebene Semester darf nicht negativ sein. Ungültige Eingabe: " + semester);
		}

		return lehrveranstaltungen[semester].length;
	}

	@Override
	public Lehrveranstaltung getLehrveranstaltung(int semester, int lehrveranstaltung) throws IllegalArgumentException {
		if(lehrveranstaltungen.length <= semester) {
			throw new IllegalArgumentException("Es gibt nur " + lehrveranstaltungen.length + " Semester. Ungültige Eingabe: " + semester);
		}
		if(semester < 0) {
			throw new IllegalArgumentException("Das angegebene Semester darf nicht negativ sein. Ungültige Eingabe: " + semester);
		}

		if(lehrveranstaltungen[semester].length <= lehrveranstaltung) {
			throw new IllegalArgumentException("Es gibt nur " + lehrveranstaltungen[semester].length + " Lehrveranstaltung in Semester " + (semester + 1) + ". Ungültige Eingabe: " + lehrveranstaltung);
		}
		if(lehrveranstaltung < 0) {
			throw new IllegalArgumentException("Die angegebene Lehrveranstaltung darf nicht negativ sein. Ungültige Eingabe: " + lehrveranstaltung);
		}

		return lehrveranstaltungen[semester][lehrveranstaltung];
	}

}
