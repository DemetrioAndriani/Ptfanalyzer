package it.andrianid.ptfanalyzer.service.model;

import java.io.Serializable;

public class Posizione implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codTitolo;
	private Double controvalore;

	
	public String getCodTitolo() {
		return codTitolo;
	}
	public void setCodTitolo(String codTitolo) {
		this.codTitolo = codTitolo;
	}
	public Double getControvalore() {
		return controvalore;
	}
	public void setControvalore(Double controvalore) {
		this.controvalore = controvalore;
	}

}
