package kr.ac.cnuswacademy.springcoffeeshop.repository;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> findOrderByUser(Long id);
}
