package stock.market.simulator;

public class stockProfile {

    private final String profileName;
    private double buyPrice;
    private double sellPrice;
    private double change;
    private double margin;
    private int quantity;

     public stockProfile(String pName, double sPrice, double bPrice, int quant) {
        profileName = pName;
        buyPrice = bPrice;
        sellPrice = sPrice;
        quantity = quant;
    }

     public stockProfile(String pName, double sPrice, double bPrice, double m) {
        profileName = pName;
        buyPrice = bPrice;
        sellPrice = sPrice;
        margin = m;
        change = 0;
    }

     public int getQuantity() {
        return quantity;
    }

     public void setQuantity(int q) {
        quantity = q;
    }

     public String getProfileName() {
        return profileName;
    }

     public double getBuyPrice() {
        return buyPrice;
    }

     public double getSellPrice() {
        return sellPrice;
    }

     public void setBuyPrice(double bPrice) {
        buyPrice = bPrice;
    }

     public void setSellPrice(double sPrice) {
        sellPrice = sPrice;
    }

     public double getMargin() {
        return margin;
    }

     public void setChange(double c) {
        change = c;
    }
    
     public double getChange() {
        return change;
    }
}
