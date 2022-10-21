package kr.ac.cnuswacademy.springcoffeeshop.service.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderItemRepository;
import kr.ac.cnuswacademy.springcoffeeshop.repository.OrderRepository;
import kr.ac.cnuswacademy.springcoffeeshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public List<OrderItemListResponseDto> findAll() {
        return orderItemRepository
                .findAll()
                .stream()
                .map(OrderItemListResponseDto::new)
                .toList();
    }

    @Override
    public OrderItemResponseDto findById(Long id) throws IllegalArgumentException {
        OrderItem orderItem = orderItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 주문 아이템이 없습니다."));
        return new OrderItemResponseDto(orderItem);
    }

    @Override
    public OrderItemResponseDto findByOrderId(Long id) throws IllegalArgumentException{
        OrderItem orderItem = orderItemRepository
                .findOrderItemByOrder(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 주문이 없습니다."));
        return new OrderItemResponseDto(orderItem);
    }

    @Override
    @Transactional
    public Long save(OrderItemSaveRequestDto requestDto) throws IllegalArgumentException{
        Order order = orderRepository
                .findById(requestDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 주문이 없습니다."));
        Product product = productRepository
                .findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 상품이 없습니다"));
        OrderItem orderItem = requestDto.toEntity(order, product);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        order.addOrderItem(savedOrderItem);
        product.addOrderItem(savedOrderItem);

        return savedOrderItem.getId();
    }

    @Override
    public Long update(Long id, OrderItemUpdateRequestDto requestDto) throws IllegalArgumentException {
        OrderItem orderItem = orderItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 id의 주문 아이템이 없습니다."));
        orderItem.update(requestDto);
        return null;
    }

    @Override
    public Long delete(Long id) {
        orderItemRepository.deleteById(id);
        return id;
    }
}
