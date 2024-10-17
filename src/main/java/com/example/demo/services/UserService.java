package com.example.demo.services;

import com.example.demo.dtos.AuthDto;
import com.example.demo.exception.CustomBadRequestException;
import com.example.demo.models.Client;
import com.example.demo.models.User;
import com.example.demo.models.UserPrincipal;
import com.example.demo.repositories.IClientRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.services.interfaces.IUserService;
import com.example.demo.vms.AuthVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtService jwtService;

    public AuthVM register(AuthDto authDto) {
        Optional<User> user = userRepository.findByUsername(authDto.getUsername());
        if(user.isPresent())
        {
            throw new CustomBadRequestException("Username is already taken");
        }
        clientRepository.save(toClientEntity(authDto));

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));

        if(authentication.isAuthenticated()){
            return new AuthVM(authDto.getUsername(),jwtService.generateToken(authDto.getUsername()));
        }
        throw new CustomBadRequestException("something went wrong " + authDto.getUsername());
    }
    public String getLoggedInEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

            return userDetails.getEmail();
        }
        return null;
    }
    public AuthVM login(AuthDto authDto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));

        if(authentication.isAuthenticated()){
            return new AuthVM(authDto.getUsername(),jwtService.generateToken(authDto.getUsername()));
        }
        throw new UsernameNotFoundException(authDto.getUsername());
    }

    Client toClientEntity(AuthDto authDto) {
        Client client = new Client();
        client.setUsername(authDto.getUsername());
        client.setPassword(encoder.encode(authDto.getPassword()));
        return client;
    }

    AuthDto toDto(User user){
        AuthDto authDto = new AuthDto();
        authDto.setUsername(user.getUsername());
        authDto.setPassword(user.getPassword());
        return authDto;
    }
}