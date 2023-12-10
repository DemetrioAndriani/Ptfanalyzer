package it.andrianid.ptfanalyzer.service.model;

import java.util.Date;

public class StatisticaAllaData{ //numero di scenari futuri è n = 100
	private Date data;
    private double rendimento;
    
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getRendimento() {
		return rendimento;
	}
	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}
	@Override
	public String toString() {
		return "StatisticaAllaData [data=" + data + ", rendimento=" + rendimento + "]";
	}
	public StatisticaAllaData(Date data, double rendimento) {
		super();
		this.data = data;
		this.rendimento = rendimento;
	}

   
}
