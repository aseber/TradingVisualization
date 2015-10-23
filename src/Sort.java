
public abstract class Sort implements Runnable {
	
	public static enum algorithms {
		
		BOGO ("Bogo Sort") {Sort sort(ElementArray array) {return new SortBogo(array);}},
		//HEAP ("Heap Sort"),
		GNOME ("Gnome Sort") {Sort sort(ElementArray array) {return new SortGnome(array);}},
		MERGE ("Merge Sort") {Sort sort(ElementArray array) {return new SortMerge(array);}},
		SHELL ("Shell Sort") {Sort sort(ElementArray array) {return new SortShell(array);}}, // Works but needs extra data regarding sizes!
		QUICK ("Quick Sort") {Sort sort(ElementArray array) {return new SortQuick(array);}},
		BUBBLE ("Bubble Sort") {Sort sort(ElementArray array) {return new SortBubble(array);}},
		SHAKER ("Shaker Sort") {Sort sort(ElementArray array) {return new SortShaker(array);}},
		BITONIC ("Bitonic Sort") {Sort sort(ElementArray array) {return new SortBitonic(array);}},
		//STDSORT ("std::sort"),
		//RADIXLSD ("Radix LSD Sort"),
		//RADIXMSD ("Radix MSD Sort"),
		INSERTION ("Insertion Sort") {Sort sort(ElementArray array) {return new SortInsertion(array);}},
		SELECTION ("Selection Sort") {Sort sort(ElementArray array) {return new SortSelection(array);}};
		//STDSTABLESORT ("std::stablesort");
		
		private final String name;
		
		private algorithms(String s) {
		
			name = s;
			
		}
	
		abstract Sort sort(ElementArray array);
		
		public String toString() {
			
			return this.name;
			
		}
	
	};
	
	protected ElementArray array = null;
	private boolean running;
	private boolean sorted;
	private boolean paused;
	private long start_time;
	
	public Sort(ElementArray inputArray) {
		
		array = inputArray;
		
	}
	
	public abstract void sort(); 
	
	public final void run() {
		
		running = true;
		paused = false;
		sorted = false;
		VisualizationBase.VISUALIZATION_GUI.setRunButtonState(false);
		start_time = System.currentTimeMillis();
		sort();
		long end_time = System.currentTimeMillis();
		VisualizationBase.VISUALIZATION_WINDOW.repaintAllElements();
		VisualizationBase.VISUALIZATION_GUI.setAccessCounter(array.counter.getAccesses());
		VisualizationBase.VISUALIZATION_GUI.setCompareCounter(array.counter.getCompares());
		VisualizationBase.VISUALIZATION_GUI.setSetCounter(array.counter.getSets());
		VisualizationBase.VISUALIZATION_GUI.setRunTimeCounter(end_time - start_time);
		VisualizationBase.VISUALIZATION_GUI.setRunButtonState(true);
		running = false;
		sorted = true;
		
	}
	
	protected final void checkWait() {
		
		try {
			
			if (VisualizationBase.SLEEP_TIMER > 0) {
				
				this.wait(VisualizationBase.SLEEP_TIMER);
				
			}
			
			if (paused) {
				
				this.wait();
				
			}
			
		} catch (InterruptedException e) {}
		
	}
	
	public final void pause() {
		
		paused = true;
		VisualizationBase.VISUALIZATION_GUI.setRunButtonState(true);
		
	}
	
	public final void unpause() {
		
		synchronized (this) {
			
			paused = false;
			VisualizationBase.VISUALIZATION_GUI.setRunButtonState(false);
			this.notify();
			
		}
		
	}
	
	public final void stopSorting() {
		
		running = false;	
		VisualizationBase.VISUALIZATION_GUI.setRunButtonState(true);
		
	}
	
	public final boolean isRunning() {
		
		return running;
		
	}
	
	public final boolean isSorted() {
		
		return sorted;
		
	}

	public final boolean isPaused() {
		
		return paused;
		
	}
	
}
