package investment.portfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;

/**
 * Class to manage set of investments of Stocks and Mutual Funds
 * @author Anirudh
 */
public class Portfolio {
    
    public static final int INVESTMENT_TYPE_STOCK = 0;
    public static final int INVESTMENT_TYPE_FUND = 1;
    
    private ArrayList<Investment> investmentList;
    private HashMap<String, ArrayList<Integer>> keywordMappings;

    /**
     * Returns a copy of the investment array list
     * @return the copy of investment array list
     */
    public ArrayList<Investment> getInvestmentList() {
        return new ArrayList(investmentList);
    }

    /**
     * Sets the investment array list
     * @param investmentList the new investment array list
     */
    public void setInvestmentList(ArrayList<Investment> investmentList) {
        this.investmentList = new ArrayList(investmentList);
    }

    /**
     * Set up a new portfolio
     */
    public Portfolio() {
        this.keywordMappings = new HashMap<>();
        this.investmentList = new ArrayList<>();
    }
    
    /**
     * Buy a new investment or more of an existing based on the given attributes
     * @param type the type of investment, stock or mutual fund
     * @param symbol the symbol of the investment
     * @param name the name for the investment
     * @param quantity the number of shares/units to buy
     * @param price the buy price for each share/unit
     * @param outputArea text area where user messages should be displayed
     * @return static constant Application.SUCCESS if purchase was successful
     */
    public int buyInvestment(int type,
                             String symbol,
                             String name,
                             int quantity,
                             double price,
                             JTextArea outputArea) {
        try {
            boolean existing = false;
            String output = "";
            switch(type) {
                case INVESTMENT_TYPE_STOCK:
                    Stock stock = new Stock(symbol, name, price);
                    for (Investment i : this.investmentList) {
                        if (i.equals(stock)) {
                            existing = true;
                            if (i instanceof MutualFund) {
                                return Application.STOCK_BUY_CONFLICT;
                            }
                            else {
                                stock = (Stock) i;
                            }
                        }
                    }
                    if (!existing) {
                        this.addInvestment(stock);
                    }
                    stock.buyShares(quantity, price);
                    output += String.format("New Book Value: $%.2f\n", stock.getBookValue());
                    output += ("Total Quantity Owned: " + stock.getQuantity() + "\n");
                    break;
                case INVESTMENT_TYPE_FUND:
                    MutualFund fund = new MutualFund(symbol, name, price);
                    for (Investment i : this.investmentList) {
                        if (i.equals(fund)) {
                            existing = true;
                            if (i instanceof Stock) {
                                return Application.FUND_BUY_CONFLICT;
                            }
                            else {
                                fund = (MutualFund) i;
                            }
                        }
                    }
                    if (!existing) {
                        this.addInvestment(fund);
                    }
                    fund.buyUnits(quantity, price);
                    output += String.format("New Book Value: $%.2f\n", fund.getBookValue());
                    output += ("Total Quantity Owned: " + fund.getQuantity() + "\n");
                    break;
            }
            output += "Successfully bought " + quantity + " " + symbol;
            outputArea.setText(output);
        }
        catch (Exception e) {
            return exceptionHandler(e);
        }
        return Application.SUCCESS;
    }
    
    /**
     * Sell an investment that matches the given symbol
     * @param symbol the symbol of the investment
     * @param amount the number of shares/units to sell
     * @param price the sell price for each share/unit
     * @param outputArea text area where messages are displayed
     * @return static constant Application.SUCESS if selling was successful,
     * otherwise one of the static constants from the Application class corresponding to an error 
     */
    public int sellInvestment(String symbol,
                              int amount,
                              double price,
                              JTextArea outputArea) {
        if (this.isEmpty()) {
            return Application.ERROR_EMPTY_PORTFOLIO;
        }
        try {
            String output = "";
            int result = -1;
            boolean found = false;
            Investment investment = null;
            for (Investment i : this.investmentList) {
                if (i instanceof Stock && i.getSymbol().equalsIgnoreCase(symbol)) {
                    Stock s = (Stock) i;
                    result = s.sellShares(amount, price);
                    investment = s;
                    break;
                }
                else if (i instanceof MutualFund && i.getSymbol().equalsIgnoreCase(symbol)) {
                    MutualFund f = (MutualFund) i;
                    result = f.sellUnits(amount, price);
                    investment = f;
                    break;
                }
            }
            if (investment == null) {
                return Application.ERROR_NOT_FOUND;
            }
            else if (result == -1) {
                return Application.ERROR_INSUFFICIENT_QUANTITY;
            }
            else if (result == 0) {
                output += ("Quantity Remaining: 0\n");
                output += ("Stock removed from portfolio\n");
                this.removeInvestment(investment);
            }
            else {
                output += ("Quantity Remaining: " + investment.getQuantity() + "\n");
                output += String.format("New Book Value: $%.2f\n", investment.getBookValue());
            }
            output += String.format("Payment Received: $%.2f\n", investment.getPaymentReceived(amount));
            output += "Successfully sold " + amount + " " + symbol;
            outputArea.setText(output);
        }
        catch (Exception e) {
            return exceptionHandler(e);
        }
        return Application.SUCCESS;
    }
    
