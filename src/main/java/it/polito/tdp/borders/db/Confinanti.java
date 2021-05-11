package it.polito.tdp.borders.db;

public class Confinanti {

	private int codiceNazione1;
	private int codiceNazione2;

	public Confinanti(int codiceNazione1, int codiceNazione2) {
		super();
		this.codiceNazione1 = codiceNazione1;
		this.codiceNazione2 = codiceNazione2;
	}

	public int getCodiceNazione1() {
		return codiceNazione1;
	}
	
	public void setCodiceNazione1(int codiceNazione1) {
		this.codiceNazione1 = codiceNazione1;
	}
	
	public int getCodiceNazione2() {
		return codiceNazione2;
	}
	
	public void setCodiceNazione2(int codiceNazione2) {
		this.codiceNazione2 = codiceNazione2;
	}
}
