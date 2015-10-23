
public class SortSelection extends Sort {

	private int i;
	private int j;
	private int iMin;
	
	public SortSelection(ElementArray inputArray) {
		super(inputArray);

	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			for (j = 0; j < array.size() - 1; j++) {
				
				iMin = j;
				checkWait();
				
				for (i = j + 1; i < array.size(); i++) {
					
					if (array.compare(i, iMin) < 0) {
						
						iMin = i;
						
					}
					
				}
				
				if (iMin != j) {
					
					array.swap(j, iMin);
					
				}
				
			}
			
		}
		
	}

}
