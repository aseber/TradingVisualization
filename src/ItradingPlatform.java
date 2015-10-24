import java.util.ArrayList;

interface ItradingPlaform
{
	double getBid(String securityName);

	double getAsk(String securityName);

	double getCash();

	ArrayList<String> getSecurities();

	boolean bid();

	boolean ask();

	boolean clearBid();

	boolean clearAsk();

	boolean closeConnection();

}