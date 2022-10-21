package kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemUpdateRequestDto {
    private Long quantity;
}
