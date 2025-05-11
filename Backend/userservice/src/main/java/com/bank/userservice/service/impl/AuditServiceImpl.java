package com.bank.userservice.service.impl;

import com.bank.userservice.entity.UserRole;
import com.bank.userservice.service.AuditService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
    
    private final RestTemplate restTemplate;
    
    @Value("${audit-service.url:http://auditservice/api/audits}")
    private String auditServiceUrl;
    
    public AuditServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void logUserCreation(UUID userId, String username, UserRole role) {
        try {
            Map<String, Object> auditData = new HashMap<>();
            auditData.put("userId", userId);
            auditData.put("actionType", "CREATE");
            auditData.put("entityType", "USER");
            auditData.put("entityId", userId.toString());
            auditData.put("description", "User created: " + username + " with role " + role);
            
            sendAuditLog(auditData);
        } catch (Exception e) {
            logger.error("Failed to log user creation: {}", e.getMessage());
        }
    }

    @Override
    public void logUserUpdate(UUID userId, String username) {
        try {
            Map<String, Object> auditData = new HashMap<>();
            auditData.put("userId", userId);
            auditData.put("actionType", "UPDATE");
            auditData.put("entityType", "USER");
            auditData.put("entityId", userId.toString());
            auditData.put("description", "User updated: " + username);
            
            sendAuditLog(auditData);
        } catch (Exception e) {
            logger.error("Failed to log user update: {}", e.getMessage());
        }
    }

    @Override
    public void logUserDeletion(UUID userId, String username) {
        try {
            Map<String, Object> auditData = new HashMap<>();
            auditData.put("userId", userId);
            auditData.put("actionType", "DELETE");
            auditData.put("entityType", "USER");
            auditData.put("entityId", userId.toString());
            auditData.put("description", "User deleted: " + username);
            
            sendAuditLog(auditData);
        } catch (Exception e) {
            logger.error("Failed to log user deletion: {}", e.getMessage());
        }
    }

    @Override
    public void logUserStatusChange(UUID userId, String username, String newStatus) {
        try {
            Map<String, Object> auditData = new HashMap<>();
            auditData.put("userId", userId);
            auditData.put("actionType", "UPDATE");
            auditData.put("entityType", "USER_STATUS");
            auditData.put("entityId", userId.toString());
            auditData.put("description", "User status changed: " + username + " to " + newStatus);
            
            sendAuditLog(auditData);
        } catch (Exception e) {
            logger.error("Failed to log user status change: {}", e.getMessage());
        }
    }

    @Override
    public void logUserRoleChange(UUID userId, String username, UserRole newRole) {
        try {
            Map<String, Object> auditData = new HashMap<>();
            auditData.put("userId", userId);
            auditData.put("actionType", "UPDATE");
            auditData.put("entityType", "USER_ROLE");
            auditData.put("entityId", userId.toString());
            auditData.put("description", "User role changed: " + username + " to " + newRole);
            
            sendAuditLog(auditData);
        } catch (Exception e) {
            logger.error("Failed to log user role change: {}", e.getMessage());
        }
    }

    @Override
    public void logUserLogin(UUID userId, String username) {
        try {
            Map<String, Object> auditData = new HashMap<>();
            auditData.put("userId", userId);
            auditData.put("actionType", "LOGIN");
            auditData.put("entityType", "USER");
            auditData.put("entityId", userId.toString());
            auditData.put("description", "User logged in: " + username);
            
            sendAuditLog(auditData);
        } catch (Exception e) {
            logger.error("Failed to log user login: {}", e.getMessage());
        }
    }
    
    private void sendAuditLog(Map<String, Object> auditData) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(auditData, headers);
            restTemplate.postForEntity(auditServiceUrl, request, Void.class);
            
            logger.debug("Audit log sent successfully: {}", auditData);
        } catch (Exception e) {
            logger.error("Error sending audit log: {}", e.getMessage());
        }
    }
}
