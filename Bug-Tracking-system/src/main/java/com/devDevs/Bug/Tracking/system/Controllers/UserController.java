package com.devDevs.Bug.Tracking.system.Controllers;

import com.devDevs.Bug.Tracking.system.Models.User;
import com.devDevs.Bug.Tracking.system.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<User> RegisterUser(User user){
        return userServices.registerUser(user);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> listOfAllUsers(){
        return userServices.listOfAllUsers();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> updateUserDetails(@PathVariable long id,@RequestParam User user){
        return userServices.updateUserDetails(id,user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        return userServices.deleteUser(id);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'DEVELOPER', 'TESTER')")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        return userServices.getUserProfile(principal.getName());
    }


    public static class AuthResponse {
        private String message;

        public AuthResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    // completed only two calls remaining for security.
    // post /login Authenticate user & get JWT token	Public
    //GET	/profile	Get logged-in user details	User
}
