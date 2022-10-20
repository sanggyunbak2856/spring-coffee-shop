package kr.ac.cnuswacademy.springcoffeeshop.dto.product;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import lombok.Getter;

@Getter
public class ProductListResponseDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;

    public ProductListResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}