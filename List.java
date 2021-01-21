package shoppingListGUI;

import java.util.*;

public class List {
	private String name = "New list";
	private ArrayList <Product> productsInTheList = new ArrayList<>();
	private int numberOfProducts = 0;
	
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
	public int getNumberOfProducts() {
		return numberOfProducts;
	}
	
	public void addProduct (String productName) {
		if(productName == "") {
			return;
		}
		Product product = new Product(productName);
		productsInTheList.add(product);
		numberOfProducts = getNumberOfProducts() + 1;
	}
	//function that searches for particular product in the list
	//if the product is found return its index
	//else return -1
	public int searchForProduct (String productName) {
		int index=0;
		while(!productsInTheList.get(index).getName().equals(productName) && getNumberOfProducts()>index ) {
			index++;
		}
		if(index==getNumberOfProducts()) {
			return -1;
		}
		else {
			return index;
		}
	}
	public void deleteProduct(String productName) {
		int index = this.searchForProduct(productName);
		productsInTheList.remove(index);
		numberOfProducts = getNumberOfProducts() - 1;
	}
	//function that returns the products on different rows with numbering
	public String toString() {
		String a = "";
		for(int i=0; i<getNumberOfProducts(); i++) {
			a += (i+1) + ". " + productsInTheList.get(i).getName() + "\n";
		}
		return a;
	}
	//function that return the products on different rows
	public String toFile() {
		String a = "";
		for(int i=0; i<getNumberOfProducts(); i++) {
			a+= productsInTheList.get(i).getName() + "\n";
		}
		return a;
	}
	public void makeListEmpty () {
		while(numberOfProducts>0) {
			productsInTheList.remove(0);
			numberOfProducts--;
		}
	}
}
