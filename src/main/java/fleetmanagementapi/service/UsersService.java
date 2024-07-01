package fleetmanagementapi.service;


import fleetmanagementapi.dto.UsersDto;
import fleetmanagementapi.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    UsersDto createUsers(UsersDto usersDto);
    Page<UsersDto> getAllUsers(Pageable pageable);
    UsersDto updateUser(String email, UsersDto usersDto);
    void deleteUser(String email);
}
