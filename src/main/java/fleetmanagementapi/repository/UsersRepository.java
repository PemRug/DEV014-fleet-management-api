package fleetmanagementapi.repository;


import fleetmanagementapi.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Page<Users> findAll(Pageable pageable);
    Optional<Users> findByName(String name);
    Optional<Users> findByEmailOrName(String email, String name);
}
