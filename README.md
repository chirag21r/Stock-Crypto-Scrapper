# Stock-Crypto-Scrapper

A RESTful API built with Spring Boot that retrieves current and historical prices of stocks and cryptocurrencies by web scraping various websites.

## Features

- Fetch real-time stock prices
- Fetch real-time cryptocurrency prices
- Retrieve historical market data
- Verify support for specific stocks or cryptocurrencies

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- An IDE or text editor of your choice

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/chirag21r/Stock-Crypto-Scrapper.git
   cd Stock-Crypto-Scrapper
   ```

2. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on port `8080` by default.

## API Endpoints

### Get Stock Price and History

- **Endpoint:** `/stock/{ticker}`
- **Method:** `GET`
- **Description:** Retrieves the current price and historical data for the specified stock ticker.
- **Example Request:**
  ```
  GET http://localhost:8080/stock/AAPL
  ```
- **Example Response:**
  ```json
  {
      "tickerName": "AAPL",
      "type": "stock",
      "currentPrice": 150.00,
      "priceChanges": {
          "day": {
              "priceDifference": 1.50,
              "percentage": 1.01
          },
          "week": {
              "priceDifference": -2.00,
              "percentage": -1.32
          },
          "month": {
              "priceDifference": 5.00,
              "percentage": 3.45
          },
          "ytd": {
              "priceDifference": 20.00,
              "percentage": 15.38
          },
          "year": {
              "priceDifference": 25.00,
              "percentage": 20.00
          }
      }
  }
  ```

### Get Cryptocurrency Price and History

- **Endpoint:** `/crypto/{ticker}`
- **Method:** `GET`
- **Description:** Retrieves the current price and historical data for the specified cryptocurrency ticker.
- **Example Request:**
  ```
  GET http://localhost:8080/crypto/BTC
  ```
- **Example Response:**
  ```json
  {
      "tickerName": "BTC",
      "type": "crypto",
      "currentPrice": 45000.00,
      "priceChanges": {
          "day": {
              "priceDifference": 500.00,
              "percentage": 1.12
          },
          "week": {
              "priceDifference": 2000.00,
              "percentage": 4.65
          },
          "month": {
              "priceDifference": 5000.00,
              "percentage": 12.50
          },
          "ytd": {
              "priceDifference": 10000.00,
              "percentage": 28.57
          },
          "year": {
              "priceDifference": 15000.00,
              "percentage": 50.00
          }
      }
  }
  ```

### Verify Stock Support

- **Endpoint:** `/stock/{ticker}/verify`
- **Method:** `GET`
- **Description:** Checks if the specified stock ticker is supported by the API.
- **Example Request:**
  ```
  GET http://localhost:8080/stock/VTSAX/verify
  ```
- **Example Response:**
  ```json
  false
  ```

### Verify Cryptocurrency Support

- **Endpoint:** `/crypto/{ticker}/verify`
- **Method:** `GET`
- **Description:** Checks if the specified cryptocurrency ticker is supported by the API.
- **Example Request:**
  ```
  GET http://localhost:8080/crypto/ETH/verify
  ```
- **Example Response:**
  ```json
  true
  ```

## System Architecture

Below is a high-level overview of the system architecture:

```plaintext
+-----------------------+       +-----------------------+
|                       |       |                       |
|  External Websites    |       |  External Websites    |
|  (e.g., Yahoo Finance)|       |  (e.g., CoinMarketCap)|
|                       |       |                       |
+-----------+-----------+       +-----------+-----------+
            |                               |
            |                               |
            v                               v
+-----------------------+       +-----------------------+
|                       |       |                       |
|  Stock Scraper        |       |  Crypto Scraper       |
|  (Java Components)    |       |  (Java Components)    |
|                       |       |                       |
+-----------+-----------+       +-----------+-----------+
            |                               |
            +-------------------------------+
                            |
                            v
+-------------------------------------------------------+
|                                                       |
|  Spring Boot Application                              |
|  - REST Controllers                                   |
|  - Service Layer                                      |
|  - Data Processing                                    |
|                                                       |
+-----------------------+-------------------------------+
                        |
                        v
+-------------------------------------------------------+
|                                                       |
|  Users / API Clients                                  |
|  - Send HTTP Requests                                 |
|  - Receive JSON Responses                             |
|                                                       |
+-------------------------------------------------------+
```

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


