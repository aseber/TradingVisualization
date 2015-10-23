public class SortBubble extends Sort {

	private boolean swapped;
	private int i;
	
	public SortBubble(ElementArray inputArray) {
		super(inputArray);
		
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			do {
			
				swapped = false;
			
				for (i = 1; i < array.size() - 1; i++) {
					
					checkWait();
					
					if (array.compare(i - 1, i) > 0) {
						
						array.swap(i - 1, i);
						swapped = true;
						
					}
					
				}
				
			} while (swapped);
			
		}
		
	}

}
