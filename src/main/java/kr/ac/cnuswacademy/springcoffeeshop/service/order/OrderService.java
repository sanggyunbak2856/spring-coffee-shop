package kr.ac.cnuswacademy.springcoffeeshop.service.order;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderUpdateRequestDto;

import java.util.List;

public interface OrderService {
    public List<OrderListResponseDto> findAll();
    public OrderResponseDto findById(Long id);
    public OrderResponseDto findByUser(Long id);
    public Long save(OrderSaveRequestDto requestDto);
    public Long update(Long id, OrderUpdateRequestDto requestDto);
    public Long delete(Long id);
}
