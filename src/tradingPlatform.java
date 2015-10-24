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

	public double getBid(String securityName)
	{
		try 
		{
			openConnection();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//HelloWorld
		pout.print("ORDERS " + securityName);

		StringBuilder parse = null;
		try
		{
			parse = closeConnectionMethod();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(parse.toString());
		return 0;
	}

	public double getAsk(String securityName)
	{
		return 0;
	}

	public double getCash()
	{
		return 0;
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
		return false;
	}

	private void openConnection() throws IOException
	{
		pout = new PrintWriter(socket.getOutputStream());
		bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pout.println(user + " " + pass);
	}

	private StringBuilder closeConnectionMethod() throws IOException
	{
		StringBuilder ret = new StringBuilder();
		pout.println();
        pout.println("CLOSE_CONNECTION");
        pout.flush();
        String line;
        while ((line = bin.readLine()) != null) {
            ret.append(line);
            ret.append("\n");
        }
        pout.close();
        bin.close();
        
        return ret;
	}
}

