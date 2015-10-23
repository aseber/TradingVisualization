import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class VisualizationWindow extends JPanel implements ComponentListener, MouseMotionListener, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3756454707674613931L;
	private Point mouse = new Point();
	private boolean drawDebugInformation = false;
	private ElementArray elementArray = null;
	private BufferedImage image;
	ElementEventDriver eventDriver = new ElementEventDriver();
	private SortExecutor sortExecutor = new SortExecutor();
	
	public VisualizationWindow() {
		
		setFocusable(true);
		addComponentListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setIgnoreRepaint(true);
		setBackground(Color.BLACK);
		
		new Thread(sortExecutor).start();
	
		image = new BufferedImage(VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height, BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().setColor(Color.BLACK);
		image.getGraphics().drawRect(0, 0, VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height);
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		int mouseX = (int) mouse.getX();
		int mouseY = (int) mouse.getY();
		
		g.drawImage(image, 0, 0, this.getBackground(), null);
		
		int windowSizeX = VisualizationBase.WINDOW_SIZE.width;
		int windowSizeY = VisualizationBase.WINDOW_SIZE.height;
		
		g.setColor(Color.WHITE);

		String display1 = "Algorithm: " + VisualizationBase.CURRENT_ALGORITHM;
		int offsetX = 5;
		g.drawString(display1, offsetX, 12);
		
		String display2 = "Data Size: " + VisualizationBase.SORT_COUNT;
		offsetX += g.getFontMetrics().stringWidth(display1) + 5;
		g.drawString(display2, offsetX, 12);
		
		if (drawDebugInformation) {
	
			String display3 = "Mouse: [" + mouseX + ", " + mouseY + "]";
			offsetX += g.getFontMetrics().stringWidth(display2) + 5;
			g.drawString(display3, offsetX, 12);
			
			if (elementArray != null) {
				
				Element curElement = elementArray.getClosestElement(mouseX);

				String display4 = "Index: " + curElement.getIndex();
				offsetX += g.getFontMetrics().stringWidth(display3) + 5;
				g.drawString(display4, offsetX, 12);
				String display5 = "Value: " + curElement.getValue();
				offsetX += g.getFontMetrics().stringWidth(display4) + 5;
				g.drawString(display5, offsetX, 12);
				
			}
			
			g.setColor(Color.RED);
			g.drawLine(mouseX, 0, mouseX, windowSizeY);
			g.drawLine(0, mouseY, windowSizeX, mouseY);
			g.setColor(Color.WHITE);
		
		}
		
	}
	
	public void repaint(Element element) {
		
		element.drawElement(image.getGraphics());
		repaint();
		
	}
	
	public void repaintAllElements() {
		
		if (elementArray == null) {
			
			return;
			
		}
		
		for (Element element : elementArray.getElements()) {
			
			element.drawElement(image.getGraphics());
			
		}
		
		repaint();
		
	}
	
	public void registerEvent(Element element, Color color, int time) {
		
		eventDriver.registerEvent(new ElementEvent(element, color), time);
		
	}

	public void createElementsArray() {
		
		elementArray = new ElementArray(VisualizationBase.SORT_COUNT, VisualizationBase.DIRECTION, VisualizationBase.ORDER, VisualizationBase.UNIQUENESS, new Counter());
		repaintAllElements();
		
	}
	
	public void deleteElementArray() {
		
		elementArray = null;
		
	}
	
	public void resetElementArray() {
		
		elementArray.resetArray();
		
	}
	
	public void runSort() {
		
		sortExecutor.runSort(elementArray, VisualizationBase.CURRENT_ALGORITHM);
		
	}
	
	public void setWindowSize(Dimension d) {
		
		image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Dimension newDimension = new Dimension(d.width + 17, d.height + 94);
		this.setSize(d);
		VisualizationBase.VISUALIZATION_GUI.setSize(newDimension);
		image.getGraphics().setColor(Color.BLACK);
		image.getGraphics().drawRect(0, 0, image.getWidth(), getHeight());
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
		int sizeX = VisualizationBase.WINDOW_SIZE.width;
		int sizeY = VisualizationBase.WINDOW_SIZE.height;
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouse.setLocation(MyUtils.clampInt(sizeX, mouseX, 0), MyUtils.clampInt(sizeY, mouseY, 0));
		repaint();
		
	}
	
	public void mouseDragged(MouseEvent e) { // This should draw line between last two points and all boxes that intersect it are colored.
		
		int sizeX = VisualizationBase.WINDOW_SIZE.width;
		int sizeY = VisualizationBase.WINDOW_SIZE.height;
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouse.setLocation(MyUtils.clampInt(sizeX, mouseX, 0), MyUtils.clampInt(sizeY, mouseY, 0));
		repaint();
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			runSort();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			if (sortExecutor.isSorting()) {
			
				sortExecutor.stopSort();
			
			}
			
			else {
				
				resetElementArray();
				
			}
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
			
			drawDebugInformation = !drawDebugInformation;
			e.consume();
			repaint();
			
		}
		
	}
	
	public void componentResized(ComponentEvent e) {
		
		int WINDOW_WIDTH = e.getComponent().getWidth();
		int WINDOW_HEIGHT = e.getComponent().getHeight();
		VisualizationBase.WINDOW_SIZE.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		image = new BufferedImage(VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height, BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().setColor(Color.BLACK);
		image.getGraphics().drawRect(0, 0, VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height);
		repaintAllElements();
		
	}
	
	public void keyTyped(KeyEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	
}