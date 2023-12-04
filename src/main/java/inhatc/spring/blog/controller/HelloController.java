package inhatc.spring.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @Controller
public class HelloController {
    
    @GetMapping("/")
    public String hello(){
        return "hello";
    }
}
