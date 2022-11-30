import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private String name;
	private String location;
	public LocalTime openingTime;
	public LocalTime closingTime;
	private List<Item> menu = new ArrayList<Item>();
	public LocalTime time3;

	public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
		this.name = name;
		this.location = location;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public boolean isRestaurantOpen() {
		LocalTime now = LocalTime.now();
		System.out.println("Current Time :" + now);

		if (now.isAfter(openingTime) && now.isBefore(closingTime)) {
			return true;
		} else {
			return false;
		}
	}

	public LocalTime getCurrentTime() {
		return LocalTime.now();
	}

	public ArrayList<Item> getMenu() {
		menu.add(new Item("sweet corn soup", 119));
		menu.add(new Item("Vegetable lasagne", 269));
		menu.add(new Item("Sizzling brownie", 319));
		menu.add(new Item("Pastry", 200));
		menu.add(new Item("mix vegentable", 400));
		menu.add(new Item("roti", 40));
		return (ArrayList<Item>) menu;

	}

	private Item findItemByName(String itemName) {
		for (Item item : menu) {
			if (item.getName().equals(itemName))
				return item;
		}
		return null;
	}

	public void addToMenu(String name, int price) {
		Item newItem = new Item(name, price);
		menu.add(newItem);
	}

	public void removeFromMenu(String itemName) throws itemNotFoundException {

		Item itemToBeRemoved = findItemByName(itemName);
		if (itemToBeRemoved == null)
			throw new itemNotFoundException(itemName);

		menu.remove(itemToBeRemoved);
	}

	public void displayDetails() {
		System.out.println("Restaurant:" + name + "\n" + "Location:" + location + "\n" + "Opening time:" + openingTime
				+ "\n" + "Closing time:" + closingTime + "\n" + "Menu:" + "\n" + getMenu());

	}

	public String getName() {
		return name;
	}

}
