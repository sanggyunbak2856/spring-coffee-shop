package kr.ac.cnuswacademy.springcoffeeshop.repository;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
