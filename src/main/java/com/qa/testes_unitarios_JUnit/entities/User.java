package com.qa.testes_unitarios_JUnit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O email não pode estar vazio")  // Mensagem personalizada
    @Email(message = "Por favor, forneça um email válido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "A senha não pode estar vazia")  // Mensagem personalizada
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um número")
    private String password;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
