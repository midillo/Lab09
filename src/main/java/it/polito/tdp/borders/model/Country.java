package it.polito.tdp.borders.model;

public class Country implements Comparable<Country>{

	private String idNazione;
	private int codiceNazione;
	private String Nazione;
	
	public Country(String idNazione, int codiceNazione, String nazione) {
		super();
		this.idNazione = idNazione;
		this.codiceNazione = codiceNazione;
		Nazione = nazione;
	}

	public String getIdNazione() {
		return idNazione;
	}

	public void setIdNazione(String idNazione) {
		this.idNazione = idNazione;
	}

	public int getCodiceNazione() {
		return codiceNazione;
	}

	public void setCodiceNazione(int codiceNazione) {
		this.codiceNazione = codiceNazione;
	}

	public String getNazione() {
		return Nazione;
	}

	public void setNazione(String nazione) {
		Nazione = nazione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codiceNazione;
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
		Country other = (Country) obj;
		if (codiceNazione != other.codiceNazione)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Nazione;
	}

	@Override
	public int compareTo(Country o) {
		return this.getNazione().compareTo(o.getNazione());
	}
	
	
}
