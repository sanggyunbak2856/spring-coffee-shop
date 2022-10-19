package kr.ac.cnuswacademy.springcoffeeshop.controller.api;

import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserListResponseDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
