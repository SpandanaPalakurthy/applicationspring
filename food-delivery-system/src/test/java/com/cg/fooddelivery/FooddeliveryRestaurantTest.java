package com.cg.fooddelivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.cg.fooddelivery.model.Admin;
import com.cg.fooddelivery.model.Orders;
import com.cg.fooddelivery.model.RestaurantItem;
import com.cg.fooddelivery.repository.OrdersRepository;
import com.cg.fooddelivery.repository.RestaurantOwnerRepository;
import com.cg.fooddelivery.repository.RestaurantRepository;
import com.cg.fooddelivery.service.impl.CustomerService;
import com.cg.fooddelivery.service.impl.RestaurantService;


@SpringBootTest
public class FooddeliveryRestaurantTest extends FooddeliveryMainTests {

	@InjectMocks
	private RestaurantService restserviceimpl;
	@Mock
	private RestaurantOwnerRepository restOwnerRepository;

	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private OrdersRepository ordersRepository;
	@Test
	public void addFoodItemsTest() {
		RestaurantItem restaurantitem = new RestaurantItem();
		restaurantitem.setItemId(101);
		restaurantitem.setItemname("panner");
		restaurantitem.setItemdescription("soft");
		restaurantitem.setItemPrice(200);
		when(restOwnerRepository.getOne(101)).thenReturn(restaurantitem);
		when(restOwnerRepository.save(restaurantitem)).thenReturn(restaurantitem);

		RestaurantItem expectedresult = restserviceimpl.addFoodItems(restaurantitem, 3);
		System.out.println("**" + restaurantitem.toString());
		System.out.println("--" + restaurantitem.toString());
		assertEquals(expectedresult, restaurantitem);
	}

	@Test
	public void getfooditemsTest() {

		when(restOwnerRepository.findAll()).thenReturn(
				Stream.of(new RestaurantItem(101, "dosa", "tasty", 100), new RestaurantItem(102, "tea", "drink", 10))
						.collect(Collectors.toList()));

		assertEquals(2, restserviceimpl.getFoodItems().size());
	}

	/*
	 * @Test public void getFoodDetailsTest() {
	 * 
	 * int itemId = 12;
	 * 
	 * when(restOwnerRepository.findById(itemId)).thenReturn(Stream .of(new
	 * RestaurantItem(101, "dosa", "tasty", 100)).collect(Collectors.toList()));
	 * 
	 */ 

	
	@Test
	public void deleteFoodDetailsTest() {
		RestaurantItem restitem = new RestaurantItem(101, "milk", "drink", 20);
		restserviceimpl.deleteFoodItems(9);
		verify(restOwnerRepository, times(1)).deleteById(9);
	}

	
	
	@Test
	public void updateItemsTest() {
		
		RestaurantItem restaurantitem = new RestaurantItem();
		restaurantitem.setItemId(10);
		restaurantitem.setItemname("coffe");
		restaurantitem.setItemPrice(12);
		restaurantitem.setItemdescription("drinks");
		when(restOwnerRepository.getOne(10)).thenReturn(restaurantitem);
		when(restOwnerRepository.save(restaurantitem)).thenReturn(restaurantitem);
		
		RestaurantItem restexp = restserviceimpl.updateFoodItems(restaurantitem, restaurantitem.getItemId());
		System.out.println("**" + restaurantitem.toString());
		System.out.println("--" + restaurantitem.toString());
		assertEquals(restexp, restaurantitem);

	}

	@Test
	public void updateorderTest() {
		Orders orders = new Orders();
		orders.setOrderId(111);
		orders.setOrderDate("12/2/2020");
		orders.setOrderStatus("deliverd");
		orders.setOrderTotalAmount(900);
		when(ordersRepository.getOne(111)).thenReturn(orders);
		when(ordersRepository.save(orders)).thenReturn(orders);
		
		Orders exporders = restserviceimpl.updateOrders(orders, orders.getOrderId());
		assertEquals(exporders, orders);
				
	}
	
	
}
