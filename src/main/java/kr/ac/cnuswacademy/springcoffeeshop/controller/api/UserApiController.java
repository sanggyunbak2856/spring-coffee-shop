package kr.ac.cnuswacademy.springcoffeeshop.controller.api;

import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserSaveRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.dto.user.UserUpdateRequestDto;
import kr.ac.cnuswacademy.springcoffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public ResponseEntity<Long> userSave(@RequestBody UserSaveRequestDto requestDto) {
        Long savedId;
        try {
            savedId = userService.save(requestDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(savedId);
    }

    @PutMapping("/api/v1/user/{userId}")
    public ResponseEntity<Long> userUpdate(@PathVariable Long userId, @RequestBody UserUpdateRequestDto requestDto) {
        Long updatedId;
        try {
            updatedId = userService.update(userId, requestDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/api/v1/user/{userId}")
    public ResponseEntity<Long> userDelete(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok(userId);
    }
}
