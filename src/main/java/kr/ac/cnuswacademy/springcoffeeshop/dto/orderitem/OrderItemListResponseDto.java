package kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemListResponseDto {
    private Long id;
    private Long quantity;
    private Long orderId;
    private String productName;
    private Long productId;
    public OrderItemListResponseDto (OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.orderId = orderItem.getOrder().getId();
        this.productId = orderItem.getOrder().getId();
        this.productName = orderItem.getProduct().getName();
    }
}
