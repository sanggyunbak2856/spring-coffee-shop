package kr.ac.cnuswacademy.springcoffeeshop.service.user;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.*;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import kr.ac.cnuswacademy.springcoffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Long save(UserSaveRequestDto requestDto) throws IllegalArgumentException{
        Optional<User> user = userRepository.findUserByEmail(requestDto.getEmail());
        if (user.isPresent()) {
            throw new IllegalArgumentException("해당 이메일을 사용하는 유저가 이미 존재합니다.");
        }
        User save = userRepository.save(requestDto.toEntity());
        return save.getId();
    }

    @Override
    public Boolean login(UserLoginRequestDto requestDto) {
        boolean isCorrect = false;
        Optional<User> user = userRepository.findUserByEmail(requestDto.getEmail());
        if (user.isPresent()) {
            isCorrect = user.get().getPassword().equals(requestDto.getPassword());
        }
        return isCorrect;
    }

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
    public Long update(Long id, UserUpdateRequestDto requestDto) throws IllegalArgumentException{
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 id의 유저가 없습니다.");
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
