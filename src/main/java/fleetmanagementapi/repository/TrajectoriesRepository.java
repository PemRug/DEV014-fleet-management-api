package fleetmanagementapi.repository;

import fleetmanagementapi.entity.Trajectories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TrajectoriesRepository extends JpaRepository<Trajectories, Integer> {
    @Query(value = "SELECT * FROM trajectories t WHERE taxi_id = :taxiId AND TO_CHAR(date, 'dd-MM-yyyy') = :date", nativeQuery = true)
    Page<Trajectories> findByTaxiIdAndDate(Integer taxiId, String date, Pageable pageable);
    @Query("SELECT t FROM Trajectories t WHERE t.id IN (SELECT MAX(t2.id) FROM Trajectories t2 GROUP BY t2.taxi.plate)")
    List<Trajectories> findLastTrajectories();
}
