package com.cg.fooddelivery;
import org.springframework.boot.SpringBootVersion; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FoodDeliveryApplication  extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
		System.out.println("welcome to Food application system ");
		System.out.println("Hungry ??? \n \n Explore the best food and drinks from Multi Cuisine Restaurants");

	}

	@Override 

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { 

	return builder.sources(FoodDeliveryApplication.class); 

	} 
}
