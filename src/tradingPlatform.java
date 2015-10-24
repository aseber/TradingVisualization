import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

class tradingPlatform extends Thread implements ItradingPlaform {

    Socket socket;
    PrintWriter pout;
    BufferedReader bin;
	private String host;
	private int port;
	private String user;
	private String pass;
	private boolean connection;

	@Override
	public void start() {
		synchronized(this) {
			while(true) {
				VisualizationBase.VISUALIZATION_GUI.stocks = iterateStocks(VisualizationBase.VISUALIZATION_GUI.stocks); 
				
				try {
					Thread.sleep(VisualizationBase.STOCK_CHECK_INTERVAL);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	HashMap<String, Stock> iterateStocks(HashMap<String, Stock> stocks) {
		ArrayList<String[]> newStockValues = getAllSecurities();
		for (String[] data : newStockValues) {
			stocks.get(data[0]).addNode(Double.parseDouble(data[1]), Double.parseDouble(data[2]),  Double.parseDouble(data[3]));
			System.out.println(data[0] + " " + stocks.get(data[0]).getCurrentVelocity());
		}
		
		System.out.println(stocks.get("AAPL").stockTimes.size());
		return stocks;
	}
	
	tradingPlatform(String host, int port, String user, String pass) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
		createConnection();
		openConnection();
	}

	public ArrayList<ArrayList<Double>> getOrders(String ticker) {
		if(!connection) {
			return null;
		}
		
		ArrayList<ArrayList<Double>> orders = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> bids = new ArrayList<Double>();
		ArrayList<Double> asks = new ArrayList<Double>();
		String[] output = sendCommand("ORDERS " + ticker);
		
		for (int index = 1; index < output.length; index += 4) {
			if (output[index].equals("BID")) {
				bids.add(Double.parseDouble(output[index + 2]));
			} else if (output[index].equals("ASK")) {
				asks.add(Double.parseDouble(output[index + 2]));
			}
		}
		
		bids = sortHigh(bids);
		asks = sortLow(asks);
		orders.add(bids);
		orders.add(asks);
		return orders;
		
	}
	
	public ArrayList<Double> getBids(String ticker) {
		if(!connection) {
			return null;
		}
		
		ArrayList<Double> bids = new ArrayList<Double>();
		String[] output = sendCommand("ORDERS " + ticker);
		
		for (int index = 1; index < output.length; index += 4) {
			if (output[index].equals("BID")) {
				bids.add(Double.parseDouble(output[index + 2]));
			}
		}
		
		return sortHigh(bids);
	}
	
	public ArrayList<Double> getAsks(String ticker) {
		if(!connection) {
			return null;
		}
		
		ArrayList<Double> asks = new ArrayList<Double>();
		String[] output = sendCommand("ORDERS " + ticker);
		
		for (int index = 1; index < output.length; index += 4) {
			if (output[index].equals("ASK")) {
				asks.add(Double.parseDouble(output[index + 2]));
			}
		}
		
		return sortLow(asks);
	}

	public ArrayList<ArrayList<String[]>> getMyOrders() {
		if(!connection) {
			return null;
		}
		
		ArrayList<ArrayList<String[]>> orders = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> bids = new ArrayList<String[]>();
		ArrayList<String[]> asks = new ArrayList<String[]>();
		String[] output = sendCommand("MY_ORDERS");
		
		for (int index = 1; index < output.length; index += 4) {
			if (output[index].equals("BID")) {
				String[] currentBid = new String[3];
				currentBid[0] = output[index + 1];
				currentBid[1] = output[index + 2];
				currentBid[2] = output[index + 3];
				bids.add(currentBid);
			} else if (output[index].equals("ASK")) {
				String[] currentBid = new String[3];
				currentBid[0] = output[index + 1];
				currentBid[1] = output[index + 2];
				currentBid[2] = output[index + 3];
				asks.add(currentBid);
			}
		}

		orders.add(bids);
		orders.add(asks);
		return orders;
		
	}
	
	public ArrayList<String[]> getMyBids() {
		if(!connection) {
			return null;
		}
		
		ArrayList<String[]> bids = new ArrayList<String[]>();
		String[] output = sendCommand("MY_ORDERS");
		
		for (int index = 1; index < output.length; index += 4) {
			if (output[index].equals("BID")) {
				String[] currentBid = new String[3];
				currentBid[0] = output[index + 1];
				currentBid[1] = output[index + 2];
				currentBid[2] = output[index + 3];
				bids.add(currentBid);
			}
		}
		
		return bids;
	}
	
	public ArrayList<String[]> getMyAsks() {
		if(!connection) {
			return null;
		}
		
		ArrayList<String[]> bids = new ArrayList<String[]>();
		String[] output = sendCommand("MY_ORDERS");
		
		for (int index = 1; index < output.length; index += 4) {
			if (output[index].equals("ASK")) {
				String[] currentBid = new String[3];
				currentBid[0] = output[index + 1];
				currentBid[1] = output[index + 2];
				currentBid[2] = output[index + 3];
				bids.add(currentBid);
			}
		}
		
		return bids;
	}
	
	public double getCash() {
		if(!connection) {
			return Double.MIN_VALUE;
		}
		
		return Double.parseDouble(sendCommand("MY_CASH")[1]);
	}

	public ArrayList<String[]> getMySecurities() {
		if(!connection) {
			return  null;
		}
		
		ArrayList<String[]> securities = new ArrayList<String[]>();
		String[] output = sendCommand("MY_SECURITIES");
		
		for (int index = 1; index < output.length; index += 3) {
			String[] currentSecurity = new String[3];
			currentSecurity[0] = output[index];
			currentSecurity[1] = output[index + 1];
			currentSecurity[2] = output[index + 2];
			securities.add(currentSecurity);
		}
		
		return securities;
	}
	
	public ArrayList<String[]> getAllSecurities() {
		if(!connection) {
			return  null;
		}
		
		ArrayList<String[]> securities = new ArrayList<String[]>();
		String[] output = sendCommand("SECURITIES");
		
		for (int index = 1; index < output.length; index += 4) {
			String[] currentSecurity = new String[4];
			currentSecurity[0] = output[index];
			currentSecurity[1] = output[index + 1];
			currentSecurity[2] = output[index + 2];
			currentSecurity[3] = output[index + 3];
			securities.add(currentSecurity);
		}
		
		return securities;
	}

	public boolean bid(String ticker, double price, int shares) {
		if(!connection) {
			return false;
		}
		
		String[] output = sendCommand("BID " + ticker + " " + price + " " + shares);
		if (output[0].equals("BID_OUT DONE")) {
			return true;
		}
		
		return false;
	}

	public boolean ask(String ticker, double price, int shares) {
		if(!connection) {
			return false;
		}
		
		String[] output = sendCommand("ASK " + ticker + " " + price + " " + shares);
		if (output[0].equals("ASK_OUT DONE")) {
			return true;
		}
		
		return false;
	}	
	
	public boolean clearBid(String ticker) {
		String[] output = sendCommand("CLEAR_BID " + ticker);
		if (output[0].equals("CLEAR_BID_OUT") && output[1].equals("DONE")) {
			return true;
		}
		
		return false;
	}

	public boolean clearAsk(String ticker) {
		String[] output = sendCommand("CLEAR_ASK " + ticker);
		if (output[0].equals("CLEAR_ASK_OUT") && output[1].equals("DONE")) {
			return true;
		}
		
		return false;
	}
	
	protected void finalize() {
		closeConnection();
	}
	
	// This is the ENTIRE command, after it we assume we are flushing and then going to get a String[]
	
	public String[] sendCommand(String command) {
		pout.println(command);
		pout.flush();
		return getResult();
	}
	
	public boolean closeConnection() {
		try {
	        pout.println("CLOSE_CONNECTION");
	        pout.flush();
			pout.close();
        	bin.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		connection = false;
		
		return true;
	}

	public boolean createConnection() {
		try {
			socket = new Socket(host, port);
		} catch (Exception e) {
			e.printStackTrace();
			closeConnection();
			return false;
		}
		
		return true;
	}
	
	public boolean openConnection() {
		try {
			pout = new PrintWriter(socket.getOutputStream());
			bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pout.println(user + " " + pass);
			connection = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			closeConnection();
			return false;
		}
		
		return true;
	}
	
	ArrayList<Double> sortHigh(ArrayList<Double> array) {
		array.sort(new Comparator<Object>() {

			@Override
			public int compare(Object arg0, Object arg1) {
				
				return (int) Math.signum((double) arg1 - (double) arg0);
			}
			
		});
		
		return array;
	}
	
	ArrayList<Double> sortLow(ArrayList<Double> array) {
		array.sort(new Comparator<Object>() {
	
			@Override
			public int compare(Object arg0, Object arg1) {
				
				return (int) Math.signum((double) arg0 - (double) arg1);
			}
			
		});

		return array;
	}
		
	private String[] getResult() {
		String[] lines = null;
		
		try {
			lines = bin.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
			closeConnection();
		}
		
        return lines;
	}

}

