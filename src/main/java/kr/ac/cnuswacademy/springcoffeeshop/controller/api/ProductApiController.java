package kr.ac.cnuswacademy.springcoffeeshop.controller.api;

import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @PostMapping("/api/v1/product")
    public ResponseEntity<Long> saveProduct(@RequestBody ProductSaveRequestDto requestDto) {
        Long save = productService.save(requestDto);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/api/v1/product/{productId}")
    public ResponseEntity<Long> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequestDto requestDto) {
        Long id;
        try {
            id = productService.update(productId, requestDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/v1/product/{productId}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok(productId);
    }

}
