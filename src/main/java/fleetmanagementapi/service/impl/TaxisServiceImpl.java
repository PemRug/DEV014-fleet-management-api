package fleetmanagementapi.service.impl;


import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.exception.NotFoundException;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.service.TaxisService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaxisServiceImpl implements TaxisService {

    @Autowired
    private TaxisRepository taxisRepository;
    
    @Override
    public Taxis getTaxisId(Integer taxisId) {
        Taxis taxis = taxisRepository.findById(taxisId)
                .orElseThrow(() -> new NotFoundException("Taxi does not exist with this ID: " + taxisId));
        return taxis;
    }

    @Override
    public Page<Taxis> getAllTaxis(int page, int limit) {
        PageRequest pageable = PageRequest.of(page,limit);
        Page<Taxis> taxisPage = taxisRepository.findAll(pageable);
        if (taxisPage.isEmpty() && page > 0) {
            throw new NotFoundException("This number of page doesn't exist: " + page);
        }
        return taxisPage;
    }

    @Override
    public Page<Taxis> findPlateContain(String partialPlate, Pageable pageable) {
        return taxisRepository.findPlateContain(partialPlate, pageable);
    }

    @Override
    @Transactional
    public void processFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean dataStarted = false;

                while ((line = br.readLine()) != null) {
                    // Skip lines until we find the start of table data
                    if (!dataStarted && line.startsWith("COPY public.taxis")) {
                        dataStarted = true;
                        continue; // Skip the COPY line itself
                    }

                    // Process only lines within the table data section
                    if (dataStarted) {
                        // Split line by tab (assuming the data is tab-separated)
                        String[] data = line.split("\t");

                        // Ensure data array has at least two elements (id and plate)
                        if (data.length >= 2) {
                            try {
                                Integer id = Integer.parseInt(data[0].trim());
                                String plate = data[1].trim();

                                // Process id and plate here (e.g., save to database)
                                // Example:
                                // taxiRepository.save(new Taxi(id, plate));
                                System.out.println("Processed id: " + id + ", plate: " + plate);
                            } catch (NumberFormatException e) {
                                // Handle invalid integer parsing
                                System.err.println("Error parsing id: " + e.getMessage());
                            }
                        } else {
                            // Handle unexpected line format
                            System.err.println("Unexpected line format: " + line);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            }
        }
    }
}

