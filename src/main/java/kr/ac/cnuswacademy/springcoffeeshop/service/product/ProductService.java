package kr.ac.cnuswacademy.springcoffeeshop.service.product;

import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {
    public List<ProductListResponseDto> findAll();
    public ProductResponseDto findById(Long id);
    public Long save(ProductSaveRequestDto requestDto);
    public Long update(Long id, ProductUpdateRequestDto requestDto);
    public Long delete(Long id);
}
