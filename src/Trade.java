
public class Trade implements Comparable<Object>{
	protected String ticker;
	protected double price;
	public int numShares;
	protected long currentTime;
	
	Trade(String ticker, double price, int numShares) {
		this.ticker = ticker;
		this.price = price;
		this.numShares = numShares;
		this.currentTime = System.currentTimeMillis();
	}

	@Override
	public int compareTo(Object arg0) {
		return (int) Math.signum(this.currentTime - ((Trade) arg0).currentTime);
	}
}
