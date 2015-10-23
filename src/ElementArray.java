import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ElementArray {
	
	// ElementArrays are an immutable data type that contains an ArrayList of elements.
	// The constructor should add all values to the array
	// and subsequent functions can modify the elementArray list, but not the copy
	// The structure keeps track of gets, sets and compares for use in testing how much each sorting algorithm uses each feature
	
	public static enum directions {
		
		FORWARD ("Forward Direction"),
		REVERSE ("Reverse Direction");
		
		private final String string;
		
		directions(String inputString) {
			
			string = inputString;
			
		}
		
		public String toString() {
			
			return string;
			
		}
		
	}
	
	public static enum orders {
		
		SORTED ("Sorted Order"),
		RANDOM ("Random Order"),
		ALMOST_SORTED ("Almost Sorted Order");
		
		private final String string;
		
		orders(String inputString) {
			
			string = inputString;
			
		}
		
		public String toString() {
			
			return string;
			
		}
		
	}
	
	public static enum uniqueness {
		
		ALL (1.0, "All unique"),
		FEW (0.75, "Few unique"),
		NONE (0.0, "No unique");
		
		private final double value;
		private final String string;
		
		uniqueness(double inputValue, String inputString) {
			
			value = inputValue;
			string = inputString;
			
		}
		
		public double getValue() {
			
			return value;
			
		}
		
		public String toString() {
			
			return string;
			
		}
		
	}
	
	private ArrayList<Element> elementArray = null;
	private List<Element> elementArrayCopy = null;
	protected Counter counter;
	private int offset;
	
	public ElementArray(int size, directions direction, orders order, uniqueness uniques, Counter inputCounter) { // Do we ever set the element index correctly?
		
		ArrayList<Element> basicElementArray = new ArrayList<Element>();
		
		elementArray = new ArrayList<Element>();
		elementArrayCopy = new ArrayList<Element>();
		counter = inputCounter;
		offset = 0;
		
		if (uniques.getValue() <= uniqueness.NONE.getValue() || uniques.getValue() > uniqueness.ALL.getValue()) {
			
			throw new IllegalArgumentException("Invalid uniques passed to ElementArray constructor: " + uniques);
			
		}
		
		int intervalIncrement = 0;
		
		if (uniques == uniqueness.ALL) {
			
			intervalIncrement = 1;
			
		}
		
		else if (uniques == uniqueness.FEW){
		
			intervalIncrement = (int) Math.ceil(size * (1.0 - uniques.getValue()));
			
		}
		
		else {
			
			throw new IllegalArgumentException("Invalid uniqueness flag passed to ElementArray constructor: " + uniques);
			
		}
		
		for (int index = 0; index < size; index++) {
		
			int value = -1;
			
			if (direction == ElementArray.directions.FORWARD) {
			
				value = (int) Math.floor((index) / intervalIncrement) * intervalIncrement + intervalIncrement;
				
			}
			
			else {
				
				value = (size) - (int) Math.ceil((index) / intervalIncrement) * intervalIncrement;
				
			}
			
			basicElementArray.add(new Element(value, -1, true)); // Creates the forward Array, very fast and simple
			
		}
		
		if (order == ElementArray.orders.SORTED) {} // Nothing to do!

		else if (order == ElementArray.orders.RANDOM) { // Randomize by taking the forward array and doing 10*size random swaps
			
			int RANDOMIZE_COUNTER = size * 3;
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				int index1 = random.nextInt(size);
				int index2 = random.nextInt(size);
				
				Element E1 = basicElementArray.get(index1);
				basicElementArray.set(index1, basicElementArray.get(index2));
				basicElementArray.set(index2, E1);
				
			}
			
		}
		
		else if (order == ElementArray.orders.ALMOST_SORTED) { // Randomize by taking the forward array and doing size / 50 random swaps
			
			int RANDOMIZE_COUNTER = (int) Math.ceil(size / 50);
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				int index1 = random.nextInt(size);
				int index2 = random.nextInt(size);
				
				Element E1 = basicElementArray.get(index1);
				basicElementArray.set(index1, basicElementArray.get(index2));
				basicElementArray.set(index2, E1);
				
			}
			
		}
		
		else {
			
			throw new IllegalArgumentException("Invalid order flag passed to ElementArray constructor: " + order);
			
		}
		
		for (int index = 0; index < size; index++) { // Fix all element indexes now!
			
			basicElementArray.get(index).setIndex(index);;
			
		}
		
		for (Element currentElement : basicElementArray) { // Push all elements to the elementArray
			
			elementArray.add(currentElement);
			
		}
		
		for (Element currentElement : elementArray) { // Make a copy of elementArray and store it in the copy variable for use later
		
			elementArrayCopy.add(new Element(currentElement.getValue(), currentElement.getIndex(), currentElement.isDrawable()));
			
		}
		
		elementArrayCopy = Collections.unmodifiableList(elementArrayCopy);
		
	}
	
	public ElementArray(ArrayList<Element> inputElementArray, Counter inputCounter, int inputOffset) {
		
		elementArray = inputElementArray;
		counter = inputCounter;
		offset = inputOffset;
		
	}
	
	public Element get(int index) {
		
		counter.incrementAccesses();
		Element element = elementArray.get(index);
		
		if (VisualizationBase.DRAW_GET_UPDATES) {
			
			VisualizationBase.VISUALIZATION_WINDOW.registerEvent(element, Color.RED, VisualizationBase.CHANGE_TIMER);
			
		}
		
		return element;
		
	}
	
	private Element getWithoutIncrement(int index) {
		
		return elementArray.get(index);
		
	}
	
	public int compare(int indexE1, int indexE2) {
		
		Element E1 = this.get(indexE1);
		Element E2 = this.get(indexE2);
		return compare(E1, E2);
		
	}
	
	public int compare(Element E1, int indexE2) {
		
		Element E2 = this.get(indexE2);
		return compare(E1, E2);
		
	}
	
	public int compare(int indexE1, Element E2) {
		
		Element E1 = this.get(indexE1);
		return compare(E1, E2);
		
	}
	
	public int compare(Element E1, Element E2) {
		
		counter.incrementCompares();
		
		if (VisualizationBase.DRAW_COMPARE_UPDATES) {
		
			VisualizationBase.VISUALIZATION_WINDOW.registerEvent(E1, Color.GREEN, VisualizationBase.CHANGE_TIMER);
			VisualizationBase.VISUALIZATION_WINDOW.registerEvent(E2, Color.GREEN, VisualizationBase.CHANGE_TIMER);
		
		}
		
		return E1.compare(E2);
		
	}

	public void swap(int indexE1, int indexE2) {
		
		Element E1 = this.get(indexE1);
		Element E2 = this.get(indexE2);
		set(indexE1, E2);
		set(indexE2, E1);
		
	}
	
	public void set(int index, Element E1) {
		
		counter.incrementSets();
		E1.setIndex(index + offset);
		elementArray.set(index, E1);
		
		if (VisualizationBase.DRAW_SET_UPDATES) {
		
			VisualizationBase.VISUALIZATION_WINDOW.registerEvent(E1, Color.BLUE, VisualizationBase.CHANGE_TIMER);
			
		}
		
		else if (VisualizationBase.DRAW_SCREEN_UPDATES_WHILE_SORTING) {
			
			VisualizationBase.VISUALIZATION_WINDOW.repaint(E1);
			
		}
		
	}
	
	public void set(int index, int indexE) {
		
		set(index, get(indexE));
		
	}
	
	public int getOffset() {
		
		return offset;
		
	}
	
	public ElementArray subList(int E1Index, int E2Index) {
		
		ArrayList<Element> subList = new ArrayList<Element>();
		
		for (Element currentElement : elementArray.subList(E1Index, E2Index)) {
			
			subList.add(currentElement);
			
		}
		
		return new ElementArray(subList, counter, E1Index);
		
	}
	
	public List<Element> getElements() {
		
		return Collections.unmodifiableList(elementArray);
		
	}
	
	public Element getClosestElement(int xPos) {
		
		int count = size();
		double interval = (VisualizationBase.WINDOW_SIZE.getWidth() / ((double) elementArray.size()));
		int index = MyUtils.clampInt(count - 1, (int) Math.round(xPos / interval), 0);
		
		return getWithoutIncrement(index);
		
	}
	
	public int size() {
		
		return elementArray.size();
		
	}
	
	public void resetArray() {	// Resets the elementArray by first creating an empty arrayList and then getting all the values from the
							// immutable list
		
		elementArray = new ArrayList<Element>();
		counter.resetCounters();
		
		for (Element currentElement : elementArrayCopy) {
			
			elementArray.add(new Element(currentElement.getValue(), currentElement.getIndex(), currentElement.isDrawable()));
			
		}
		
		VisualizationBase.VISUALIZATION_WINDOW.repaintAllElements();
		
	}
	
}
