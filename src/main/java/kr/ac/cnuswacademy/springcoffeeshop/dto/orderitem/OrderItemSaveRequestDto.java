package kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemSaveRequestDto {
    private Long quantity;
    private Long orderId;
    private Long productId;
    public OrderItem toEntity() {

        return OrderItem
                .builder()
                .quantity(quantity)
                .build();
    }
}
