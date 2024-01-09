package innerClassDemo.burger;

public class Store {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Meal regularMeal = new Meal();
		regularMeal.addingTopping("avacado","onion");
		System.out.println(regularMeal);
		
		
		Meal USRegularMeal = new Meal(0.68);
		
		USRegularMeal.addingTopping("AVACADO","MAYO","MUSTARD");
		System.out.println(USRegularMeal);
		//System.out.println(USRegularMeal);
	}

}
