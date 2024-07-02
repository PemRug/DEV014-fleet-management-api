package fleetmanagementapi.service.impl;


import fleetmanagementapi.dto.UsersDto;
import fleetmanagementapi.entity.Users;
import fleetmanagementapi.mappers.UsersMapper;
import fleetmanagementapi.repository.UsersRepository;
import fleetmanagementapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UsersDto createUsers(UsersDto usersDto) {
        Optional<Users> existingUserEmail = usersRepository.findByEmail(usersDto.getEmail());
        if (existingUserEmail.isPresent()) {
            throw  new IllegalArgumentException("Este email ya fue registrado.");
        }
        Optional<Users> existingUserName = usersRepository.findByName(usersDto.getName());
        if (existingUserName.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con este nombre.");
        }
        Users users = usersMapper.mapToUsersEntity(usersDto);
        users = usersRepository.save(users);
        return usersMapper.mapToUsersDto(users);
    }

    @Override
    public Page<UsersDto> getAllUsers(Pageable pageable) {
        Page<Users> usersPage = usersRepository.findAll(pageable);
        return usersPage.map(usersMapper::mapToUsersDto);
    }

    @Override
    public UsersDto updateUser(String identifier, UsersDto usersDto) {
        Users existingUser = usersRepository.findByEmailOrName(identifier, identifier)
                .orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));

        if (!existingUser.getEmail().equals(usersDto.getEmail())) {
            throw new IllegalArgumentException("No se puede modificar el email, regístrate nuevamente.");
        }

        existingUser.setName(usersDto.getName());
        existingUser = usersRepository.save(existingUser);
        return usersMapper.mapToUsersDto(existingUser);
    }

    @Override
    public void deleteUser(String email) {
        Users existingUser = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado:" + email));
        usersRepository.delete(existingUser);
    }
}
