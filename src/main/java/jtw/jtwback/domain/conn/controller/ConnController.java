package jtw.jtwback.domain.conn.controller;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.conn.service.ConnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conn")
public class ConnController {
    private final ConnService connService;

    @PostMapping
    public ResponseEntity<?> pushCli(@RequestBody BaseMap body) {
        BaseMap result = connService.pushCli(body);
        return ResponseEntity.ok(result);
    }
}
