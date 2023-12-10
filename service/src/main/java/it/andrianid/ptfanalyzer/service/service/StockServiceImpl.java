package it.andrianid.ptfanalyzer.service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.andrianid.ptfanalyzer.service.dao.TitoloDao;
import it.andrianid.ptfanalyzer.service.dto.MisureDto;
import it.andrianid.ptfanalyzer.service.dto.RendimentoAllaDataDto;
import it.andrianid.ptfanalyzer.service.dto.StatisticheDto;
import it.andrianid.ptfanalyzer.service.model.OsservazioneStorica;
import it.andrianid.ptfanalyzer.service.model.Posizione;
import it.andrianid.ptfanalyzer.service.model.StatisticaAllaData;

public class StockServiceImpl implements StockServiceInterface {

	private Logger logger = LoggerFactory.getLogger(TitoloDao.class);
	public static final int numeroScenariFuturi = 100;


	public StockServiceImpl() {
		logger.debug("costruttore " + this.getClass().getName());
	}

	public List<StatisticaAllaData> bootstrap(String codTitolo) {
		TitoloDao stockDao = new TitoloDao();
		long seed = 1234;
		Random random = new Random(seed);
		List<OsservazioneStorica> titoli = stockDao.extractAllData(codTitolo);

		List<StatisticaAllaData> stockStatisticheAllaData = new ArrayList<>();


		for (int i = 0; i < numeroScenariFuturi; i++) {
			StatisticaAllaData statisticaAllaData = new StatisticaAllaData(null, 0);

			// Pesca casualmente la data e il rendimento dalla lista
			int index = random.nextInt(titoli.size());
			OsservazioneStorica randomStock = titoli.get(index);

			statisticaAllaData.setData(randomStock.getDate());
			statisticaAllaData.setRendimento(randomStock.getRend());

			stockStatisticheAllaData.add(statisticaAllaData);
			logger.info("Statistica Alla Data estratti: " + statisticaAllaData.toString());
		}

		return stockStatisticheAllaData;
	}

	public double[] calcolaRendimentiCumulati(List<StatisticaAllaData> stockDtoStatistiche) {
		int n = stockDtoStatistiche.size();
		double[] rendimentiCumulati = new double[n];

		// Inizializza il primo valore dei rendimenti cumulati
		rendimentiCumulati[0] = stockDtoStatistiche.get(0).getRendimento();

		for (int t = 1; t < n; t++) {
			double rendimentoCorrente = stockDtoStatistiche.get(t).getRendimento();
			rendimentiCumulati[t] = (1 + rendimentiCumulati[t - 1]) * (1 + rendimentoCorrente) - 1;
		}

		return rendimentiCumulati;
	}

	public double calcolaPercentile(double[] rendimentiCumulati, int percentuale) {
		if (rendimentiCumulati == null || rendimentiCumulati.length == 0) {
			throw new IllegalArgumentException("L'array dei rendimenti cumulati è vuoto o nullo.");
		}

		// Ordina l'array dei rendimenti cumulati
		double[] sortedArray = Arrays.copyOf(rendimentiCumulati, rendimentiCumulati.length);
		Arrays.sort(sortedArray);

		// Calcola l'indice del percentile
		int index = (int) Math.ceil((percentuale / 100.0) * sortedArray.length) - 1;

		// Restituisci il valore corrispondente all'indice calcolato
		return sortedArray[index];
	}

	// somma per giornate successive
	public double sommaArray(double[] array1, double[] array2) {
		if (array1.length != array2.length) {
			throw new IllegalArgumentException("Gli array devono avere la stessa lunghezza");
		}

		double sommaTotale = 0.0;
		for (int i = 0; i < array1.length; i++) {
			sommaTotale += array1[i] + array2[i];
		}

		// Restituisci la somma totale
		return sommaTotale;
	}

	// Metodi per calcolare la media, la deviazione standard, il massimo, il minimo
	// e il valore al percentile 99
	public double calcolaMedia(List<Double> data) {
		double sum = 0.0;
		for (double value : data) {
			sum += value;
		}
		return sum / data.size();
	}

