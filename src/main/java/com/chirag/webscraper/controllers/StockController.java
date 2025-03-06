package com.mcmasters.webscraper.controllers;

import com.mcmasters.webscraper.entities.Ticker;
import com.mcmasters.webscraper.exceptions.UnableToScrapeTickerException;
import com.mcmasters.webscraper.services.TickerSupportedChecker;
import com.mcmasters.webscraper.services.WebScraperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StockController {

    @Autowired
    private WebScraperService webScraper;

    @Autowired
    private TickerSupportedChecker tickerSupportedChecker;


    @GetMapping("/stock/{ticker}")
    public ResponseEntity<Ticker> getStockPrice(@PathVariable String ticker) throws UnableToScrapeTickerException {
        log.info("Scraping prices for stock, {}", ticker);

        Ticker stockTicker = webScraper.scrapeStockInfo(ticker);
        log.info("Retrieved prices for stock {}, {}", ticker, stockTicker);
        return ResponseEntity.ok().body(stockTicker);
    }

    // If a stock/crypto/index fund can not be web scraped (such as VTSAX) then this API does not support it.
    @GetMapping("/stock/{ticker}/verify")
    public ResponseEntity<Boolean> verifyStockIsSupported(@PathVariable String ticker) {
        boolean result = tickerSupportedChecker.checkIfStockIsScrapable(ticker);
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<Set<String>> getAllUnsupportedStocks() {
        Set<String> unsupportedStocks = tickerSupportedChecker.getUnsupportedStocks();
        return ResponseEntity.ok().body(unsupportedStocks);
    }
}

