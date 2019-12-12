package masterplan.impl;

import masterplan.MasterplanBuilder;
import masterplan.SemesterBuilder;
import masterplan.Lehrveranstaltung;
import masterplan.InvalidMasterplanException;
import masterplan.Bereich;
import masterplan.Masterplan;

public class BuilderImpl implements MasterplanBuilder, SemesterBuilder {

	private Lehrveranstaltung[][] semesters;
	private int currentSemester, currentLehrverantstaltung;

	public BuilderImpl() {
		this.semesters = new Lehrveranstaltung[10][10];
		this.currentSemester = 0;
		this.currentLehrverantstaltung = 0;
	}

	public BuilderImpl beginSemester() throws InvalidMasterplanException {
		if(currentSemester >= semesters.length) {
			throw new InvalidMasterplanException("Es können nicht mehr als " + semesters.length + " Semester in Anspruch genommen werden.");
		}

		return this;
	}

	public BuilderImpl endSemester() {
		currentSemester++;
		currentLehrverantstaltung = 0;
		return this;
	}

	public Masterplan validateAndCreate() throws InvalidMasterplanException {
		//GET LENGTHS
		int semesterLength = 0;
		while(semesterLength < semesters.length && semesters[semesterLength] != null && semesters[semesterLength][0] != null) {
			semesterLength++;
		}
		int[] lehrveranstaltungenProSemester = new int[semesterLength];
		for(int s = 0; s < semesterLength; s++) {
			int lehrVeranstaltungenLength = 0;
			while(lehrVeranstaltungenLength < semesters[s].length && semesters[s][lehrVeranstaltungenLength] != null) {
				lehrVeranstaltungenLength++;
			}
			lehrveranstaltungenProSemester[s] = lehrVeranstaltungenLength;
		}

		//CREATE AND INIT ARRAY
		Lehrveranstaltung[][] newSemesters = new Lehrveranstaltung[semesterLength][];
		for(int s = 0; s < semesterLength; s++) {
			newSemesters[s] = new Lehrveranstaltung[lehrveranstaltungenProSemester[s]];
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				newSemesters[s][l] = semesters[s][l];
			}
		}

