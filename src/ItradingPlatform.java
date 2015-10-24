interface ItradingPlaform
{
	double getBid(String securityName);

	double getAsk(String securityName);

	double getCash();

	ArrayList<String> getSecurities();

	bool bid();

	bool ask();

	bool clearBid();

	bool clearAsk();

	bool closeConnection();

}