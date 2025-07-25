package jtw.jtwback.domain.user.controller;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public BaseMap selectUserList(@ModelAttribute BaseMap body) {
        BaseMap result = userService.selectUserList(body);
        return result;
    }

    @PostMapping("/login")
    public BaseMap login(@RequestBody BaseMap body) {
        BaseMap result = userService.userLogin(body);
        return result;
    }

    @PostMapping("/otp")
    public BaseMap readUserOtp(@RequestBody BaseMap body) {
        BaseMap result = userService.readUserOtp(body);
        return result;
    }

    @PostMapping("/join")
    public void joinUser(@RequestBody BaseMap body) {
        userService.joinUser(body);
    }
}
