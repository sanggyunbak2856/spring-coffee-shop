package kr.ac.cnuswacademy.springcoffeeshop.dto.product;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private String name;
    private Long price;
    private Long quantity;
    private String description;

    public Product toEntity() {
        return Product
                .builder()
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .description(this.description)
                .build();
    }
}
