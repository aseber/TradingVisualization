public class SortBitonic extends Sort {

	public SortBitonic(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			int middle = array.size() / 2;
			bitonic_merge(true, 0, middle);
			bitonic_merge(false, middle + 1, array.size() - 1);
			
		}
		
	}

	public void bitonic_merge(boolean direction, int low, int high) {
		
		System.out.println(high - low);
		
		if (high - low <= 1) {
			
			return;
			
		}
		
		int middle = low + (high - low) / 2;
		compare(direction, low, high);
		bitonic_merge(direction, low, middle);
		bitonic_merge(direction, middle + 1, high);
		
	}
	
	public void compare(boolean direction, int low, int high) {
		
		int middle = low + (high - low) / 2;
		
		for (int i = low; i < middle; i++) {
			
			checkWait();
			
			if (array.compare(i, high - i) > 0 == direction) {
				
				array.swap(i, high - i);
				
			}
			
		}
		
	}
	
}
