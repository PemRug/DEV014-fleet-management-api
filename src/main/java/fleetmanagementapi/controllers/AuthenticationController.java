package fleetmanagementapi.controllers;

import fleetmanagementapi.dto.LoginResponse;
import fleetmanagementapi.dto.LoginUserDto;
import fleetmanagementapi.dto.RegisterUserDto;
import fleetmanagementapi.entity.Users;
import fleetmanagementapi.service.impl.AuthenticationService;
import fleetmanagementapi.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody RegisterUserDto registerUserDto) {
        Users registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Users authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Create LoginResponse with JWT token and expiration time
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}


