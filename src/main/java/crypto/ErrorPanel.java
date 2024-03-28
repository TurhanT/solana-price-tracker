package crypto;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ErrorPanel extends JPanel {

    // Labels
    private JLabel title;
    private JLabel text1;
    private JLabel text2;
    private JLabel text3;
    private JLabel text4;
    private JLabel text5;
    private JLabel signature;
    private JLabel version;
    private JLabel hyperlink1 = new JLabel("link");
    private JLabel hyperlink2 = new JLabel("TheTT");
    // Set path for the GIF file
    final String errorImagePath = "assets\\error-image.gif";
    // Load the GIF
    ImageIcon logo = new ImageIcon(errorImagePath);
    // Create a JLabel with the GIF
    JLabel errorGIF = new JLabel(logo);
    
    // Constructor method for creating a ErrorPanel object
    ErrorPanel() {
        // Set location and size of the panel
        this.setBounds(0, 0, 700, 500);
        // Set the layout manager to null (to use absolute positioning)
        this.setLayout(null);
        // Set background color
        this.setBackground(new Color(181, 192, 208));
        // Invoke method to create JLabel for text and set alignment to left and foreground color
        title = createLabel("Shoot!", 10, 20, 400, 100);
        title.setHorizontalAlignment(SwingConstants.LEFT); 
        title.setForeground(new Color(255, 32, 78));
        // Invoke method to create JLabel for text and set font size
        text1 = createLabel("Well, this is unexpected...", 20, 140, 400, 40);
        text1.setFont(new Font("Century", Font.BOLD, 36));
        // Invoke method to create JLabel for error code and set font size
        text2 = createLabel("Error Code: " + SolanaPriceFetcher.responseCodeString, 22, 200, 380, 20);
        text2.setFont(new Font("Century", Font.BOLD, 14));
        // Invoke method to create JLabel for text and set font size
        text3 = createLabel("<html>An error occurred while retrieving <br>the price of <font color='#FF204E'>Solana!</font></html>", 22, 230, 380, 50);
        text3.setFont(new Font("Century", Font.BOLD, 18));
        // Invoke method to create JLabel for text and set font size
        text4 = createLabel("To access error code details and find more information, please check the ", 5, 400, 370, 50);
        text4.setFont(new Font("Century", Font.BOLD, 10));
        // Invoke method to create JLabel for text and set font size and use html tags
        text5 = createLabel("<html>Tip: Excessive opening and closing of the app can <br>lead to HTTP Error 429 (Too Many Requests)</html>", 5, 360, 370, 50);
        text5.setFont(new Font("Century", Font.BOLD, 10));
        // Set properties for hyperlink1 to link to the HTTP code documentation website
        hyperlink1.setBounds(366, 400, 50, 50);
        hyperlink1.setForeground(new Color(255, 32, 78));
        hyperlink1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink1.setFont(new Font("Century", Font.BOLD, 10));
        // Invoke method to create JLabel for signature and set font size
        signature = createLabel("Developed by ", 5, 440, 80, 15);
        signature.setFont(new Font("Century", Font.BOLD, 12));
        // Set properties for hyperlink1 to link to my Github profile
        hyperlink2.setBounds(83, 440, 50, 15);
        hyperlink2.setForeground(new Color(255, 32, 78));
        hyperlink2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink2.setFont(new Font("Century", Font.BOLD, 12));
        // Invoke method to create JLabel for version and set font size
        version = createLabel(SolanaFrame.version, 310, 440, 80, 15);
        version.setFont(new Font("Century", Font.BOLD, 12));
        // Set size and location of the GIF label
        errorGIF.setBounds(400, 20, 300, 500);
        // Add JLabels
        this.add(title);
        this.add(text1);
        this.add(text2);
        this.add(text3);
        this.add(text4);
        this.add(text5);
        this.add(signature);
        this.add(version);
        this.add(hyperlink1);
        this.add(hyperlink2);
        this.add(errorGIF);

        // Add mouse listener for hyperlink1 label
        hyperlink1.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event
                try {
                    Desktop.getDesktop().browse(new URI("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status"));
                } catch(IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change appearance of the label when mouse enters
                hyperlink1.setText("<html><u>link</u></html>");
                hyperlink1.setForeground(Color.BLUE.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore the appearance of the label when mouse exits
                hyperlink1.setText("link");
                hyperlink1.setForeground(new Color(255, 32, 78));
            }
        });

        // Add mouse listener for hyperlink2 label
        hyperlink2.addMouseListener(new MouseAdapter() {
            
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
                hyperlink2.setText("<html><u>TheTT</u></html>");
                hyperlink2.setForeground(Color.BLUE.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore the appearance of the label when mouse exits
                hyperlink2.setText("TheTT");
                hyperlink2.setForeground(new Color(255, 32, 78));
            }
        });
    }

    // Method to create labels includes text
    private JLabel createLabel(String message, int x, int y, int width, int height) {
        JLabel text = new JLabel();
        // Set location and size of the panel
        text.setBounds(x, y, width, height);
        // Set foreground color
        text.setForeground(Color.DARK_GRAY.darker());
        // Set Font type, style, and size
        text.setFont(new Font("Century", Font.BOLD, 120));
        // Align the text to the right horizontally
        text.setHorizontalAlignment(SwingConstants.LEFT); 
        // Center the text vertically
        text.setVerticalAlignment(JLabel.CENTER);
        // Set text
        text.setText(message);
        // Return the JLabel
        return text;
    }


}