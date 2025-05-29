package com.Dev.Bug_Tracking_System2.Controller;

import com.Dev.Bug_Tracking_System2.Model.Role;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Service.AdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Get a list of all users
     */
    @GetMapping("/users")
   // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
    	System.out.println("hello");
    	 
        return ResponseEntity.ok(adminService.getAllUsers());
    	//return new ResponseEntity<List<String>>(List.of("A","B"), HttpStatus.ACCEPTED);
    }

    /**
     * Get details of a specific user by ID
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        return adminService.getUserById(userId);
    }

    /**
     * Update a user's role (Only for Admin)
     */
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> updateUserRole(
            @PathVariable int userId,
            @RequestParam("role") Role newRole
    ) {
        return adminService.updateUserRole(userId, newRole);
    }
}
