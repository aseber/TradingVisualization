import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.lang.StringBuilder;

class tradingPlatform implements ItradingPlaform
{

	// String host = "codebb.cloudapp.net";
    // int port = 17429;
    // String user = "TraderGators";
    // String pass = "insidertrading";
    Socket socket;
    PrintWriter pout;
    BufferedReader bin;
	private String host;
	private int port;
	private String user;
	private String pass;
	private boolean connection;

	tradingPlatform(String host, int port, String user, String pass)
	{
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;

		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Double> getBids(String securityName)
	{
		return getOrder(securityName, "BID");
	}
	
	public ArrayList<Double> getAsks(String securityName)
	{
		return getOrder(securityName, "ASK");
	}
	
	private ArrayList<Double> getOrder(String securityName, String bidOrAsk)
	{		
		if(!connection)
		{
			return null;
		}
		//HelloWorld
		pout.print("ORDERS " + securityName);

		StringBuilder parse = null;
		try
		{
			parse = pullValue();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(parse.toString());
		String[] split = parse.toString().split(" ");
		
		ArrayList<Double> toReturn = new ArrayList<Double>();
		
		for(int i = 0; i < split.length; i++)
		{
			if(split[i].equals(bidOrAsk))
			{
				i+=2;
				toReturn.add(Double.parseDouble(split[i]));
				i++;
				toReturn.add(Double.parseDouble(split[i]));
			}
		}
		
		return toReturn;
	}


	public double getCash()
	{
		if(!connection)
		{
			return (Double) null;
		}
		
		pout.print("MY_CASH");
		
		StringBuilder parse = null;
		try
		{
			parse = pullValue();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		parse.delete(0, 12);
		
		return Double.parseDouble(parse.toString());
	}

	public ArrayList<String> getSecurities()
	{
		return null;
	}

	public boolean bid()
	{
		return false;
	}

	public boolean ask()
	{
		return false;
	}

	public boolean clearBid()
	{
		return false;
	}

	public boolean clearAsk()
	{
		return false;
	}

	public boolean closeConnection()
	{
		try
		{
	        pout.println("CLOSE_CONNECTION");
	        pout.flush();
			pout.close();
        	bin.close();
		}
		catch(Exception E)
		{
			E.printStackTrace();
			return false;
		}
		connection = false;
		
		return true;
	}

	public boolean openConnection() throws IOException
	{
		connection = true;
		pout = new PrintWriter(socket.getOutputStream());
		bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pout.println(user + " " + pass);
		return true;
	}
	

	private StringBuilder pullValue() throws IOException
	{
		StringBuilder ret = new StringBuilder();
		pout.println();
        pout.flush();
        String line;
        while ((line = bin.readLine()) != null) {
            ret.append(line);
            ret.append("\n");
        }

        
        return ret;
	}
}

