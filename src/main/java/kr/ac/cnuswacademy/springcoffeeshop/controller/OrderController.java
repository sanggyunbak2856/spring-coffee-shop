package kr.ac.cnuswacademy.springcoffeeshop.controller;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.order.OrderService;
import kr.ac.cnuswacademy.springcoffeeshop.service.orderitem.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/orders")
    public String orderList(Model model) {
        List<OrderListResponseDto> all = orderService.findAll();
        model.addAttribute("orderList", all);
        return "order/orderList";
    }

    @GetMapping("/order/save")
    public String orderSave(Model model) {
        OrderSaveRequestDto requestDto = new OrderSaveRequestDto();
        model.addAttribute("order", requestDto);
        return "order/orderSave";
    }

    @GetMapping("/order/{orderId}")
    public String order(@PathVariable Long orderId, Model model) {
        OrderResponseDto responseDto = orderService.findById(orderId);
        List<OrderItemListResponseDto> orderItemListResponseDtos = orderItemService.findByOrderId(orderId);
        model.addAttribute("order", responseDto);
        model.addAttribute("orderItemList", orderItemListResponseDtos);
        return "order/orderDetail";
    }
}
