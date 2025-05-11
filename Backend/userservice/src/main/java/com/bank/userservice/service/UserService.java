package com.bank.userservice.service;

import com.bank.userservice.dto.CreateUserRequest;
import com.bank.userservice.dto.UpdateUserRequest;
import com.bank.userservice.dto.UserDTO;
import com.bank.userservice.entity.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {
    
    UserDTO createUser(CreateUserRequest request);
    
    UserDTO getUserById(UUID id);
    
    UserDTO getUserByUsername(String username);
    
    UserDTO getUserByEmail(String email);
    
    List<UserDTO> getAllUsers();
    
    List<UserDTO> getUsersByRole(UserRole role);
    
    UserDTO updateUser(UUID id, UpdateUserRequest request);
    
    void deleteUser(UUID id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
}
