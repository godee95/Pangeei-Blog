package inhatc.spring.blog.repo;

import org.springframework.stereotype.Repository;

import inhatc.spring.blog.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