		//VALIDATE ARRAY
		int cp;
		boolean error;
		//12 CP aus TI (mindestens)
		cp = 0;
		for(int s = 0; s < semesterLength; s++) {
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				if(newSemesters[s][l] instanceof LehrveranstaltungMitBereichszuordnung) {
					LehrveranstaltungMitBereichszuordnung veranstaltung = (LehrveranstaltungMitBereichszuordnung) newSemesters[s][l];
					if(veranstaltung.getBereich() == Bereich.THEORETISCHE_INFORMATIK) cp += veranstaltung.getCreditPoints();
				}
			}
		}
		if(cp < 12) {
			throw new InvalidMasterplanException("Aus dem Bereich " + Bereich.THEORETISCHE_INFORMATIK.getDescription() + " müssen mindestens 12 Credit Points belegt worden sein. Belegte Credit Points: " + cp);
		}

		//35 CP pro Bereich (höchstens)
		int[] CPproBereich = new int[4]; //0 = TI; 1 = SK; 2 = DI; 3 = AI;
		for(int s = 0; s < semesterLength; s++) {
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				if(newSemesters[s][l] instanceof LehrveranstaltungMitBereichszuordnung) {
					LehrveranstaltungMitBereichszuordnung veranstaltung = (LehrveranstaltungMitBereichszuordnung) newSemesters[s][l];
					if(veranstaltung.getBereich() == Bereich.THEORETISCHE_INFORMATIK) CPproBereich[0] += veranstaltung.getCreditPoints();
					if(veranstaltung.getBereich() == Bereich.SOFTWARE_UND_KOMMUNIKATION) CPproBereich[1] += veranstaltung.getCreditPoints();
					if(veranstaltung.getBereich() == Bereich.DATEN_UND_INFORMATIONSMANAGEMENT) CPproBereich[2] += veranstaltung.getCreditPoints();
					if(veranstaltung.getBereich() == Bereich.ANGEWANDTE_INFORMATIK) CPproBereich[3] += veranstaltung.getCreditPoints();
				}
			}
		}
		error = false;
		for(int b = 0; b < CPproBereich.length; b++) {
			if(CPproBereich[b] > 35) error = true;
		}
		if(error) {
			String errormessage = "Pro Bereich dürfen mindestens 335 Credit Points belegt worden sein. Belegte Credit Points: ";
			for(int b = 0; b < CPproBereich.length; b++) {
				Bereich bereich;
				switch(b) {
					case 0:
						bereich = Bereich.THEORETISCHE_INFORMATIK;
						break;
					case 1:
						bereich = Bereich.SOFTWARE_UND_KOMMUNIKATION;
						break;
					case 2:
						bereich = Bereich.DATEN_UND_INFORMATIONSMANAGEMENT;
						break;
					case 3: default:
						bereich = Bereich.ANGEWANDTE_INFORMATIK;
						break;
				}
				errormessage += "[" + bereich.getDescription() + ": " + CPproBereich[b] + " CP]";
				if(b != CPproBereich.length - 1) errormessage += ", ";
			}
			throw new InvalidMasterplanException(errormessage);
		}

		//Belegte Bereiche (mindestens 3)
		boolean[] belegteBereiche = new boolean[4]; //0 = TI; 1 = SK; 2 = DI; 3 = AI;
		for(int s = 0; s < semesterLength; s++) {
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				if(newSemesters[s][l] instanceof LehrveranstaltungMitBereichszuordnung) {
					LehrveranstaltungMitBereichszuordnung veranstaltung = (LehrveranstaltungMitBereichszuordnung) newSemesters[s][l];
					if(veranstaltung.getBereich() == Bereich.THEORETISCHE_INFORMATIK) belegteBereiche[0] = true;
					if(veranstaltung.getBereich() == Bereich.SOFTWARE_UND_KOMMUNIKATION) belegteBereiche[1] = true;
					if(veranstaltung.getBereich() == Bereich.DATEN_UND_INFORMATIONSMANAGEMENT) belegteBereiche[2] = true;
					if(veranstaltung.getBereich() == Bereich.ANGEWANDTE_INFORMATIK) belegteBereiche[3] = true;
				}
			}
		}
		int count = 0;
		for(int b = 0; b < belegteBereiche.length; b++) {
			if(belegteBereiche[b]) count++;
		}
		if(count < 3) {
			String errormessage = "Es müssen Lernveranstaltungen aus midestens 3 verschiedenen Bereichen belegt worden sein. Belegte Bereiche: ";
			for(int b = 0; b < CPproBereich.length; b++) {
				Bereich bereich;
				switch(b) {
					case 0:
						bereich = Bereich.THEORETISCHE_INFORMATIK;
						break;
					case 1:
						bereich = Bereich.SOFTWARE_UND_KOMMUNIKATION;
						break;
					case 2:
						bereich = Bereich.DATEN_UND_INFORMATIONSMANAGEMENT;
						break;
					case 3: default:
						bereich = Bereich.ANGEWANDTE_INFORMATIK;
						break;
				}
				errormessage += bereich.getDescription();
				if(b != CPproBereich.length - 1) errormessage += ", ";
			}
			throw new InvalidMasterplanException(errormessage);
		}

		//18 Credit Points aus dem Anwendungsfach (genau)
		cp = 0;
		for(int s = 0; s < semesterLength; s++) {
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				if(newSemesters[s][l] instanceof Anwendungsfach) {
					cp += newSemesters[s][l].getCreditPoints();
				}
			}
		}
		if(cp != 18) {
			throw new InvalidMasterplanException("Für das Anwendungsfach müssen genau 18 Credit Points belegt worden sein. Belegte Credit Points: " + cp);
		}

		//Belegung von genau ein Seminar, ein Praktikum, ein Schwerpunktkolloquium sowie eine Masterarbeit
		int[] belegung = new int[4]; //0 = Seminar; 1 = Praktikum; 2 = Schwerpunktkolloquium; 3 = Masterarbeit
		for(int s = 0; s < semesterLength; s++) {
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				if(newSemesters[s][l] instanceof Seminar) belegung[0]++;
				if(newSemesters[s][l] instanceof Praktikum) belegung[1]++;
				if(newSemesters[s][l] instanceof Schwerpunktkolloquium) belegung[2]++;
				if(newSemesters[s][l] instanceof Masterarbeit) belegung[3]++;
			}
		}
		error = false;
		for(int b = 0; b < belegung.length; b++) {
			if(belegung[b] != 1) error = true;
		}
		if(error) {
			throw new InvalidMasterplanException("Es muss genau ein Seminar, ein Praktikum, ein Schwerpunktkolloquium sowie eine Masterarbeit belegt worden sein.");
		}

		//Insgesamt 120 Credit Points (mindestens)
		cp = 0;
		for(int s = 0; s < semesterLength; s++) {
			for(int l = 0; l < lehrveranstaltungenProSemester[s]; l++) {
				cp += newSemesters[s][l].getCreditPoints();
			}
		}
		if(cp < 120) {
			throw new InvalidMasterplanException("Insgesamt müssen mindestens 120 Credit Points belegt worden sein. Belegte Credit Points: " + cp);
		}

		//RETURN MASTERPLAN OBJECT
		return new MasterplanImpl(newSemesters);
	}

	@Override
	public SemesterBuilder anwendungsfach(int creditPoints, String title) throws InvalidMasterplanException {
		if(currentLehrverantstaltung >= semesters[currentSemester].length) {
			throw new InvalidMasterplanException("Das Semester " + currentSemester + " ist leider schon voll belegt. Man kann nicht mehr als " + semesters[currentSemester].length + " Lehrveranstaltungen pro Semester besuchen.");
		}

		semesters[currentSemester][currentLehrverantstaltung] = new Anwendungsfach(creditPoints, title);
		currentLehrverantstaltung++;
		return this;
	}

	@Override
	public SemesterBuilder masterarbeit(String title) throws InvalidMasterplanException {
		if(currentLehrverantstaltung >= semesters[currentSemester].length) {
			throw new InvalidMasterplanException("Das Semester " + currentSemester + " ist leider schon voll belegt. Man kann nicht mehr als " + semesters[currentSemester].length + " Lehrveranstaltungen pro Semester besuchen.");
		}

		semesters[currentSemester][currentLehrverantstaltung] = new Masterarbeit(title);
		currentLehrverantstaltung++;
		return this;
	}

	@Override
	public SemesterBuilder praktikum(String title) throws InvalidMasterplanException {
		if(currentLehrverantstaltung >= semesters[currentSemester].length) {
			throw new InvalidMasterplanException("Das Semester " + currentSemester + " ist leider schon voll belegt. Man kann nicht mehr als " + semesters[currentSemester].length + " Lehrveranstaltungen pro Semester besuchen.");
		}

		semesters[currentSemester][currentLehrverantstaltung] = new Praktikum(title);
		currentLehrverantstaltung++;
		return this;
	}

	@Override
	public SemesterBuilder schwerpunktkolloquium(String title) throws InvalidMasterplanException{
		if(currentLehrverantstaltung >= semesters[currentSemester].length) {
			throw new InvalidMasterplanException("Das Semester " + currentSemester + " ist leider schon voll belegt. Man kann nicht mehr als " + semesters[currentSemester].length + " Lehrveranstaltungen pro Semester besuchen.");
		}

		semesters[currentSemester][currentLehrverantstaltung] = new Schwerpunktkolloquium(title);
		currentLehrverantstaltung++;
		return this;
	}

	@Override
	public SemesterBuilder seminar(String title, Bereich bereich) throws InvalidMasterplanException{
		if(currentLehrverantstaltung >= semesters[currentSemester].length) {
			throw new InvalidMasterplanException("Das Semester " + currentSemester + " ist leider schon voll belegt. Man kann nicht mehr als " + semesters[currentSemester].length + " Lehrveranstaltungen pro Semester besuchen.");
		}

		semesters[currentSemester][currentLehrverantstaltung] = new Seminar(title, bereich);
		currentLehrverantstaltung++;
		return this;
	}

	@Override
	public SemesterBuilder wahlpflichtvorlesung(int creditPoints, String title, Bereich bereich) throws InvalidMasterplanException{
		if(currentLehrverantstaltung >= semesters[currentSemester].length) {
			throw new InvalidMasterplanException("Das Semester " + currentSemester + " ist leider schon voll belegt. Man kann nicht mehr als " + semesters[currentSemester].length + " Lehrveranstaltungen pro Semester besuchen.");
		}

		semesters[currentSemester][currentLehrverantstaltung] = new Wahlpflichtvorlesung(creditPoints, title, bereich);
		currentLehrverantstaltung++;
		return this;
	}

}
