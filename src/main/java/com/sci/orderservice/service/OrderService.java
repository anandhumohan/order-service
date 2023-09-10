package com.sci.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sci.orderservice.dto.OrderLineRequestDTO;
import com.sci.orderservice.dto.OrderRequest;
import com.sci.orderservice.model.Order;
import com.sci.orderservice.model.OrderLine;
import com.sci.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final WebClient webClient;
	
	public void createOrder(OrderRequest orderRequest) {
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLine> orderLineList = orderRequest.getOrderLineRequestDTOList()
			.stream()
			.map(orderLineRequestDTO -> mapToDTO(orderLineRequestDTO)).toList();
		
		order.setOrderLines(orderLineList);
		
		Boolean result = webClient.get()
			.uri("http://localhost:8081/api/inventory/{sku-code}", orderRequest.getOrderLineRequestDTOList().get(0).getSkuCode())
			.retrieve()
			.bodyToMono(Boolean.class)
			.block();
		if(result) {
			orderRepository.save(order);
		}else {
			throw new IllegalArgumentException("product code is not exist");
		}
		
	}
	
	private OrderLine mapToDTO(OrderLineRequestDTO orderLineRequestDTO) {
		
		OrderLine orderLine = new OrderLine();
		orderLine.setPrice(orderLineRequestDTO.getPrice());
		orderLine.setQuantity(orderLineRequestDTO.getQuantity());
		orderLine.setSkuCode(orderLineRequestDTO.getSkuCode());
		return orderLine;
		
	}

}
