package shoppingListGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ShoppingList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//initializing frame and panels
		JFrame frame = new JFrame("Shopping List");
		frame.setSize(1000, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(2, 4));
		
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		
		// left side
		//adding components
		JButton addButton = new JButton("Add");
		leftPanel.add(addButton);
		JTextField addField = new JTextField();
		leftPanel.add(addField);
		
		JButton saveButton = new JButton("Save as");
		leftPanel.add(saveButton);
		JTextField saveField = new JTextField();
		leftPanel.add(saveField);

		JButton deleteButton = new JButton("Delete");
		leftPanel.add(deleteButton);
		JTextField deleteField = new JTextField();
		leftPanel.add(deleteField);
		
		JButton openButton = new JButton("Open");
		leftPanel.add(openButton);
		JTextField openField = new JTextField();
		leftPanel.add(openField);
		
		
		//right side
		JLabel nameOfTheList = new JLabel("New list");
		rightPanel.add(nameOfTheList);
		
		JTextArea listArea= new JTextArea();
		listArea.setBounds(200,100,100,100);
		listArea.setEditable(false);
		listArea.setText("Here is your list:");
		rightPanel.add(listArea);

		//fonts
		Font buttonFont = new Font("Times New Roman", Font.BOLD, 16);
		Font fieldFont = new Font("Times New Roman", Font.PLAIN, 14);
		Font listFont = new Font("Times New Roman", Font.ITALIC, 14);
		Font titleFont = new Font("Verdana", Font.BOLD, 18);
		
		addButton.setFont(buttonFont);
		saveButton.setFont(buttonFont);
		deleteButton.setFont(buttonFont);
		openButton.setFont(buttonFont);
		
		addField.setFont(fieldFont);
		deleteField.setFont(fieldFont);
		saveField.setFont(fieldFont);
		openField.setFont(fieldFont);
		
		listArea.setFont(listFont);
		
		nameOfTheList.setFont(titleFont);
		
		//Initializing an object from type List
		
		List currentList = new List();
		
		//Button actions
		addButton.addActionListener( new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				if(addField.getText().equals("")) {
					System.out.println("You have to fill the field next to the button.");
					return;
				}
				String addText = addField.getText();
				currentList.addProduct(addText);
				listArea.setText(currentList.toString());
				addField.setText("");
			}
		});
		deleteButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(deleteField.getText().equals("")) {
					System.out.println("You have to fill the field next to the button.");
					return;
				}
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
				saveField.setText(""); //clearing the field
				if(name.equals("")) {
					System.out.println("You have to fill the field next to the button.");
					return;
				}
				File previousLists = new File("previousLists.txt");
				//checking for file with the same name
				try {
					Scanner sc = new Scanner(previousLists);
					String line = "", previousLine = "";
					boolean hasTheSameName = false;
					while(sc.hasNextLine() && !hasTheSameName) {
						previousLine = line;
						line = sc.nextLine();
						if(line.equals(name) && previousLine.equals("")) {
							hasTheSameName = true;
						}
					}
					if(hasTheSameName) {
						System.out.println("There is an already existing list with this name.");
						sc.close();
						return;
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
				// adding the new list to the file
				String toFile = "\n" + name + "\n" + currentList.toFileFormat();
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
				if(openField.getText().equals("")) {
					System.out.println("You have to fill the field next to the button.");
					return;
				}
				File previousLists = new File("previousLists.txt");
				String name = openField.getText();
				nameOfTheList.setText(name);
				openField.setText("");
				try {
					Scanner sc = new Scanner(previousLists);
					String line = "", previousLine = "";
					boolean hasToAdd = false; // a variable to indicate if the scanner has found the file or not
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
						else if(line.equals(name) && previousLine.equals("")) { //if the statement is true we have found the list
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
