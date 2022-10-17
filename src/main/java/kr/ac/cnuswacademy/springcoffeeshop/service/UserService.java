package kr.ac.cnuswacademy.springcoffeeshop.service;

import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserUpdateRequestDto;

import java.util.List;

public interface UserService {

    public List<UserListResponseDto> findAll();

    public UserResponseDto findById(Long id);

    public Long update(Long id, UserUpdateRequestDto requestDto);

    public Long deleteById(Long id);
}
