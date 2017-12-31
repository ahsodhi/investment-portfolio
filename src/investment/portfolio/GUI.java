package investment.portfolio;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Handles creation of all GUI components and binding them to action listeners.
 * @author Anirudh
 */
public class GUI extends JFrame {
    private int index = 0;
    
    public GUI() {
        super("Investment Portfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        Font font = new Font("Verdana", Font.PLAIN, 20);
        
        JLabel commandLbl = new JLabel("Commands: ");
        commandLbl.setFont(font);
        JButton buyButton = new JButton("Buy Investment");
        buyButton.setFont(font);
        JButton sellButton = new JButton("Sell Investment");
        sellButton.setFont(font);
        JButton updateButton = new JButton("Update Prices");
        updateButton.setFont(font);
        JButton getGainBtn = new JButton("Get Gain");
        getGainBtn.setFont(font);
        JButton searchButton = new JButton("Search");
        searchButton.setFont(font);
        JButton saveAs = new JButton("Save");
        saveAs.setFont(font);
        JButton loadBtn = new JButton("Load");
        loadBtn.setFont(font);
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(font);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(commandLbl);
        topPanel.add(buyButton);
        topPanel.add(sellButton);
        topPanel.add(updateButton);
        topPanel.add(getGainBtn);
        topPanel.add(searchButton);
        topPanel.add(saveAs);
        topPanel.add(loadBtn);
        topPanel.add(quitButton);
        topPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        JTextArea welcomeArea = new JTextArea(10,10);
        welcomeArea.setEditable(false);
        welcomeArea.setFont(font);
        welcomeArea.setText("\nWelcome to Investment Portfolio.\n\n"
                + "Choose a command from the “Commands” menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program");
        welcomeArea.setLineWrap(true);
        welcomeArea.setWrapStyleWord(true);
        
        JPanel buyPanelOutput = new JPanel();
        buyPanelOutput.setBorder(new EmptyBorder(10,0,0,0));
        buyPanelOutput.setLayout(new BoxLayout(buyPanelOutput, BoxLayout.Y_AXIS));
        JTextArea messageArea = new JTextArea(10,10);
        messageArea.setBorder(new EmptyBorder(10,10,10,10));
        messageArea.setEditable(false);
        messageArea.setFont(font);
        JLabel msgLbl = new JLabel("Messages");
        msgLbl.setFont(font);
        JScrollPane scroll = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        buyPanelOutput.add(msgLbl);
        buyPanelOutput.add(scroll);
        
        JPanel sellPanelOutput = new JPanel();
        sellPanelOutput.setBorder(new EmptyBorder(10,0,0,0));
        sellPanelOutput.setLayout(new BoxLayout(sellPanelOutput, BoxLayout.Y_AXIS));
        JTextArea sellMessageArea = new JTextArea(10,10);
        sellMessageArea.setBorder(new EmptyBorder(10,10,10,10));
        sellMessageArea.setEditable(false);
        sellMessageArea.setFont(font);
        JLabel sellMsgLbl = new JLabel("Messages");
        sellMsgLbl.setFont(font);
        JScrollPane scroll2 = new JScrollPane(sellMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sellPanelOutput.add(sellMsgLbl);
        sellPanelOutput.add(scroll2);
        
        JPanel updatePanelOutput = new JPanel();
        updatePanelOutput.setBorder(new EmptyBorder(10,0,0,0));
        updatePanelOutput.setLayout(new BoxLayout(updatePanelOutput, BoxLayout.Y_AXIS));
        JTextArea updateMessageArea = new JTextArea(10,10);
        updateMessageArea.setBorder(new EmptyBorder(10,10,10,10));
        updateMessageArea.setEditable(false);
        updateMessageArea.setFont(font);
        JLabel updateMsgLbl = new JLabel("Messages");
        updateMsgLbl.setFont(font);
        JScrollPane scroll3 = new JScrollPane(updateMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        updatePanelOutput.add(updateMsgLbl);
        updatePanelOutput.add(scroll3);
        
        JPanel getGainOutput = new JPanel();
        getGainOutput.setBorder(new EmptyBorder(10,0,0,0));
        getGainOutput.setLayout(new BoxLayout(getGainOutput, BoxLayout.Y_AXIS));
        JTextArea getGainMsgArea = new JTextArea(10,10);
        getGainMsgArea.setBorder(new EmptyBorder(10,10,10,10));
        getGainMsgArea.setEditable(false);
        getGainMsgArea.setFont(font);
        JLabel getGainLbl = new JLabel("Individual Gains");
        getGainLbl.setFont(font);
        JScrollPane scroll4 = new JScrollPane(getGainMsgArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getGainOutput.add(getGainLbl);
        getGainOutput.add(scroll4);
        
        JPanel searchResults = new JPanel();
        searchResults.setBorder(new EmptyBorder(10,0,0,0));
        searchResults.setLayout(new BoxLayout(searchResults, BoxLayout.Y_AXIS));
        JTextArea searchOutputArea = new JTextArea(10,10);
        searchOutputArea.setBorder(new EmptyBorder(10,10,10,10));
        searchOutputArea.setEditable(false);
        searchOutputArea.setFont(font);
        JLabel searchResultsLbl = new JLabel("Search Results");
        searchResultsLbl.setFont(font);
        JScrollPane scroll5 = new JScrollPane(searchOutputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        searchResults.add(searchResultsLbl);
        searchResults.add(scroll5);
        
        JPanel buyPanelWrapper = new JPanel();
        buyPanelWrapper.setLayout(new BorderLayout());
        JPanel buyPanel = new JPanel();
        buyPanel.setLayout(new GridLayout(1,2));
        JLabel buyLbl = new JLabel("Buying an investment");
        buyLbl.setFont(font);
        JLabel typeLbl = new JLabel("Type: ");
        typeLbl.setFont(font);
        JLabel symbolLbl = new JLabel("Symbol: ");
        symbolLbl.setFont(font);
        JLabel nameLbl = new JLabel("Name: ");
        nameLbl.setFont(font);
        JLabel quantityLbl = new JLabel("Quantity: ");
        quantityLbl.setFont(font);
        JLabel priceLbl = new JLabel("Price: ");
        priceLbl.setFont(font);
        String[] types = new String[2];
        types[0] = "Stock";
        types[1] = "Mutual Fund";
        JComboBox typeFld = new JComboBox(types);
        typeFld.setFont(font);
        JTextField symbolFld = new JTextField(30);
        symbolFld.setFont(font);
        JTextField nameFld = new JTextField(30);
        nameFld.setFont(font);
        JTextField quantityFld = new JTextField(30);
        quantityFld.setFont(font);
        JTextField priceFld = new JTextField(30);
        priceFld.setFont(font);
        JPanel buyForm = new JPanel();
        buyForm.setBorder(new EmptyBorder(10,0,0,0));
        buyForm.setLayout(new GridLayout(5,2));
        buyForm.add(typeLbl);
        buyForm.add(typeFld);
        buyForm.add(symbolLbl);
        buyForm.add(symbolFld);
        buyForm.add(nameLbl);
        buyForm.add(nameFld);
        buyForm.add(quantityLbl);
        buyForm.add(quantityFld);
        buyForm.add(priceLbl);
        buyForm.add(priceFld);
        JPanel buyFormWrapper = new JPanel();
        buyFormWrapper.setLayout(new BorderLayout());
        buyFormWrapper.add(buyLbl, BorderLayout.NORTH);
        buyFormWrapper.add(buyForm, BorderLayout.SOUTH);
        buyFormWrapper.setBorder(new EmptyBorder(10,10,0,10));
        JPanel buyPanelRight = new JPanel();
        buyPanelRight.setLayout(new BorderLayout());
        JPanel buyPanelButtons = new JPanel();
        buyPanelButtons.setLayout(new BoxLayout(buyPanelButtons, BoxLayout.Y_AXIS));
        JButton resetBuyBtn = new JButton("Reset");
        resetBuyBtn.setFont(font);
        JButton buyBtn = new JButton("Buy");
        buyBtn.setFont(font);
        buyPanelButtons.add(resetBuyBtn);
        buyPanelButtons.add(buyBtn);
        buyPanelRight.add(buyPanelButtons, BorderLayout.SOUTH);
        buyPanel.add(buyFormWrapper);
        buyPanel.add(buyPanelRight);
        buyPanelWrapper.add(buyPanel, BorderLayout.NORTH);
        buyPanelWrapper.add(buyPanelOutput, BorderLayout.CENTER);
        
        JPanel sellPanelWrapper = new JPanel();
        sellPanelWrapper.setLayout(new BorderLayout());
        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(1,2));
        JLabel sellLbl = new JLabel("Selling an investment");
        sellLbl.setFont(font);
        JTextField sellSymbolFld = new JTextField(30);
        sellSymbolFld.setFont(font);
        JTextField sellQuantityFld = new JTextField(30);
        sellQuantityFld.setFont(font);
        JTextField sellPriceFld = new JTextField(30);
        sellPriceFld.setFont(font);
        JLabel sellSymbolLbl = new JLabel("Symbol: ");
        sellSymbolLbl.setFont(font);
        JLabel sellQuantityLbl = new JLabel("Quantity: ");
        sellQuantityLbl.setFont(font);
        JLabel sellPriceLbl = new JLabel("Price: ");
        sellPriceLbl.setFont(font);
        JPanel sellForm = new JPanel();
        sellForm.setLayout(new GridLayout(3,2));
        sellForm.setBorder(new EmptyBorder(10,0,0,0));
        sellForm.add(sellSymbolLbl);
        sellForm.add(sellSymbolFld);
        sellForm.add(sellQuantityLbl);
        sellForm.add(sellQuantityFld);
        sellForm.add(sellPriceLbl);
        sellForm.add(sellPriceFld);
        JPanel sellFormWrapper = new JPanel();
        sellFormWrapper.setLayout(new BorderLayout());
        sellFormWrapper.add(sellLbl, BorderLayout.NORTH);
        sellFormWrapper.add(sellForm, BorderLayout.SOUTH);
        sellFormWrapper.setBorder(new EmptyBorder(10,10,0,10));
        JPanel sellPanelRight = new JPanel();
        sellPanelRight.setLayout(new BorderLayout());
        JPanel sellPanelButtons = new JPanel();
        sellPanelButtons.setLayout(new BoxLayout(sellPanelButtons, BoxLayout.Y_AXIS));
        JButton resetSellBtn = new JButton("Reset");
        resetSellBtn.setFont(font);
        JButton sellBtn = new JButton("Sell");
        sellBtn.setFont(font);
        sellPanelButtons.add(resetSellBtn);
        sellPanelButtons.add(sellBtn);
        sellPanelRight.add(sellPanelButtons, BorderLayout.SOUTH);
        sellPanel.add(sellFormWrapper);
        sellPanel.add(sellPanelRight);
        sellPanelWrapper.add(sellPanel, BorderLayout.NORTH);
        sellPanelWrapper.add(sellPanelOutput, BorderLayout.CENTER);
        
        JPanel updatePanelWrapper = new JPanel();
        updatePanelWrapper.setLayout(new BorderLayout());
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(1,2));
        JLabel updateLbl = new JLabel("Updating investments");
        updateLbl.setFont(font);
        JTextField updateSymbolFld = new JTextField(30);
        updateSymbolFld.setEditable(false);
        updateSymbolFld.setFont(font);
        JTextField updateNameFld = new JTextField(30);
        updateNameFld.setEditable(false);
        updateNameFld.setFont(font);
        JTextField updatePriceFld = new JTextField(30);
        updatePriceFld.setFont(font);
        JLabel updateSymbolLbl = new JLabel("Symbol: ");
        updateSymbolLbl.setFont(font);
        JLabel updateNameLbl = new JLabel("Name: ");
        updateNameLbl.setFont(font);
        JLabel updatePriceLbl = new JLabel("Price: ");
        updatePriceLbl.setFont(font);
        JPanel updateForm = new JPanel();
        updateForm.setBorder(new EmptyBorder(10,0,0,0));
        updateForm.setLayout(new GridLayout(3,2));
        updateForm.add(updateSymbolLbl);
        updateForm.add(updateSymbolFld);
        updateForm.add(updateNameLbl);
        updateForm.add(updateNameFld);
        updateForm.add(updatePriceLbl);
        updateForm.add(updatePriceFld);
        JPanel updateFormWrapper = new JPanel();
        updateFormWrapper.setLayout(new BorderLayout());
        updateFormWrapper.add(updateLbl, BorderLayout.NORTH);
        updateFormWrapper.add(updateForm, BorderLayout.SOUTH);
        updateFormWrapper.setBorder(new EmptyBorder(10,10,0,10));
        JPanel updatePanelRight = new JPanel();
        updatePanelRight.setLayout(new BorderLayout());
        JPanel updatePanelButtons = new JPanel();
        updatePanelButtons.setLayout(new BoxLayout(updatePanelButtons, BoxLayout.Y_AXIS));
        JButton prevButton = new JButton("Prev");
        prevButton.setFont(font);
        JButton nextButton = new JButton("Next");
        nextButton.setFont(font);
        JButton updateSaveButton = new JButton("Save");
        updateSaveButton.setFont(font);
        updatePanelButtons.add(prevButton);
        updatePanelButtons.add(nextButton);
        updatePanelButtons.add(updateSaveButton);
        updatePanelRight.add(updatePanelButtons, BorderLayout.SOUTH);
        updatePanel.add(updateFormWrapper);
        updatePanel.add(updatePanelRight);
        updatePanelWrapper.add(updatePanel, BorderLayout.NORTH);
        updatePanelWrapper.add(updatePanelOutput, BorderLayout.CENTER);
        
        JPanel getGainPanelWrapper = new JPanel();
        getGainPanelWrapper.setLayout(new BorderLayout());
        JPanel getGainPanel = new JPanel();
        JLabel getGainHeading = new JLabel("Getting total gain");
        getGainHeading.setFont(font);
        JLabel totalGainLbl = new JLabel("Total gain: ");
        totalGainLbl.setFont(font);
        JTextField getGainFld = new JTextField(20);
        getGainFld.setEditable(false);
        getGainFld.setFont(font);
        JPanel getGainForm = new JPanel();
        getGainForm.setBorder(new EmptyBorder(10,0,0,0));
        getGainForm.setLayout(new BoxLayout(getGainForm, BoxLayout.X_AXIS));
        getGainForm.add(totalGainLbl);
        getGainForm.add(getGainFld);
        JPanel getGainFormWrapper = new JPanel();
        getGainFormWrapper.setLayout(new BorderLayout());
        getGainFormWrapper.add(getGainHeading, BorderLayout.NORTH);
        getGainFormWrapper.add(getGainForm, BorderLayout.SOUTH);
        getGainFormWrapper.setBorder(new EmptyBorder(10,10,0,10));
        getGainPanel.add(getGainFormWrapper);
        getGainPanelWrapper.add(getGainPanel, BorderLayout.NORTH);
        getGainPanelWrapper.add(getGainOutput, BorderLayout.CENTER);

        JPanel searchPanelWrapper = new JPanel();
        searchPanelWrapper.setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,2));
        JLabel searchLbl = new JLabel("Searching investments");
        searchLbl.setFont(font);
        updateLbl.setFont(font);
        JTextField searchSymbolFld = new JTextField(30);
        searchSymbolFld.setFont(font);
        JTextField searchNameFld = new JTextField(30);
        searchNameFld.setFont(font);
        JTextField searchLowPriceFld = new JTextField(30);
        searchLowPriceFld.setFont(font);
        JTextField searchHighPriceFld = new JTextField(30);
        searchHighPriceFld.setFont(font);
        JLabel searchSymbolLbl = new JLabel("Symbol: ");
        searchSymbolLbl.setFont(font);
        JLabel searchNameLbl = new JLabel("Name keywords: ");
        searchNameLbl.setFont(font);
        JLabel searchLowPriceLbl = new JLabel("Low price: ");
        searchLowPriceLbl.setFont(font);
        JLabel searchHighPriceLbl = new JLabel("High price: ");
        searchHighPriceLbl.setFont(font);
        JPanel searchForm = new JPanel();
        searchForm.setBorder(new EmptyBorder(10,0,0,0));
        searchForm.setLayout(new GridLayout(4,2));
        searchForm.add(searchSymbolLbl);
        searchForm.add(searchSymbolFld);
        searchForm.add(searchNameLbl);
        searchForm.add(searchNameFld);
        searchForm.add(searchLowPriceLbl);
        searchForm.add(searchLowPriceFld);
        searchForm.add(searchHighPriceLbl);
        searchForm.add(searchHighPriceFld);
        JPanel searchFormWrapper = new JPanel();
        searchFormWrapper.setLayout(new BorderLayout());
        searchFormWrapper.add(searchLbl, BorderLayout.NORTH);
        searchFormWrapper.add(searchForm, BorderLayout.SOUTH);
        searchFormWrapper.setBorder(new EmptyBorder(10,10,0,10));
        JPanel searchPanelRight = new JPanel();
        searchPanelRight.setLayout(new BorderLayout());
        JPanel searchPanelButtons = new JPanel();
        searchPanelButtons.setLayout(new BoxLayout(searchPanelButtons, BoxLayout.Y_AXIS));
        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(font);
        JButton resetSearchBtn = new JButton("Reset");
        resetSearchBtn.setFont(font);
        searchPanelButtons.add(resetSearchBtn);
        searchPanelButtons.add(searchBtn);
        searchPanelRight.add(searchPanelButtons, BorderLayout.SOUTH);
        searchPanel.add(searchFormWrapper);
        searchPanel.add(searchPanelRight);
        searchPanelWrapper.add(searchPanel, BorderLayout.NORTH);
        searchPanelWrapper.add(searchResults, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new CardLayout());
        bottomPanel.add(welcomeArea, "welcomeScreen");
        bottomPanel.add(buyPanelWrapper, "buyPanel");
        bottomPanel.add(sellPanelWrapper, "sellPanel");
        bottomPanel.add(updatePanelWrapper, "updatePanel");
        bottomPanel.add(getGainPanelWrapper, "getGainPanel");
        bottomPanel.add(searchPanelWrapper, "searchPanel");
        ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "welcomeScreen");
        
        add(topPanel);
        add(bottomPanel);
        
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                messageArea.setText("");
                ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "buyPanel");
            }
        });
        
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sellMessageArea.setText("");
                ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "sellPanel");
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!Application.portfolio.getInvestmentList().isEmpty()) {
                    index = 0;
                    Investment i = Application.portfolio.getInvestmentList().get(index);
                    updateSymbolFld.setText(i.getSymbol());
                    updateNameFld.setText(i.getName());
                    updatePriceFld.setText("" + i.getPrice());
                    updateMessageArea.setText("");
                }
                ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "updatePanel");
            }
        });
        
        getGainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                double totalGain = Application.portfolio.getGain(getGainMsgArea);
                String o = String.format("$%.2f", totalGain);
                getGainFld.setText(o);
                ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "getGainPanel");
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchOutputArea.setText("");
                ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "searchPanel");
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int type = typeFld.getSelectedIndex();
                String symbol = symbolFld.getText();
                String name = nameFld.getText();
                int quantity;
                double price;
                try {
                    price = Double.parseDouble(priceFld.getText());
                    quantity = Integer.parseInt(quantityFld.getText());
                }
                catch (NumberFormatException e) {
                    messageArea.setText("Input Error: NumberFormatException");
                    return;
                }
                int result = Application.portfolio.buyInvestment(type, symbol, name, quantity, price, messageArea);
                if (result != Application.SUCCESS)
                    messageArea.setText(Application.getError(result));
                else {
                    symbolFld.setText("");
                    nameFld.setText("");
                    quantityFld.setText("");
                    priceFld.setText("");
                }
            }
        });
        
        resetBuyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                symbolFld.setText("");
                nameFld.setText("");
                quantityFld.setText("");
                priceFld.setText("");
            }
        });
        
        sellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String symbol = sellSymbolFld.getText();
                int quantity;
                double price;
                try {
                    price = Double.parseDouble(sellPriceFld.getText());
                    quantity = Integer.parseInt(sellQuantityFld.getText());
                }
                catch (NumberFormatException e) {
                    sellMessageArea.setText("Input Error: NumberFormatException");
                    return;
                }
                int result = Application.portfolio.sellInvestment(symbol, quantity, price, sellMessageArea);
                if (result != Application.SUCCESS)
                    sellMessageArea.setText(Application.getError(result));
                 else {
                    sellSymbolFld.setText("");
                    sellQuantityFld.setText("");
                    sellPriceFld.setText("");
                }
            }
        });
        
        resetSellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sellSymbolFld.setText("");
                sellQuantityFld.setText("");
                sellPriceFld.setText("");
            }
        });
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList investmentList = Application.portfolio.getInvestmentList();
                if (investmentList.isEmpty())
                    return;
                if (index + 1 > investmentList.size()-1)
                    return;
                index++;
                Investment i = Application.portfolio.getInvestmentList().get(index);
                updateSymbolFld.setText(i.getSymbol());
                updateNameFld.setText(i.getName());
                updatePriceFld.setText("" + i.getPrice());
            }
        });
        
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList investmentList = Application.portfolio.getInvestmentList();
                if (investmentList.isEmpty())
                    return;
                if (index == 0)
                    return;
                index--;
                Investment i = Application.portfolio.getInvestmentList().get(index);
                updateSymbolFld.setText(i.getSymbol());
                updateNameFld.setText(i.getName());
                updatePriceFld.setText("" + i.getPrice());
            }
        });
        
        updateSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String symbol = updateSymbolFld.getText();
                double price;
                try {
                    price = Double.parseDouble(updatePriceFld.getText());
                    int result = Application.portfolio.updatePrice(symbol, price);
                    if (result != Application.SUCCESS)
                        updateMessageArea.setText(Application.getError(result));
                    else
                        updateMessageArea.setText("Successfully updated price for " + symbol);
                }
                catch (NumberFormatException ex) {
                    updateMessageArea.setText("Input Error: NumberFormatException");
                }
            }
        });
        
        resetSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchHighPriceFld.setText("");
                searchLowPriceFld.setText("");
                searchSymbolFld.setText("");
                searchNameFld.setText("");
            }
        });
        
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                double highPrice;
                double lowPrice;
                try {
                    if (searchHighPriceFld.getText().isEmpty())
                        highPrice = 0;
                    else
                        highPrice = Double.parseDouble(searchHighPriceFld.getText());
                    if (searchLowPriceFld.getText().isEmpty())
                        lowPrice = 0;
                    else
                        lowPrice = Double.parseDouble(searchLowPriceFld.getText());
                    String[] keywords = searchNameFld.getText().toLowerCase().split(" ");
                    if (searchNameFld.getText().isEmpty())
                        keywords = new String[0];
                    String symbol = searchSymbolFld.getText();
                    Application.portfolio.search(symbol, keywords, lowPrice, highPrice, searchOutputArea);
                }
                catch (NumberFormatException ex) {
                    searchOutputArea.setText("Input Error: NumberFormatException");
                }
            }
        });
        
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(null);
                if (fileChooser.getSelectedFile() != null) {
                    String filepath = fileChooser.getSelectedFile().getPath();
                    Application.portfolio.savePortfolio(filepath);
                }
            }
        });
        
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                if (fileChooser.getSelectedFile() != null) {
                    String filepath = fileChooser.getSelectedFile().getPath();
                    Application.portfolio.loadPortfolio(filepath);
                }
            }
        });
    }
}
