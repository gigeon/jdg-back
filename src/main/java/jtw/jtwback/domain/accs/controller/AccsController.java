package jtw.jtwback.domain.accs.controller;

import jtw.jtwback.com.BaseMap;
import jtw.jtwback.com.annotation.QueryParam;
import jtw.jtwback.domain.accs.service.AccsService;
import jtw.jtwback.domain.conn.service.ConnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accs")
public class AccsController {

    private final AccsService accsService;

    @GetMapping
    public ResponseEntity<?> readAccsAll(@QueryParam BaseMap body) {
        BaseMap result = accsService.readAccsAll(body);
        return ResponseEntity.ok(result);
    }
}
