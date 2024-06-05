package fleetmanagementapi.service;


import fleetmanagementapi.entity.Trajectories;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public interface TrajectoriesService {

    Trajectories getTrajectoriesByIdDate (int id, Timestamp date);

}
