package kr.ac.cnuswacademy.springcoffeeshop.service;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserLoginRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.Order;
import kr.ac.cnuswacademy.springcoffeeshop.entity.OrderStatus;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import kr.ac.cnuswacademy.springcoffeeshop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void 유저를_전체조회한다 () {
        // given
        User user1 = User.builder()
                .email("helloworld@gmail.com")
                .password("1234")
                .build();
        User user2 = User.builder()
                .email("helloworld2@gmail.com")
                .password("1234")
                .build();
        UserListResponseDto responseDto1 = new UserListResponseDto(user1);
        UserListResponseDto responseDto2 = new UserListResponseDto(user2);
        given(userRepository.findAll()).willReturn(List.of(user1, user2));

        // when
        List<UserListResponseDto> all = userService.findAll();

        // then
        then(userRepository).should().findAll();
        assertThat(all).hasSize(2);
        assertThat(all.get(0)).usingRecursiveComparison().isEqualTo(responseDto1);
        assertThat(all.get(1)).usingRecursiveComparison().isEqualTo(responseDto2);
    }

    @Test
    void 유저를_단건조회한다 () {
        // given
        User user = User.builder()
                .email("helloworld@gmail.com")
                .password("1234")
                .build();
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

        // when
        UserResponseDto responseDto = userService.findById(1L);

        // then
        then(userRepository).should().findById(any(Long.class));
        assertThat(responseDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(responseDto.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void 유저_단건_조회시_주문_목록을_확인할_수_있다 () {
        // given
        User user = User.builder()
                .email("helloworld@gmail.com")
                .password("1234")
                .build();
        Order order = Order.builder()
                .email("helloworld@gmail.com")
                .status(OrderStatus.PREPARING)
                .build();
        user.addOrder(order);
        OrderListResponseDto orderListResponseDto = new OrderListResponseDto(order);
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

        // when
        UserResponseDto responseDto = userService.findById(1L);

        // then
        then(userRepository).should().findById(any(Long.class));
        assertThat(responseDto.getOrderListResponseDtoList()).hasSize(1);
        assertThat(responseDto.getOrderListResponseDtoList().get(0))
                .usingRecursiveComparison()
                .isEqualTo(orderListResponseDto);
    }

    @Test
    void 유저_로그인에_성공한다 () {
        // given
        User user = User.builder()
                .email("helloworld@gmail.com")
                .password("password")
                .build();
        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .email("helloworld@gmail.com")
                .password("password")
                .build();
        given(userRepository.findUserByEmail(any(String.class))).willReturn(Optional.of(user));

        // when
        Boolean isLogin = userService.login(requestDto);

        // then
        then(userRepository).should().findUserByEmail(any(String.class));
        assertThat(isLogin).isTrue();
    }

    @Test
    void 유저_로그인에_실패한다 () {
        // given
        User user = User.builder()
                .email("helloworld@gmail.com")
                .password("password")
                .build();
        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .email("helloworld2@gmail.com")
                .password("password1")
                .build();
        given(userRepository.findUserByEmail(any(String.class))).willReturn(Optional.of(user));

        // when
        Boolean isLogin = userService.login(requestDto);

        // then
        then(userRepository).should().findUserByEmail(any(String.class));
        assertThat(isLogin).isFalse();
    }
}