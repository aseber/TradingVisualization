public class StockNode {

	protected double netWorth;
	protected double dividend;
	protected double volatility;
	protected double velocity;
	protected long currentTime;
    
	StockNode(double netWorth, double dividend, double volatility, double velocity, long currentTime) {
		this.netWorth = netWorth;
		this.dividend = dividend;
		this.volatility = volatility;
		this.velocity = velocity;
		this.currentTime = currentTime;
	}
}
