package stock.market.simulator;

import java.util.Random;
import java.text.DecimalFormat;

import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StockMarketSimulator {
    
     
    public static final String HISTORYFILEPATH = "history/";
    
    public static void main(String[] args) throws IOException {

        accountProfile accProfile = accountCreation();

        stockProfile[][] stockProfile = createAllStocks();

        deleteHistoryFiles(new File(HISTORYFILEPATH));
        createHistoryFiles(stockProfile);

        mainWindow window = new mainWindow(accProfile, stockProfile);

        recalculationLoop(stockProfile, window);

    }

     public static void recalculationLoop(stockProfile[][] stockProfile, mainWindow window) {
        Timer t1 = new Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                recalulationPrice(stockProfile);
                window.setTextBoxValues(stockProfile);

            }
        }, 0, 5000);
    }

     public static accountProfile accountCreation() {
        createAccount accCreate = new createAccount();

        boolean successfulCreate;
        Object sync = new Object();
        do {
             synchronized (sync) {
                successfulCreate = accCreate.getCreated();
            }
        } while (!successfulCreate);

        accountProfile accProfile = accCreate.getAccountProfile();
        accCreate.terminate();
        return accProfile;
    }
    
     public static void recalulationPrice(stockProfile[][] stocks) {

        Random number = new Random();

        double priceChange;
        stockProfile stockToEdit;

        for (stockProfile[] stockArray : stocks) {
            for (stockProfile stock : stockArray) {
                priceChange = number.nextDouble() + 0.1;
                int increaseORdecrease = number.nextInt(10) + 1;

                stockToEdit = stock;
                if (increaseORdecrease > 6) {
                    increasePrice(stockToEdit, priceChange);

                } else {
                    decreasePrice(stockToEdit, priceChange);
                }

            }
        }

    }
    
     public static void setPriceChange(stockProfile profile, double sellChange, double buyChange) {
        double difference = ((sellChange - profile.getSellPrice()) / profile.getSellPrice() * 100);
        profile.setChange(roundTo2DP(difference));
        profile.setSellPrice(roundTo4DP(sellChange));
        profile.setBuyPrice(roundTo4DP(buyChange));

    }
    
     public static void increasePrice(stockProfile profile, double priceChange) {
        double sellChange = priceChange
                + (profile.getSellPrice() + ((profile.getSellPrice() * profile.getMargin()) / 2));
        double buyChange = priceChange
                + (profile.getBuyPrice() + ((profile.getBuyPrice() * profile.getMargin()) / 2));

        setPriceChange(profile, sellChange, buyChange);

    }
    
     public static void decreasePrice(stockProfile profile, double priceChange) {
        double sellChange = priceChange
                + (profile.getSellPrice() - ((profile.getSellPrice() * profile.getMargin()) / 2));
        double buyChange = priceChange
                + (profile.getBuyPrice() + ((profile.getBuyPrice() * profile.getMargin()) / 2));

        setPriceChange(profile, sellChange, buyChange);

    }
    
 
    public static Double roundTo2DP(double number) {
        DecimalFormat roundFormat = new DecimalFormat(".##");
        return (Double.parseDouble(roundFormat.format(number)));
    }

     public static Double roundTo4DP(double number) {
        DecimalFormat roundFormat = new DecimalFormat(".####");
        return (Double.parseDouble(roundFormat.format(number)));
    }
    
     public static stockProfile[][] createAllStocks() {
         String[][] from_To = {{"EUR", "USD"}, {"GBP", "USD"}, {"EUR", "GBP"}, {"GBP", "JPY"}};
        double[][] currencyPrice = {{1.2183, 1.2185}, {1.3767, 1.3768}, {0.88491, 0.88511},
        {147.279, 147.320}};

         String[] companyName = {"Facebook", "Apple", "Microsoft", "BMW"};
        double[] companyMargin = {0.05, 0.05, 0.05, 0.5};
        double[][] companyPrice = {{178.31, 178.56}, {178.12, 178.37}, {93.96, 93.05}, {85.87, 85.99}};

         String[] countryName = {"UK", "USA", "AUS", "JPY"};
        String[] stockName = {"FTSE 100", "Dow Jones", "$AUSSIE200", "NIKKEI 225"};
        double[] economyMargin = {0.005, 0.005, 0.01, 0.01};
        double[][] economyPrice = {{718.18, 718.28}, {25056, 25060}, {5974.8, 5978.3}, {21634, 21642}};

        stockProfile[][] stocks = {createCurrencyStock(from_To, currencyPrice),
            createCompanyStock(companyName, companyMargin, companyPrice),
            createEconomyStock(countryName, stockName, economyMargin, economyPrice)};

        return stocks;
    }

     public static currencyStock[] createCurrencyStock(String[][] from_To, double[][] price) {
        currencyStock[] stocks = new currencyStock[from_To.length];

        for (int i = 0; i < from_To.length; i++) {
            stocks[i] = new currencyStock(from_To[i][0], from_To[i][1], price[i][0], price[i][1]);

        }

        return stocks;
    }

     public static companyStock[] createCompanyStock(String[] companyName, double[] margin, double[][] price) {
        companyStock[] stocks = new companyStock[companyName.length];

        for (int i = 0; i < companyName.length; i++) {

            stocks[i] = new companyStock(companyName[i], margin[i], price[i][0], price[i][1]);

        }

        return stocks;
    }

     public static economyStock[] createEconomyStock(String[] countryName, String[] stockName, double[] margin,
            double[][] price) {
        economyStock[] stocks = new economyStock[countryName.length];

        for (int i = 0; i < countryName.length; i++) {

            stocks[i] = new economyStock(countryName[i], stockName[i], margin[i], price[i][0], price[i][1]);

        }

        return stocks;
    }

     public static void createHistoryFiles(stockProfile[][] stocks) throws IOException {

        String fileName;
        FileWriter fileWriter;

        for (stockProfile[] stockArray : stocks) {
            for (stockProfile stock : stockArray) {
                fileName = stock.getProfileName() + ".csv";
                fileWriter = new FileWriter(HISTORYFILEPATH + fileName);
            }
        }

    }

     public static void deleteHistoryFiles(File directory) {
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

    }

}
