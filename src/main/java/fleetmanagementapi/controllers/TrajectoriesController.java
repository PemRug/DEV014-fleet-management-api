package fleetmanagementapi.controllers;

import fleetmanagementapi.entity.Trajectories;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import fleetmanagementapi.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/trajectories")
public class TrajectoriesController {

    private TrajectoriesService trajectoriesService;

    @Autowired
    public TrajectoriesController(TrajectoriesService trajectoriesService) {
        this.trajectoriesService = trajectoriesService;
    }

    @GetMapping("/consult")
    public ResponseEntity<Trajectories> getTrajectoriesByIdDate(
            @PathVariable int id,
            @RequestParam("date") String date) {

        return ResponseEntity.ok(trajectories);
    }
}
