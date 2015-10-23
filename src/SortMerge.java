public class SortMerge extends Sort{
	
	public SortMerge(ElementArray inputArray) {
		super(inputArray);
	}

	@Override
	public void sort() {
		synchronized (this) {	
			split(0, array.size() - 1);
		}
	}
	
	public void split(int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			split(low, middle);
			split(middle + 1, high);
			merge(low, middle, high);
		}
	}
	
	public void merge(int low, int middle, int high) {
		int i = low;
		int j = middle + 1;
		
		while (i < high && j <= high) {
			checkWait();
			
			// Remember, the data is always sorted left to right here.
			// 
			
			if (array.compare(i, j) > 0) {
				array.swap(i, j);
				j = Math.max(i + 1, j);
				i++;
			}
			
			i++;
		}
	}
}
