package inhatc.spring.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import inhatc.spring.blog.dto.UserDto;
import inhatc.spring.blog.entity.User;
import inhatc.spring.blog.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    public User login(String loginEmail, String loginPassword){
        Optional <User> findMember =  userRepository.findByEmail(loginEmail);

        if(findMember.isPresent()) {
            User userEntity = findMember.get();
            String encodedPwd = userEntity.getPassword();
            
            if (passwordEncoder.matches(loginPassword, encodedPwd)) {
                return userEntity;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
