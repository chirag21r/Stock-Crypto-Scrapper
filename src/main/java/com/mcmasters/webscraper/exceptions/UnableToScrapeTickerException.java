package com.mcmasters.webscraper.exceptions;

public class UnableToScrapeTickerException extends Exception {

    public UnableToScrapeTickerException(String errorMessage) {
        super(errorMessage);
    }
}
