package fleetmanagementapi.controllers;

import fleetmanagementapi.entity.Users;
import fleetmanagementapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUsers(
            @RequestBody Users user) {
        Users createdUser = usersService.createUsers(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<Page<Users>> getAllUsers(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Users> users = usersService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable String email) {
        usersService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Users> updateUser(@PathVariable String email, @RequestBody Users userDetail) {
        Users updatedUser = usersService.updateUser(email, userDetail);
        return ResponseEntity.ok(updatedUser);
    }
}