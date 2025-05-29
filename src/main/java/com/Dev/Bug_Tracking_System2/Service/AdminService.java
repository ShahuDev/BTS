package com.Dev.Bug_Tracking_System2.Service;

import com.Dev.Bug_Tracking_System2.Model.Role;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class AdminService {

    private final UserRepository userRepository;
    private final EmailService emailService;


    
    /**
     * Get all users in the system for the admin view
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Get a specific user by ID
     */
    public ResponseEntity<?> getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }
    
    /**
     * Update user role - only admins can perform this action
     */
    @Transactional
    public ResponseEntity<?> updateUserRole(int userId, Role newRole) {
    	
    	if(newRole==Role.ADMIN) { return ResponseEntity.badRequest().body("Cannot promote a user/developer to the admin, only one admin is allowed in the system");}
    	
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found with ID: " + userId);
        }
        
        User user = optionalUser.get();
        
        // Don't allow changing role if current user is already ADMIN
        if (newRole != Role.ADMIN && user.getRole() == Role.ADMIN ) {
            return ResponseEntity.badRequest().body("Cannot change role of existing admin users");
        }
        
        
        // Store old role for notification
        Role oldRole = user.getRole();
        
        // Update role
        user.setRole(newRole);
        userRepository.save(user);
        
        // Notify user about role change via email
        //notifyUserAboutRoleChange(user, oldRole, newRole);
        
        return ResponseEntity.ok(user.getUsername()+" user role updated from " + oldRole + " to " + newRole);
    }
    
    /**
     * Send notification email to user when their role changes
     */
    private void notifyUserAboutRoleChange(User user, Role oldRole, Role newRole) {
        String subject = "Your Role Has Been Updated";
        String message = String.format(
            "Hello %s,\n\n" +
            "Your role in the Bug Tracking System has been changed from %s to %s.\n\n" +
            "If you have any questions, please contact the system administrator.\n\n" +
            "Thank you,\n" +
            "Bug Tracking System Team",
            user.getUsername(), oldRole, newRole
        );
        
        emailService.sendEmail(user.getEmail(), subject, message);
    }
}