package shoppingListGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ShoppingList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Shopping List");
		frame.setSize(1000, 200);
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
		JTextField addField = new JTextField();
		upLeftPanel.add(addField);
		
		JButton saveButton = new JButton("Save as");
		upLeftPanel.add(saveButton);
		JTextField saveField = new JTextField();
		upLeftPanel.add(saveField);
		
		//down left side 
		JButton deleteButton = new JButton("Delete");
		downLeftPanel.add(deleteButton);
		JTextField deleteField = new JTextField();
		downLeftPanel.add(deleteField);
		
		JButton openButton = new JButton("Open");
		downLeftPanel.add(openButton);
		JTextField openField = new JTextField();
		downLeftPanel.add(openField);
		
		
		//right side
		JLabel nameOfTheList = new JLabel("New list");
		rightPanel.add(nameOfTheList);
		
		JTextArea listArea= new JTextArea();
		listArea.setBounds(200,100,100,100);
		listArea.setEditable(false);
		listArea.setText("Here is your list:");
		rightPanel.add(listArea);
		
		//Initializing object from type List
		
		List currentList = new List();
		
		//Button actions
		addButton.addActionListener( new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				String addText = addField.getText();
				currentList.addProduct(addText);
				listArea.setText(currentList.toString());
				addField.setText("");
			}
		});
		deleteButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String deleteText = deleteField.getText();
				currentList.deleteProduct(deleteText);
				listArea.setText(currentList.toString());
				deleteField.setText("");
				
			}
		});
		saveButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = saveField.getText();
				if(currentList.getNumberOfProducts()<=0 || name.equals("")) {
					return;
				}
				String toFile = "\n" + name + "\n" + currentList.toFile();
				try {
					FileWriter myWriter = new FileWriter("previousLists.txt", true);
					myWriter.write(toFile);
					myWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error.");
					e.printStackTrace();
				}
				nameOfTheList.setText(name);
				saveField.setText("");
			}
		});
		openButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File previousLists = new File("previousLists.txt");
				String name = openField.getText();
				nameOfTheList.setText(name);
				openField.setText("");
				try {
					Scanner sc = new Scanner(previousLists);
					String line = "", previousLine = "";
					boolean hasToAdd = false;
					while(sc.hasNextLine()) {
						previousLine = line;
						line = sc.nextLine();
						if(hasToAdd) {
							if(!line.equals("")) {
								currentList.addProduct(line);
								listArea.setText(currentList.toString());
							}
							else {
								sc.close();
								return;
							}
						}
						else if(line.equals(name) && previousLine.equals("")) {
							currentList.setName(name);
							hasToAdd = true;
							currentList.makeListEmpty();
						}
					}
					if(!hasToAdd) {
						listArea.setText("No file with such name");
					}
					sc.close();
				} catch (FileNotFoundException e) {
					try {
						previousLists.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		});
		// make frame visible
		frame.setVisible(true);
	}

}
