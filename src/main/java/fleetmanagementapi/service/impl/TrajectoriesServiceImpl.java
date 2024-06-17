package fleetmanagementapi.service.impl;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.entity.Trajectories;
import fleetmanagementapi.exception.NotFoundException;
import fleetmanagementapi.mappers.TrajectoriesMapper;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.repository.TrajectoriesRepository;
import fleetmanagementapi.service.TrajectoriesService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Override
    public List<TrajectoriesDto> getTrajectoriesByIdDate(Integer taxiId, Date date, int page, int limit) {
        Taxis taxi = taxisRepository.findById(taxiId).orElseThrow(() ->
                new NotFoundException("Taxi not found with ID: " + taxiId));
        Pageable pageable = PageRequest.of(page, limit);
        Page<Trajectories> trajectoriesPage = trajectoriesRepository.findByTaxiIdAndDate(taxi, date, pageable);
        if (trajectoriesPage.isEmpty()) {
            throw new NotFoundException("Trajectory not found for ID and Date: " + taxiId + ", " + date);
        }
        return trajectoriesPage.stream()
                .map(trajectoriesMapper::toDto)
                .collect(Collectors.toList());
    }
}