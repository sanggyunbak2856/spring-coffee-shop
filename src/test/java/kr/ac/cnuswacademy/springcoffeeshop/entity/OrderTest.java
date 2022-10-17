package kr.ac.cnuswacademy.springcoffeeshop.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void 주문아이템이_주문엔티티에_저장된다() {
        // given
        Order order = Order.builder().build();
        OrderItem orderItem = OrderItem.builder().build();

        // when
        orderItem.setOrder(order);

        // then
        assertThat(order.getOrderItems()).contains(orderItem);
        assertThat(orderItem.getOrder()).isEqualTo(order);
    }
}