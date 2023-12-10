package it.andrianid.ptfanalyzer.service.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.andrianid.ptfanalyzer.service.model.OsservazioneStorica;

public class TitoloDao {

    private Logger logger = LoggerFactory.getLogger(TitoloDao.class);

    // Restituisce tutti gli stock (dati storici)
    public List<OsservazioneStorica> extractAllData(String codTitolo) {
        String csvFilePath = "/stocks/" + codTitolo + ".csv";
        List<OsservazioneStorica> historicalData = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(csvFilePath)) {
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    // Salta la prima riga (l'intestazione)
                    reader.readLine();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] values = line.split(";");

                        if (values.length >= 9) {
                            String dateString = values[0];
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                            Date date;
                            try {
                                date = dateFormat.parse(dateString);
                            } catch (ParseException e) {
                                logger.error("Errore durante la conversione della data: " + e.getMessage());
                                continue;
                            }

                            DecimalFormat decimalFormat = new DecimalFormat("0.00");

                            double open, high, low, adjClose, rend, rendPerc;
                            int close;
                            long volume;

                            try {
                                open = Double.valueOf(values[1]);
                                high = Double.valueOf(values[2]);
                                low = Double.valueOf(values[3]);
                                close = Integer.parseInt(values[4]);
                                adjClose = Double.valueOf(values[5]);
                                volume = Long.parseLong(values[6]);
                                rend = decimalFormat.parse(values[7]).doubleValue();
                                rendPerc = decimalFormat.parse(values[8]).doubleValue();
                            } catch (NumberFormatException | ParseException e) {
                                logger.error("Errore durante la conversione di uno dei valori numerici: " + e.getMessage());
                                continue;
                            }

                            OsservazioneStorica stock = new OsservazioneStorica(date, open, high, low, close, adjClose, volume, rend, rendPerc);
                            historicalData.add(stock);
                            logger.info("Dato estratto: " + stock.toString());
                        } else {
                            logger.error("Dati mancanti in una riga: " + line);
                            throw new IllegalArgumentException("Dati mancanti in una riga: " + line);
                        }
                    }
                }
            } else {
                logger.error("Il file CSV non è stato trovato: " + csvFilePath);
                throw new IllegalArgumentException("Il file CSV non è stato trovato: " + csvFilePath);
            }
        } catch (IOException e) {
            logger.error("Errore durante la lettura del file CSV: " + e.getMessage());
        }

        return historicalData;
    }
}
