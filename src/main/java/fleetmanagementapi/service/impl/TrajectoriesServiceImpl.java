package fleetmanagementapi.service.impl;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Taxis;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
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

    @Transactional
    public void processFiles(List<String> filePaths) {
        int batchSize = 100;
        List<Trajectories> batch = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Skip lines that are not data lines (like headers or metadata)
                    if (!line.startsWith("\\.")) {
                        // Split line by tabs
                        String[] data = line.split("\t");

                        if (data.length >= 6) {
                            try {
                                // Parse data from the line
                                int id = Integer.parseInt(data[0].trim());
                                int taxiId = Integer.parseInt(data[1].trim());
                                Date date = dateFormat.parse(data[2].trim());
                                double latitude = Double.parseDouble(data[3].trim());
                                double longitude = Double.parseDouble(data[4].trim());

                                // Create a new Trajectories object
                                Trajectories trajectory = new Trajectories();
                                trajectory.setId(id);

                                // Fetch the corresponding taxi from database
                                Taxis taxi = taxisRepository.findById(taxiId)
                                        .orElseThrow(() -> new RuntimeException("Taxi not found: " + taxiId));
                                trajectory.setTaxi(taxi);

                                trajectory.setDate(date);
                                trajectory.setLatitude(latitude);
                                trajectory.setLongitude(longitude);

                                // Add to batch
                                batch.add(trajectory);

                                // Save batch if it reaches batchSize
                                if (batch.size() >= batchSize) {
                                    saveBatch(batch);
                                }
                            } catch (NumberFormatException | ParseException e) {
                                e.printStackTrace();
                                System.err.println("Error processing line: " + line);
                            }
                        }
                    }
                }

                if (!batch.isEmpty()) {
                    saveBatch(batch);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveBatch(List<Trajectories> batch) {
        trajectoriesRepository.saveAll(batch);
        batch.clear();
    }
}


