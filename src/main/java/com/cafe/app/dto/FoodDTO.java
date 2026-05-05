package com.cafe.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    private Long id;

    @NotBlank(message = "Food name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    @NotNull(message = "Price is required")
    private BigDecimal price;

    private boolean available = true;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private String categoryName;
}
