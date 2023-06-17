package com.shahian.foodallergic.model.dataBaseInitializing;



import com.shahian.foodallergic.model.entity.*;
import com.shahian.foodallergic.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;
    private final FoodRepository foodRepository;

    public DatabaseInitializer(UserRepository userRepository, AllergyRepository allergyRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.allergyRepository = allergyRepository;
        this.foodRepository = foodRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0 && allergyRepository.count() == 0 && foodRepository.count() == 0 ) {

            User user1 = new User();
            user1.setFullName("HamidReza Shahian");

            User user2 = new User();
            user2.setFullName("Kian Shahian");

            // Create allergies
            Allergy allergy1 = new Allergy();
            allergy1.setAllergyName("Milk");
            allergy1.setUser(user1);

            Allergy allergy2 = new Allergy();
            allergy2.setAllergyName("Eggs");
            allergy2.setUser(user1);

            Allergy allergy3 = new Allergy();
            allergy3.setAllergyName("Wheat");
            allergy3.setUser(user1);

            Allergy allergy4 = new Allergy();
            allergy4.setAllergyName("Peanuts");
            allergy4.setUser(user2);

            Allergy allergy5 = new Allergy();
            allergy5.setAllergyName("Shellfish");
            allergy5.setUser(user2);

            // Create foods
            Food food1 = new Food();
            food1.setFoodName("Pizza");

            Food food2 = new Food();
            food2.setFoodName("Pasta");

            // Create ingredients
            Ingredient ingredient1 = new Ingredient();
            ingredient1.setName("Cheese");
            ingredient1.setFood(food1);

            Ingredient ingredient2 = new Ingredient();
            ingredient2.setName("Tomato");
            ingredient2.setFood(food1);

            Ingredient ingredient3 = new Ingredient();
            ingredient3.setName("Flour");
            ingredient3.setFood(food2);

            Ingredient ingredient4 = new Ingredient();
            ingredient4.setName("Eggs");
            ingredient4.setFood(food2);

            // Associate allergies with users
            user1.setAllergies(Arrays.asList(allergy1, allergy2, allergy3));
            user2.setAllergies(Arrays.asList(allergy4, allergy5));

            // Associate ingredients with foods
            food1.setIngredients(Arrays.asList(ingredient1, ingredient2));
            food2.setIngredients(Arrays.asList(ingredient3, ingredient4));

            // Save entities to the database
            userRepository.saveAll(Arrays.asList(user1, user2));
            allergyRepository.saveAll(Arrays.asList(allergy1, allergy2, allergy3, allergy4, allergy5));
            foodRepository.saveAll(Arrays.asList(food1, food2));
        }
    }
}
