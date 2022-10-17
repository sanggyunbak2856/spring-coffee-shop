package kr.ac.cnuswacademy.springcoffeeshop.service;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import kr.ac.cnuswacademy.springcoffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserListResponseDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserListResponseDto::new)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 id의 유저가 없습니다.");
        });
        List<OrderListResponseDto> orderListResponseDtoList = user
                .getOrders()
                .stream()
                .map(OrderListResponseDto::new)
                .toList();
        UserResponseDto responseDto = new UserResponseDto(user, orderListResponseDtoList);
        return responseDto;
    }

    @Override
    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 id의 유저가 없습니다.");
        });
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return user.getId();
    }

    @Override
    @Transactional
    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
