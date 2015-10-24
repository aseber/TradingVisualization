import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class VisualizationGUI extends JFrame {
	
	private static final long serialVersionUID = 7918519549017633056L;

	VisualizationGUI() {
		
		initializeGUI();
		
	}
	
	private void initializeGUI() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenuGroup = new JMenu("File");
		JMenuItem fileExit = new JMenuItem("Exit");
		
		fileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fileExit();
			}
		});
		
		setLayout(new FormLayout("5px, 1px:grow, 1px:grow, 1px:grow, 1px:grow, 1px:grow, 5px", "25px, 1px:grow, 1px:grow, 5px"));
		
		fileMenuGroup.add(fileExit);
		menuBar.add(fileMenuGroup);
		add(menuBar, CC.xywh(2, 1, 5, 1));
		pack();
		
		Dimension size = new Dimension(VisualizationBase.WINDOW_SIZE.width + 17, VisualizationBase.WINDOW_SIZE.height + 94);
		setSize(size);
		
	}
	
	private void fileExit() {
		
		System.exit(0);
		
	}
	
}
