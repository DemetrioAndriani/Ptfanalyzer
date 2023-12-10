package it.andrianid.ptfanalyzer.service.dto;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class StatisticheAllaDataDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private StatisticheDto statistiche;

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StatisticheDto getStatistiche() {
		return statistiche;
	}

	public void setStatistiche(StatisticheDto statistiche) {
		this.statistiche = statistiche;
	}
	

}
