package com.cafe.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;

    @NotNull(message = "Food ID is required")
    private Long foodId;

    private String foodName;

    @Positive(message = "Quantity must be at least 1")
    private int quantity;

    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
