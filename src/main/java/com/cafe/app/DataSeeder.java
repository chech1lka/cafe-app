package com.cafe.app;

import com.cafe.app.model.*;
import com.cafe.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final FoodCategoryRepository categoryRepository;
    private final FoodRepository foodRepository;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) {
        // Categories
        FoodCategory hotDrinks = new FoodCategory(null, "Hot Drinks", "Coffee, tea and more", null);
        FoodCategory coldDrinks = new FoodCategory(null, "Cold Drinks", "Juices, smoothies, iced drinks", null);
        FoodCategory mainDishes = new FoodCategory(null, "Main Dishes", "Burgers, pasta, rice dishes", null);
        FoodCategory desserts = new FoodCategory(null, "Desserts", "Cakes, ice cream, pastries", null);
        FoodCategory snacks = new FoodCategory(null, "Snacks", "Light bites and appetizers", null);

        categoryRepository.saveAll(List.of(hotDrinks, coldDrinks, mainDishes, desserts, snacks));

        // Foods
        Food espresso = new Food(null, "Espresso", "Strong Italian coffee", new BigDecimal("500"), true, hotDrinks, null);
        Food latte = new Food(null, "Latte", "Espresso with steamed milk", new BigDecimal("800"), true, hotDrinks, null);
        Food cappuccino = new Food(null, "Cappuccino", "Espresso with foam", new BigDecimal("750"), true, hotDrinks, null);
        Food orangeJuice = new Food(null, "Orange Juice", "Freshly squeezed", new BigDecimal("700"), true, coldDrinks, null);
        Food lemonade = new Food(null, "Lemonade", "Homemade with mint", new BigDecimal("650"), true, coldDrinks, null);
        Food burger = new Food(null, "Classic Burger", "Beef patty with veggies", new BigDecimal("2500"), true, mainDishes, null);
        Food pasta = new Food(null, "Pasta Carbonara", "Creamy pasta with bacon", new BigDecimal("2200"), true, mainDishes, null);
        Food cheesecake = new Food(null, "Cheesecake", "New York style", new BigDecimal("1200"), true, desserts, null);
        Food frenchFries = new Food(null, "French Fries", "Crispy golden fries", new BigDecimal("900"), true, snacks, null);
        Food salad = new Food(null, "Caesar Salad", "Romaine, croutons, parmesan", new BigDecimal("1500"), true, snacks, null);

        foodRepository.saveAll(List.of(espresso, latte, cappuccino, orangeJuice, lemonade,
                burger, pasta, cheesecake, frenchFries, salad));

        // Persons
        Person alice = new Person(null, "Alice Johnson", "alice@example.com", "+7 777 111 2233", null);
        Person bob = new Person(null, "Bob Smith", "bob@example.com", "+7 777 444 5566", null);
        Person carol = new Person(null, "Carol Williams", "carol@example.com", "+7 777 777 8899", null);

        personRepository.saveAll(List.of(alice, bob, carol));

        // Sample Order
        Order order1 = new Order();
        order1.setPerson(alice);
        order1.setStatus(OrderStatus.CONFIRMED);
        order1.setNotes("Extra sugar please");
        order1.setItems(new ArrayList<>());

        OrderItem item1 = new OrderItem();
        item1.setOrder(order1);
        item1.setFood(latte);
        item1.setQuantity(2);
        item1.setUnitPrice(latte.getPrice());
        item1.calculateSubtotal();

        OrderItem item2 = new OrderItem();
        item2.setOrder(order1);
        item2.setFood(cheesecake);
        item2.setQuantity(1);
        item2.setUnitPrice(cheesecake.getPrice());
        item2.calculateSubtotal();

        order1.getItems().add(item1);
        order1.getItems().add(item2);
        order1.setTotalPrice(item1.getSubtotal().add(item2.getSubtotal()));

        orderRepository.save(order1);

        System.out.println("✅ Demo data loaded successfully!");
        System.out.println("📌 H2 Console: http://localhost:8080/h2-console");
        System.out.println("📌 API Base:   http://localhost:8080/api");
    }
}
