package kr.ac.cnuswacademy.springcoffeeshop.repository;

import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findOrderItemByOrder (Order order);
    public List<OrderItem> findOrderItemByProduct (Long id);
}
