package kr.ac.cnuswacademy.springcoffeeshop.service.order;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderStatus;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder().build();
    }

    @Test
    void 주문을_모두_조회한다() {
        // given
        Order order = Order
                .builder()
                .user(user)
                .build();
        given(orderRepository.findAll()).willReturn(List.of(order));

        // when
        List<OrderListResponseDto> all = orderService.findAll();

        // then
        then(orderRepository).should().findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0)).isInstanceOf(OrderListResponseDto.class);
    }

    @Test
    void 주문을_주문아이디로_단건_조회한다 () {
        // given
        Order order = Order
                .builder()
                .user(user)
                .status(OrderStatus.PREPARING)
                .build();
        given(orderRepository.findById(any(Long.class))).willReturn(Optional.of(order));

        // when
        OrderResponseDto responseDto = orderService.findById(1L);

        // then
        then(orderRepository).should().findById(any(Long.class));
    }

    @Test
    void 주문을_잘못된_아이디로_단건_조회한다 () {
        // given
        given(orderRepository.findById(any(Long.class))).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> orderService.findById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 id의 주문이 없습니다");
        then(orderRepository).should().findById(any(Long.class));
    }

    @Test
    void 주문을_유저아이디로_단건_조회한다 () {
        // given
        Order order = Order
                .builder()
                .user(user)
                .status(OrderStatus.PREPARING)
                .build();
        given(orderRepository.findOrderByUser(any(Long.class))).willReturn(Optional.of(order));

        // when
        OrderResponseDto responseDto = orderService.findByUser(1L);

        // then
        then(orderRepository).should().findOrderByUser(any(Long.class));
    }

    @Test
    void 주문을_잘못된_유저아이디로_단건_조회한다 () {
        // given
        given(orderRepository.findOrderByUser(any(Long.class))).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> orderService.findByUser(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 id의 유저가 없습니다");
        then(orderRepository).should().findOrderByUser(any(Long.class));
    }
}