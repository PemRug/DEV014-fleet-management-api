package fleetmanagementapi.service;


import fleetmanagementapi.entity.Taxis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TaxisService {

    Taxis getTaxisId(Integer taxisId);
    Page<Taxis> getAllTaxis(int page, int limit);
    Page<Taxis> findPlateContain(String partialPlate, Pageable pageable);
    void processFiles(List<String> filePaths);
}
