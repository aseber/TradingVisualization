import java.awt.Color;

public class ElementEvent implements Runnable { // class that executes the region flashing thing
	
	private Element element = null;
	private Color color = null;
	
	public ElementEvent(Element inputElement, Color inputColor) {
		
		element = inputElement;
		color = inputColor;
		
	}
	
	public void drawBeginning() {
		
		element.setColor(color);
		//element.drawElement(g);
		VisualizationBase.VISUALIZATION_WINDOW.repaint(element);
		
	}
	
	private void drawEnd() {
		
		element.setColor(Color.WHITE);
		VisualizationBase.VISUALIZATION_WINDOW.repaint(element);
		
	}
	
	public void run() {
		
		drawEnd();
		ElementEventDriver.removeEvent(this);
		
	}
	
	public Element getElement() {
		
		return element;
		
	}
	
	public Color getColor() {
		
		return color;
		
	}
	
	@Override
	public int hashCode() {
		
		return element.getValue();
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		ElementEvent otherEvent = (ElementEvent) o;
		
		if (element.getIndex() == otherEvent.getElement().getIndex()) {
			
			return true;
			
		}
		
		return false;
		
	}
	
}
