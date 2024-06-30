package fleetmanagementapi.service;

import fleetmanagementapi.dto.TrajectoriesDto;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface TrajectoriesService {
    List<TrajectoriesDto> getTrajectoriesByIdDate(Integer taxiId, String dateStr, int page, int limit);
    List<TrajectoriesDto> getLastTrajectories();
    void processFiles(List<String> filePaths);
}

