public class testMain
{
	public static void main(String[] args)
	{
		String host = "codebb.cloudapp.net";
	    int port = 17429;
	    String user = "TraderGators";
	    String pass = "insidertrading";

		tradingPlatform t = new tradingPlatform(host, port, user, pass);
		t.getBid("SNY");
	}
}