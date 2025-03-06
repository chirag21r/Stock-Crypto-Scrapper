package com.mcmasters.webscraper.services;


import com.mcmasters.webscraper.exceptions.UnableToScrapeTickerException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


// If a ticker can not be webscraped, then this API does not support it.

@Slf4j
@Service
public class TickerSupportedChecker {

    @Autowired
    private WebScraperService webScraperService;

    // Cache results for a faster response if the same ticker is sent again
    @Getter Set<String> unsupportedStocks = new HashSet<>();
    @Getter Set<String> unsupportedCryptos = new HashSet<>();

    @Getter Set<String> supportedStocks = new HashSet<>();
    @Getter Set<String> supportedCryptos = new HashSet<>();


    public boolean checkIfStockIsScrapable(String tickerName) {
        if (unsupportedStocks.contains(tickerName)) return false;
        else if (supportedStocks.contains(tickerName)) return true;

        log.info("Checking if stock {} is supported by this API", tickerName);
        try {
            webScraperService.scrapeStockInfo(tickerName);
        } catch (UnableToScrapeTickerException e) {
            log.info("Stock {} is not supported by this API", tickerName);
            unsupportedStocks.add(tickerName);
            return false;
        }
        log.info("Stock {} is supported by this API", tickerName);
        supportedStocks.add(tickerName);
        return true;
    }

    public boolean checkIfCryptoIsScrapable(String tickerName) {
        if (unsupportedCryptos.contains(tickerName)) return false;
        else if (supportedCryptos.contains(tickerName)) return true;

        log.info("Checking if crypto {} is supported by this API", tickerName);
        try {
            webScraperService.scrapeCryptoInfo(tickerName);
        } catch (UnableToScrapeTickerException e) {
            log.info("Crypto {} is not supported by this API", tickerName);
            unsupportedCryptos.add(tickerName);
            return false;
        }
        log.info("Crypto {} is supported by this API", tickerName);
        supportedCryptos.add(tickerName);
        return true;
    }
}










