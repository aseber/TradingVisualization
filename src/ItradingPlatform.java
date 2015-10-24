import java.util.ArrayList;

interface ItradingPlaform
{
	ArrayList<Double> getBids(String securityName);

	ArrayList<Double> getAsks(String securityName);

	double getCash();

	ArrayList<String> getSecurities();

	boolean bid();

	boolean ask();

	boolean clearBid();

	boolean clearAsk();

	boolean closeConnection();

}