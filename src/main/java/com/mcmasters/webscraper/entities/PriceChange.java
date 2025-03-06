package com.mcmasters.webscraper.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

// The price/percentage increase from the previous price (for the day, week, month, YTD, and year).

@AllArgsConstructor
@Data
public class PriceChange {

    private double priceDifference;
    private double percentage;

}
