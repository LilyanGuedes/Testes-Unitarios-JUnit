package com.qa.testes_unitarios_JUnit;
import com.qa.testes_unitarios_JUnit.service.UserService;
import com.qa.testes_unitarios_JUnit.entities.User;
import com.qa.testes_unitarios_JUnit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import com.qa.testes_unitarios_JUnit.controller.UserController;
import org.springframework.validation.BindingResult;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BindingResult bindingResult;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPassword("senha123");
    }


    // Teste de cadastro de usuário válido
    @Test
    public void testRegisterUser_ValidData() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user.getEmail(), user.getName(), user.getPassword());

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
    }


    // Teste de cadastro com email duplicado
    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user.getEmail(), user.getName(), user.getPassword());
        });

        assertEquals("Email já está em uso", thrown.getMessage());
    }

    // Teste de cadastro com senha curta
    @Test
    public void testRegisterUser_ShortPassword() {
        String shortPassword = "12345";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user.getEmail(), user.getName(), shortPassword);
        });

        assertEquals("A senha deve ter pelo menos 8 caracteres", thrown.getMessage());
    }

    // Teste de login bem-sucedido
    @Test
    public void testLoginUser_Success() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User result = userService.loginUser(user.getEmail(), user.getPassword());

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
    }

    // Teste de login com senha incorreta
    @Test
    public void testLoginUser_InvalidPassword() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.loginUser(user.getEmail(), "wrongPassword");
        });

        assertEquals("Email ou senha inválidos", thrown.getMessage());
    }

    // Teste de login com email não cadastrado
    @Test
    public void testLoginUser_EmailNotFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.loginUser(user.getEmail(), user.getPassword());
        });

        assertEquals("Email ou senha inválidos", thrown.getMessage());
    }
}
