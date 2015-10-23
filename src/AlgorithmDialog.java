import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class AlgorithmDialog extends JDialog implements KeyListener {

	Container contentPane;
	JPanel AlgorithmDialogGUI;
	JPanel customPanel;
	JComboBox<Sort.algorithms> algorithmsList;
	JComboBox<ElementArray.directions> directionsList;
	JComboBox<ElementArray.orders> ordersList;
	JComboBox<ElementArray.uniqueness> uniquenessList;
	JTextField sortSize;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7388554549130678395L;

	public AlgorithmDialog(Frame owner) {
		
		super(owner);
		initComponents();
		
	}
	
	public AlgorithmDialog(Dialog owner) {
		
		super(owner);
		initComponents();
		
	}
	
	private void initComponents() {
		
		AlgorithmDialogGUI = new JPanel();
		JPanel contentPanel = new JPanel();
		JPanel contentPanel2 = new JPanel();
		
		algorithmsList = new JComboBox<Sort.algorithms>(Sort.algorithms.values());
		directionsList = new JComboBox<ElementArray.directions>(ElementArray.directions.values());
		ordersList = new JComboBox<ElementArray.orders>(ElementArray.orders.values());
		uniquenessList = new JComboBox<ElementArray.uniqueness>();
		
		for (ElementArray.uniqueness uniqueElement : ElementArray.uniqueness.values()) {
			
			if (uniqueElement != ElementArray.uniqueness.NONE) {
			
				uniquenessList.addItem(uniqueElement);
			
			}
			
		}
		
		sortSize = new JTextField();
		
		JButton CancelButton = new JButton();
		JButton AcceptButton = new JButton();
		
		setTitle("Algorithm Selector");
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		AlgorithmDialogGUI.setLayout(new BorderLayout());
		
		contentPanel.setLayout(new FormLayout("3px, 300px, 5px", "5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px"));
		
		algorithmsList.setSelectedItem(VisualizationBase.CURRENT_ALGORITHM);
		contentPanel.add(algorithmsList, CC.xywh(2, 2, 1, 1));

		directionsList.setSelectedItem(VisualizationBase.DIRECTION);
		contentPanel.add(directionsList, CC.xywh(2, 4, 1, 1));
		
		ordersList.setSelectedItem(VisualizationBase.ORDER);
		contentPanel.add(ordersList, CC.xywh(2, 6, 1, 1));
		
		uniquenessList.setSelectedItem(VisualizationBase.UNIQUENESS);
		contentPanel.add(uniquenessList, CC.xywh(2, 8, 1, 1));
		
		sortSize.setText(Integer.toString(VisualizationBase.SORT_COUNT));
		contentPanel.add(sortSize, CC.xywh(2, 10, 1, 1));
		
		contentPanel2.setLayout(new FormLayout("3px, 50px, 5px, 100px, 5px, 100px, 5px, 50px", "5px, 50px"));
		
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CancelButtonMouseCLicked();
				
			}
			
		});
		contentPanel2.add(CancelButton, CC.xywh(4, 2, 1, 1));
		
		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				AcceptButtonMouseCLicked();
				
			}
			
		});
		contentPanel2.add(AcceptButton, CC.xywh(6, 2, 1, 1));
		
		AlgorithmDialogGUI.add(contentPanel, BorderLayout.NORTH);
		AlgorithmDialogGUI.add(contentPanel2, BorderLayout.SOUTH);
		contentPane.add(AlgorithmDialogGUI, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		
	}

	private void CancelButtonMouseCLicked() {
		
		this.dispose();
		
	}
	
	private void AcceptButtonMouseCLicked() {
		
		if (VisualizationBase.CURRENT_ALGORITHM != (Sort.algorithms) algorithmsList.getSelectedItem()) {
			
			VisualizationBase.CURRENT_ALGORITHM = (Sort.algorithms) algorithmsList.getSelectedItem();
			
		}
		
		if (VisualizationBase.DIRECTION != (ElementArray.directions) directionsList.getSelectedItem() || VisualizationBase.ORDER != (ElementArray.orders) ordersList.getSelectedItem() || VisualizationBase.UNIQUENESS != (ElementArray.uniqueness) uniquenessList.getSelectedItem() || VisualizationBase.SORT_COUNT != Integer.parseInt(sortSize.getText())) {
			
			VisualizationBase.VISUALIZATION_WINDOW.deleteElementArray();
			VisualizationBase.DIRECTION = (ElementArray.directions) directionsList.getSelectedItem();
			VisualizationBase.ORDER = (ElementArray.orders) ordersList.getSelectedItem();
			VisualizationBase.UNIQUENESS = (ElementArray.uniqueness) uniquenessList.getSelectedItem();
			VisualizationBase.SORT_COUNT = Integer.parseInt(sortSize.getText());
			VisualizationBase.VISUALIZATION_WINDOW.createElementsArray();
			
		}
		
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