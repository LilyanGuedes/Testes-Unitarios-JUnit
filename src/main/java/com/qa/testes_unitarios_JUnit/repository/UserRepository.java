package com.qa.testes_unitarios_JUnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.qa.testes_unitarios_JUnit.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
