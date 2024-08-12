package com.tiddev.sample.security;

import com.tiddev.sample.service.model.User;
import com.tiddev.sample.service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException("user not existing by name or email")
                );
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                (role)-> new SimpleGrantedAuthority(role.getRole())
        ).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername() , user.getPassword(),authorities
        );
    }
}
