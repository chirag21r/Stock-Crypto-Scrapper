<<<<<<< HEAD
# Stock-Crypto-Scrapper
=======
# Stock-And-Crypto-Backend-WebScraper


A REST API that returns the current price of the given stock or cryptocurrency by web scraping various websites. Built using Spring.

## To Run Locally
Clone this repo, open the project in your IDE of choice, and in the WebScraperApplication class run the main() method.
<br>
The project will run on port 8080.
<br>
Then copy/paste the following endpoints into your browser or in Postman.

## Endpoints

(Replace the following stock/crypto tickers with a ticker of your choice. AAPL, AMZN, DOGE, TSLA etc.)
<br>
<br>
Get stock's price and history
<br>
http://localhost:8080/stock/twtr
```json
{
    "tickerName": "TWTR",
    "type": "stock",
    "currentPrice": 62.52,
    "priceChanges": {
        "day": {
            "priceDifference": 0.47,
            "percentage": 0.76
        },
        "week": {
            "priceDifference": -2.3,
            "percentage": -3.55
        },
        "month": {
            "priceDifference": -5.42,
            "percentage": -7.98
        },
        "ytd": {
            "priceDifference": 7.81,
            "percentage": 14.28
        },
        "year": {
            "priceDifference": -9.76,
            "percentage": -13.5
        }
    }
}
```

Get cryptocurrency's price and history
<br>
http://localhost:8080/crypto/btc
```json
{
    "tickerName": "BTC",
    "type": "crypto",
    "currentPrice": 49214.91,
    "priceChanges": {
        "day": {
            "priceDifference": 345.41,
            "percentage": 0.71
        },
        "week": {
            "priceDifference": 3170.6,
            "percentage": 6.88
        },
        "month": {
            "priceDifference": 16751.35,
            "percentage": 51.55
        },
        "ytd": {
            "priceDifference": 13930.1,
            "percentage": 39.45
        },
        "year": {
            "priceDifference": 1241.35,
            "percentage": 2.59
        }
    }
}
```

Verify that the API supports a stock.
<br>
(A stock will not be supported if it is not able to webscrape its information. This will happen for less popular cryptocurrencies or for Vanguard index funds such as VTSAX)
<br>
http://localhost:8080/stock/vtsax/verify
```json
false
```

Verify that the API supports a cryptocurrency
<br>
http://localhost:8080/crypto/eth/verify
```json
true
```
