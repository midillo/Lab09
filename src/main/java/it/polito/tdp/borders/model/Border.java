package it.polito.tdp.borders.model;

public class Border {
	
	private int dyad;
	private int codiceNazione1;
	private String idNazione1;
	private int codiceNazione2;
	private String idNazione2;
	private int year;
	private int conttype;
	private float version;
	
	public Border(int dyad, int codiceNazione1, String idNazione1, int codiceNazione2, String idNazione2, int year,
			int conttype, float version) {
		super();
		this.dyad = dyad;
		this.codiceNazione1 = codiceNazione1;
		this.idNazione1 = idNazione1;
		this.codiceNazione2 = codiceNazione2;
		this.idNazione2 = idNazione2;
		this.year = year;
		this.conttype = conttype;
		this.version = version;
	}

	public int getDyad() {
		return dyad;
	}

	public void setDyad(int dyad) {
		this.dyad = dyad;
	}

	public int getCodiceNazione1() {
		return codiceNazione1;
	}

	public void setCodiceNazione1(int codiceNazione1) {
		this.codiceNazione1 = codiceNazione1;
	}

	public String getIdNazione1() {
		return idNazione1;
	}

	public void setIdNazione1(String idNazione1) {
		this.idNazione1 = idNazione1;
	}

	public int getCodiceNazione2() {
		return codiceNazione2;
	}

	public void setCodiceNazione2(int codiceNazione2) {
		this.codiceNazione2 = codiceNazione2;
	}

	public String getIdNazione2() {
		return idNazione2;
	}

	public void setIdNazione2(String idNazione2) {
		this.idNazione2 = idNazione2;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getConttype() {
		return conttype;
	}

	public void setConttype(int conttype) {
		this.conttype = conttype;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codiceNazione1;
		result = prime * result + codiceNazione2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (codiceNazione1 != other.codiceNazione1)
			return false;
		if (codiceNazione2 != other.codiceNazione2)
			return false;
		return true;
	}
}
