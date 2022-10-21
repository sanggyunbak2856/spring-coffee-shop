package kr.ac.cnuswacademy.springcoffeeshop.controller;

import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String productList(Model model) {
        List<ProductListResponseDto> all = productService.findAll();
        model.addAttribute("productList", all);
        return "product/productList";
    }

    @GetMapping("/product/{productId}")
    public String productDetail(@PathVariable Long productId, Model model) {
        ProductResponseDto responseDto = productService.findById(productId);
        model.addAttribute("product", responseDto);
        return "product/productDetail";
    }

    @GetMapping("/product/save")
    public String productSave(Model model) {
        ProductSaveRequestDto requestDto = new ProductSaveRequestDto();
        model.addAttribute("product", requestDto);
        return "product/productSave";
    }
}
