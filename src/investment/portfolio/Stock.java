package investment.portfolio;


/**
 * A class defining a Stock object
 * @author Anirudh
 */
public class Stock extends Investment {
    
    public static final double COMMISSION = 9.99;

    /**
     * Create a new Stock object
     * @param symbol the symbol for the stock
     * @param name the name of the stock
     * @param price the price of the stock
     */
    public Stock(String symbol, String name, double price) throws EmptySymbolError,
                                                                  EmptyNameError,
                                                                  PriceRangeError {
        super(symbol, name, price);
    }
    
    /**
     * Buy the given number of shares for the Stock at the given price
     * and update the current price of the Stock
     * @param amount the amount of shares to buy
     * @param price the buy price for each share
     */
    public void buyShares(int amount, double price) throws QuantityRangeError {
        if (amount <= 0)
            throw new QuantityRangeError();
        if (price != this.price) {
            this.price = price;
        }
        this.quantity = this.quantity + amount;
        this.bookValue = this.bookValue + (amount * this.price + Stock.COMMISSION);
    }
    
    /**
     * Sell the given number of shares for the Stock at the given price
     * and update the current price of the Stock
     * @param amount the number of shares to sell
     * @param price the sell price for each share
     * @return remaining quantity of shares or -1 if request quantity was more than the owned quantity
     */
    public int sellShares(int amount, double price) throws QuantityRangeError, PriceRangeError {
        if (amount <= 0)
            throw new QuantityRangeError();
        if (this.quantity >= amount) {
            if (this.price != price) {
                this.setPrice(price);
            }
            int newAmount = this.quantity - amount;
            this.bookValue = this.bookValue * ((double)newAmount / this.quantity);
            this.quantity = newAmount;
            return newAmount;
        }
        return -1;
    }
    
    /**
     * Calculates the total gain of the Stock
     * @return the gain for the Stock
     */
    public double getGain() {
        return (this.quantity * this.price - Stock.COMMISSION) - this.bookValue;
    }
    
    /**
     * Calculates the payment received after selling the Stock
     * @param amount the number of shares sold
     * @return the payment received
     */
    public double getPaymentReceived(int amount) {
        return amount * this.price - Stock.COMMISSION;
    }
    
    /**
     * Prints the details of this Stock
     */
    public void print() {
        System.out.println("Investment Type: Stock");
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Name: " + this.name);
        System.out.printf("Current Price: $%.2f\n", this.price);
        System.out.println("Owned Quantity: " + this.quantity);
        System.out.printf("Book Value: $%.2f\n", this.bookValue);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj);
    }
    
    /**
     * Returns a string representation this Stock
     * @return the string representation of this Stock
     */
    @Override
    public String toString() {
        String output = "Investment Type: Stock\n";
        output += "Symbol: " + this.symbol + "\n";
        output += "Name: " + this.name + "\n";
        output += String.format("Current Price: $%.2f\n", this.price);
        output += "Owned Quantity: " + this.quantity + "\n";
        output += String.format("Book Value: $%.2f\n", this.bookValue);
        return output;
    }
}
