package fleetmanagementapi.mapper;

import fleetmanagementapi.dto.TaxisDto;
import fleetmanagementapi.entity.Taxis;

public class TaxisMapper {

    public static TaxisDto mapToTaxisDto(Taxis taxis) {
        return new TaxisDto(
                taxis.getId(),
                taxis.getPlate()
        );
    }

    public static Taxis mapToTaxis(TaxisDto taxisDto) {
        return new Taxis(
                taxisDto.getId(),
                taxisDto.getPlate()
        );
    }
}
