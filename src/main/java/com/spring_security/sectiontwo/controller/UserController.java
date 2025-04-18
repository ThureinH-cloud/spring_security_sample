package com.spring_security.sectiontwo.controller;

import com.spring_security.sectiontwo.model.Customer;
import com.spring_security.sectiontwo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try{
            String hashPwd=passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            try {
                Customer savedCustomer=customerRepository.save(customer);
                if(savedCustomer.getId()>0){
                    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
                }else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username register failed");
                }
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
