
public class SortQuick extends Sort {

	public SortQuick(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			split(0, array.size() - 1);
			
		}
		
	}

	private void split(int low, int high) {
		
		if (low < high) {
		
			int partitionIndex = partition(low, high);
			split(low, partitionIndex);
			split(partitionIndex + 1, high);
			
		}
		
	}
	
	private int partition(int low, int high) {
		
		Element pivot = array.get(low);
		int i = low;
		int j = high + 1;
		
		while (true) {
			
			checkWait();
			
			j--;
			
			while (array.compare(j, pivot) > 0) {
			
				j--;
				
			}
			
			while (array.compare(i, pivot) < 0) {
				
				i++;
				
			}
			
			if (i < j) {
				
				array.swap(i, j);
				
			}
			
			else {
				
				return j;
				
			}
			
		}
		
	}
	
}
