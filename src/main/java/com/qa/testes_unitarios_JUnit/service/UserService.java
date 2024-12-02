package com.qa.testes_unitarios_JUnit.service;

import com.qa.testes_unitarios_JUnit.entities.User;
import com.qa.testes_unitarios_JUnit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registrar usuário
    public User registerUser(String email, String name, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        // Aqui você deve considerar uma forma de criptografar a senha, por exemplo, com BCrypt

        return userRepository.save(user);
    }

    // Login do usuário
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get(); // Se o email e senha estiverem corretos, retorna o usuário
        }
        throw new IllegalArgumentException("Email ou senha inválidos");
    }
}
