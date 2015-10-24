import java.awt.Dimension;

import javax.swing.JFrame;

public class VisualizationBase {

	//protected static VisualizationWindow VISUALIZATION_WINDOW;
	protected static VisualizationGUI VISUALIZATION_GUI;
	//public static int SLEEP_TIMER = 0;
	//public static int CHANGE_TIMER = 10;
	//public static int SORT_COUNT = 10000;
	//public static boolean DRAW_SET_UPDATES = true;
	//public static boolean DRAW_GET_UPDATES = true;
	//public static boolean DRAW_COMPARE_UPDATES = true;
	//public static boolean DRAW_SCREEN_UPDATES_WHILE_SORTING = true;
	public static Dimension WINDOW_SIZE = new Dimension(700, 400);
	//public static Sort.algorithms CURRENT_ALGORITHM = Sort.algorithms.QUICK;
	//public static ElementArray.directions DIRECTION = ElementArray.directions.REVERSE;
	//public static ElementArray.orders ORDER = ElementArray.orders.RANDOM;
	//public static ElementArray.uniqueness UNIQUENESS = ElementArray.uniqueness.ALL;
	
	public static void main(String[] args) {
		
		VISUALIZATION_GUI = new VisualizationGUI();
		VISUALIZATION_GUI.setTitle("TraderGators Visualization");
		VISUALIZATION_GUI.setVisible(true);
		VISUALIZATION_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}	
}
