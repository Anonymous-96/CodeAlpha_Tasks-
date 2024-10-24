package stock.market.simulator;

import java.util.*;

public class accountProfile {

    private String accountName;
    private double balance;
    private ArrayList<stockProfile> stocksBought;

     public accountProfile(String aName, double bal) {
        accountName = aName;
        balance = bal;
        stocksBought = new ArrayList<>();

    }

     public String getAccountName() {
        return accountName;
    }

     public void setBalance(double b) {
        balance += b;
    }

     public double getBalance() {
        return balance;
    }

     public void addStock(stockProfile s) {
        stocksBought.add(s);
    }

     public void removeStock(stockProfile s) {
        stocksBought.remove(s);
    }

     public ArrayList<stockProfile> getStocks() {
        return stocksBought;
    }
}
