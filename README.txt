<img src="/dist/Screenshot.png?raw=true" />

Author: Anirudh Sodhi

Program Description:
Manage an investment portfolio consisting of Stocks and Mutual Funds.

Limitations of Program:
• Prices for investments are entered by user and not based on real market data
• Due to the way prices are handled, there is no support for limit orders. If an investment is bought or sold at different price than the current price, it's current price is adjusted to match the buy/sell price.

User Guide:
1. Clone the repo.
2. Open Project Folder in NetBeans IDE
2. From the menu bar, choose Run->Run Project (Investment Portfolio)

Program Test Plan:
Testing of the program was done by inputting data that was different from the expected data type or inputing the correct type of data, but which logically did not make sense.
The possible scenarios for this were:
• Entering negative decimal or 0 as the price when buying or selling investments
• Entering negative integer or 0 as the quantity when buying or selling investments
• Entering a decimal point value for quantity (trying to buy half a share)
• Entering non-decimal input for price or quantity
• Leaving any of the inputs empty when buying a new investment
• Enter file name that does not exist as command line argument
All these scenarios are handled appropriately by the program by letting the user try again.
Defensive programming has also been exercised as the program is able to match reasonable input variations to the appropriate menu commands, but let's the user try again otherwise.