    /**
     * Calculates the total accumulated gain of all Stocks and Mutual Funds in the Portfolio
     * @param outputArea text area where individual gains for each investment are displayed
     * @return the total accumulated gain
     */
    public double getGain(JTextArea outputArea) {
        double totalGain = 0;
        String output = "";
        for (Investment i : this.investmentList) {
            totalGain += i.getGain();
            output += String.format("%s: $%.2f\n", i.getSymbol(), i.getGain());
        }
        outputArea.setText(output);
        return totalGain;
    }
    
    /**
     * Used to update the prices for all investments in the portfolio, one-by-one
     * @param symbol the symbol of investment to update
     * @param price the new price for the symbol
     * @return Application.SUCCESS if there were no errors, or Application.ERROR_EMPTY_PORTFOLIO if
     * portfolio contains no investments
     */
    public int updatePrice(String symbol, double price) {
        if (this.isEmpty()) {
            return Application.ERROR_EMPTY_PORTFOLIO;
        }
        try {
            for (Investment i : this.investmentList) {
                if (symbol.equalsIgnoreCase(i.getSymbol())) {
                    i.setPrice(price);
                }
            }
        }
        catch (Exception e) {
            return exceptionHandler(e);
        }
        return Application.SUCCESS;
    }
    
    /**
     * Displays the details of all investments that match a given search criteria
     * @param symbol the symbol to match
     * @param keywords the word-level keywords to match in the investment name
     * @param minPrice the investment price must be greater than this value
     * @param maxPrice  the investment price must be lower than this value
     * @param outputArea text area where results of output will be displayed
     */
    public void search(String symbol, String[] keywords,
                       double minPrice, double maxPrice, JTextArea outputArea) {
        String output = "";
        ArrayList<Integer> possibleIndexes = new ArrayList<>();
        if (keywords.length == 0) {
            for (int i = 0; i < this.investmentList.size(); i++) {
                possibleIndexes.add(i);
            }
        }
        else {
            boolean firstRun = true;
            for (String keyword : keywords) {
                if (this.keywordMappings.containsKey(keyword)) {
                    ArrayList<Integer> matches = this.keywordMappings.get(keyword);
                    if (firstRun) {
                        for (Integer i : matches)
                            possibleIndexes.add(i);
                        firstRun = false;
                    }
                    else {
                        for (Integer i : new ArrayList<Integer>(possibleIndexes)) {
                            if (!matches.contains(i))
                                possibleIndexes.remove(i);
                        }
                    }
                }
                else {
                    possibleIndexes = new ArrayList<>();
                }
            }
        }
        if (!(minPrice == 0 && maxPrice == 0 && symbol.isEmpty())) {
            for (Integer i : new ArrayList<Integer>(possibleIndexes)) {
                Investment investment = this.investmentList.get(i);
                double investmentPrice = investment.getPrice();
                String investmentSymbol = investment.getSymbol();
                boolean match = true;
                if (minPrice != 0 && investmentPrice < minPrice)
                    match = false;
                if (maxPrice != 0 && investmentPrice > maxPrice)
                    match = false;
                if (!symbol.isEmpty() && !investmentSymbol.equalsIgnoreCase(symbol))
                    match = false;
                if (!match)
                    possibleIndexes.remove(i);
            }
        }
        if (possibleIndexes.isEmpty()) {
            output = ("No results found");
        }
        else {
            for (Integer i : possibleIndexes) {
                Investment investment = this.investmentList.get(i);
                if (investment instanceof Stock)
                    output += ((Stock) investment).toString() + "\n";
                else if (investment instanceof MutualFund)
                    output += ((MutualFund) investment).toString() + "\n";
            }
        }
        outputArea.setText(output);
    }
    
