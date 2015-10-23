public class SortInsertion extends Sort {

	private int i;
	private int j;
	private Element key;
	
	public SortInsertion(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
				
			for (i = 1; i < array.size(); i++) {
				
				key = array.get(i);
				
				for (j = i - 1; j >= 0 && array.compare(key, j) < 0; j--) {
					
					array.set(j + 1, array.get(j));
					checkWait();
					
				}
				
				array.set(j + 1, key);
				
			}
			
		}
			
	}

}
