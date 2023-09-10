package com.sci.orderservice.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineRequestDTO {
	private Long id;
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
