package kr.ac.cnuswacademy.springcoffeeshop.controller;

import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String userList(Model model) {
        List<UserListResponseDto> all = userService.findAll();
        model.addAttribute("userList", all);
        return "user/userList";
    }

    @GetMapping("/user/save")
    public String userSave(Model model) {
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder().build();
        model.addAttribute("user", requestDto);
        return "user/userSave";
    }

    @GetMapping("/user/{userId}")
    public String userDetail(@PathVariable Long userId, Model model) {
        UserResponseDto responseDto = userService.findById(userId);
        model.addAttribute("user", responseDto);
        return "user/userDetail";
    }
}
