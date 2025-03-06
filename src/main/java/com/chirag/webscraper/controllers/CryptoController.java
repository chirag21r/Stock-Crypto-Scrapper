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
public class CryptoController {

    @Autowired
    private WebScraperService webScraperService;

    @Autowired
    private TickerSupportedChecker tickerSupportedChecker;


    @GetMapping("/crypto/{ticker}")
    public ResponseEntity<Ticker> getCyrptoPrice(@PathVariable String ticker) throws UnableToScrapeTickerException {
        log.info("Scraping prices for crypto, {}", ticker);

        Ticker cryptoTicker = webScraperService.scrapeCryptoInfo(ticker);
        log.info("Retrieved prices for crypto {}, {}", ticker, cryptoTicker);
        return ResponseEntity.ok().body(cryptoTicker);
    }

    // If a stock/crypto/index fund can not be web scraped (such as VTSAX) then this API does not support it.
    @GetMapping("/crypto/{ticker}/verify")
    public ResponseEntity<Boolean> verifyCrypIsSupported(@PathVariable String ticker) {
        boolean result = tickerSupportedChecker.checkIfCryptoIsScrapable(ticker);
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<Set<String>> getAllUnsupportedCryptos() {
        Set<String> unsupportedCryptos = tickerSupportedChecker.getUnsupportedCryptos();
        return ResponseEntity.ok().body(unsupportedCryptos);
    }
}
