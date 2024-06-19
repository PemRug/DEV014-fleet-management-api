package fleetmanagementapi.service.impl;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Trajectories;
import fleetmanagementapi.exception.NotFoundException;
import fleetmanagementapi.mappers.TrajectoriesMapper;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.repository.TrajectoriesRepository;
import fleetmanagementapi.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajectoriesServiceImpl implements TrajectoriesService {

    @Autowired
    private TrajectoriesRepository trajectoriesRepository;
    @Autowired
    private TaxisRepository taxisRepository;
    @Autowired
    private TrajectoriesMapper trajectoriesMapper;
    @Autowired
    private DateTimeFormatter dateFormatter;
    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public List<TrajectoriesDto> getTrajectoriesByIdDate(Integer taxiId, String dateStr, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);
        Page<Trajectories> trajectoriesPage = trajectoriesRepository.findByTaxiIdAndDate(taxiId, dateStr, pageable);
        if (trajectoriesPage.isEmpty()) {
            throw new NotFoundException("Trajectory not found for ID and Date: " + taxiId + ", " + dateStr);
        }
        return trajectoriesPage.stream()
                .map(trajectoriesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrajectoriesDto> getLastTrajectories() {
        List<Trajectories> lastTrajectories = trajectoriesRepository.findLastTrajectories();
        return lastTrajectories.stream()
                .map(trajectoriesMapper::toDto)
                .collect(Collectors.toList());
    }

}