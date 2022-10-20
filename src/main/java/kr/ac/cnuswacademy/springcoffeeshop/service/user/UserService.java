package kr.ac.cnuswacademy.springcoffeeshop.service.user;

import kr.ac.cnuswacademy.springcoffeeshop.dto.user.*;

import java.util.List;

public interface UserService {

    public Long save(UserSaveRequestDto requestDto);
    public Boolean login(UserLoginRequestDto requestDto);
    public List<UserListResponseDto> findAll();

    public UserResponseDto findById(Long id);

    public Long update(Long id, UserUpdateRequestDto requestDto);

    public Long deleteById(Long id);
}
