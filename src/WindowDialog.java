import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class WindowDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295277117437760048L;
	JTextField windowXSizeField;
	JTextField windowYSizeField;
	JRadioButton drawSets;
	JRadioButton drawCompares;
	JRadioButton drawGets;
	JRadioButton drawScreenUpdatesWhileSorting;

	public WindowDialog(Frame owner) {
		
		super(owner);
		initComponents();
		
	}
	
	public WindowDialog(Dialog owner) {
		
		super(owner);
		initComponents();
		
	}
	
	private void initComponents() {
		
		JPanel AlgorithmDialogGUI = new JPanel();
		JPanel contentPanel = new JPanel();
		JLabel windowXSizeLabel = new JLabel();
		JLabel windowYSizeLabel = new JLabel();
		windowXSizeField = new JTextField();
		windowYSizeField = new JTextField();
		drawSets = new JRadioButton();
		drawCompares = new JRadioButton();
		drawGets = new JRadioButton();
		drawScreenUpdatesWhileSorting = new JRadioButton();
		JButton CancelButton = new JButton();
		JButton AcceptButton = new JButton();
			
		setTitle("Window Settings");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		AlgorithmDialogGUI.setLayout(new BorderLayout());
		
		contentPanel.setLayout(new FormLayout("3px, 150px, 5px, 150px, 5px", "5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px"));
		
		windowXSizeLabel.setText("Window X Size");
		contentPanel.add(windowXSizeLabel, CC.xywh(2, 2, 1, 1));
		
		windowYSizeLabel.setText("Window Y Size");
		contentPanel.add(windowYSizeLabel, CC.xywh(4, 2, 1, 1));
		
		windowXSizeField.setText(Integer.toString(VisualizationBase.WINDOW_SIZE.width));
		contentPanel.add(windowXSizeField, CC.xywh(2, 4, 1, 1));
		
		windowYSizeField.setText(Integer.toString(VisualizationBase.WINDOW_SIZE.height));
		contentPanel.add(windowYSizeField, CC.xywh(4, 4, 1, 1));
		
		drawSets.setText("Draw set updates");
		drawSets.setSelected(VisualizationBase.DRAW_SET_UPDATES);
		contentPanel.add(drawSets, CC.xywh(2, 6, 3, 1));
		
		drawCompares.setText("Draw compare updates");
		drawCompares.setSelected(VisualizationBase.DRAW_COMPARE_UPDATES);
		contentPanel.add(drawCompares, CC.xywh(2, 8, 3, 1));
		
		drawGets.setText("Draw get updates");
		drawGets.setSelected(VisualizationBase.DRAW_GET_UPDATES);
		contentPanel.add(drawGets, CC.xywh(2, 10, 3, 1));
		
		drawScreenUpdatesWhileSorting.setText("Draw screen updates while sorting");
		drawScreenUpdatesWhileSorting.setSelected(VisualizationBase.DRAW_SCREEN_UPDATES_WHILE_SORTING);
		contentPanel.add(drawScreenUpdatesWhileSorting, CC.xywh(2, 12, 3, 1));
		
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CancelButtonMouseCLicked();
				
			}
			
		});
		contentPanel.add(CancelButton, CC.xywh(2, 14, 1, 1));
		
		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				AcceptButtonMouseCLicked();
				
			}
			
		});
		contentPanel.add(AcceptButton, CC.xywh(4, 14, 1, 1));
		
		AlgorithmDialogGUI.add(contentPanel, BorderLayout.NORTH);
		contentPane.add(AlgorithmDialogGUI, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	private void CancelButtonMouseCLicked() {
		
		this.dispose();
		
	}
	
	private void AcceptButtonMouseCLicked() {
		
		Dimension newSize = new Dimension(Integer.parseInt(windowXSizeField.getText()), Integer.parseInt(windowYSizeField.getText()));

		if (newSize.width != VisualizationBase.WINDOW_SIZE.width || newSize.height != VisualizationBase.WINDOW_SIZE.height) {
		
			VisualizationBase.WINDOW_SIZE.setSize(newSize);
			VisualizationBase.VISUALIZATION_WINDOW.setWindowSize(VisualizationBase.WINDOW_SIZE);
			
		}
		
		VisualizationBase.DRAW_GET_UPDATES = drawGets.isSelected();
		VisualizationBase.DRAW_SET_UPDATES = drawSets.isSelected();
		VisualizationBase.DRAW_COMPARE_UPDATES = drawCompares.isSelected();
		VisualizationBase.DRAW_SCREEN_UPDATES_WHILE_SORTING = drawScreenUpdatesWhileSorting.isSelected();
		
		this.dispose();
		
	}
	
	public void keyPressed(KeyEvent key) {
		
		if (key.getKeyCode() == KeyEvent.VK_ENTER) { 
			
			AcceptButtonMouseCLicked();
	
		}
		
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			CancelButtonMouseCLicked();
			
		}
		
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
	
}
