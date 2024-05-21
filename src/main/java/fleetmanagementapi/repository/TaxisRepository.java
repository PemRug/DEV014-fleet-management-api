package fleetmanagementapi.repository;

import fleetmanagementapi.entity.Taxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxisRepository extends JpaRepository<Taxis, Integer> {
}
