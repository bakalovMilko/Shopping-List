package shoppingListGUI;

public class Product {
	private String name;
	private boolean alreadyBought = false;
	
	Product(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public boolean isAlreadyBought() {
		return alreadyBought;
	}

	public void setAlreadyBought(boolean alreadyBought) {
		this.alreadyBought = alreadyBought;
	}
}
