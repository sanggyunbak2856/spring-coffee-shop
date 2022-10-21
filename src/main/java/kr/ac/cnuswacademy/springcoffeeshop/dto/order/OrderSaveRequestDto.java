package kr.ac.cnuswacademy.springcoffeeshop.dto.order;

import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderStatus;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderSaveRequestDto {
    private String address;
    private Long userId;
    private List<OrderItemSaveRequestDto> orderItems = new ArrayList<>();

    public Order toEntity() {
        return Order
                .builder()
                .address(address)
                .status(OrderStatus.PREPARING)
                .build();
    }
}
