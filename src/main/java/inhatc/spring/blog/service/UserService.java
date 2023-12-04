package inhatc.spring.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import inhatc.spring.blog.dto.UserDto;
import inhatc.spring.blog.entity.User;
import inhatc.spring.blog.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDto userDto){
        String encryptPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encryptPassword);
        User user = User.createMember(userDto);
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User userEntity){
        Optional<User> findMember = userRepository.findByEmail(userEntity.getEmail()); 

        if(findMember.isPresent()) {
            System.out.println("findMember : "+findMember.get().getEmail());
            throw new IllegalStateException("[ERROR] 이미 가입된 회원입니다.");
        }
    }
}
