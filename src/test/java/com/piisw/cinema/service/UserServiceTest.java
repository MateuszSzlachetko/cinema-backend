package com.piisw.cinema.service;

import com.piisw.cinema.model.entity.User;
import com.piisw.cinema.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByUsername_Success() {
        // Mock user details
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("test@example.com");

        // Mock repository call
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        // Call service method
        User result = userService.getUserByUsername("test@example.com");

        // Assertions
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testGetUserByUsername_UserNotFound() {
        // Mock repository call
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Assertions
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByUsername("notfound@example.com");
        });

        assertEquals("User with email: notfound@example.com not found", exception.getMessage());
    }
}