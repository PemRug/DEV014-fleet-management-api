package fleetmanagementapi.mappers;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Trajectories;
import fleetmanagementapi.repository.TaxisRepository;
import fleetmanagementapi.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class TrajectoriesMapper {

    @Autowired
    private TrajectoriesRepository trajectoriesRepository;
    @Autowired
    private TaxisMapper taxisMapper;
    @Autowired
    private TaxisRepository taxisRepository;


    public TrajectoriesDto toDto(Trajectories trajectories) {
        TrajectoriesDto dto = new TrajectoriesDto();
        dto.setId(trajectories.getId());
        dto.setTaxi(taxisMapper.mapToTaxisDto(trajectories.getTaxi()));
        dto.setPlate(trajectories.getTaxi().getPlate());
        dto.setDate(trajectories.getDate());
        dto.setLatitude(trajectories.getLatitude());
        dto.setLongitude(trajectories.getLongitude());
        return dto;
    }

    public Trajectories toEntity(TrajectoriesDto dto) {
        Trajectories trajectories = new Trajectories();
        trajectories.setId(dto.getId());
        trajectories.setTaxi(taxisMapper.mapToTaxisEntity(dto.getTaxi()));
        dto.setPlate(trajectories.getTaxi().getPlate());
        trajectories.setDate(dto.getDate());
        trajectories.setLatitude(dto.getLatitude());
        trajectories.setLongitude(dto.getLongitude());
        return trajectories;
    }
}
