package kr.ac.cnuswacademy.springcoffeeshop.service.product;

import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.product.ProductUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import kr.ac.cnuswacademy.springcoffeeshop.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void 상품_목록을_전체_조회한다 () {
        // given
        Product product = Product
                .builder()
                .name("아이스 아메리카노")
                .build();
        given(productRepository.findAll()).willReturn(List.of(product));

        // when
        List<ProductListResponseDto> all = productService.findAll();

        // then
        then(productRepository).should().findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo("아이스 아메리카노");
    }

    @Test
    void 상품_목록을_단건_조회한다 () {
        // given
        Product product = Product
                .builder()
                .name("아이스 아메리카노")
                .build();
        given(productRepository.findById(any(Long.class))).willReturn(Optional.of(product));

        // when
        ProductResponseDto responseDto = productService.findById(1L);

        // then
        then(productRepository).should().findById(any(Long.class));
        assertThat(responseDto.getName()).isEqualTo(product.getName());

    }

    @Test
    void 상품_목록을_단건_조회하는데_실패한다 () {
        // given
        given(productRepository.findById(any(Long.class))).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> productService.findById(1L))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void 상품을_등록한다 () {
        // given
        ProductSaveRequestDto requestDto = new ProductSaveRequestDto();
        Product product = requestDto.toEntity();
        requestDto.setName("아이스 아메리카노");
        given(productRepository.save(any(Product.class))).willReturn(product);

        // when
        Long save = productService.save(requestDto);

        // then
        then(productRepository).should().save(any(Product.class));
    }

    @Test
    void 상품을_수정한다 () {
        // given
        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto();
        requestDto.setName("아이스 아메리카노");
        Product product = Product.builder()
                .name("뜨거운 아메리카노")
                .build();
        given(productRepository.findById(any(Long.class))).willReturn(Optional.of(product));

        // when
        productService.update(1L, requestDto);

        // then
        then(productRepository).should().findById(any(Long.class));
        assertThat(product.getName()).isEqualTo("아이스 아메리카노");
    }

    @Test
    void 상품을_수정할때_잘못된_아이디를_준다 () {
        // given
        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto();
        given(productRepository.findById(any(Long.class))).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> productService.update(1L, requestDto))
                .isInstanceOf(IllegalArgumentException.class);

    }
}