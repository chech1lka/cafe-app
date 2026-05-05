package com.cafe.app.service;

import com.cafe.app.model.Food;
import com.cafe.app.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> findAll() {
        return foodRepository.findAll();
    }
}
