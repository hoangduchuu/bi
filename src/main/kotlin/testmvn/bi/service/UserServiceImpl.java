package testmvn.bi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import testmvn.bi.domain.User;
import testmvn.bi.mapper.UserMapper;
import testmvn.bi.repository.IUserRepository;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.CreateUserRequest;
import testmvn.bi.web.dto.request.GetUserCriteria;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto saveUser(@RequestBody CreateUserRequest request) {
        User user = new User();

        // Set values from request
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullname(request.getFullname());
        user.setPhone(request.getPhone());

        // Set role
        try {
            user.setRole(User.Role.admin);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role specified", e);
        }

        // Set balance
        try {
            user.setBalance(new BigDecimal(1000));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid balance format", e);
        }

        // Validate required fields
        if (user.getUsername() == null || user.getEmail() == null || user.getFullname() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username, email, fullname, and password are required");
        }

        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new IllegalArgumentException("Phone already exists");
        }


        // Hash and set the password
        user.setPassword(hashPassword(request.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public Page<UserDto> getAllUsers(@RequestBody GetUserCriteria request, Pageable pageable) {
        // Get all users from the database
        var users = userRepository.findAll();

        // Return a page of UserDto
        return new PageImpl<>(userMapper.toDto(users), pageable, users.size());
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }


}