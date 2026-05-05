package com.cafe.app.controller;

import com.cafe.app.dto.FoodDTO;
import com.cafe.app.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/available")
    public ResponseEntity<List<FoodDTO>> getAvailableFoods() {
        return ResponseEntity.ok(foodService.getAvailableFoods());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<FoodDTO>> getFoodsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(foodService.getFoodsByCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @PostMapping
    public ResponseEntity<FoodDTO> createFood(@Valid @RequestBody FoodDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.createFood(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood(@PathVariable Long id,
                                               @Valid @RequestBody FoodDTO dto) {
        return ResponseEntity.ok(foodService.updateFood(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