	public double calcolaDeviazioneStandard(List<Double> data, double media) {
		double sumOfSquaredDifferences = 0.0;
		for (double value : data) {
			double diff = value - media;
			sumOfSquaredDifferences += diff * diff;
		}
		return Math.sqrt(sumOfSquaredDifferences / data.size());
	}

	public double calcolaMassimo(List<Double> data) {
		return data.stream().mapToDouble(Double::doubleValue).max().orElse(0);
	}

	public double calcolaMinimo(List<Double> data) {
		return data.stream().mapToDouble(Double::doubleValue).min().orElse(0);
	}

	public double calcolaPercentile(List<Double> data, int percentile) {
		int index = (int) Math.ceil((percentile / 100.0) * data.size()) - 1;
		return data.stream().sorted().skip(index).findFirst().orElse((double) 0);
	}

	public double calcolaMassimoDrawdown(List<Double> data) {
		double maxDrawdown = 0.0;
		double currentDrawdown = 0.0;

		for (int i = 0; i < data.size(); i++) {
			double value = data.get(i);
			if (value < 0) {
				currentDrawdown += value;
				if (currentDrawdown < maxDrawdown) {
					maxDrawdown = currentDrawdown;
				}
			} else {
				currentDrawdown = 0;
			}
		}
		return maxDrawdown;
	}

	public List<RendimentoAllaDataDto> extractRendimento(String titolo) {
		TitoloDao stockDao = new TitoloDao();
		List<OsservazioneStorica> titoli = stockDao.extractAllData(titolo);

		List<RendimentoAllaDataDto> stockDtoRendimento = new ArrayList<>();

		for (OsservazioneStorica stock : titoli) {
			RendimentoAllaDataDto stockDtoR = new RendimentoAllaDataDto(null, 0, 0, 0);
			stockDtoR.setDate(stock.getDate());
			stockDtoR.setClose(stock.getClose());
			stockDtoR.setRend(stock.getRend());
			stockDtoR.setRendPerc(stock.getRendPerc());

			stockDtoRendimento.add(stockDtoR);
			logger.info("Rendimenti estratti: " + stockDtoR.toString());
		}

		return stockDtoRendimento;

	}

	public List<StatisticheDto> extractStat(String codTitolo) {
		TitoloDao stockDao = new TitoloDao();
		List<OsservazioneStorica> rend = stockDao.extractAllData(codTitolo);

		List<Double> rendimenti = new ArrayList<>();
		for (OsservazioneStorica stock : rend) {
			rendimenti.add(stock.getRend());
		}

		StatisticheDto statistics = new StatisticheDto();
		if (!rendimenti.isEmpty()) {
			statistics.setMediaRendimenti(calcolaMedia(rendimenti));
			statistics.setDeviazioneStandard(calcolaDeviazioneStandard(rendimenti, statistics.getMediaRendimenti()));
			statistics.setMassimoRendimento(calcolaMassimo(rendimenti));
			statistics.setMinimoRendimento(calcolaMinimo(rendimenti));
			statistics.setPercentile99(calcolaPercentile(rendimenti, 99));
			statistics.setMassimoDrawdown(calcolaMassimoDrawdown(rendimenti));
			statistics.setPercentile5(calcolaPercentile(rendimenti, 5));
			statistics.setPercentile50(calcolaPercentile(rendimenti, 50));
			statistics.setPercentile95(calcolaPercentile(rendimenti, 95));

			List<StatisticheDto> stockDtoStatistics = new ArrayList<>();
			stockDtoStatistics.add(statistics);
			return stockDtoStatistics;
		} else {
			return Collections.emptyList();
		}
	}

