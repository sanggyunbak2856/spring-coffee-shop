package kr.ac.cnuswacademy.springcoffeeshop.dto.order;

import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private Long id;
    private String address;
    private Long userId;
    private String orderStatus;
    private List<OrderItemListResponseDto> orderItems;

    public OrderResponseDto(Order order, List<OrderItemListResponseDto> responseDtoList) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.userId = order.getUser().getId();
        this.orderStatus = order.getStatus().toString();
        this.orderItems = responseDtoList;
    }
}
