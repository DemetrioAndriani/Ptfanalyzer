package it.andrianid.ptfanalyzer.service.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioDao {
    private Map<String, List<String>> portfolios; 

    public PortfolioDao() {
        this.portfolios = new HashMap<>();
    }

    public boolean esisteStockInPortfolio(String portfolioName, String stockName) {
        List<String> stockNames = portfolios.get(portfolioName);

        if (stockNames != null && !stockNames.isEmpty()) {
            for (String title : stockNames) {
                if (title.equals(stockName)) {
                    return true; // Il titolo esiste 
                }
            }
        }

        return false; // Il titolo non è presente 
    }
}
