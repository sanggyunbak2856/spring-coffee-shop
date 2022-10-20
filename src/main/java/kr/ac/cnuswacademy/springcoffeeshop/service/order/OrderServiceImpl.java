package kr.ac.cnuswacademy.springcoffeeshop.service.order;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderRepository;
import kr.ac.cnuswacademy.springcoffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<OrderListResponseDto> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(OrderListResponseDto::new)
                .toList();
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public Long save(OrderSaveRequestDto requestDto) throws IllegalArgumentException {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 유저가 없습니다"));
        Order order = requestDto.toEntity();
        order.setUser(user);
        Order saved = orderRepository.save(order);
        return saved.getId();
    }

    @Override
    @Transactional
    // 수정 필요 (오더 아이템 수정 기능 추가해야함)
    public Long update(Long id, OrderUpdateRequestDto requestDto) throws IllegalArgumentException{
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 주문이 없습니다"));
        order.update(requestDto);
        return order.getId();
    }

    @Override
    public Long delete(Long id) {
        orderRepository.deleteById(id);
        return id;
    }
}
