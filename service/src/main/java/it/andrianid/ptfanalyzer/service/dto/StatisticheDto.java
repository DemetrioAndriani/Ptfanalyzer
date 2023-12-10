package it.andrianid.ptfanalyzer.service.dto;

public class StatisticheDto {

	private double mediaRendimenti;
	private double deviazioneStandard;
	private double massimoRendimento;
	private double minimoRendimento;
	private double percentile99;
	private double massimoDrawdown;
	private double percentile5;
	private double percentile50;
	private double percentile95;

	public double getMediaRendimenti() {
		return mediaRendimenti;
	}

	public void setMediaRendimenti(double mediaRendimenti) {
		this.mediaRendimenti = mediaRendimenti;
	}

	public double getDeviazioneStandard() {
		return deviazioneStandard;
	}

	public void setDeviazioneStandard(double deviazioneStandard) {
		this.deviazioneStandard = deviazioneStandard;
	}

	public double getMassimoRendimento() {
		return massimoRendimento;
	}

	public void setMassimoRendimento(double massimoRendimento) {
		this.massimoRendimento = massimoRendimento;
	}

	public double getMinimoRendimento() {
		return minimoRendimento;
	}

	public void setMinimoRendimento(double minimoRendimento) {
		this.minimoRendimento = minimoRendimento;
	}

	public double getPercentile99() {
		return percentile99;
	}

	public void setPercentile99(double percentile99) {
		this.percentile99 = percentile99;
	}

	public double getMassimoDrawdown() {
		return massimoDrawdown;
	}

	public void setMassimoDrawdown(double massimoDrawdown) {
		this.massimoDrawdown = massimoDrawdown;
	}

	public double getPercentile5() {
		return percentile5;
	}

	public void setPercentile5(double percentile5) {
		this.percentile5 = percentile5;
	}

	public double getPercentile50() {
		return percentile50;
	}

	public void setPercentile50(double percentile50) {
		this.percentile50 = percentile50;
	}

	public double getPercentile95() {
		return percentile95;
	}

	public void setPercentile95(double percentile95) {
		this.percentile95 = percentile95;
	}

}
