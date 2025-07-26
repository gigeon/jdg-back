package jtw.jtwback.domain.conn.controller;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.domain.conn.service.ConnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conn")

public class ConnController {
    private final ConnService connService;

    @GetMapping
    public ResponseEntity<> pushCli(@RequestBody BaseMap body) {
        BaseMap result = connService.pushCli(body);
        return ;
    }
}
