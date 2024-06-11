package fleetmanagementapi.mappers;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Trajectories;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TrajectoriesMapper {

    @Autowired
    private TrajectoriesRepository trajectoriesRepository;

    @Autowired
    private TaxisRepository taxisRepository;

    public TrajectoriesDto toDto(Trajectories trajectories) {
        TrajectoriesDto dto = new TrajectoriesDto();
        dto.setId(trajectories.getId());
        dto.setTaxiId(trajectories.getTaxiId());
        dto.setDate(trajectories.getDate());
        dto.setLatitude(trajectories.getLatitude());
        dto.setLongitude(trajectories.getLongitude());
        return dto;
    }

    public Trajectories toEntity(TrajectoriesDto dto) {
        Trajectories trajectories = new Trajectories();
        trajectories.setId(dto.getId());
        trajectories.setTaxiId(dto.getTaxiId());
        trajectories.setDate(dto.getDate());
        trajectories.setLatitude(dto.getLatitude());
        trajectories.setLongitude(dto.getLongitude());
        return trajectories;
    }
}
