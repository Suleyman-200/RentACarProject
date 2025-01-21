package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.UserService;
import com.example.rentacar.dto.request.CreateUserRequest;
import com.example.rentacar.dto.request.LoginUserRequest;
import com.example.rentacar.dto.responses.JwtResponse;
import com.example.rentacar.dto.responses.MessageResponse;
import com.example.rentacar.repositories.RoleRepository;
import com.example.rentacar.repositories.UserRepository;
import com.example.rentacar.models.ERole;
import com.example.rentacar.models.Role;
import com.example.rentacar.models.User;
import com.example.rentacar.security.jwt.JwtUtils;

import com.example.rentacar.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";


    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          @Qualifier("passwordEncoder") PasswordEncoder encoder,
                          JwtUtils jwtUtils,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginUserRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        JwtResponse jwtResponse = new JwtResponse(jwt, "Bearer", userDetails.getId(),
                userDetails.getUsername(), userDetails.geteMail(), roles);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest) {
        if (userRepository.existsUserByuserName(signUpRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsUserByeMail(signUpRequest.geteMail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUserName(), signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getDateOfBirth(), signUpRequest.geteMail(), signUpRequest.getPhone(),
                encoder.encode(signUpRequest.getPassword()), new HashSet<>());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("ROLE_ADMIN".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}