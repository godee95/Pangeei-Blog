package inhatc.spring.blog.dto;

import inhatc.spring.blog.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor // default 생성자
@Builder
public class UserDto {
    private String email;
    private String name;
    private String password;
    private Role role;
}
