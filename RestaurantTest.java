import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    
	Restaurant restaurant;
    RestaurantService restaurantService;
	LocalTime openingTime;
	LocalTime closingTime;
	String name;
	String location;
	
	@BeforeEach
	public void beforeEachTest() {
		restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
		restaurantService = new RestaurantService();
		openingTime = null;
		closingTime = null;
		name = null;
		location = null;
		
	}
	
	public boolean timeBetweenOpeningAndClosingTime;
    //REFACTOR ALL THE REPEATED LINES OF CODE
	

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	restaurant = Mockito.mock(Restaurant.class);
    	timeBetweenOpeningAndClosingTime = LocalTime.now().isAfter(openingTime) && LocalTime.now().isBefore(closingTime);
		Mockito.when(restaurant.isRestaurantOpen()).thenReturn(timeBetweenOpeningAndClosingTime);
		Restaurant calculateRestaurantTime = restaurantService.addRestaurant(name, location, openingTime, closingTime);
		assertThat(calculateRestaurantTime.equals(isBetween(openingTime,closingTime)));
		Mockito.verify(restaurant,Mockito.times(2)).isRestaurantOpen();
		
		
    }

    private void assertThat(boolean equals) {
		// TODO Auto-generated method stub
		
	}

	private Object isBetween(LocalTime openingTime, LocalTime closingTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
		restaurant = Mockito.mock(Restaurant.class);
    	boolean timeOutsideOpeningAndClosingTime = LocalTime.now().isBefore(openingTime) && LocalTime.now().isAfter(closingTime);
		Mockito.when(restaurant.isRestaurantOpen()).thenReturn(timeOutsideOpeningAndClosingTime);
		Restaurant calculateRestaurantTime = restaurantService.addRestaurant(name, location, openingTime, closingTime);
		assertThat(calculateRestaurantTime.equals(isBetween(openingTime,closingTime)));
		Mockito.verify(restaurant,Mockito.times(2)).isRestaurantOpen();
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}