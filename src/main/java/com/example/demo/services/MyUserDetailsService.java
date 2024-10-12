package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.UserPrincipal;
import com.example.demo.repositories.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public MyUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserPrincipal(user);
    }
}
