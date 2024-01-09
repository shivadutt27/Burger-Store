package innerClassDemo.burger;

import java.util.ArrayList;
import java.util.List;

public class Meal {

	private double price = 5.0;
	private Burger burger;
	private Item drink;
	private Item side;
	private double conversionRate;
	
	public Meal() {
		this(1);
	}
	
	public Meal(double conversionRate) {
		this.conversionRate= conversionRate;
		this.burger = new Burger("regular");
		this.drink = new Item("coke","drink", 1.5);
		System.out.println(drink.name);
		this.side = new Item("fries", "side", 2.0);
	}
	
	private double getTotal() {
		double total = burger.getPrice() + drink.price + side.price;
		return Item.getPrice(total, conversionRate);
	}
	
	
	public void addingTopping(String... toppings) {
		burger.addToppings(toppings);
	}
	
	@Override
	public String toString() {
		return "%s%n%s%n%s%n%26s$%.2f".formatted(burger,drink,side,
				"Total due: ", getTotal());
	}
	
	
	
	
	//Inner Nested class demo
	private class Item {
		private String name;
		private String type;
		private double price;
		
		private Item(String name, String type) {
			this(name,type, type.equals("burger")? Meal.this.price:0);
		}
		
		private Item(String name, String type, double price) {
			this.name=name;
			this.type=type;
			this.price= price;
		}
		
		private static double getPrice(double price, double rate) {
			return price*rate;
		}
		
		@Override
		public String toString() {
			return "%10s%15s $%.2f".formatted(name, type, getPrice(price,conversionRate));
		}
	}

	private class Burger extends Item{ // Inner nested class inherits from Item as Burger is also an item	
		private enum Extra{ //Adding enums to have set variety of Toppings
			AVACADO,
			BACON,
			CHEESE,
			KETCHUP,
			MAYO,
			MUSTARD,
			PICKLES;
			private double getPrice() {  //This method set the price for different toppings
				return switch(this) {
				case AVACADO -> 1.0;
				case BACON,CHEESE -> 1.5;
				default -> 0;
				};
			} 
		}		
		List<Item> toppingList = new ArrayList<>(); // Initiate the empty list for the toppings 
		private Burger(String name) { //Constructor
			super(name, "burger", Meal.this.price);
		}
		private void addToppings(String... selectedToppings) {
			for(String selectedTopping: selectedToppings) {
				try {
					Extra topping = Extra.valueOf(selectedTopping.toUpperCase());
					toppingList.add(new Item(topping.name(),"topping",topping.getPrice()));
				}catch(IllegalArgumentException ie) {
					System.out.println(selectedTopping+" does not exsist");
				}
			}
		}
		private double getPrice() {
		
			double total = super.price;
			for(Item topping: toppingList) {
				total+=topping.price;
			}
			return total;
		}
			
		@Override
		public String toString() {
			StringBuilder itemized = new StringBuilder(super.toString());
			for(Item topping: toppingList) {
				itemized.append("\n");
				itemized.append(topping);
			}
			
			return itemized.toString();
		}
		
	}	
	
}
