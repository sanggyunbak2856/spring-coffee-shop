package kr.ac.cnuswacademy.springcoffeeshop.dto.order;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import lombok.Getter;

@Getter
public class OrderListResponseDto {

    private Long id;
    private String address;
    private String orderStatus;
    private String userEmail;

    public OrderListResponseDto(Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.orderStatus = order.getStatus().toString();
        this.userEmail = order.getUser().getEmail();
    }
}
