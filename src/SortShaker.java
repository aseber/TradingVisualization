
public class SortShaker extends Sort {

	boolean swapped;
	int i;
	
	public SortShaker(ElementArray inputArray) {
		super(inputArray);
		
		
	}

	@Override
	public void sort() {

		synchronized (this) {
		
			do {
				
				swapped = false;
				
				for (i = 0; i < array.size() - 2; i++) {
					
					checkWait();
					
					if (array.compare(i, i + 1) > 0) {
						
						array.swap(i, i + 1);
						swapped = true;
						
					}
					
				}
				
				if (!swapped) {
					
					break;
					
				}
				
				for (i = array.size() - 2; i >= 0; i--) {
					
					checkWait();
					
					if (array.compare(i, i + 1) > 0) {
						
						array.swap(i, i + 1);
						swapped = true;
						
					}			
					
				}
				
			} while (swapped);
			
		}
		
	}

}
