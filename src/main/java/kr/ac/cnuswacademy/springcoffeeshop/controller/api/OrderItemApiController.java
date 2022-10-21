package kr.ac.cnuswacademy.springcoffeeshop.controller.api;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.orderitem.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderItemApiController {

    private final OrderItemService orderItemService;

    @GetMapping("/api/v1/orderitems")
    public ResponseEntity<List<OrderItemListResponseDto>> orderItemList() {
        List<OrderItemListResponseDto> all = orderItemService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/api/v1/orderitems/{orderItemId}")
    public ResponseEntity<OrderItemResponseDto> orderItem(@PathVariable Long orderItemId) {
        OrderItemResponseDto responseDto;
        try {
            responseDto = orderItemService.findById(orderItemId);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/api/v1/orderitem")
    public ResponseEntity<Long> saveOrderItem(@RequestBody OrderItemSaveRequestDto requestDto) {
        Long id;
        log.info("{} {} {}", requestDto.getQuantity(), requestDto.getOrderId(), requestDto.getProductId());
        try {
            id = orderItemService.save(requestDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/v1/orderitem/{orderItemId}")
    public ResponseEntity<Long> deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.delete(orderItemId);
        return ResponseEntity.ok(orderItemId);
    }
}
