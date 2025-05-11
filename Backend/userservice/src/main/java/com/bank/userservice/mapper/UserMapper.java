package com.bank.userservice.mapper;

import com.bank.userservice.dto.CreateUserRequest;
import com.bank.userservice.dto.UpdateUserRequest;
import com.bank.userservice.dto.UserDTO;
import com.bank.userservice.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getRole(),
            user.getStatus(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
    
    public User toEntity(CreateUserRequest request) {
        if (request == null) {
            return null;
        }
        
        return new User(
            request.getUsername(),
            request.getEmail(),
            request.getPassword(), // Note: Password should be encoded by the service
            request.getPhoneNumber(),
            request.getRole()
        );
    }
    
    public void updateEntityFromDto(UpdateUserRequest request, User user) {
        if (request == null || user == null) {
            return;
        }
        
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword()); // Note: Password should be encoded by the service
        }
        
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        
        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }
    }
}
