package com.mcmasters.webscraper.utils;

public class StringConverter {

    // Regex removes + $ and ,
    public static double convertPriceToDouble(String price) {
        price = price.replaceAll("\\+|\\$|,", "");
        return Double.parseDouble(price);
    }

    // Percentage comes in as (+3.82%) ... Regex removes ( ) $ , and %
    public static double convertPercentageToDouble(String percentage) {
        percentage = percentage.replaceAll("\\(|$|,|%|\\)", "");
        return Double.parseDouble(percentage);
    }
}
