import java.io.IOException;
import java.util.ArrayList;

public class testMain
{
	public static void main(String[] args)
	{
		String host = "codebb.cloudapp.net";
	    int port = 17429;
	    String user = "TraderGators";
	    String pass = "insidertrading";

		TradingPlatform t = new TradingPlatform(host, port, user, pass);
		
		
//		ArrayList<Double> a = t.getBids("SNY");
//			
//		for(int i = 0; i < a.size(); i++)
//		{
//			System.out.println(a.get(i));
//		}
			
		System.out.println(t.getCash());
		
		System.out.println(t.bid("TWTR", 34.401, 10));
		
		System.out.println(t.getCash());
	}
		
}
