package kr.ac.cnuswacademy.springcoffeeshop.dto.product;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private String description;

    public ProductResponseDto (Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
    }
}
