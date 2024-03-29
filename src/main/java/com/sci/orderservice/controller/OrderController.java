package com.sci.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sci.orderservice.dto.OrderRequest;
import com.sci.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createOrder(@RequestBody OrderRequest orderRequest) {
		orderService.createOrder(orderRequest);
		return "Saved Successfully";
	}
}
