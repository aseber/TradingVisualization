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

		tradingPlatform t = new tradingPlatform(host, port, user, pass);
		try
		{
			t.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try
		{
		
	//		ArrayList<Double> a = t.getBids("SNY");
			
	//		for(int i = 0; i < a.size(); i++)
	//		{
	//			System.out.println(a.get(i));
	//		}
			
			System.out.println(t.getCash());
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage());
		}
		
		t.closeConnection();
	}
}