package com.mcmasters.webscraper.services;

import com.mcmasters.webscraper.entities.PriceChange;
import com.mcmasters.webscraper.entities.Ticker;
import com.mcmasters.webscraper.exceptions.UnableToScrapeTickerException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WebScraperService {

    @Autowired
    private RobinhoodScraperService robinhoodScraperService;

    @Autowired
    private BarchartScraperService barchartScraperService;


    public Ticker scrapeStockInfo(String stockTickerStr) throws UnableToScrapeTickerException {
        try {
            String robinhoodURI = "https://robinhood.com/stocks/" + stockTickerStr;
            String barchartURI = "https://www.barchart.com/stocks/quotes/" + stockTickerStr + "/performance";
            return scrape(stockTickerStr, "stock", robinhoodURI, barchartURI);
        } catch (Exception exc) {
            throw new UnableToScrapeTickerException("Unable to webscrape stock " + stockTickerStr);
        }
    }

    public Ticker scrapeCryptoInfo(String coinTickerStr) throws UnableToScrapeTickerException {
        try {
            String robinhoodURI = "https://robinhood.com/crypto/" + coinTickerStr;
            String barchartURI = "https://www.barchart.com/crypto/quotes/%5E" + coinTickerStr + "USD/performance";
            return scrape(coinTickerStr, "crypto", robinhoodURI, barchartURI);
        } catch (Exception exc) {
            throw new UnableToScrapeTickerException("Unable to webscrape crypto " + coinTickerStr);
        }
    }

    // Scrapes the given websites for the given stock/crypto ticker and returns data as a Ticker object.
    private Ticker scrape(String tickerStr, String type, String robinhoodURI, String barchartURI) throws IOException {
        log.info("Scraping for robinhoodURI = {} and barchartURI = {}", robinhoodURI, barchartURI);
        Document robinhoodDoc = Jsoup.connect(robinhoodURI).get();
        Document barchartDoc = Jsoup.connect(barchartURI).get();

        double currentPrice = robinhoodScraperService.scrapeCurrentPrice(robinhoodDoc);
        log.info("currentPrice = {}", currentPrice);

        List<PriceChange> priceChanges = new ArrayList<>();
        priceChanges.add(robinhoodScraperService.scrapeTodaysPriceChange(robinhoodDoc));
        priceChanges.addAll(barchartScraperService.scrapeHistoricPriceChanges(barchartDoc));
        log.info("priceChanges = {}", priceChanges);

        return new Ticker(tickerStr, type, currentPrice, priceChanges);
    }
}







