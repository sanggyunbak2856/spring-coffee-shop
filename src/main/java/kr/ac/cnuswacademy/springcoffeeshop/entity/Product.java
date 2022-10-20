package kr.ac.cnuswacademy.springcoffeeshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
public class Product extends BaseTimeEntity{

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @NotBlank
    private String description;

    @Builder
    public Product (
            String name,
            int price,
            int quantity,
            String description
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public void addOrderItem(OrderItem orderItem) {orderItem.setProduct(this);}
}
