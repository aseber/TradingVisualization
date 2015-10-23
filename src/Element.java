import java.awt.Color;
import java.awt.Graphics;

public class Element {
	
	// Elements encompass the concept of values and the methods required to evaluate and draw them.
	// White color refers to no operation happening
	// Red color refers to an array access
	// Blue color refers to a swap
	// Green color refers to a compare
	
	private int value;
	private int index;
	private Color color;
	private boolean drawable;
	
	public Element(int inputValue, int arrayIndex, boolean inputDrawable) {
		
		value = inputValue;
		index = arrayIndex;
		color = Color.WHITE;
		drawable = inputDrawable;
		
		if (drawable) {
		
			VisualizationBase.VISUALIZATION_WINDOW.repaint(this);
			
		}
		
	}
	
	public void drawElement(Graphics g) {
		
		if (drawable) {
		
			int topOffset = 20;
			int bottomOffset = 0;
			
			double width = VisualizationBase.WINDOW_SIZE.getWidth();
			double total_height = VisualizationBase.WINDOW_SIZE.getHeight();
			double reduced_height = VisualizationBase.WINDOW_SIZE.getHeight() - topOffset - bottomOffset;
			double count_double = (double) VisualizationBase.SORT_COUNT;
			
			int x = (int) Math.round(width * ((double) index / (count_double)));
			int y = ((int) total_height - bottomOffset) - (int) Math.round(reduced_height * ((double) value / count_double));
			int deltaX = (int) Math.round(width / count_double) + 1;
			int deltaY = (int) total_height - y - bottomOffset;
			
			g.clearRect(x, topOffset, deltaX, (int) reduced_height);
			g.setColor(color);
			g.fillRect(x, y, deltaX, deltaY);
			
		}
		
	}
	
	public int getValue() {
		
		return value;
		
	}
	
	public int getIndex() {
		
		return index;
		
	}
	
	public boolean isDrawable() {
		
		return drawable;
		
	}
	
	public void setIndex(int newIndex) {
		
		index = newIndex;
		
	}
	
	public Color getColor() {
		
		return color;
		
	}
	
	public void setColor(Color newColor) {
		
		color = newColor;
		
	}
	
	public int compare(Element otherElement) {
		
		int thisValue = this.getValue();
		int otherValue = otherElement.getValue();
		int difference = thisValue - otherValue;
		
		return MyUtils.clampInt(1, difference, -1);
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		Element otherElement = (Element) o;
		
		if (this.getValue() == otherElement.getValue()) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	@Override
	public String toString() {
		
		return "Element " + getIndex() + ": " + getValue();
		
	}
	
}
