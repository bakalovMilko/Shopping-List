package shoppingListGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShoppingList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Shopping List");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JPanel upLeftPanel =new JPanel();
		leftPanel.add(upLeftPanel);
		upLeftPanel.setLayout(new BoxLayout(upLeftPanel, BoxLayout.X_AXIS));
		
		JPanel downLeftPanel = new JPanel();
		leftPanel.add(downLeftPanel);
		downLeftPanel.setLayout(new BoxLayout(downLeftPanel, BoxLayout.X_AXIS));
		
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		
		// left side
		//up left side
		JButton addButton = new JButton("Add");
		upLeftPanel.add(addButton);
		JTextField addField = new JTextField("Product for adding");
		upLeftPanel.add(addField);
		JButton saveButton = new JButton("Save as");
		upLeftPanel.add(saveButton);
		JTextField saveField = new JTextField("Name of the list");
		upLeftPanel.add(saveField);
		
		//down left side 
		JButton deleteButton = new JButton("Delete");
		downLeftPanel.add(deleteButton);
		JTextField deleteField = new JTextField("Product for deleting");
		downLeftPanel.add(deleteField);
		JButton openButton = new JButton("Open");
		downLeftPanel.add(openButton);
		JTextField openField = new JTextField("Name of the list");
		downLeftPanel.add(openField);
		
		
		
		//right side
		JLabel nameOfTheList = new JLabel("New list");
		rightPanel.add(nameOfTheList);
		
		JTextArea listArea= new JTextArea();
		listArea.setBounds(200,100,100,100);
		listArea.setEditable(false);
		listArea.setText("Here is your list:");
		rightPanel.add(listArea);
		
		
		frame.pack();
		// make frame visible
		frame.setVisible(true);
	}

}
