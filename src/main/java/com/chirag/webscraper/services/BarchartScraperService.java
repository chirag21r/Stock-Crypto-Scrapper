package com.mcmasters.webscraper.services;

import com.mcmasters.webscraper.entities.PriceChange;
import com.mcmasters.webscraper.utils.StringConverter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BarchartScraperService {

    private static final String historicPriceQuery = "barchart-table-scroll tr";

    // Returns the difference/percentage price change compared to the week, month, YTD, and year
    public List<PriceChange> scrapeHistoricPriceChanges(Document barchartDoc) {
        List<PriceChange> priceChanges = new ArrayList<>();

        Elements elements = barchartDoc.select(historicPriceQuery);
        elements.remove(0);         // Remove first row because it is the name of each column
        for (Element e : elements) {
            log.info("Barchart element for historic prices and percentages = {}", e.text());
            String[] arr = e.text().split(" ");
            double price = StringConverter.convertPriceToDouble(arr[9]);
            double percentage = StringConverter.convertPercentageToDouble(arr[10]);
            priceChanges.add(new PriceChange(price, percentage));
        }
        return priceChanges;
    }
}
