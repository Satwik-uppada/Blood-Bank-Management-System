package com.bank.userservice.util;

import com.bank.userservice.exception.ResourceNotFoundException;
import com.bank.userservice.exception.UserAlreadyExistsException;
import com.bank.userservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidationUtil {

    private final UserRepository userRepository;

    public ValidationUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserExists(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public void validateEmailNotTaken(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already in use: " + email);
        }
    }

    public void validateUsernameNotTaken(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already taken: " + username);
        }
    }

    public void validateEmailNotTakenByOthers(UUID userId, String email) {
        if (userRepository.findByEmail(email).isPresent() &&
                !userRepository.findByEmail(email).get().getId().equals(userId)) {
            throw new UserAlreadyExistsException("Email already in use: " + email);
        }
    }

    public void validateUsernameNotTakenByOthers(UUID userId, String username) {
        if (userRepository.findByUsername(username).isPresent() &&
                !userRepository.findByUsername(username).get().getId().equals(userId)) {
            throw new UserAlreadyExistsException("Username already taken: " + username);
        }
    }
}
