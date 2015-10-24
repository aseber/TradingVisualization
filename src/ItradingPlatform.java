import java.util.ArrayList;

interface ItradingPlaform
{
	
	//returns an ArrayList of doubles where each value corresponds to [i,i+1]  and [value, quantity]
	ArrayList<Double> getBids(String securityName);

	//returns an ArrayList of doubles where each value corresponds to [i,i+1]  and [value, quantity]
	ArrayList<Double> getAsks(String securityName);

	double getCash();

	ArrayList<String[]> getMySecurities();
	
	ArrayList<String[]> getAllSecurities();

	boolean ask(String ticker, double price, int shares);

	boolean bid(String ticker, double price, int shares);

	boolean clearBid(String ticker);

	boolean clearAsk(String ticker);

	boolean closeConnection();
	
	boolean openConnection();

}