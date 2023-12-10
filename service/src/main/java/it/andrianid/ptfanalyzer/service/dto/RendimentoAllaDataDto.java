package it.andrianid.ptfanalyzer.service.dto;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class RendimentoAllaDataDto {
	private Date date;
	private int close;
	private double rend;
	private double rendPerc;

	public RendimentoAllaDataDto(Date date, int close, double rend, double rendPerc) {
		this.date = date;
		this.close = close;
		this.rend = rend;
		this.rendPerc = rendPerc;
	}

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

	public double getRend() {
		return rend;
	}

	public void setRend(double rend) {
		this.rend = rend;
	}

	public double getRendPerc() {
		return rendPerc;
	}

	public void setRendPerc(double rendPerc) {
		this.rendPerc = rendPerc;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.####");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String formattedDate = sdf.format(date);
		String formattedClose = df.format(close);
		String formattedRend = df.format(rend);
		String formattedRendPerc = df.format(rendPerc);

		return "[date=" + formattedDate + ", close= " + formattedClose + ", rend=" + formattedRend + ", rendPerc=" + formattedRendPerc
				+ "]";
	}

}
