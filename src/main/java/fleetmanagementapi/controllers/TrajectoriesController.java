package fleetmanagementapi.controllers;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.entity.Trajectories;
import org.springframework.http.ResponseEntity;
import fleetmanagementapi.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/trajectories")
public class TrajectoriesController {


    @Autowired
    private TrajectoriesService trajectoriesService;

            @GetMapping("/consult")
            public ResponseEntity<List<TrajectoriesDto>> getTrajectoriesByIdDate(
                    @RequestParam Integer taxiId,
                    @RequestParam String dateStr,
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int limit) {

                List<TrajectoriesDto> trajectories = trajectoriesService.getTrajectoriesByIdDate(taxiId, dateStr, page, limit);
                return ResponseEntity.ok(trajectories);
            }

            @GetMapping("/latest")
            public List<TrajectoriesDto> getLastTrajectories() {
                return trajectoriesService.getLastTrajectories();
            }
        }

