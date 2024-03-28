package crypto;

import java.awt.Color;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Refresher {
    // Create a String variable to hold Solana prices
    private static String oldSolPrice;
    public static String newSolPrice;
    // Integer value to show elapsed seconds
    public static String updatedSeconds;
    // Create an Integer variable to count time
    private static int timeCounter = 0;
    // Create a String array to hold previous Solana prices
    private static String[] prevSolPrices = new String[120];
    // Create Double variable to hold price for previous Solana prices
    private static double prePrice1 = 0;
    private static double prePrice2 = 0;
    private static double prePrice3 = 0;
    private static double prePrice4 = 0;
    private static double prePrice5 = 0;
    // TEST
    private static int totalTime = 0;

    // Update current time each second and update Solana price each 30 seconds
    public static void updateValues() {
        while(true) {
            // For loop to update current time each second and sleep for 30 seconds
            for (int i = 0; i < 30; i++) {
                // Update current time
                updateCurrentTime();
                // Update seconds from last Solana price update
                updateSeconds(i);
                // TEST
                System.out.println(i + " Seconds");
                try {
                    // Sleep for one second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateSolPrice();
            SolanaPanel.priceLabel.setText("$" + newSolPrice);
            SolanaPanel.percentageLabel.setText(updatePercentage(oldSolPrice, newSolPrice));
            updateBottomLabels();
            highestPrice();
            lowestPrice();
            // TEST
            totalTime = totalTime + 30;
            System.out.println(totalTime + " Seconds passed totally");
        }
    }

    // Method to update and keep last Solana price
    private static void updateSolPrice() {
        try {
            // Old Solana price is equals to new Solana price
            oldSolPrice = newSolPrice;
            // Update the new Solana price
            newSolPrice = SolanaPriceFetcher.getSolanaPrice();
            // If old Solana price is null
            if (oldSolPrice == null) {
                // Old Solana price is equals to new Solana price
                oldSolPrice = newSolPrice;
            }
            // Update the text in priceLabel to display updated price
            SolanaPanel.priceLabel.setText("$" + newSolPrice);
        } catch (IOException e) {
            // If price cannot retrieved, show an error
            JOptionPane.showMessageDialog(null, "Could not fetched Solana price!", "Error", JOptionPane.ERROR_MESSAGE);
            // Exit the program
            System.exit(1);
        }
    }

    // Method to update current time
    private static void updateCurrentTime() {
        SolanaPanel.timeStamp = SolanaPanel.timeFormat.format(Calendar.getInstance().getTime());
        SolanaPanel.currentTime.setText(SolanaPanel.timeStamp);
    }

    // Method to update seconds from last Solana price update
    private static void updateSeconds(int i) {
        updatedSeconds = String.valueOf(i);
        SolanaPanel.timeCounterLabel.setText("Updated " + updatedSeconds + " seconds ago");
    }
    
    // Method to update the percentage change between the last Solana price and New Solana price
    private static String updatePercentage(String oldPriceString, String newPriceString) {

        // Convert String prices into double values
        double oldPrice = Double.parseDouble(oldPriceString);
        double newPrice = Double.parseDouble(newPriceString);
        // Calculate percentage change between old and new price
        double percentage =  ((newPrice * 100) / oldPrice) - 100;
        // Return the percentage value in percentage format depending on the condition
        // If the old price equals to new price
        if (oldPrice == newPrice) {
            // Set percentageLabel's foreground color to gray
            SolanaPanel.percentageLabel.setForeground(Color.GRAY);
            return String.format("%.2f%%", 0.0);
        } else if (newPrice > oldPrice) { // If new price is greater than old pricr
            // Set percentageLabel's foreground color to green
            SolanaPanel.percentageLabel.setForeground(Color.GREEN);
            return String.format("+%.2f%%", percentage);
        } else { 
            // Else set percentageLabel's foreground color to red
            SolanaPanel.percentageLabel.setForeground(Color.RED); 
            return String.format("%.2f%%", percentage);
        }
    }

    // Method to update previous Solana prices
    private static void updateBottomLabels() {

        // Reset the 'timeCounter' variable if it exceeds the limit of the 'prevSolPrices' array        
        if (timeCounter == 120) {
            // Each timeCounter value equals to 30 seconds
            timeCounter = 0;
        }
        // Record previous Solana prices for up to the last hour
        prevSolPrices[timeCounter] = newSolPrice;
        // TEST
        System.out.println("Array Index [" + timeCounter + "]" + " = " + prevSolPrices[timeCounter]);
        // Convert newSolPrice variable into a double value
        Double currentSolPrice = Double.parseDouble(newSolPrice);

        // Update the label every 5 minutes with the price from 5 minutes ago
        if ((timeCounter+1)%10 == 0 && timeCounter > 0) {
            prePrice5 = Double.parseDouble(prevSolPrices[timeCounter-9]);
            // Update the text of the label
            SolanaPanel.prePriceLabel5.setText("$" + prePrice5);
        }
        // Adjust label foreground color based on the comparison between the current Solana price and that of 5 minutes ago
        if (prePrice5 > 0) {
            changeTextColor(currentSolPrice, prePrice5, SolanaPanel.prePriceLabel5);
        }

        // Update the label every 15 minutes with the price from 15 minutes ago
        if ((timeCounter+1)%30 == 0 && timeCounter > 0) {
            prePrice4 = Double.parseDouble(prevSolPrices[timeCounter-29]);
            // Update the text of the label
            SolanaPanel.prePriceLabel4.setText("$" + prePrice4);
        }
        // Adjust label foreground color based on the comparison between the current Solana price and that of 15 minutes ago
        if (prePrice4 > 0) {
            changeTextColor(currentSolPrice, prePrice4, SolanaPanel.prePriceLabel4);
        }

        // Update the label every 30 minutes with the price from 30 minutes ago
        if ((timeCounter+1)%60 == 0 && timeCounter > 0) {
            prePrice3 = Double.parseDouble(prevSolPrices[timeCounter-59]);
            // Update the text of the label
            SolanaPanel.prePriceLabel3.setText("$" + prePrice3);
        }
        // Adjust label foreground color based on the comparison between the current Solana price and that of 30 minutes ago
        if (prePrice3 > 0) {
            changeTextColor(currentSolPrice, prePrice3, SolanaPanel.prePriceLabel3);
        }

        // Update the label every 45 minutes with the price from 45 minutes ago
        if ((timeCounter+1)%90 == 0 && timeCounter > 0) {
            prePrice2 = Double.parseDouble(prevSolPrices[timeCounter-89]);
            // Update the text of the label
            SolanaPanel.prePriceLabel2.setText("$" + prePrice2);
        }
        // Adjust label foreground color based on the comparison between the current Solana price and that of 45 minutes ago
        if (prePrice2 > 0) {
            changeTextColor(currentSolPrice, prePrice2, SolanaPanel.prePriceLabel2);
        }

        // Update the label every 60 minutes with the price from 60 minutes ago
        if ((timeCounter+1)%120 == 0 && timeCounter > 0) {
            prePrice1 = Double.parseDouble(prevSolPrices[timeCounter-119]);
            // Update the text of the label
            SolanaPanel.prePriceLabel1.setText("$" + prePrice1);
        }
        // Adjust label foreground color based on the comparison between the current Solana price and that of 60 minutes ago
        if (prePrice1 > 0) {
            changeTextColor(currentSolPrice, prePrice1, SolanaPanel.prePriceLabel1);
        }

        // This variable increments every 30 seconds
        timeCounter++;
    }

    // Method to compare the old and current Solana prices and change the foreground color of labels
    private static void changeTextColor(double currentPrice, double oldPrice, JLabel label) {
        // If current price equals to old pricr
        if (currentPrice == oldPrice) {
            // Set label's foreground color to black
            label.setForeground(Color.BLACK);
        } else if (currentPrice > oldPrice) { // Else if current price is greather than old price
            // Set label's foreground color to red
            label.setForeground(Color.RED);
        } else {
            // Else set label's foreground color to green
            label.setForeground(Color.GREEN);
        }
    }

    // Method to find highest price in the last hour
    private static void highestPrice() {
        // Set the smallest number possible
        double highestPrice = Double.MIN_VALUE;
        // Initiate a null String variable
        String highestPriceString = "";
        for(int i = 0; i < prevSolPrices.length; i++) {
            // If there is a null element in the array, stop and exit the for loop
            if (prevSolPrices[i] == null) {break;}
            // Parse the price stored at the current index of the array
            double price = Double.parseDouble(prevSolPrices[i]);
            // If price is greater than highest price
            if (price > highestPrice) {
                // Highest price equals to price
                highestPrice = price;
                highestPriceString = prevSolPrices[i];
            }
        }
        // Set highestPrice's panel text to highestPriceString
        SolanaPanel.highestPrice.setText(highestPriceString);
    }

    private static void lowestPrice() {
        // Set the largest number possible
        double lowestPrice = Double.MAX_VALUE;
        // Initiate a null String variable
        String lowestPriceString = "";
        for(int i = 0; i < prevSolPrices.length; i++) {
            // If there is a null element in the array, stop and exit the for loop
            if (prevSolPrices[i] == null) {break;}
            // Parse the price stored at the current index of the array
            double price = Double.parseDouble(prevSolPrices[i]);
            // If price is lower than highest price
            if (price < lowestPrice) {
                // Lowest price equals to price
                lowestPrice = price;
                lowestPriceString = prevSolPrices[i];
            }
        }
        // Set lowestPrice's panel text to lowestPriceString
        SolanaPanel.lowestPrice.setText(lowestPriceString);
    }
 
}