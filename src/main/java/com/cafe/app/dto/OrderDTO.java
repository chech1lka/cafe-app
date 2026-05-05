package com.cafe.app.dto;

import com.cafe.app.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    private String orderNumber;

    @NotNull(message = "Person ID is required")
    private Long personId;

    private String personName;

    private OrderStatus status;

    private BigDecimal totalPrice;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<OrderItemDTO> items;
}
