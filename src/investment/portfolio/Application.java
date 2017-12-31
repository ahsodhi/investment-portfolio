package investment.portfolio;

import javax.swing.JOptionPane;

/**
 * Class that contains the main entry point of the program
 * @author Anirudh
 */
public class Application {
    
    public static final int ERROR_INSUFFICIENT_QUANTITY = -1;
    public static final int ERROR_NOT_FOUND = -3;
    public static final int ERROR_EMPTY_PORTFOLIO = -4;
    public static final int FUND_BUY_CONFLICT = -5;
    public static final int STOCK_BUY_CONFLICT = -2;
    public static final int INVALID_PRICE = -6;
    public static final int INVALID_QUANTITY = -7;
    public static final int EMPTY_NAME = -8;
    public static final int EMPTY_SYMBOL = -9;
    public static final int UNKNOWN_ERROR = -10;
    public static final int SUCCESS = 0;
    
    public static Portfolio portfolio;
    
    /**
     * The main method of the program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        portfolio = new Portfolio();
        GUI gui = new GUI();
        gui.setSize(1200, 650);
        gui.setLocationRelativeTo(null);
        gui.setResizable(false);
        gui.setVisible(true);
    }
    
    /**
     * This method is solely dedicated to be called from outside to 
     * display appropriate error messages to the user
     * @param errorCode the displayed error is based on this error code
     */
    public static String getError(int errorCode) {
        switch(errorCode) {
            case ERROR_INSUFFICIENT_QUANTITY:
                return ("You don't have enough quantity to sell");
            case ERROR_NOT_FOUND:
                return ("You can only sell an investment you own");
            case ERROR_EMPTY_PORTFOLIO:
                return ("Your portfolio has no active investments");
            case FUND_BUY_CONFLICT:
                return ("A Stock with the same symbol already exists");                
            case STOCK_BUY_CONFLICT:
                return ("A Mutual Fund with the same symbol already exists");                
            case INVALID_PRICE:
                return ("Price cannot be zero or negative");                
            case INVALID_QUANTITY:
                return ("Quantity cannot be zero or negative");                
            case EMPTY_NAME:
                return ("Investment name cannot be empty");                
            case EMPTY_SYMBOL:
                return ("Investment symbol cannot be empty");                
            case UNKNOWN_ERROR:
                return ("An unknown error occured");                
        }
        return null;
    }
    
    /**
     * Shows an error message dialog
     * @param msg The message to display in dialog
     */
    public static void showErrorDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Shows an information message dialog
     * @param msg The message to display in dialog
     */
    public static void showInfoDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
