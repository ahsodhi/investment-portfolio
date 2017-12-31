package investment.portfolio;

/**
 * A class defining a Mutual Fund object
 * @author Anirudh
 */
public class MutualFund extends Investment {
    
    public static final double REDEMPTION = 45;

    public MutualFund(String symbol, String name, double price) throws EmptySymbolError,
                                                                       EmptyNameError, 
                                                                       PriceRangeError {
        super(symbol, name, price);
    }
    
    /**
     * Buy the given number of units for the Mutual Fund at the given price
     * and update the current price of the Mutual Fund
     * @param amount the amount of units to buy
     * @param price the buy price for each unit
     */
    public void buyUnits(int amount, double price) throws QuantityRangeError {
        if (amount <= 0)
            throw new QuantityRangeError();
        if (this.price != price) {
            this.price = price;
        }
        this.quantity = this.quantity + amount;
        this.bookValue = this.bookValue + (amount * this.price);
    }
    
    /**
     * Sell the given number of shares for the Mutual Fund at the given price
     * and update the current price of the Mutual Fund
     * @param amount the number of units to sell
     * @param price the sell price for each unit
     * @return remaining quantity of units or -1 if request quantity was more than the owned quantity
     */
    public int sellUnits(int amount, double price) throws QuantityRangeError, PriceRangeError {
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
        return Application.ERROR_INSUFFICIENT_QUANTITY;
    }
    
    /**
     * Calculates the total gain of the Mutual Fund
     * @return the gain for the Mutual Fund
     */
    public double getGain() {
        return (this.quantity * this.price - MutualFund.REDEMPTION) - this.bookValue;
    }
    
    /**
     * Calculates the payment received after selling the Mutual Fund
     * @param amount the number of units sold
     * @return the payment received
     */
    public double getPaymentReceived(int amount) {
        return amount * this.price - MutualFund.REDEMPTION;
    }
    
    /**
     * Prints the details of this Mutual Fund
     */
    public void print() {
        System.out.println("Investment Type: Mutual Fund");
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Name: " + this.name);
        System.out.printf("Current Price: $%.2f\n", this.price);
        System.out.println("Owned Quantity: " + this.quantity);
        System.out.printf("Book Value: $%.2f\n", this.bookValue);
    }
    
    /**
     * Compares the symbol of this Mutual Fund to another Mutual Fund
     * @param obj the MutualFund to be compared to this one
     * @return true if symbol names match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj);
    }
    
    /**
     * Returns a string representation this MutualFund
     * @return the string representation of this MutualFund
     */
    @Override
    public String toString() {
        String output = "Investment Type: Mutual Fund\n";
        output += "Symbol: " + this.symbol + "\n";
        output += "Name: " + this.name + "\n";
        output += String.format("Current Price: $%.2f\n", this.price);
        output += "Owned Quantity: " + this.quantity + "\n";
        output += String.format("Book Value: $%.2f\n", this.bookValue);
        return output;
    }
}
