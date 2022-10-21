package kr.ac.cnuswacademy.springcoffeeshop.service.product;

import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import kr.ac.cnuswacademy.springcoffeeshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public List<ProductListResponseDto> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductListResponseDto::new)
                .toList();
    }

    @Override
    public ProductResponseDto findById(Long id) throws IllegalArgumentException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다."));

        return new ProductResponseDto(product);
    }

    @Override
    public Long save(ProductSaveRequestDto requestDto) {
        Product product = requestDto.toEntity();
        Product saved = productRepository.save(product);
        return saved.getId();
    }

    @Transactional
    @Override
    public Long update(Long id, ProductUpdateRequestDto requestDto) throws IllegalArgumentException{
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 상품이 없습니다."));
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());
        return id;
    }

    @Override
    public Long delete(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
