package com.spring_security.sectiontwo.config;

import com.spring_security.sectiontwo.model.Customer;
import com.spring_security.sectiontwo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionTwoUserDetailsService implements UserDetailsService {
     private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found for the user: " + username));
        List<GrantedAuthority> grantedAuthorities=List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPwd(), grantedAuthorities);
    }
}
