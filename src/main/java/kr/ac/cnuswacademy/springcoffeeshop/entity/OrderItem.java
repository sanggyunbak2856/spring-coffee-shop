package kr.ac.cnuswacademy.springcoffeeshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Getter
public class OrderItem extends BaseTimeEntity{

    @Id
    @Column(name = "order_item_id")
    private Long id;

    @NotNull
    private Long price;

    @NotNull
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public OrderItem (
            Long price,
            Long quantity,
            Order order,
            Product product
    ) {
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public void setOrder(Order order) {
        if (Objects.nonNull(this.order)) {
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        order.getOrderItems().add(this);
    }
}
