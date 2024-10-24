package stock.market.simulator;

public class currencyStock extends stockProfile {

    private final String from;
    private final String to;
   
     public currencyStock(String f, String t, double sPrice, double bPrice) {

        super(f + "-" + t, sPrice, bPrice, 0.01);
        from = f;
        to = t;
        

    }
    
     @Override
    public double getMargin() {
        return 0.01;
    }
}
