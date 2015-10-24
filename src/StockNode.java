public class StockNode {

	protected double netWorth;
	protected double dividend;
	protected double volatility;
	protected double maxBid;
	protected double minAsk;
	protected double velocity;
	protected long currentTime;
    
	StockNode(double netWorth, double dividend, double volatility, double maxBid, double minAsk, double velocity, long currentTime) {
		this.netWorth = netWorth;
		this.dividend = dividend;
		this.volatility = volatility;
		this.maxBid = maxBid;
		this.minAsk = minAsk;
		this.velocity = velocity;
		this.currentTime = currentTime;
	}
}
