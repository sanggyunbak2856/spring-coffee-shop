package kr.ac.cnuswacademy.springcoffeeshop.repository;

import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public Optional<OrderItem> findOrderItemByOrder (Long id);
    public Optional<OrderItem> findOrderItemByProduct (Long id);
}
