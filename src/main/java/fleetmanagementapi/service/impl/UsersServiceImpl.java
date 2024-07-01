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
// Necesita hacer cambios, porque debe permitir find por email o nombre, pero no cambiar ni email ni password.
    @Override
    public UsersDto updateUser(String email, UsersDto usersDto) {
        Optional<Users> usersChange = usersRepository.findByEmail(email);
        if (usersChange.isPresent()) {
            Users existingUser = usersChange.get();

            if(!existingUser.getEmail().equals(usersDto.getEmail())) {
                Optional<Users> existingUserByEmail = usersRepository.findByEmail(usersDto.getEmail());
                if (existingUserByEmail.isPresent()) {
                    throw new IllegalArgumentException("Este email ya existe.");
                }
            }
            if (!existingUser.getName().equals(usersDto.getName())) {
                Optional<Users> existingUserByName = usersRepository.findByName(usersDto.getName());
                if (existingUserByName.isPresent()) {
                    throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre.");
                }
            }


            existingUser.setName(usersDto.getName());
            existingUser.setEmail(usersDto.getEmail());
            existingUser.setPassword(usersDto.getPassword());
            usersRepository.save(existingUser);
            return usersMapper.mapToUsersDto(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(String email) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        usersRepository.delete(user);
    }
}
