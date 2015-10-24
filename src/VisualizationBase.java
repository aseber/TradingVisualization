import java.awt.Dimension;

import javax.swing.JFrame;

public class VisualizationBase {

	protected static VisualizationGUI VISUALIZATION_GUI;
	protected static int STOCK_HISTORY_TIME = 20000;
	protected static int STOCK_CHECK_INTERVAL = 500;
	public static Dimension WINDOW_SIZE = new Dimension(700, 400);
	protected static String HOST = "codebb.cloudapp.net";
	protected static int PORT = 17429;
	protected static String USERNAME = "TraderGators";
	protected static String PASSWORD = "insidertrading";
	protected static tradingPlatform ourTradingPlatform = new tradingPlatform(HOST, PORT, USERNAME, PASSWORD);
	
	public static void main(String[] args) {
		VISUALIZATION_GUI = new VisualizationGUI();
		VISUALIZATION_GUI.setTitle("TraderGators Visualization");
		VISUALIZATION_GUI.setVisible(true);
		VISUALIZATION_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VisualizationBase.ourTradingPlatform.start();
		
		// Log sales
		// velocity of net worth
		// 
	}	
}
