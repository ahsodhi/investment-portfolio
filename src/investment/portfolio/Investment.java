package investment.portfolio;

import java.util.Objects;

/**
 * A class defining an Investment object
 * @author Anirudh
 */
public abstract class Investment {
    protected String symbol;
    protected String name;
    protected int quantity = 0;
    protected double price;
    protected double bookValue = 0;
    
    /**
     * Create a new Investment object based on the given attributes
     * @param symbol the symbol for the Investment
     * @param name the name of the Investment
     * @param price the price of the Investment
     */
    public Investment(String symbol, String name, double price) throws EmptySymbolError,
                                                                       EmptyNameError,
                                                                       PriceRangeError {
        if (symbol.isEmpty())
            throw new EmptySymbolError();
        if (name.isEmpty())
            throw new EmptyNameError();
        if (price <= 0)
            throw new PriceRangeError();
        this.symbol = new String(symbol);
        this.name = new String(name);
        this.price = price;
    }

    /**
     * Get the symbol name of the Investment
     * @return the symbol of the Investment
     */
    public String getSymbol() {
        return new String(symbol);
    }

    /**
     * Update the symbol name of the Investment
     * @param symbol the new symbol name for the Investment
     */
    public void setSymbol(String symbol) {
        this.symbol = new String(symbol);
    }

    /**
     * Get the name of the Investment
     * @return the name of the mutual fund
     */
    public String getName() {
        return new String(name);
    }

    /**
     * Update the name of the Investment
     * @param name the new name for the Investment
     */
    public void setName(String name) {
        this.name = new String(name);
    }

    /**
     * Get the number of units of this Investment currently owned
     * @return the number of units owned
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Update the number of units owned for the Investment
     * @param quantity the new amount of units
     */
    public void setQuantity(int quantity) throws QuantityRangeError {
        if (quantity <= 0)
            throw new QuantityRangeError();
        this.quantity = quantity;
    }

    /**
     * Get the current market price of the Investment
     * @return the current market price of the Stock
     */
    public double getPrice() {
        return price;
    }

    /**
     * Update the market price of the Investment
     * @param price the new market price for the Investment
     */
    public void setPrice(double price) throws PriceRangeError {
        if (price <= 0)
            throw new PriceRangeError();
        this.price = price;
    }

    /**
     * Get the current book value of the Investment
     * @return the current book value for the Investment
     */
    public double getBookValue() {
        return bookValue;
    }

    /**
     * Update the book value of the Investment
     * @param bookValue the new book value for the Investment
     */
    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    /**
     * Checks if this investment is equal to another investment
     * @param obj the other investment to be compared
     * @return true if symbol names match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Stock) {
            Stock other = (Stock) obj;
            return symbol.equalsIgnoreCase(other.getSymbol());
        }
        else if (obj instanceof MutualFund) {
            MutualFund other = (MutualFund) obj;
            return symbol.equalsIgnoreCase(other.getSymbol());
        }
        else {
            Investment other = (Investment) obj;
            return symbol.equalsIgnoreCase(other.getSymbol());
        }
    }
    
    /**
     * Returns a string representation this Investment
     * @return the string representation of this Investment
     */
    @Override
    public String toString() {
        return this.symbol + ", " + this.name + ", " + this.price + ", " +
               this.quantity + ", " + this.bookValue;
    }
    
    /**
     * Returns the gain of the investment
     * @return the gain on this investment
     */
    public abstract double getGain();
    
    /**
     * Returns payment received from selling an investment
     * @param amount the quantity sold
     * @return the payment received
     */
    public abstract double getPaymentReceived(int amount);

    protected static class EmptySymbolError extends Exception {

        public EmptySymbolError() {
        }
    }

    protected static class EmptyNameError extends Exception {

        public EmptyNameError() {
        }
    }

    protected static class PriceRangeError extends Exception {

        public PriceRangeError() {
        }
    }
    
    protected static class QuantityRangeError extends Exception {

        public QuantityRangeError() {
        }
    }
}
