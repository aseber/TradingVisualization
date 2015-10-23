public class SortExecutor implements Runnable { // Simple class that allows me to move the processing to a new thread so the UI doesn't lag.
													// Also lets me test the algorithms speed
	private Sort sort;
	private Thread sortingThread;
	private long start_time;
	
	@Override
	public void run() {

		synchronized (this) {
		
			while (true) {
				
				if (isSorting()) {
				
					VisualizationBase.VISUALIZATION_GUI.setAccessCounter(sort.array.counter.getAccesses());
					VisualizationBase.VISUALIZATION_GUI.setCompareCounter(sort.array.counter.getCompares());
					VisualizationBase.VISUALIZATION_GUI.setSetCounter(sort.array.counter.getSets());
					long intermiediate_time = System.currentTimeMillis();
					VisualizationBase.VISUALIZATION_GUI.setRunTimeCounter(intermiediate_time - start_time);
					
				}

				try {
					
					wait(100); // So we could write a method in Sort to lock this thread until running is true, but I don't feel like writing it lol
								// So the result is to poll this method every 100 ms and check if it is sorting and just set it every 'tick'
				
				} catch (InterruptedException e) {}
				
			}
			
		}
		
	}
	
	public void runSort(ElementArray inputArray, Sort.algorithms sortingAlgorithm) {

		synchronized (this) {
		
			if (sort != null) {
				
				if (sort.isRunning()) {
				
					if (!sort.isPaused()) {
					
						sort.pause();
					
					}
					
					else {
						
						sort.unpause();
						
					}
					
				}
				
				else {
					
					inputArray.counter.resetCounters();
					sort = VisualizationBase.CURRENT_ALGORITHM.sort(inputArray);
					start_time = System.currentTimeMillis();
					sortingThread = new Thread(sort);
					sortingThread.start();
					this.notify();
					
				}
				
			}
			
			else {
				
				sort = VisualizationBase.CURRENT_ALGORITHM.sort(inputArray);
				start_time = System.currentTimeMillis();
				sortingThread = new Thread(sort);
				sortingThread.start();
				this.notify();
				
			}
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void stopSort() {
		
		if (sort != null) {
			
			sort.stopSorting();
			sortingThread.stop();
			
		}
		
	}
	
	public boolean isSorting() {
		
		if (sort != null) {
			
			return sort.isRunning();
			
		}
		
		return false;
		
	}
	
}
