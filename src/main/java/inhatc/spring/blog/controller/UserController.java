package inhatc.spring.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inhatc.spring.blog.dto.UserDto;
import inhatc.spring.blog.entity.User;
import inhatc.spring.blog.service.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/member")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public UserDto hello(){
        UserDto userDto = new UserDto();
        userDto.setEmail("dabyeon@naver.com");
        userDto.setName("영희");
        userDto.setPassword("1234");
        System.out.println("userDTO : " + userDto);
        return userDto;
    }

    @PostMapping("/new")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        userService.saveUser(userDto);
        return userDto;
    }

    @PostMapping("/login")
    public String memberLogin(@RequestBody UserDto userDto, HttpSession session) {
    System.out.println("UserController.login");
    System.out.println(userDto);
    
    User loginResult = userService.login(userDto.getEmail(), userDto.getPassword());
    System.out.println("로그인 성공여부 : " + loginResult);
    if (loginResult != null) {
        session.setAttribute("loginUserId", loginResult.getId());
        session.setAttribute("loginEmail", loginResult.getEmail());

        return loginResult.getId() + "로그인 성공";
    } else {
        return "로그인 실패";
    }


    }
}
