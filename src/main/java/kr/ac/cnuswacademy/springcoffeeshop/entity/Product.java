package kr.ac.cnuswacademy.springcoffeeshop.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product extends BaseTimeEntity{

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "product")
    private OrderItem orderItem;

    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @Builder
    public Product (
            OrderItem orderItem,
            String name,
            int price,
            int quantity
    ) {
        this.orderItem = orderItem;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
