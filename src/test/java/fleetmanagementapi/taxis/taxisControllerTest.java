package fleetmanagementapi.taxis;

import fleetmanagementapi.FleetManagementApiApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = FleetManagementApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class taxisControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Probar getAllTaxis")
    void testGetAllTaxis() {
        webTestClient.get()
                .uri("/taxis?page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.content").isArray()
                .jsonPath("$.content[0].id").exists()
                .jsonPath("$.content[0].plate").exists();
    }

    @Test
    @DisplayName("Probar getTaxisId")
    void testGetTaxisId() {
        webTestClient.get()
                .uri("/15")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(15);
    }

    @Test
    @DisplayName("Probar searchTaxis")
    void searchTaxis() {
        webTestClient.get()
                .uri("/search?partialPlate=7&page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.content").isArray()
                .jsonPath("$.content.length()").isEqualTo(10);
    }

    @Test
    @DisplayName("Probar que NotFoundException es lanzada")
    void testNotFoundException() {
        webTestClient.get()
                .uri("/?page=100&limit=10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message").isEqualTo("This number of page doesn't exist: 100");
    }
}
