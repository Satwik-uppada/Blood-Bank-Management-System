package com.bank.userservice.repository;

import com.bank.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsernameAndIsDeletedFalse(String username);
    
    Optional<User> findByEmailAndIsDeletedFalse(String email);
    
    Optional<User> findByIdAndIsDeletedFalse(UUID id);
    
    List<User> findByIsDeletedFalse();
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
