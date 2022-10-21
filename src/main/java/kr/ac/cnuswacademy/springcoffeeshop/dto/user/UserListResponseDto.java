package kr.ac.cnuswacademy.springcoffeeshop.dto.user;

import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import lombok.Getter;

@Getter
public class UserListResponseDto {

    private Long id;
    private String email;
    private String password;

    public UserListResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
