import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class VisualizationGUI extends JFrame {

	protected VisualizationWindow mainWindow;
	private JLabel accessCounter = new JLabel("| Accesses: 0 ");
	private JLabel compareCounter = new JLabel("| Compares: 0 ");
	private JLabel swapCounter = new JLabel("| Sets: 0 ");
	private JLabel runTimeCounter = new JLabel("| Run Time: 0 ms");
	private JButton runButton = new JButton("Start Simulation");
	
	private static final long serialVersionUID = -6664286942946303464L;
	
	VisualizationGUI() {
		
		initializeGUI();
		VisualizationBase.VISUALIZATION_WINDOW.createElementsArray();
		
	}
	
	public void setAccessCounter(long var) {
		
		accessCounter.setText("| Accesses: " + var + " ");
		
	}
	
	public void resetAccessCounter() {
		
		setAccessCounter(0);
		
	}
	
	public void setCompareCounter(long var) {
		
		compareCounter.setText("| Compares: " + var + " ");
		
	}
	
	public void resetCompareCounter() {
		
		setCompareCounter(0);
		
	}
	
	public void setSetCounter(long var) {
		
		swapCounter.setText("| Sets: " + var + " ");
		
	}
	
	public void resetSetCounter() {
		
		setSetCounter(0);
		
	}
	
	public void setRunTimeCounter(long var) {
		
		runTimeCounter.setText("| Run Time: " + var + " ms");
		
	}
	
	public void resetRunTimeCounter() {
		
		setRunTimeCounter(0);
		
	}
	
	public void setRunButtonState(boolean paused) {
		
		if (paused) {
			
			runButton.setText("Start Simulation");
			
		}
		
		else {
			
			runButton.setText("Pause Simulation");
			
		}
		
	}
	
	private void initializeGUI() {
		
		VisualizationWindow mainWindow = new VisualizationWindow();
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenuGroup = new JMenu("File");
		JMenuItem fileResetArray = new JMenuItem("Reset Array");
		JMenuItem fileExit = new JMenuItem("Exit program");
		JMenu settingsMenuGroup = new JMenu("Settings");
		JMenuItem settingsChangeAlgorithm = new JMenuItem("Modify Algorithm");
		JMenuItem settingsChangeWindow = new JMenuItem("Modify Window");
		JMenuItem settingsChangeSleepTimer = new JMenuItem("Change Sleep Timer");
		
		VisualizationBase.VISUALIZATION_WINDOW = mainWindow;
		
		this.mainWindow = mainWindow;
		
		//FormLayout layout = new FormLayout();
		
		setLayout(new FormLayout("5px, 1px:grow, 5px", "25px, 1px:grow, 5px, 20px, 5px"));
		
		//PanelBuilder builder = new PanelBuilder(layout);
		
		add(menuBar, CC.xywh(1, 1, 3, 1, CC.FILL, CC.FILL));
			menuBar.add(fileMenuGroup);
				fileMenuGroup.add(fileResetArray);
				fileResetArray.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						
						fileResetArray();
						
					}
					
				});
				fileMenuGroup.add(fileExit);
				fileExit.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						fileExit();
						
					}
					
				});
			menuBar.add(settingsMenuGroup);
				settingsMenuGroup.add(settingsChangeAlgorithm);
				settingsChangeAlgorithm.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						settingsChangeAlgorithm();
						
					}
					
				});
				settingsMenuGroup.add(settingsChangeWindow);
				settingsChangeWindow.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						settingsChangeWindow();
						
					}
					
				});
				settingsMenuGroup.add(settingsChangeSleepTimer);
				settingsChangeSleepTimer.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						settingsChangeSleepTimer();
						
					}
					
				});
				
			menuBar.add(accessCounter);
			menuBar.add(compareCounter);
			menuBar.add(swapCounter);
			menuBar.add(runTimeCounter);
				
		add(mainWindow, CC.xywh(1, 2, 3, 1, CC.FILL, CC.FILL));
		runButton.setFocusable(false);
		add(runButton, CC.xywh(2, 4, 1, 1, CC.FILL, CC.FILL));
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				runButtonAction();
				
			}
			
		});
		pack();
		
		Dimension size = new Dimension(VisualizationBase.WINDOW_SIZE.width + 17, VisualizationBase.WINDOW_SIZE.height + 94);
		setSize(size);
		
	}
	
	private void runButtonAction() {
		
		VisualizationBase.VISUALIZATION_WINDOW.runSort();
		
	}
	
	private void fileResetArray() {
		
		VisualizationBase.VISUALIZATION_WINDOW.resetElementArray();
		
	}
	
	private void fileExit() {
		
		System.exit(0);
		
	}
	
	private void settingsChangeAlgorithm() {
		
		AlgorithmDialog AlgorithmGUI = new AlgorithmDialog(this);
		AlgorithmGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		AlgorithmGUI.setVisible(true);
		
	}
	
	private void settingsChangeWindow() {
		
		WindowDialog WindowGUI = new WindowDialog(this);
		WindowGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		WindowGUI.setVisible(true);
		
	}
	
	private void settingsChangeSleepTimer() {
		
		SleepTimeDialog SleepTimeGUI = new SleepTimeDialog(this);
		SleepTimeGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		SleepTimeGUI.setVisible(true);
		
	}
	
}
