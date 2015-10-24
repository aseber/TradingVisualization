//import java.util.Date;
import java.util.LinkedList;

// Only 10 instances of stocks should be here. Each stock has an arraylist of StockNodes that is an instance of time for that stock


class Stock {
    public String ticker;
    private int maxLinkedListSize;
    public LinkedList<StockNode> stockTimes = new LinkedList<StockNode>();

    public Stock (String ticker, double netWorth, double dividend, double volatility) {
        this.ticker = ticker;
        this.maxLinkedListSize = VisualizationBase.STOCK_HISTORY_TIME * (1000 / VisualizationBase.STOCK_CHECK_INTERVAL) / 1000;
        StockNode initialStockNode = new StockNode(netWorth, dividend, volatility, 0, System.currentTimeMillis());
        addNode(initialStockNode);
    }
    
    // This method pushes elements to the LinkedList and removes them from the end if they are larger than
    // the max size based on the number of polls per second, and the time history we want to keep.
    // The first element is the oldest, the last is the newest.
    
    private boolean addNode(StockNode stockNode) {
    	if (stockTimes.size() > maxLinkedListSize) {
    		stockTimes.removeFirst();
    	}
    	
    	return stockTimes.add(stockNode);
    	
    }
    
    boolean addNode(double netWorth, double dividend, double volatility) {
    	if (stockTimes.size() > maxLinkedListSize) {
    		stockTimes.removeFirst();
    	}
    	
    	long currentTime = System.currentTimeMillis();
    	double velocity = (netWorth - stockTimes.getLast().netWorth) / (currentTime - stockTimes.getLast().currentTime);
    	
    	StockNode newStockNode = new StockNode(netWorth, dividend, volatility, velocity, currentTime);
    	return stockTimes.add(newStockNode);
    	
    }
    
    double getCurrentNetWorth() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().netWorth;
    }
    
    /*double getCurrentMaxBid() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().maxBid;
    }
    
    double getCurrentMinAsk() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().minAsk;
    }*/
    
    double getCurrentDividend() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().dividend;
    }
    
    double getCurrentVelocity() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().velocity;
    }
    
    double getCurrentVolatility() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().volatility;
    }
    
    // Need methods for the 
    
    void updateMaxLinkedListSize() {
    	this.maxLinkedListSize = VisualizationBase.STOCK_HISTORY_TIME * (1000 / VisualizationBase.STOCK_CHECK_INTERVAL) / 1000;
    }
    
    
    
}