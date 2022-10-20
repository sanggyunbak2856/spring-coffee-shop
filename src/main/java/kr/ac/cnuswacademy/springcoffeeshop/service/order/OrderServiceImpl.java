package kr.ac.cnuswacademy.springcoffeeshop.service.order;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<OrderListResponseDto> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(OrderListResponseDto::new)
                .toList();
    }

    @Override
    public OrderResponseDto findById(Long id) throws IllegalArgumentException {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 id의 주문이 없습니다");
        });
        List<OrderItemListResponseDto> orderItemListResponseDtoList = order
                .getOrderItems()
                .stream()
                .map(OrderItemListResponseDto::new)
                .toList();
        return new OrderResponseDto(order, orderItemListResponseDtoList);
    }

    @Override
    public OrderResponseDto findByUser(Long id) throws IllegalArgumentException {
        Order order = orderRepository.findOrderByUser(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 id의 유저가 없습니다");
        });
        List<OrderItemListResponseDto> orderItemListResponseDtoList = order
                .getOrderItems()
                .stream()
                .map(OrderItemListResponseDto::new)
                .toList();
        return new OrderResponseDto(order, orderItemListResponseDtoList);
    }

    @Override
    public Long save(OrderSaveRequestDto requestDto) {
        return null;
    }

    @Override
    public Long update(Long id, OrderUpdateRequestDto requestDto) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
