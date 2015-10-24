public class StockNode {

	protected double netWorth;
	protected double maxBid;
	protected double minAsk;
	protected double dividend;
	protected double volatility;
	protected long currentTime;
    
	StockNode(double value, double maxBid, double minAsk, double dividend, double volatility) {
		this.netWorth = value;
		this.maxBid = maxBid;
		this.minAsk = minAsk;
		this.dividend = dividend;
		this.volatility = volatility;
		currentTime = System.currentTimeMillis();
	}
}
