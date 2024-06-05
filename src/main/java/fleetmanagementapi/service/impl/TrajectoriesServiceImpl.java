package fleetmanagementapi.service.impl;


import fleetmanagementapi.entity.Trajectories;
import fleetmanagementapi.exception.NotFoundException;
import fleetmanagementapi.repository.TrajectoriesRepository;
import fleetmanagementapi.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TrajectoriesServiceImpl implements TrajectoriesService {

    private TrajectoriesRepository trajectoriesRepository;

    @Autowired
    public TrajectoriesServiceImpl(TrajectoriesRepository trajectoriesRepository) {
        this.trajectoriesRepository = trajectoriesRepository;
    }

    @Override
    public Trajectories getTrajectoriesByIdDate (int id, Timestamp date) {
        Optional<Trajectories> trajectories = trajectoriesRepository.findByIdAndDate(id, date);
                return trajectories.orElseThrow(() -> new NotFoundException("Does not exist trajectories with this ID or Date: " + id + date));
    }

}
