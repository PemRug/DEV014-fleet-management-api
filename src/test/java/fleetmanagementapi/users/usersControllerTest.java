package fleetmanagementapi.users;

import fleetmanagementapi.FleetManagementApiApplication;
import fleetmanagementapi.dto.UsersDto;
import fleetmanagementapi.service.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = FleetManagementApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class usersControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UsersService usersService;


    @Test
    @DisplayName("Probar getAllUsers")
    public void testGetAllUsers() {
        UsersDto user1 = new UsersDto();
        user1.setName("User 1");
        user1.setEmail("user1@example.com");
        user1.setPassword("123");

        UsersDto user2 = new UsersDto();
        user2.setName("User 2");
        user2.setEmail("user2@example.com");
        user2.setPassword("134");

        List<UsersDto> usersList = Arrays.asList(user1, user2);
        Page<UsersDto> usersPage = new PageImpl<>(usersList);
        when(usersService.getAllUsers(any(Pageable.class)))
                .thenReturn(usersPage);


        webTestClient.get().uri("/api/users?page=0&limit=10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.content").isArray()
                .jsonPath("$.content[0].name").isEqualTo("User 1")
                .jsonPath("$.content[0].email").isEqualTo("user1@example.com")
                .jsonPath("$.content[1].name").isEqualTo("User 2")
                .jsonPath("$.content[1].email").isEqualTo("user2@example.com");
    }
    @Test
    @DisplayName("Probar createUsers - Creación exitosa")
    public void testCreateUsers_Success() {
        UsersDto newUser = new UsersDto();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setPassword("password");

        when(usersService.createUsers(any(UsersDto.class)))
                .thenReturn(newUser);

        webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newUser)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.email").isEqualTo("john.doe@example.com");
    }


    @Test
    @DisplayName("Probar createUsers - Error de validación")
    public void testCreateUsers_ValidationError() {
        UsersDto invalidUser = new UsersDto();
        invalidUser.setEmail("john.doe@example.com");
        when(usersService.createUsers(any(UsersDto.class)))
                .thenThrow(new IllegalArgumentException("Email no válido"));
        webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidUser)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.error").isEqualTo("Email no válido");
    }
    @Test
    @DisplayName("Probar deleteUser - Eliminación exitosa")
    public void testDeleteUser_Success() {
        String email = "test@example.com";
        doNothing().when(usersService).deleteUser(email);

        webTestClient.delete().uri("/api/users/{email}", email)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @DisplayName("Probar deleteUser - Usuario no encontrado")
    public void testDeleteUser_UserNotFound() {
        String email = "nonexistent@example.com";
        doThrow(new IllegalArgumentException("Usuario no encontrado")).when(usersService).deleteUser(email);
        webTestClient.delete().uri("/api/users/{email}", email)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Probar updateUser - Actualización exitosa")
    public void testUpdateUser_Success() {
        String identifier = "user@example.com";
        UsersDto updatedUserDto = new UsersDto();
        updatedUserDto.setName("Updated User");
        updatedUserDto.setEmail("user@example.com");
        updatedUserDto.setPassword("password");

        when(usersService.updateUser(identifier, updatedUserDto)).thenReturn(updatedUserDto);

        webTestClient.put().uri("/api/users/{identifier}", identifier)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedUserDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .isEmpty();
    }
}
