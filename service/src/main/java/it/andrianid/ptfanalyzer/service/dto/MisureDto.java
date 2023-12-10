package it.andrianid.ptfanalyzer.service.dto;

public class MisureDto extends StatisticheDto {
    public MisureDto() {
        super();
    }

    public void setMediaRendimenti(double mediaRendimenti) {
        super.setMediaRendimenti(mediaRendimenti);
    }

    public double getDeviazioneStandard() {
        return super.getDeviazioneStandard();
    }

    public void setDeviazioneStandard(double deviazioneStandard) {
        super.setDeviazioneStandard(deviazioneStandard);
    }

    public double getMassimoRendimento() {
        return super.getMassimoRendimento();
    }

    public void setMassimoRendimento(double massimoRendimento) {
        super.setMassimoRendimento(massimoRendimento);
    }

    public double getMinimoRendimento() {
        return super.getMinimoRendimento();
    }

    public void setMinimoRendimento(double minimoRendimento) {
        super.setMinimoRendimento(minimoRendimento);
    }

    public double getPercentile99() {
        return super.getPercentile99();
    }

    public void setPercentile99(double percentile99) {
        super.setPercentile99(percentile99);
    }

    public double getMassimoDrawdown() {
        return super.getMassimoDrawdown();
    }

    public void setMassimoDrawdown(double massimoDrawdown) {
        super.setMassimoDrawdown(massimoDrawdown);
    }
}
