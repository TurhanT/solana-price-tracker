package crypto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class SolanaPanel extends JPanel{

    // Labels
    private JLabel solanaImage1;
    private JLabel solanaImage2;
    private JLabel bottomLabel;
    private JLabel lowestPriceText;
    private JLabel highestPriceText;
    private JLabel preTextLabel1;
    private JLabel preTextLabel2;
    private JLabel preTextLabel3;
    private JLabel preTextLabel4;
    private JLabel preTextLabel5;
    private JLabel signature;
    private JLabel version;
    private JLabel copyright;
    private JLabel hyperlink = new JLabel("TheTT");
    public static JLabel currentTime;
    public static JLabel priceLabel;
    public static JLabel percentageLabel;
    public static JLabel timeCounterLabel;
    public static JLabel lowestPrice;
    public static JLabel highestPrice;
    public static JLabel prePriceLabel1;
    public static JLabel prePriceLabel2;
    public static JLabel prePriceLabel3;
    public static JLabel prePriceLabel4;
    public static JLabel prePriceLabel5;
    // Set time variables
    public static SimpleDateFormat timeFormat;
    public static String timeStamp;
    // Create global border to use in labels
    Border border = BorderFactory.createBevelBorder(0);

    // Constructor method for creating a SolanaPanel object
    SolanaPanel() {
        // Set location and size of the panel
        this.setBounds(0, 0, 700, 500);
        // Set the layout manager to null (to use absolute positioning)
        this.setLayout(null);
        // Add Solana Images to top left and right corners
        solanaImage1 = solanaImage(20, 110);
        solanaImage2 = solanaImage(610, 110);
        // Create a SimpleDateFormat object the specified pattern
        timeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");        
        // Invoke method to create JLabel for displaying current time
        currentTime = currentTimeLabel(timeStamp, 0, 0, 700, 100);
        // Invoke method to create JLabel for Solana's current time and set empty border for padding
        priceLabel = createLabel("$" + SolanaFrame.solPrice, 250, 150, 140, 60, 32);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        // Invoke method to create JLabel for the change percantage in Solana's price
        percentageLabel = percentageLabel("0.00%");
        // Invoke method to create JLabel for elapsed seconds since the last price update
        timeCounterLabel = createLabel("Updated " + Refresher.updatedSeconds + " seconds ago", 260, 210, 180, 18, 10);
        // Set opqaue to false and foreground color to white for timeCounterLabel
        timeCounterLabel.setOpaque(false);
        timeCounterLabel.setForeground(Color.WHITE);
        // Invoke method to create JLabel for displaying the lowest price text and set its background color
        lowestPriceText = createLabel("Lowest price in the last hour", 85, 230, 144, 20, 10);
        lowestPriceText.setOpaque(false);
        lowestPriceText.setForeground(Color.WHITE);
        // Invoke method to create JLabel for displaying the lowest price and set its background color and border
        lowestPrice = createLabel("--", 87, 250, 140, 60, 32);
        lowestPrice.setForeground(Color.LIGHT_GRAY);
        lowestPrice.setBackground(new Color(184, 0, 10));
        lowestPrice.setBorder(border);
        // Invoke method to create JLabel for displaying the highest price text and set its background color
        highestPriceText = createLabel("Highest price in the last hour", 467, 230, 144, 20, 10);
        highestPriceText.setOpaque(false);
        highestPriceText.setForeground(Color.WHITE);
        // Invoke method to create JLabel for displaying the highest price and set its background color and border
        highestPrice = createLabel("--", 469, 250, 140, 60, 32);
        highestPrice.setForeground(Color.LIGHT_GRAY);
        highestPrice.setBackground(new Color(65, 109, 25));
        highestPrice.setBorder(border);
        // Invoke method create label for bakcground color for price labels
        bottomLabel = transparentLabel(0, 350, 700, 65);
        // Invoke method to create a label displaying the Solana price 1 hour ago
        prePriceLabel1 = prePriceLabel("--", 58);
        preTextLabel1 = preTextLabel("1 Hour", 43);
        // Invoke method to create a label displaying the Solana price 45 minutes ago
        prePriceLabel2 = prePriceLabel("--", 186);
        preTextLabel2 = preTextLabel("45 Minutes", 171);
        // Invoke method to create a label displaying the Solana price 30 minutes ago
        prePriceLabel3 = prePriceLabel("--", 312);
        preTextLabel3 = preTextLabel("30 Minutes", 297);
        // Invoke method to create a label displaying the Solana price 15 minutes ago
        prePriceLabel4 = prePriceLabel("--", 440);
        preTextLabel4 = preTextLabel("15 Minutes", 425);
        // Invoke method to create a label displaying the Solana price 5 minutes ago
        prePriceLabel5 = prePriceLabel("--", 566);
        preTextLabel5 = preTextLabel("5 Minutes", 551);
        // Set properties for hyperlink1 to link to my Github profile
        hyperlink.setBounds(83, 440, 50, 15);
        hyperlink.setForeground(Color.ORANGE);
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink.setFont(new Font("Century", Font.BOLD, 12));
        // Invoke method to create labels for bottom part of the panel
        signature = bottomText(5, 440, 80, 15, "Developed by ");
        version = bottomText(310, 440, 80, 15, SolanaFrame.version);
        copyright = bottomText(540, 440, 150, 15, "Copyright © 2024 TheTT");
        // Add JLabels
        this.add(solanaImage1);
        this.add(solanaImage2);
        this.add(currentTime);
        this.add(priceLabel);
        this.add(percentageLabel);
        this.add(timeCounterLabel);
        this.add(lowestPriceText);
        this.add(lowestPrice);
        this.add(highestPriceText);
        this.add(highestPrice);
        this.add(bottomLabel);
        this.add(prePriceLabel1);
        this.add(preTextLabel1);
        this.add(prePriceLabel2);
        this.add(preTextLabel2);
        this.add(prePriceLabel3);
        this.add(preTextLabel3);
        this.add(prePriceLabel4);
        this.add(preTextLabel4);
        this.add(prePriceLabel5);
        this.add(preTextLabel5);
        this.add(hyperlink);
        this.add(signature);
        this.add(version);
        this.add(copyright);

        // Add mouse listener for hyperlink label
        hyperlink.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/TurhanT"));
                } catch(IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change appearance of the label when mouse enters
                hyperlink.setText("<html><u>TheTT</u></html>");
                hyperlink.setForeground(Color.BLUE.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore the appearance of the label when mouse exits
                hyperlink.setText("TheTT");
                hyperlink.setForeground(Color.ORANGE);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth(), h = getHeight();
        // Set up gradient color
        Color color1 = new Color(154, 69, 254); // Start color
        Color color2 = new Color(26, 251, 154); // End color
        GradientPaint gp = new GradientPaint(300, 300, color1, w, h, color2);
        // Apply gradient paint and fill the component
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    // Create a label to hold Solana images
    private JLabel solanaImage(int x, int y) {
        // File path for images
        final String IMAGE_PATH = "assets\\sol-image.png";
        // Create ImageIcon to be used as a logo
        ImageIcon imageIcon  = new ImageIcon(IMAGE_PATH);
        // Scale the image to the desired resolution
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // Create a new ImageIcon from the scaled image
        ImageIcon scaledIcon = new ImageIcon(image);
        // Set the picture for the label
        JLabel imageLabel = new JLabel(scaledIcon);
        // Set location and size of the label
        imageLabel.setBounds(x, y, 50, 50);
        // Return the JLabel
        return imageLabel;
    }

    // Create a label to display current time
    public static JLabel currentTimeLabel(String currentTime, int x, int y, int width, int height) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(x, y, width, height);
        // Set background color
        text.setBackground(new Color(12, 14, 18));
        // Set foreground color
        text.setForeground(new Color(240, 185, 11));
        // Set opaque
        text.setOpaque(true);
        // Set Font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, 40));
        // Align the text to the center
        text.setHorizontalAlignment(SwingConstants.CENTER); 
        // Center the text vertically
        text.setVerticalAlignment(JLabel.CENTER);
        // Set text 
        text.setText(currentTime);
        // Return the JLabel
        return text;
    }

    //  Create a Label to show price of Solana
    private JLabel createLabel(String price, int x, int y, int width, int height, int fontSize) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(x, y, width, height);
        // Set background color
        text.setBackground(new Color(12, 14, 18));
        // Set foreground color
        text.setForeground(new Color(240, 185, 11));
        // Set opaque
        text.setOpaque(true);
        // Align the text to the center
        text.setHorizontalAlignment(SwingConstants.CENTER); 
        // Center the text vertically
        text.setVerticalAlignment(JLabel.CENTER);
        // Set Font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, fontSize));
        // Set text
        text.setText(price);
        // Return JLabel
        return text;
    }

    // Create a label to show change percentage in Solana's price
    public static JLabel percentageLabel(String percentage) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(390, 150, 60, 60);
        // Set foreground color
        text.setForeground(Color.GRAY);
        // Set opaque
        text.setOpaque(true);
        // Set background color
        text.setBackground(new Color(12, 14, 18));
        // Align the text to the center
        text.setHorizontalAlignment(SwingConstants.CENTER); 
        // Center the text vertically
        text.setVerticalAlignment(JLabel.CENTER);
        // Set Font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, 14));
        // Set empty border for padding
        text.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        // Set text
        text.setText("0.00%");
        // Return JLabel
        return text;
    }

    // Create a background label for decoration at the bottom
    private JLabel transparentLabel(int x, int y, int width, int height) {
        JLabel body = new JLabel();
        // Set location and size of the panel
        body.setBounds(x, y, width, height);
        // Set background color
        body.setBackground(new Color(242, 241, 237, 90));
        // Set opaque
        body.setOpaque(true);
        // Return JLabel
        return body;
    }

    // Create a label to show previous prices of Solana
    private JLabel prePriceLabel(String price, int x) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(x, 355, 70, 40);
        // Set background color
        text.setBackground(new Color(242, 241, 237));
        // Set foreground color
        text.setForeground(Color.BLACK);
        // Align the text to the center
        text.setHorizontalAlignment(SwingConstants.CENTER); 
        // Center the text vertically
        text.setVerticalAlignment(JLabel.CENTER);
        // Set opaque
        text.setOpaque(true);
        // Set font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, 16));
        // Set text
        text.setText(price);
        // Set bevel border
        text.setBorder(BorderFactory.createBevelBorder(0));
        // Return JLabel
        return text;
    }

    // Create a label to tag previous prices of Solana
    private JLabel preTextLabel(String message, int x) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(x, 395, 100, 20);
        // Set foreground color
        text.setForeground(Color.BLACK);
        // Align the text to the center
        text.setHorizontalAlignment(SwingConstants.CENTER);
        // Center the text vertically
        text.setVerticalAlignment(JLabel.CENTER);
        // Set font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, 12));
        // Set text
        text.setText(message);
        // Return JLabel
        return text;
    }

    // Create a label to display text at the bottom of the panel
    private JLabel bottomText(int x, int y, int width, int height, String message) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(x, y, width, height);
        // Set foreground color
        text.setForeground(Color.BLACK);
        // Set font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, 12));
        // Set text
        text.setText(message);
        // Return JLabel
        return text;
    }

}