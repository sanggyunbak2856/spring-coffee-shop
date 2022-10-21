package kr.ac.cnuswacademy.springcoffeeshop.service.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderStatus;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderItemRepository;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderRepository;
import kr.ac.cnuswacademy.springcoffeeshop.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceImplTest {

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    private Order order;
    private Product product;

    @BeforeEach
    void setup() {
        order = Order.builder()
                .address("대전 유성구 궁동")
                .status(OrderStatus.PREPARING)
                .build();
        product = Product.builder()
                .name("아이스 아메리카노")
                .quantity(100L)
                .price(1000L)
                .description("아메리카노")
                .build();
    }

    @Test
    void 주문_아이템을_저장한다 () {
        // given
        OrderItemSaveRequestDto requestDto = new OrderItemSaveRequestDto();
        requestDto.setQuantity(10L);
        requestDto.setOrderId(1L);
        requestDto.setProductId(1L);
        OrderItem orderItem = requestDto.toEntity();
        given(orderRepository.findById(any(Long.class))).willReturn(Optional.of(order));
        given(productRepository.findById(any(Long.class))).willReturn(Optional.of(product));
        given(orderItemRepository.save(any(OrderItem.class))).willReturn(orderItem);

        // when
        Long save = orderItemService.save(requestDto);

        // then
        then(orderRepository).should().findById(any(Long.class));
        then(productRepository).should().findById(any(Long.class));
        assertThat(product.getQuantity()).isEqualTo(90L);
        assertThat(order.getOrderItems()).hasSize(1);
        assertThat(product.getOrderItems()).hasSize(1);
    }
}