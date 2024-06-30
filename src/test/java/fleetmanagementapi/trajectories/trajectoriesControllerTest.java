package fleetmanagementapi.trajectories;


import fleetmanagementapi.FleetManagementApiApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = FleetManagementApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class trajectoriesControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Probar getTrajectoriesByIdDate")
    void testGetTrajectoriesByIdDate() {
        webTestClient.get()
                .uri("/api/trajectories/consult?taxiId=6418&dateStr=02-02-2008&page=0&limit=10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].taxi.id").isEqualTo(6418)
                .jsonPath("$[0].date").isEqualTo("2008-02-02T19:22:40.000+00:00")
                .jsonPath("$.length()").isEqualTo(10);
    }

    @Test
    @DisplayName("Test getLastTrajectories")
    void testGetLastTrajectories() {
        webTestClient.get()
                .uri("/api/trajectories/latest")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].taxiId").exists()
                .jsonPath("$[0].plate").exists()
                .jsonPath("$[0].dateStr").exists()
                .jsonPath("$[0].latitude").exists()
                .jsonPath("$[0].longitude").exists();
    }
}
