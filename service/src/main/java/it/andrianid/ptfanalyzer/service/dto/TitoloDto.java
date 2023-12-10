package it.andrianid.ptfanalyzer.service.dto;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class TitoloDto {
	private Date date;
	private int close;

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}

	public TitoloDto(Date date, int close) {
		this.date = date;
		this.close = close;
	}

	@Override
	public String toString() {
        DecimalFormat df = new DecimalFormat("0.##");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String formattedDate = sdf.format(date);        
        String formattedClose = df.format(close);
        
		return "[date=" + formattedDate + ", close=" + formattedClose + "]";
	}
}

