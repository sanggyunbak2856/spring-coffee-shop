package kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;

public class OrderItemSaveRequestDto {

    public OrderItem toEntity() {
        return OrderItem.builder().build();
    }
}
