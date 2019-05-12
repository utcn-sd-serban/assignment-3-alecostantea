package ro.utcn.alecostantea.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.alecostantea.assignment1.dto.UserDTO;
import ro.utcn.alecostantea.assignment1.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDTO> readAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public UserDTO create(@RequestBody UserDTO dto) {
        return userService.save(dto);
    }

}
