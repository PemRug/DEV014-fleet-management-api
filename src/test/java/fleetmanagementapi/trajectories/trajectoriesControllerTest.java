package fleetmanagementapi.trajectories;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class trajectoriesControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Probar getTrajectoriesByIdDate")
    void testGetTrajectoriesByIdDate() {
        webTestClient.get()
                .uri("/api/trajectories/consult?taxiId=6418&dateStr=2008-02-02&page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].taxiId").isEqualTo(6418)
                .jsonPath("$[0].date").isEqualTo("2008-02-02")
                .jsonPath("$.length()").isEqualTo(10);
    }
}
/*Agregar test y nuevas excepciones en los test*/