package it.ruffini.progettoCorsi;

public class Corsi {

	private String titolo;
	private Integer id;
	private String sede;
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String toString() {
		String result = this.titolo;
		
		if (!this.sede.isEmpty()) {
			result = result + " [" + this.sede + "]";
		}
		
		return result;
	}
	
}
