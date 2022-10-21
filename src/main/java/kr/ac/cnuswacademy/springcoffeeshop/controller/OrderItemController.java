package kr.ac.cnuswacademy.springcoffeeshop.controller;

import kr.ac.cnuswacademy.springcoffeeshop.dto.orderitem.OrderItemSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.orderitem.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/orderitem/save")
    public String orderItemSave(Model model) {
        OrderItemSaveRequestDto requestDto = new OrderItemSaveRequestDto();
        model.addAttribute("orderItem", requestDto);
        return "orderItem/orderItemSave";
    }
}
