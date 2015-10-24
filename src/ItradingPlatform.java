import java.util.ArrayList;

interface ItradingPlaform
{
	
	//returns an ArrayList of doubles where each value corresponds to [i,i+1]  and [value, quantity]
	ArrayList<Double> getBids(String securityName);

	//returns an ArrayList of doubles where each value corresponds to [i,i+1]  and [value, quantity]
	ArrayList<Double> getAsks(String securityName);

	double getCash();

	ArrayList<String[]> getSecurities();

	boolean ask(String ticker, double price, int shares);

	boolean bid(String ticker, double price, int shares);

	boolean clearBid();

	boolean clearAsk();

	boolean closeConnection();
	
	boolean openConnection();

}