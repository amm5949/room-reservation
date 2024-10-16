package com.example.demo.services;

import com.example.demo.dtos.AuthDto;
import com.example.demo.exception.CustomBadRequestException;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtService jwtService;

    public String register(AuthDto authDto) {
        userRepository.findByUsername(authDto.getUsername()).orElseThrow(() ->
                new CustomBadRequestException("user with this username already exists " + authDto.getUsername()));

        userRepository.save(toEntity(authDto));
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authDto.getUsername());
        }
        throw new CustomBadRequestException("something went wrong");
    }

    public String login(AuthDto authDto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authDto.getUsername());
        }
        throw new UsernameNotFoundException(authDto.getUsername());
    }

    public String getUserEmailByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(User::getEmail).orElseThrow(() -> new CustomNotFoundException("User not found"));
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return userDetails.getUsername();
        }
        return null;
    }
    User toEntity(AuthDto authDto) {
        User user = new User();
        user.setUsername(authDto.getUsername());
        user.setPassword(encoder.encode(authDto.getPassword()));
        return user;
    }

    AuthDto toDto(User user) {
        AuthDto authDto = new AuthDto();
        authDto.setUsername(user.getUsername());
        authDto.setPassword(user.getPassword());
        return authDto;
    }
}
