package it.andrianid.ptfanalyzer.service.model;

import java.util.Arrays;
import java.util.List;

public class ScenariFuturi { // giorni(5 piloni)
	private List<StatisticaAllaData>[] statisticaAllaData;

	public List<StatisticaAllaData>[] getStatisticaAllaData() {
		return statisticaAllaData;
	}

	public void setStatisticaAllaData(List<StatisticaAllaData>[] statisticaAllaData) {
		this.statisticaAllaData = statisticaAllaData;
	}

	public ScenariFuturi(List<StatisticaAllaData>[] statisticaAllaDatas) {
		super();
		this.statisticaAllaData = statisticaAllaDatas;
	}

	public ScenariFuturi(StatisticaAllaData[] array) {
		
	}

	@Override
	public String toString() {
		return "ScenariFuturi [statisticaAllaData=" + Arrays.toString(statisticaAllaData) + "]";
	}

}