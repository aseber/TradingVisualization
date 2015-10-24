import java.awt.Dimension;
import javax.swing.JFrame;

public class VisualizationBase {

	protected static VisualizationGUI VISUALIZATION_GUI;
	public static int STOCK_HISTORY_TIME = 20000;
	public static int STOCK_CHECK_INTERVAL = 200;
	public static Dimension WINDOW_SIZE = new Dimension(700, 400);
	
	public static void main(String[] args) {
		
		//Stock applStock = new Stock("APPL");
		StockGraph graph = new StockGraph();
		VISUALIZATION_GUI = new VisualizationGUI();
		VISUALIZATION_GUI.setTitle("TraderGators Visualization");
		VISUALIZATION_GUI.setVisible(true);
		VISUALIZATION_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}	
}
