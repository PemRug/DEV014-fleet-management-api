package fleetmanagementapi.service;

import fleetmanagementapi.dto.TaxisDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaxisService {

    TaxisDto getTaxisId(Integer taxisId);
    List<TaxisDto> getTaxisPlate(String taxisPlate);
}
