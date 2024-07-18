package fleetmanagementapi.authenticationFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fleetmanagementapi.controllers.AuthenticationController;
import fleetmanagementapi.dto.LoginUserDto;
import fleetmanagementapi.entity.Users;
import fleetmanagementapi.service.impl.AuthenticationService;
import fleetmanagementapi.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthenticationController.class)
public class JwtAuthenticationFilterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtServiceImpl jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAuthenticationWithoutToken() throws Exception {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setEmail("fleetmanagementexport@gmail.com");
        loginUserDto.setPassword("FleetManagement2024");

        Users authenticatedUser = new Users();
        authenticatedUser.setEmail(loginUserDto.getEmail());
        authenticatedUser.setPassword(passwordEncoder.encode(loginUserDto.getPassword()));

        Mockito.when(authenticationService.authenticate(Mockito.any(LoginUserDto.class)))
                .thenReturn(authenticatedUser);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginUserDto)))
                .andExpect(status().isForbidden()); // Expecting HTTP 403 Forbidden
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}