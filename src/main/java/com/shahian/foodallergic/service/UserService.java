package com.shahian.foodallergic.service;


import com.shahian.foodallergic.exception.UserNotFoundException;
import com.shahian.foodallergic.model.entity.Allergy;
import com.shahian.foodallergic.model.entity.Food;
import com.shahian.foodallergic.model.entity.Ingredient;
import com.shahian.foodallergic.model.entity.User;
import com.shahian.foodallergic.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;
    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;

    public UserService(UserRepository userRepository, AllergyRepository allergyRepository, FoodRepository foodRepository, IngredientRepository ingredientRepository) {
        this.userRepository = userRepository;
        this.allergyRepository = allergyRepository;
        this.foodRepository = foodRepository;
        this.ingredientRepository = ingredientRepository;
    }


    public List<Allergy> getAllergies(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Allergy> userAllergies = user.getAllergies();
        return userAllergies;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }


    public List<String> checkSensitivityFood(Long userId) {
        User user = getUserById(userId);
        List<String> sensitiveList = new ArrayList<>();
        List<Food> foods = foodRepository.findAll();
        for (Food food : foods) {
            if (isSensitiveTo(user)) {
                sensitiveList.add(food.getFoodName() + " - Sensitive");
            }
        }
        return sensitiveList;
    }

    private boolean isSensitiveTo(User user) {
        List<Allergy> allergies = userAllergics(user.getId());

        List<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            if (allergies.contains(ingredient.getName())) {
                return true;
            }
        }
        return false;
    }

    private List<Allergy> userAllergics(Long id) {
       return allergyRepository.findAllByUser_Id(id);
    }


    public List<Food> getSubstitutes(Long userId) {
        User user = getUserById(userId);
        List<Food> substitutes = new ArrayList<>();
        List<Allergy> allergies = userAllergics(user.getId());
        if (allergies.contains("Milk")) {
            substitutes.add(new Food("Almond Milk", Arrays.asList(new Ingredient("Almond"), new Ingredient("Water"))));
        }
        if (allergies.contains("Eggs")) {
            substitutes.add(new Food("Tofu", Arrays.asList(new Ingredient("Soybean"), new Ingredient("Water"))));
        }


        return substitutes;
    }
}