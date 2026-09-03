package kanchay.auth;

import kanchay.users.dto.CreateUserRequest;
import kanchay.users.dto.LoginRequest;
import kanchay.users.model.User;
import kanchay.users.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "El correo ya está registrado"));
        }

        if (userRepository.existsByDni(request.getDni())) {
            return ResponseEntity.badRequest().body(Map.of("message", "El DNI ya está registrado"));
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getDni(),
                request.getBirthDate(),
                request.getEmail(),
                request.getPhone(),
                request.getPassword()
        );

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Credenciales incorrectas"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Inicio de sesión exitoso",
                "user", userOpt.get()
        ));
    }
}