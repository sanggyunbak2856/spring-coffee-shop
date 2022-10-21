package kr.ac.cnuswacademy.springcoffeeshop.service.orderitem;

import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemUpdateRequestDto;

import java.util.List;

public interface OrderItemService {

    public List<OrderItemListResponseDto> findAll();
    public OrderItemResponseDto findById(Long id);
    public List<OrderItemListResponseDto> findByOrderId(Long id);
    public Long save(OrderItemSaveRequestDto requestDto);
    public Long update(Long id, OrderItemUpdateRequestDto requestDto);
    public Long delete(Long id);
}
