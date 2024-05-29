package fleetmanagementapi.repository;

import fleetmanagementapi.entity.Taxis;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaxisRepository extends JpaRepository<Taxis, Integer> {
    List<Taxis> findAllByPlate(String taxisPlate);
    Page<Taxis> findAll(Pageable pageable);
    @Query("SELECT t FROM Taxis t WHERE LOWER(t.plate) LIKE LOWER(CONCAT('%', :partialPlate,'%'))")
    Page<Taxis> findPlateContain(@Param("partialPlate") String partialPlate, Pageable pageable);
}
