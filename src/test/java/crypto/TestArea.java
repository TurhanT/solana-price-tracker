package crypto;


public class TestArea {

    private static String[] prevSolPrices = {"10.5", "20.75", "15.2", "18.6", "12.8", "212.2", "10.2"};

    public static void main(String[] args){
        highestPrice();
        lowestPrice();
    }

    private static void highestPrice() {
        // Set the smallest number possible
        double highestPrice = Double.MIN_VALUE;
        String highestPriceString = "";
        for(int i = 0; i < prevSolPrices.length; i++) {
            double price = Double.parseDouble(prevSolPrices[i]);
            if (price > highestPrice) {
                highestPrice = price;
                highestPriceString = prevSolPrices[i];
            }
        }
        //MidPanel.highestPrice.setText(highestPriceString);
        System.out.println(highestPrice);
    }

    private static void lowestPrice() {
        // Set the largest number possible
        double lowestPrice = Double.MAX_VALUE;
        String lowestPriceString = "";
        for(int i = 0; i < prevSolPrices.length; i++) {
            double price = Double.parseDouble(prevSolPrices[i]);
            if (price < lowestPrice) {
                lowestPrice = price;
                lowestPriceString = prevSolPrices[i];
            }
        }
        //MidPanel.highestPrice.setText(lowestPriceString);
        System.out.println(lowestPriceString);
    }

}








