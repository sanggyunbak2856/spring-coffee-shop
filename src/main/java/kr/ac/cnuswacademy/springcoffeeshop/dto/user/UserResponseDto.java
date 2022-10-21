package kr.ac.cnuswacademy.springcoffeeshop.dto.user;

import kr.ac.cnuswacademy.springcoffeeshop.dto.order.OrderListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;
    private List<OrderListResponseDto> orderListResponseDtoList;

    public UserResponseDto (User user, List<OrderListResponseDto> orderListResponseDtoList) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.orderListResponseDtoList = orderListResponseDtoList;
    }
}
