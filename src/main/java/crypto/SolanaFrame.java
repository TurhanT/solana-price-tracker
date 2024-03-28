package crypto;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SolanaFrame extends JFrame {

    public static String solPrice;
    public static String version = "Version 1.0.0";

    public static void main(String[] args) throws IOException {
        try {
            // Fetch the price of Solana and assign it to solPrice
            solPrice = SolanaPriceFetcher.getSolanaPrice();
        } catch (IOException e) {
            // If an error occurs while fetching the price, ErrorPanel will open instead of the SolanaPanel
        }
        // Instantiate a Solana frame instance
        new SolanaFrame();
    }

    // File path for the logo image
    final String LOGO_PATH = "assets\\sol-logo.png";
    // Create ImageIcon to be used as the logo
    ImageIcon logo = new ImageIcon(LOGO_PATH);

    // Create a SolanaPanel instance for using the Solana panel
    SolanaPanel solanaPanel = new SolanaPanel();
    // Create a ErrorPanel instance for using the Error panel
    ErrorPanel errorPanel = new ErrorPanel();
    
    // Constructor method for creating a SolanaFrame object
    SolanaFrame() {
        // If no error occurred during the retrieval of Solana's price
        if (!SolanaPriceFetcher.isErrorOccurred) {
            // Add the solanaPanel instance to the frame
            this.add(solanaPanel);
        } else { // If an error occurred during the retrieval of Solana's price
            // Add the errorPanel instance to the frame
            this.add(errorPanel);
        }
        // Set title of the frame
        this.setTitle("Solana Price Tracker");
        // Set icon of the frame
        this.setIconImage(logo.getImage());
        // Set the location of the frame to the center of the screen
        this.setLocationRelativeTo(null);
        // Set the size of the frame
        this.setSize(700, 500);
        // Disable resizing of the frame
        this.setResizable(false);
        // Specify the default close operation for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the layout manager to null (to use absolute positioning)
        this.setLayout(null);
        // Make the frame visible
        this.setVisible(true);
        // If no error occurred during the retrieval of Solana's price
        if (!SolanaPriceFetcher.isErrorOccurred) {
            // Invoke the updateValues method in the  Refresher class to update Solana related values
            Refresher.updateValues();
        }
    }

}