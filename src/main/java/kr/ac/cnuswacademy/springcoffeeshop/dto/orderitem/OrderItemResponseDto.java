package kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemResponseDto {
    private Long id;
    private Long price;
    private Long quantity;
    private Long orderId;
    private Long productId;
    public OrderItemResponseDto (OrderItem orderItem) {
        this.id = orderItem.getId();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.orderId = orderItem.getOrder().getId();
        this.productId = orderItem.getOrder().getId();
    }
}
