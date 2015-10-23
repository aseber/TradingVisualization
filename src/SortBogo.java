import java.util.Random;


public class SortBogo extends Sort {

	public SortBogo(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			while (!isInOrder()) {
		
				checkWait();
				
				shuffle();
				
			}
			
		}
		
	}

	private boolean isInOrder() {
		
		for (int i = 1; i < array.size(); i++) {
			
			if (array.compare(i, i + 1) > 0) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	private void shuffle() {
		
		Random random = new Random();
		
		for (int i = 1; i < array.size(); i++) {
			
			array.swap(random.nextInt(array.size()), random.nextInt(array.size()));
			
		}
		
	}
	
}
