package com.cafe.app.service;

import com.cafe.app.model.FoodCategory;
import com.cafe.app.repository.FoodCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public List<FoodCategory> findAll() {
        return foodCategoryRepository.findAll();
    }
}
