package it.andrianid.ptfanalyzer.service.dto;

import java.io.Serializable;
import java.util.List;

import it.andrianid.ptfanalyzer.service.model.Posizione;

public class PortfolioDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Posizione> posizioni;

	public List<Posizione> getPosizioni() {
		return posizioni;
	}

	public void setPosizioni(List<Posizione> posizioni) {
		this.posizioni = posizioni;
	}

	@Override
	public String toString() {
		return "PortfolioDto [posizioni=" + posizioni + "]";
	}

	public PortfolioDto(List<Posizione> posizioni) {
		super();
		this.posizioni = posizioni;
	}

}
