public class SortGnome extends Sort {

	int pos;
	
	public SortGnome(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			pos = 1;
			
			while (pos < array.size()) {
				
				checkWait();
				
				if (array.compare(pos, pos - 1) > 0) {
					
					pos++;
					
				}
				
				else {
					
					array.swap(pos, pos - 1);
					
					if (pos > 1) {
						
						pos--;
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
