package fleetmanagementapi.repository;

import fleetmanagementapi.entity.Trajectories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface TrajectoriesRepository extends JpaRepository<Trajectories, Integer> {
    Optional<Trajectories> findByIdAndDate(int id, Timestamp date);
}
