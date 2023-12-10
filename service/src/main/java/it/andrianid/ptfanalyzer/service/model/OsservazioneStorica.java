package it.andrianid.ptfanalyzer.service.model;

import java.io.Serializable;
import java.util.Date;

public class OsservazioneStorica implements Serializable{ 
    private static final long serialVersionUID = 4697034891565539922L;
	private Date date;
    private double open;
    private double high;
    private double low;
	private int close;
	private double adjClose;
	private long volume;
	private double rend;
	private double rendPerc;
	

    
    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public int getClose() {
		return close;
	}
	public void setClose(int close) {
		this.close = close;
	}
	public double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
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
	public OsservazioneStorica(Date date, double open, double high, double low, int close, double adjClose, long volume, double rend, double rendPerc) {
		super();
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
		this.volume = volume;
		this.rend = rend;
		this.rendPerc = rendPerc;
    }
    
    @Override
	public String toString() {
		return "Stock [date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", adjClose=" + adjClose + ", volume=" + volume + ", rend=" + rend + ", rendPerc=" + rendPerc + "]";
	}
    
	
}

    
    

