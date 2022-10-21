package kr.ac.cnuswacademy.springcoffeeshop.repository;

import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);
}
