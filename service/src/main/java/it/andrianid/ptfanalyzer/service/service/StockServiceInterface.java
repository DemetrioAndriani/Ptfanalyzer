package it.andrianid.ptfanalyzer.service.service;

import java.util.List;

import it.andrianid.ptfanalyzer.service.dto.MisureDto;
import it.andrianid.ptfanalyzer.service.dto.RendimentoAllaDataDto;
import it.andrianid.ptfanalyzer.service.dto.StatisticheDto;
import it.andrianid.ptfanalyzer.service.model.Posizione;
import it.andrianid.ptfanalyzer.service.model.StatisticaAllaData;

public interface StockServiceInterface {

	List<RendimentoAllaDataDto> extractRendimento(String codTitolo);

	List<StatisticheDto> extractStat(String codTitolo);

	List<StatisticheDto> extractStatPtf(List<Posizione> titoli);

	double calcolaRendimentoMedioPesato(List<Posizione> titoli);

	List<StatisticaAllaData> bootstrap(String codTitolo);

	double[] calcolaRendimentiCumulati(List<StatisticaAllaData> stockDtoStatistiche);

	double calcolaPercentile(double[] rendimentiCumulati, int percentuale);

	double sommaArray(double[] risultatiCumulativi, double[] percentiliGiornata);

}
