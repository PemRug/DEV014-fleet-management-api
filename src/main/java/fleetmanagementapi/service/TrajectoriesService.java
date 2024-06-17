package fleetmanagementapi.service;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.entity.Taxis;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;


@Service
public interface TrajectoriesService {
    List<TrajectoriesDto> getTrajectoriesByIdDate(Integer taxiId, Date date, int page, int limit);
}

