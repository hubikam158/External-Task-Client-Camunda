package pl.altkomsoftware.eventsdemo.controller;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class MainController {

    @GetMapping
    public String home() {
        return "Hello World";
    }

}
