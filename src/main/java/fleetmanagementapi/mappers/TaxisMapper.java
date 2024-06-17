package fleetmanagementapi.mappers;

import fleetmanagementapi.dto.TaxisDto;
import fleetmanagementapi.entity.Taxis;
import org.springframework.stereotype.Component;

@Component
public class TaxisMapper {

    public TaxisDto mapToTaxisDto(Taxis taxis) {
        TaxisDto dto = new TaxisDto();
        dto.setId(taxis.getId());
        dto.setPlate(taxis.getPlate());
        return dto;
    }

    public Taxis mapToTaxisEntity(TaxisDto dto) {
        Taxis taxis = new Taxis();
        taxis.setId(dto.getId());
        taxis.setPlate(dto.getPlate());
        return taxis;
    }
}

