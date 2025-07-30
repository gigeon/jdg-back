package jtw.jtwback.save.controller;

import jtw.jtwback.save.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/save")
public class SaveController {
    private final SaveService saveService;

}
