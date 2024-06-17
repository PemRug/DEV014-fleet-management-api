package fleetmanagementapi.repository;

import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.entity.Trajectories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Date;



@Repository
public interface TrajectoriesRepository extends JpaRepository<Trajectories, Integer> {
    Page<Trajectories> findByTaxiIdAndDate(Taxis taxi, Date date, Pageable pageable);
}
