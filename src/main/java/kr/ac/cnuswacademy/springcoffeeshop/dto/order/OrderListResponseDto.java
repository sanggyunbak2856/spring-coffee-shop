package kr.ac.cnuswacademy.springcoffeeshop.dto.order;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;

public class OrderListResponseDto {

    private Long id;
    private String email;
    private String address;
    private Long userId;

    public OrderListResponseDto(Order order) {
        this.id = order.getId();
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.userId = order.getUser().getId();
    }
}
