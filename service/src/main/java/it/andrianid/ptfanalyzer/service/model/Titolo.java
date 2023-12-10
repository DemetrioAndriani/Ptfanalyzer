package it.andrianid.ptfanalyzer.service.model;

import java.util.List;

public class Titolo {
	private String codTitolo;
	private List<OsservazioneStorica> OsservazioneStorica;
	private List<ScenariFuturi> ScenariFuturi;
	
	public String getCodTitolo() {
		return codTitolo;
	}
	public void setCodTitolo(String codTitolo) {
		this.codTitolo = codTitolo;
	}
	public List<OsservazioneStorica> getOsservazioneStorica() {
		return OsservazioneStorica;
	}
	public void setOsservazioneStorica(List<OsservazioneStorica> osservazioneStorica) {
		OsservazioneStorica = osservazioneStorica;
	}
	public List<ScenariFuturi> getScenariFuturi() {
		return ScenariFuturi;
	}
	public void setScenariFuturi(List<ScenariFuturi> scenariFuturi) {
		ScenariFuturi = scenariFuturi;
	}
	
	public Titolo(String codTitolo) {
        this.codTitolo = codTitolo;
    }
	@Override
	public String toString() {
		return "Titolo [codTitolo=" + codTitolo + ", OsservazioneStorica=" + OsservazioneStorica + ", ScenariFuturi="
				+ ScenariFuturi + "]";
	}
	
	
	

	
}
