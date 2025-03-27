package org.wildcodeschool.myblog.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.ArticleDTO;
import org.wildcodeschool.myblog.exception.ResourceNotFoundException;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.User;
import org.wildcodeschool.myblog.repository.UserRepository;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password, Set<String> roles) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'utilisateur avec l'id " + id + " n'a pas été trouvé."));

        if (user == null) {
            return null;
        }

        return user;
    }
}
