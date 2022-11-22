package org.example;

public class Main {
    public static void main(String[] args) {

        String url1 = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
        String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";

        DatabaseSaver databaseSaver = new DatabaseSaver();
        databaseSaver.saveToDBConvertedUSD(url1);

        DataBaseSaverWithDates dataBaseSaverWithDates = new DataBaseSaverWithDates();
        dataBaseSaverWithDates.saveToDBWithDates(url);

    }
}