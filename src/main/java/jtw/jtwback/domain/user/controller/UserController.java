package jtw.jtwback.domain.user.controller;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public BaseMap login(@RequestBody BaseMap body) {
        BaseMap result = userService.userLogin(body);
        return result;
    }
}
