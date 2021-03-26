package it.polito.tdp.corsi.model;

public class Iscrizione {

	private String codins;
	private Integer totiscritti;
	
	public Iscrizione(String codins, Integer totiscritti) {
		
		this.codins = codins;
		this.totiscritti = totiscritti;
		
	}
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public Integer getMatricola() {
		return totiscritti;
	}
	public void setMatricola(Integer totiscritti) {
		this.totiscritti = totiscritti;
	}
	@Override
	public String toString() {
		return codins + " " + totiscritti;
	}
	
	
	
}
