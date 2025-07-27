package jtw.jtwback.domain.auth.controller;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> readAuthAll(@ModelAttribute BaseMap body) {
        BaseMap result = authService.readAuthAll(body);
        return ResponseEntity.ok(result);
    }
}
