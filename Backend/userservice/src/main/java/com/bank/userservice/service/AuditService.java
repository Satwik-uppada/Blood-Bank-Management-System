package com.bank.userservice.service;

import java.util.UUID;

import com.bank.userservice.entity.UserRole;

public interface AuditService {
    
    void logUserCreation(UUID userId, String username, UserRole role);
    
    void logUserUpdate(UUID userId, String username);
    
    void logUserDeletion(UUID userId, String username);
    
    void logUserStatusChange(UUID userId, String username, String newStatus);
    
    void logUserRoleChange(UUID userId, String username, UserRole newRole);
    
    void logUserLogin(UUID userId, String username);
    
}
