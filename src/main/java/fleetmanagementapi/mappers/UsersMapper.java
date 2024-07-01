package fleetmanagementapi.mappers;

import fleetmanagementapi.dto.TaxisDto;
import fleetmanagementapi.dto.UsersDto;
import fleetmanagementapi.entity.Taxis;
import fleetmanagementapi.entity.Users;
import fleetmanagementapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    @Autowired
    private UsersRepository usersRepository;

    public UsersDto mapToUsersDto(Users users) {
        UsersDto dto = new UsersDto();
        dto.setName(users.getName());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        return dto;
    }

    public Users mapToUsersEntity(UsersDto usersDto) {
        Users users = new Users();
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        return users;
    }
}