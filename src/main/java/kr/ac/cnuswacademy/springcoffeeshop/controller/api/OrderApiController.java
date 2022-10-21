package kr.ac.cnuswacademy.springcoffeeshop.controller.api;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    public ResponseEntity<Long> saveOrder(@RequestBody OrderSaveRequestDto requestDto) {
        Long id;
        try {
            id = orderService.save(requestDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }

    @PutMapping("/api/v1/order/{orderId}")
    public ResponseEntity<Long> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdateRequestDto requestDto) {
        Long id;
        try {
            id = orderService.update(orderId, requestDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/v1/order/{orderId}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/api/v1/orders")
    public ResponseEntity<List<OrderListResponseDto>> orderList() {
        List<OrderListResponseDto> all = orderService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/api/v1/order/{orderId}")
    public ResponseEntity<OrderResponseDto> order(@PathVariable Long orderId) {
        OrderResponseDto responseDto;
        try {
            responseDto = orderService.findById(orderId);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseDto);
    }
}
