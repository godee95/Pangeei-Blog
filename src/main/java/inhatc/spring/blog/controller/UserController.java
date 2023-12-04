package inhatc.spring.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import inhatc.spring.blog.dto.UserDto;
import inhatc.spring.blog.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/member")
    public UserDto hello(){
        UserDto userDto = new UserDto();
        userDto.setEmail("dabyeon@naver.com");
        userDto.setName("영희");
        userDto.setPassword("1234");
        System.out.println("userDTO : " + userDto);
        return userDto;
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        userService.saveUser(userDto);
        return "0";
    }
}