	public Map<String, Double> extractPesiDaControvalori(List<Posizione> titoli) {
		if (titoli == null || titoli.isEmpty()) {
			throw new IllegalArgumentException("Errore: titoliAzionari assenti o vuoti");
		}

		Map<String, Double> pesiTitoli = new HashMap<>();
		double sum = 0.0;

		// Calcolo della somma totale dei controvalori
		for (Posizione posizione : titoli) {
			sum += posizione.getControvalore();
		}

		// Calcolo dei pesi in base ai controvalori
		for (Posizione posizione : titoli) {
			String titolo = posizione.getCodTitolo();
			double controvalore = posizione.getControvalore();

			double peso = controvalore / sum;
			if (peso > 1.0 || peso <= 0.0) {
				throw new IllegalArgumentException("Errore: La somma dei pesi deve essere compresa tra 0 e 1");
			}
			pesiTitoli.put(titolo, peso);
		}

		return pesiTitoli;
	}

	@Override
	public double calcolaRendimentoMedioPesato(List<Posizione> titoli) {
		if (titoli == null || titoli.isEmpty()) {
			throw new IllegalArgumentException("Errore: titoliAzionari assenti o vuoti");
		}

		Map<String, Double> pesiTitoli = extractPesiDaControvalori(titoli);

		double weightedReturnSum = 0.0;
		double weightSum = 0.0;

		// Itera attraverso le posizioni anziché la mappa
		for (Posizione posizione : titoli) {
			String titolo = posizione.getCodTitolo();
			double controvalore = posizione.getControvalore();

			if (pesiTitoli.containsKey(titolo)) {
				double pesoTitolo = pesiTitoli.get(titolo);
				List<RendimentoAllaDataDto> rendimentiTitolo = extractRendimento(titolo);

				if (rendimentiTitolo != null && !rendimentiTitolo.isEmpty()) {
					double sum = 0.0;

					// Calcola la somma dei rendimenti
					for (RendimentoAllaDataDto rendimento : rendimentiTitolo) {
						sum += rendimento.getRend();
					}

					// Calcola la media dei rendimenti per il titolo
					double mediaRendimentoTitolo = sum / rendimentiTitolo.size();

					weightedReturnSum += mediaRendimentoTitolo * (pesoTitolo * controvalore);
					weightSum += pesoTitolo * controvalore;
				}
			}
		}

		if (weightSum == 0.0) {
			return 0; // Evita la divisione per zero
		}

		return weightedReturnSum / weightSum;
	}

	@Override
	public List<StatisticheDto> extractStatPtf(List<Posizione> titoli) {
	    if (titoli == null || titoli.isEmpty()) {
	        return new ArrayList<>();
	    }

	    // Ottenere i pesi basati sui controvalori
	    Map<String, Double> pesiTitoli = extractPesiDaControvalori(titoli);

	    List<Double> rendimentiPesati = new ArrayList<>();

	    // Calcola rendimenti pesati utilizzando i pesi
	    for (Posizione posizione : titoli) {
	        String titolo = posizione.getCodTitolo();
	        Double peso = pesiTitoli.get(titolo);

	        TitoloDao stockDao = new TitoloDao();
	        List<OsservazioneStorica> rendimenti = stockDao.extractAllData(titolo);

	        if (!rendimenti.isEmpty()) {
	            for (OsservazioneStorica stock : rendimenti) {
	                rendimentiPesati.add(stock.getRend() * peso);
	            }
	        }
	    }

	    // Restituisci le statistiche calcolate
	    MisureDto portfolioStat = new MisureDto();
	    portfolioStat.setMediaRendimenti(calcolaMedia(rendimentiPesati));
	    portfolioStat.setDeviazioneStandard(calcolaDeviazioneStandard(rendimentiPesati, portfolioStat.getMediaRendimenti()));
	    portfolioStat.setMassimoDrawdown(calcolaMassimoDrawdown(rendimentiPesati));
	    portfolioStat.setMassimoRendimento(calcolaMassimo(rendimentiPesati));
	    portfolioStat.setMinimoRendimento(calcolaMinimo(rendimentiPesati));
	    portfolioStat.setPercentile99(calcolaPercentile(rendimentiPesati, 99));
	    portfolioStat.setPercentile5(calcolaPercentile(rendimentiPesati, 5));
	    portfolioStat.setPercentile50(calcolaPercentile(rendimentiPesati, 50));
	    portfolioStat.setPercentile95(calcolaPercentile(rendimentiPesati, 95));

	    return Collections.singletonList(portfolioStat);
	}


}
