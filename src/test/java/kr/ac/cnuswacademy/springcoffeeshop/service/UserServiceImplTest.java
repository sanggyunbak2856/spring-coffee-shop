package kr.ac.cnuswacademy.springcoffeeshop.service;

import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import kr.ac.cnuswacademy.springcoffeeshop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void 전체_유저_목록을_불러온다 () {
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


}