package fleetmanagementapi.service.impl;

import fleetmanagementapi.dto.LoginUserDto;
import fleetmanagementapi.dto.RegisterUserDto;
import fleetmanagementapi.entity.Users;
import fleetmanagementapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(
            UsersRepository usersRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Users signup(RegisterUserDto input) {
        Users user = new Users();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return usersRepository.save(user);
    }

    public Users authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return usersRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Este usuario nose pudo encontrar con este email: " + input.getEmail()));
    }
}

