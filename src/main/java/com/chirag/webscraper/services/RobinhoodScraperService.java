package com.mcmasters.webscraper.services;

import com.mcmasters.webscraper.entities.PriceChange;
import com.mcmasters.webscraper.utils.StringConverter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RobinhoodScraperService {

    private static final String currentPriceQuery = "._1Nw7xfQTjIvcCkNYkwQMzL";
    private static final String currentDifferenceQuery = "._27rSsse3BjeLj7Y1bhIE_9";

    public double scrapeCurrentPrice(Document robinhoodDoc) {
        String priceStr = robinhoodDoc.selectFirst(currentPriceQuery).text();
        return StringConverter.convertPriceToDouble(priceStr);
    }

    public PriceChange scrapeTodaysPriceChange(Document robinhoodDoc) {
        Element element = robinhoodDoc.selectFirst(currentDifferenceQuery);
        log.info("Robinhood element for 1 day percentage = {}", element);

        String percentageStr = element.text();
        String[] arr = percentageStr.split(" ");
        double price = StringConverter.convertPriceToDouble(arr[0]);
        double percentage = StringConverter.convertPercentageToDouble(arr[1]);
        return new PriceChange(price, percentage);
    }
}
