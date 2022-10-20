package kr.ac.cnuswacademy.springcoffeeshop.dto.user;

import kr.ac.cnuswacademy.springcoffeeshop.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String email;
    private String password;

    @Builder
    public UserUpdateRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}
