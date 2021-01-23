package shoppingListGUI;

import java.util.*;

public class List {
	private String name = "";
	private ArrayList <Product> productsInTheList = new ArrayList<>();
	
	List(){
		
	}
	List(String name){
		this.setName(name);
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Product> getProductsInTheList() {
		return this.productsInTheList;
	}
	
	public void addProduct (String productName) {
		int index = this.searchForProduct(productName);
		if(index>-1) {
			 //add 1 to timesInList for this product
			productsInTheList.get(index).setTimesInList(productsInTheList.get(index).getTimesInList()+1);
		}
		else {
			Product product = new Product(productName);
			productsInTheList.add(product);
		}
	}
	//function that searches for particular product in the list
	//if the product is found return its index
	//else return -1
	public int searchForProduct (String productName) {
		if(productsInTheList.size()==0) return -1;
		int index=0;
		while(!productsInTheList.get(index).getName().equals(productName) && productsInTheList.size()>index ) {
			index++;
			if(index == productsInTheList.size()) break;
		}
		if(index==productsInTheList.size()) {
			return -1;
		}
		else {
			return index;
		}
	}
	public void deleteProduct(String productName) {
		int index = this.searchForProduct(productName);
		if(index>=0) {
			if(productsInTheList.get(index).getTimesInList()>1) {
				productsInTheList.get(index).setTimesInList(productsInTheList.get(index).getTimesInList()-1);
			}
			else {
				productsInTheList.remove(index);
			}
		}
		else System.out.println("There is no product with this name in the list.");
	}
	//function that returns the products on different rows with numbering
	public String toString() {
		String a = "";
		for(int i=0; i<productsInTheList.size(); i++) {
			a += (i+1) + ". " + productsInTheList.get(i).getName() 
		 + ((productsInTheList.get(i).getTimesInList()>1)? (" x " + productsInTheList.get(i).getTimesInList()) : "") + "\n";
		}
		return a;
	}
	//function that return the products on different rows
	public String toFileFormat() {
		String a = "";
		for(int i=0; i<productsInTheList.size(); i++) {
			for(int j=0; j<productsInTheList.get(i).getTimesInList(); j++) {
				a+= productsInTheList.get(i).getName() + "\n";
			}
		}
		return a;
	}
	public void makeListEmpty () {
		while(productsInTheList.size()>0) {
			productsInTheList.remove(0);
		}
	}
}
