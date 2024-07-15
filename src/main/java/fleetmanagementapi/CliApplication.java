package fleetmanagementapi;

import fleetmanagementapi.service.TaxisService;
import fleetmanagementapi.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CliApplication implements CommandLineRunner {

    @Autowired
    private TaxisService taxisService;

    @Autowired
    TrajectoriesService trajectoriesService;

    public static void main(String[] args) {
        SpringApplication.run(CliApplication.class, args);
    }

    @Override
    public void run(String...args) throws Exception {
        if (args.length < 2) {
            System.out.println("Por favor, proporciona el tipo de entidad (taxis/trajectories) y la ruta de los archivos como argumentos.");
            return;
        }
        String entityType = args[0];
        String[] filePaths = Arrays.copyOfRange(args, 1, args.length);
        System.out.println("Tipo de entidad: " + entityType);
        System.out.println("Archivos: " + Arrays.toString(filePaths));

        switch (entityType.toLowerCase()) {
            case "taxis":
                taxisService.processFiles(Arrays.asList(filePaths));
                break;
            case "trajectories":
                trajectoriesService.processFiles(Arrays.asList(filePaths));
                break;
            default:
                System.out.println("Tipo de entidad no reconocido: " + entityType);
        }
    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CliApplication();
    }
}



