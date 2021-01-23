package shoppingListGUI;

public class Product {
	private String name;
	private int timesInTheList=1;
	
	Product(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getTimesInList() {
		return timesInTheList;
	}

	public void setTimesInList(int timesInList) {
		this.timesInTheList = timesInList;
	}
}