    /**
     * Checks if there are any active investments in the portfolio
     * @return true if portfolio is empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.investmentList.isEmpty());
    }
    
    /**
     * Saves all investments to external file
     */
    public void savePortfolio(String filepath) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            for (Investment i : this.investmentList) {
                if (i instanceof Stock)
                    writer.write("type = \"stock\"\n");
                else if (i instanceof MutualFund)
                    writer.write("type = \"mutualfund\"\n");
                writer.write("symbol = \"" + i.getSymbol() + "\"\n");
                writer.write("name = \"" + i.getName()+ "\"\n");
                writer.write("quantity = \"" + i.getQuantity()+ "\"\n");
                writer.write("price = \"" + i.getPrice()+ "\"\n");
                writer.write("bookValue = \"" + i.getBookValue()+ "\"\n\n");
            }
            writer.close();
            Application.showInfoDialog("Data saved to file: " + filepath);
        }
        catch(IOException io) {
            Application.showErrorDialog("Failed to save portfolio data to file: " + filepath);
        }
    }
    
    /**
     * Loads investment data from external file
     */
    public void loadPortfolio(String filepath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String type = "";
            String symbol = "";
            String name = "";
            int quantity = 0;
            double price = 0;
            double bookValue = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                else if (line.isEmpty()) {
                    if (type.equals("stock")) {
                        Stock s = new Stock(symbol, name, price);
                        s.setQuantity(quantity);
                        s.setBookValue(bookValue);
                        this.addInvestment(s);
                        
                    }
                    else if (type.equals("mutualfund")) {
                        MutualFund f = new MutualFund(symbol, name, price);
                        f.setQuantity(quantity);
                        f.setBookValue(bookValue);
                        this.addInvestment(f);
                    }
                    type = "";
                    symbol = "";
                    name = "";
                    quantity = 0;
                    price = 0;
                    bookValue = 0;
                }
                else {
                    String[] vals = line.split("=");
                    String key = vals[0].trim();
                    String value = vals[1].replace("\"", "").trim();
                    if (key.equals("type")) {
                        type = value;
                    }
                    else if (key.equals("symbol")) {
                        symbol = value;
                    }
                    else if (key.equals("name")) {
                        name = value;
                    }
                    else if (key.equals("quantity")) {
                        quantity = Integer.parseInt(value);
                    }
                    else if (key.equals("price")) {
                        price = Double.parseDouble(value);
                    }
                    else if (key.equals("bookValue")) {
                        bookValue = Double.parseDouble(value);
                    }
                }
            }
            reader.close();
            Application.showInfoDialog("Portfolio loaded from file: " + filepath);
        }
        catch (Exception io) {
            Application.showErrorDialog("Failed to load portfolio data from file: " + filepath);
        }
    }
    
    /**
     * Updates hash map indexes used for fast search
     * @param name the investment name
     * @param index the index of the investment stored in ArrayList
     */
    private void addKeywordMapping(String name, int index) {
        String investmentName = name.toLowerCase();
        String[] keywords = investmentName.split(" ");
        for (String keyword : keywords) {
            if (!this.keywordMappings.containsKey(keyword)) {
                ArrayList<Integer> value = new ArrayList<>();
                value.add(index);
                this.keywordMappings.put(keyword, value);
            }
            else {
                ArrayList<Integer> value = this.keywordMappings.get(keyword);
                value.add(index);
            }
        }
    }
    
    /**
     * Updates hash map used for search when an investment is removed
     * @param name the name of the removed investment
     * @param index the index of the investment that was removed from ArrayList
     */
    private void removeKeywordMapping(String name, int index) {
        String investmentName = name.toLowerCase();
        String[] keywords = investmentName.split(" ");
        for (String keyword : keywords) {
            if (this.keywordMappings.containsKey(keyword) &&
                this.keywordMappings.get(keyword).contains(index)) {
                ArrayList<Integer> mappings = this.keywordMappings.get(keyword);
                mappings.remove((Integer) index);
                if (mappings.isEmpty()) {
                    this.keywordMappings.remove(keyword);
                }
            }
        }
        for (ArrayList<Integer> mapping : this.keywordMappings.values()) {
            for (Integer i : mapping) {
                if (i > index) {
                    i--;
                }
            }
        }
    }
    
    /**
     * Removes an investment from the portfolio along with any hash map keyword mappings
     * @param i The investment to be removed
     */
    private void removeInvestment(Investment i) {
        this.removeKeywordMapping(i.getName(), this.investmentList.indexOf(i));
        this.investmentList.remove(i);
    }
    
    /**
     * Adds an investment to the portfolio along with its hash map keywords mappings
     * @param i The investment to be added
     */
    private void addInvestment(Investment i) {
        this.investmentList.add(i);
        this.addKeywordMapping(i.getName(), this.investmentList.size()-1);
    }
    
    private int exceptionHandler(Exception e) {
        if (e instanceof Investment.EmptyNameError)
            return Application.EMPTY_NAME;
        if (e instanceof Investment.EmptySymbolError)
            return Application.EMPTY_SYMBOL;
        if (e instanceof Investment.PriceRangeError)
            return Application.INVALID_PRICE;
        if (e instanceof Investment.QuantityRangeError)
            return Application.INVALID_QUANTITY;
        return Application.UNKNOWN_ERROR;
    }
}
