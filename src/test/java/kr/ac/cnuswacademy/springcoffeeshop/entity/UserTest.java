package kr.ac.cnuswacademy.springcoffeeshop.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserTest {

    @Test
    void 유저엔티티에_주문이_저장된다() {
        // given
        User user = User.builder().build();
        Order order = Order.builder()
                .email("helloworld@gmail.com")
                .user(user)
                .status(OrderStatus.PREPARING)
                .address("대전 유성구 궁동")
                .build();

        // when
        user.addOrder(order);

        // then
        assertThat(user.getOrders()).contains(order);
        assertThat(order.getUser()).usingRecursiveComparison().isEqualTo(user);
    }
}