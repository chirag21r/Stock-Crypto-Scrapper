package com.mcmasters.webscraper.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// A Ticker can be a Stock or a Crypto.

@Slf4j
@Data
public class Ticker {

    private String tickerName;
    private String type;
    private double currentPrice;

    Map<String, PriceChange> priceChanges = new LinkedHashMap<>();      // day, week, month, ytd, year


    public Ticker(String tickerName, String type, double price, List<PriceChange> priceChangesList) {
        this.tickerName = tickerName.toUpperCase();
        this.type = type;
        this.currentPrice = price;

        if (priceChangesList.size() < 4)
            log.warn("Expected priceChanges list to have a size of 4 but it instead has a size of {}", priceChangesList.size());

        priceChanges.put("day", priceChangesList.size() > 0 ? priceChangesList.get(0) : null);
        priceChanges.put("week", priceChangesList.size() > 1 ? priceChangesList.get(1) : null);
        priceChanges.put("month", priceChangesList.size() > 2 ? priceChangesList.get(2) : null);
        priceChanges.put("ytd", priceChangesList.size() > 3 ? priceChangesList.get(3) : null);
        priceChanges.put("year", priceChangesList.size() > 4 ? priceChangesList.get(4) : null);
    }
}
