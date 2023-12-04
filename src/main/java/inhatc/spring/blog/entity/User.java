package inhatc.spring.blog.entity;

import inhatc.spring.blog.constant.Role;
import inhatc.spring.blog.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User createMember(UserDto userDto){
        return User.builder()
                    .email(userDto.getEmail())
                    .name(userDto.getName())
                    .password(userDto.getPassword())
                    .role(userDto.getRole())
                    .build();
    }
}
