package fleetmanagementapi.controllers;

import fleetmanagementapi.dto.UsersDto;
import fleetmanagementapi.entity.Users;
import fleetmanagementapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<?> createUsers(
            @RequestBody UsersDto usersDto) {
        try {
            UsersDto createdUser = usersService.createUsers(usersDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @GetMapping
    public ResponseEntity<Page<UsersDto>> getAllUsers(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<UsersDto> usersDtos = usersService.getAllUsers(pageable);
        return new ResponseEntity<>(usersDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        try {
            usersService.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{identifier}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable String identifier, @RequestBody UsersDto usersDto) {
        UsersDto updatedUser = usersService.updateUser(identifier, usersDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}