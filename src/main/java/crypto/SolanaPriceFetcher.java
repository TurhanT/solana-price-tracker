package crypto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SolanaPriceFetcher {

    public static boolean isErrorOccurred;
    public static String responseCodeString;

    public static String getSolanaPrice() throws IOException {
        // URL for the API to get Solana price
        String apiUrl = "https://api.coingecko.com/api/v3/simple/price?ids=solana&vs_currencies=usd";
        // Initialize an URL object using API URL
        URL url = new URL(apiUrl);
        // Open the connection using the URL
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Use GET method to retrieve data from the server
        conn.setRequestMethod("GET");
        // Wait up to 10 seconds to establish the connection
        conn.setConnectTimeout(10000);
        // Wait up to 5 seconds to for server to send data once connection is established
        conn.setReadTimeout(5000);
        // Set response code into a int variable 200
        int responseCode = conn.getResponseCode();
        // If response code is equal to HTTP_OK (200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read server's response body
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // Initialize a string variable to store each line of text
            String inputLine;
            StringBuilder response = new StringBuilder();
            // While line of text is not null
            while ((inputLine = in.readLine()) != null) {
                // Add line of text read from the input stream
                response.append(inputLine);
            }
            in.close();
            // Convert 'response' StringBuilder to a response to String get Solana price
            String jsonResponse = response.toString();
            // Set isErrorOccurred variable to false since no error occurred during Solana price fetch
            isErrorOccurred = false;
            // Extract Solana price from the String variable
            return jsonResponse.split(":")[2].replaceAll("[^0-9.]", ""); 
        } else {
            // Set isErrorOccurred variable to true because error occurred during Solana price fetch
            isErrorOccurred = true;
            // Convert the HTTP response code to a String and store it in responseCodeString variable
            responseCodeString = String.valueOf(responseCode);
            // Return null to handle the scenario where the Error panel is opened instead of the Solana panel
            return null;
        }
    }



}

