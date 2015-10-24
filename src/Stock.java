//import java.util.Date;
import java.util.LinkedList;

// Only 10 instances of stocks should be here. Each stock has an arraylist of StockNodes that is an instance of time for that stock


class Stock {
    public String ticker;
    public int maxLinkedListSize;
    
    private LinkedList<StockNode> stockTimes = new LinkedList<StockNode>();
    //public Date time;

    public Stock (String ticker) {
        this.ticker = ticker;
        this.maxLinkedListSize = VisualizationBase.STOCK_HISTORY_TIME * (1000 / VisualizationBase.STOCK_CHECK_INTERVAL) / 1000;
    }

    // This method pushes elements to the LinkedList and removes them from the end if they are larger than
    // the max size based on the number of polls per second, and the time history we want to keep.
    // The first element is the oldest, the last is the newest.
    
    boolean addNode(StockNode newStockNode) {
    	if (stockTimes.size() > maxLinkedListSize) {
    		stockTimes.removeFirst();
    	}
    	
    	return stockTimes.add(newStockNode);
    	
    }
    
    double getCurrentNetWorth() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().netWorth;
    }
    
    double getCurrentMaxBid() {
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
    }
    
    double getCurrentDividend() {
    	if (stockTimes.isEmpty()) {
    		return 0;
    	}
    	
    	return stockTimes.getLast().dividend;
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